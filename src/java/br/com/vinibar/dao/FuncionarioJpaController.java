/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vinibar.dao;

import br.com.vinibar.dao.exceptions.IllegalOrphanException;
import br.com.vinibar.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import br.com.vinibar.model.Comanda;
import br.com.vinibar.model.Funcionario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author joels
 */
public class FuncionarioJpaController implements Serializable {

    public FuncionarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Funcionario funcionario) {
        if (funcionario.getComandaList() == null) {
            funcionario.setComandaList(new ArrayList<Comanda>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Comanda> attachedComandaList = new ArrayList<Comanda>();
            for (Comanda comandaListComandaToAttach : funcionario.getComandaList()) {
                comandaListComandaToAttach = em.getReference(comandaListComandaToAttach.getClass(), comandaListComandaToAttach.getId());
                attachedComandaList.add(comandaListComandaToAttach);
            }
            funcionario.setComandaList(attachedComandaList);
            em.persist(funcionario);
            for (Comanda comandaListComanda : funcionario.getComandaList()) {
                Funcionario oldIdfuncionarioOfComandaListComanda = comandaListComanda.getIdfuncionario();
                comandaListComanda.setIdfuncionario(funcionario);
                comandaListComanda = em.merge(comandaListComanda);
                if (oldIdfuncionarioOfComandaListComanda != null) {
                    oldIdfuncionarioOfComandaListComanda.getComandaList().remove(comandaListComanda);
                    oldIdfuncionarioOfComandaListComanda = em.merge(oldIdfuncionarioOfComandaListComanda);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Funcionario funcionario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Funcionario persistentFuncionario = em.find(Funcionario.class, funcionario.getId());
            List<Comanda> comandaListOld = persistentFuncionario.getComandaList();
            List<Comanda> comandaListNew = funcionario.getComandaList();
            List<String> illegalOrphanMessages = null;
            for (Comanda comandaListOldComanda : comandaListOld) {
                if (!comandaListNew.contains(comandaListOldComanda)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Comanda " + comandaListOldComanda + " since its idfuncionario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Comanda> attachedComandaListNew = new ArrayList<Comanda>();
            for (Comanda comandaListNewComandaToAttach : comandaListNew) {
                comandaListNewComandaToAttach = em.getReference(comandaListNewComandaToAttach.getClass(), comandaListNewComandaToAttach.getId());
                attachedComandaListNew.add(comandaListNewComandaToAttach);
            }
            comandaListNew = attachedComandaListNew;
            funcionario.setComandaList(comandaListNew);
            funcionario = em.merge(funcionario);
            for (Comanda comandaListNewComanda : comandaListNew) {
                if (!comandaListOld.contains(comandaListNewComanda)) {
                    Funcionario oldIdfuncionarioOfComandaListNewComanda = comandaListNewComanda.getIdfuncionario();
                    comandaListNewComanda.setIdfuncionario(funcionario);
                    comandaListNewComanda = em.merge(comandaListNewComanda);
                    if (oldIdfuncionarioOfComandaListNewComanda != null && !oldIdfuncionarioOfComandaListNewComanda.equals(funcionario)) {
                        oldIdfuncionarioOfComandaListNewComanda.getComandaList().remove(comandaListNewComanda);
                        oldIdfuncionarioOfComandaListNewComanda = em.merge(oldIdfuncionarioOfComandaListNewComanda);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = funcionario.getId();
                if (findFuncionario(id) == null) {
                    throw new NonexistentEntityException("The funcionario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Funcionario funcionario;
            try {
                funcionario = em.getReference(Funcionario.class, id);
                funcionario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The funcionario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Comanda> comandaListOrphanCheck = funcionario.getComandaList();
            for (Comanda comandaListOrphanCheckComanda : comandaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Funcionario (" + funcionario + ") cannot be destroyed since the Comanda " + comandaListOrphanCheckComanda + " in its comandaList field has a non-nullable idfuncionario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(funcionario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Funcionario> findFuncionarioEntities() {
        return findFuncionarioEntities(true, -1, -1);
    }

    public List<Funcionario> findFuncionarioEntities(int maxResults, int firstResult) {
        return findFuncionarioEntities(false, maxResults, firstResult);
    }

    private List<Funcionario> findFuncionarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Funcionario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Funcionario findFuncionario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Funcionario.class, id);
        } finally {
            em.close();
        }
    }

    public int getFuncionarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Funcionario> rt = cq.from(Funcionario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
