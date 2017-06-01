/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vinibar.dao;

import br.com.vinibar.dao.exceptions.IllegalOrphanException;
import br.com.vinibar.dao.exceptions.NonexistentEntityException;
import br.com.vinibar.model.Cliente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import br.com.vinibar.model.Comanda;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author joels
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getComandaList() == null) {
            cliente.setComandaList(new ArrayList<Comanda>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Comanda> attachedComandaList = new ArrayList<Comanda>();
            for (Comanda comandaListComandaToAttach : cliente.getComandaList()) {
                comandaListComandaToAttach = em.getReference(comandaListComandaToAttach.getClass(), comandaListComandaToAttach.getId());
                attachedComandaList.add(comandaListComandaToAttach);
            }
            cliente.setComandaList(attachedComandaList);
            em.persist(cliente);
            for (Comanda comandaListComanda : cliente.getComandaList()) {
                Cliente oldIdclienteOfComandaListComanda = comandaListComanda.getIdcliente();
                comandaListComanda.setIdcliente(cliente);
                comandaListComanda = em.merge(comandaListComanda);
                if (oldIdclienteOfComandaListComanda != null) {
                    oldIdclienteOfComandaListComanda.getComandaList().remove(comandaListComanda);
                    oldIdclienteOfComandaListComanda = em.merge(oldIdclienteOfComandaListComanda);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getId());
            List<Comanda> comandaListOld = persistentCliente.getComandaList();
            List<Comanda> comandaListNew = cliente.getComandaList();
            List<String> illegalOrphanMessages = null;
            for (Comanda comandaListOldComanda : comandaListOld) {
                if (!comandaListNew.contains(comandaListOldComanda)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Comanda " + comandaListOldComanda + " since its idcliente field is not nullable.");
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
            cliente.setComandaList(comandaListNew);
            cliente = em.merge(cliente);
            for (Comanda comandaListNewComanda : comandaListNew) {
                if (!comandaListOld.contains(comandaListNewComanda)) {
                    Cliente oldIdclienteOfComandaListNewComanda = comandaListNewComanda.getIdcliente();
                    comandaListNewComanda.setIdcliente(cliente);
                    comandaListNewComanda = em.merge(comandaListNewComanda);
                    if (oldIdclienteOfComandaListNewComanda != null && !oldIdclienteOfComandaListNewComanda.equals(cliente)) {
                        oldIdclienteOfComandaListNewComanda.getComandaList().remove(comandaListNewComanda);
                        oldIdclienteOfComandaListNewComanda = em.merge(oldIdclienteOfComandaListNewComanda);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getId();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Comanda> comandaListOrphanCheck = cliente.getComandaList();
            for (Comanda comandaListOrphanCheckComanda : comandaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Comanda " + comandaListOrphanCheckComanda + " in its comandaList field has a non-nullable idcliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
