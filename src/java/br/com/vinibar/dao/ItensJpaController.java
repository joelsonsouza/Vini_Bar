/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vinibar.dao;

import br.com.vinibar.dao.exceptions.IllegalOrphanException;
import br.com.vinibar.dao.exceptions.NonexistentEntityException;
import br.com.vinibar.model.Itens;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import br.com.vinibar.model.Itenscomanda;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author joels
 */
public class ItensJpaController implements Serializable {

    public ItensJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Itens itens) {
        if (itens.getItenscomandaList() == null) {
            itens.setItenscomandaList(new ArrayList<Itenscomanda>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Itenscomanda> attachedItenscomandaList = new ArrayList<Itenscomanda>();
            for (Itenscomanda itenscomandaListItenscomandaToAttach : itens.getItenscomandaList()) {
                itenscomandaListItenscomandaToAttach = em.getReference(itenscomandaListItenscomandaToAttach.getClass(), itenscomandaListItenscomandaToAttach.getIditenscomanda());
                attachedItenscomandaList.add(itenscomandaListItenscomandaToAttach);
            }
            itens.setItenscomandaList(attachedItenscomandaList);
            em.persist(itens);
            for (Itenscomanda itenscomandaListItenscomanda : itens.getItenscomandaList()) {
                Itens oldIdprodutoOfItenscomandaListItenscomanda = itenscomandaListItenscomanda.getIdproduto();
                itenscomandaListItenscomanda.setIdproduto(itens);
                itenscomandaListItenscomanda = em.merge(itenscomandaListItenscomanda);
                if (oldIdprodutoOfItenscomandaListItenscomanda != null) {
                    oldIdprodutoOfItenscomandaListItenscomanda.getItenscomandaList().remove(itenscomandaListItenscomanda);
                    oldIdprodutoOfItenscomandaListItenscomanda = em.merge(oldIdprodutoOfItenscomandaListItenscomanda);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Itens itens) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Itens persistentItens = em.find(Itens.class, itens.getId());
            List<Itenscomanda> itenscomandaListOld = persistentItens.getItenscomandaList();
            List<Itenscomanda> itenscomandaListNew = itens.getItenscomandaList();
            List<String> illegalOrphanMessages = null;
            for (Itenscomanda itenscomandaListOldItenscomanda : itenscomandaListOld) {
                if (!itenscomandaListNew.contains(itenscomandaListOldItenscomanda)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Itenscomanda " + itenscomandaListOldItenscomanda + " since its idproduto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Itenscomanda> attachedItenscomandaListNew = new ArrayList<Itenscomanda>();
            for (Itenscomanda itenscomandaListNewItenscomandaToAttach : itenscomandaListNew) {
                itenscomandaListNewItenscomandaToAttach = em.getReference(itenscomandaListNewItenscomandaToAttach.getClass(), itenscomandaListNewItenscomandaToAttach.getIditenscomanda());
                attachedItenscomandaListNew.add(itenscomandaListNewItenscomandaToAttach);
            }
            itenscomandaListNew = attachedItenscomandaListNew;
            itens.setItenscomandaList(itenscomandaListNew);
            itens = em.merge(itens);
            for (Itenscomanda itenscomandaListNewItenscomanda : itenscomandaListNew) {
                if (!itenscomandaListOld.contains(itenscomandaListNewItenscomanda)) {
                    Itens oldIdprodutoOfItenscomandaListNewItenscomanda = itenscomandaListNewItenscomanda.getIdproduto();
                    itenscomandaListNewItenscomanda.setIdproduto(itens);
                    itenscomandaListNewItenscomanda = em.merge(itenscomandaListNewItenscomanda);
                    if (oldIdprodutoOfItenscomandaListNewItenscomanda != null && !oldIdprodutoOfItenscomandaListNewItenscomanda.equals(itens)) {
                        oldIdprodutoOfItenscomandaListNewItenscomanda.getItenscomandaList().remove(itenscomandaListNewItenscomanda);
                        oldIdprodutoOfItenscomandaListNewItenscomanda = em.merge(oldIdprodutoOfItenscomandaListNewItenscomanda);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = itens.getId();
                if (findItens(id) == null) {
                    throw new NonexistentEntityException("The itens with id " + id + " no longer exists.");
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
            Itens itens;
            try {
                itens = em.getReference(Itens.class, id);
                itens.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The itens with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Itenscomanda> itenscomandaListOrphanCheck = itens.getItenscomandaList();
            for (Itenscomanda itenscomandaListOrphanCheckItenscomanda : itenscomandaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Itens (" + itens + ") cannot be destroyed since the Itenscomanda " + itenscomandaListOrphanCheckItenscomanda + " in its itenscomandaList field has a non-nullable idproduto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(itens);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Itens> findItensEntities() {
        return findItensEntities(true, -1, -1);
    }

    public List<Itens> findItensEntities(int maxResults, int firstResult) {
        return findItensEntities(false, maxResults, firstResult);
    }

    private List<Itens> findItensEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Itens.class));
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

    public Itens findItens(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Itens.class, id);
        } finally {
            em.close();
        }
    }

    public int getItensCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Itens> rt = cq.from(Itens.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
