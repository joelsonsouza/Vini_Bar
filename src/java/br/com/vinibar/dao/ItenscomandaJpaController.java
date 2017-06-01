/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vinibar.dao;

import br.com.vinibar.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import br.com.vinibar.model.Comanda;
import br.com.vinibar.model.Itens;
import br.com.vinibar.model.Itenscomanda;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author joels
 */
public class ItenscomandaJpaController implements Serializable {

    public ItenscomandaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Itenscomanda itenscomanda) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comanda idcomanda = itenscomanda.getIdcomanda();
            if (idcomanda != null) {
                idcomanda = em.getReference(idcomanda.getClass(), idcomanda.getId());
                itenscomanda.setIdcomanda(idcomanda);
            }
            Itens idproduto = itenscomanda.getIdproduto();
            if (idproduto != null) {
                idproduto = em.getReference(idproduto.getClass(), idproduto.getId());
                itenscomanda.setIdproduto(idproduto);
            }
            em.persist(itenscomanda);
            if (idcomanda != null) {
                idcomanda.getItenscomandaList().add(itenscomanda);
                idcomanda = em.merge(idcomanda);
            }
            if (idproduto != null) {
                idproduto.getItenscomandaList().add(itenscomanda);
                idproduto = em.merge(idproduto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Itenscomanda itenscomanda) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Itenscomanda persistentItenscomanda = em.find(Itenscomanda.class, itenscomanda.getId());
            Comanda idcomandaOld = persistentItenscomanda.getIdcomanda();
            Comanda idcomandaNew = itenscomanda.getIdcomanda();
            Itens idprodutoOld = persistentItenscomanda.getIdproduto();
            Itens idprodutoNew = itenscomanda.getIdproduto();
            if (idcomandaNew != null) {
                idcomandaNew = em.getReference(idcomandaNew.getClass(), idcomandaNew.getId());
                itenscomanda.setIdcomanda(idcomandaNew);
            }
            if (idprodutoNew != null) {
                idprodutoNew = em.getReference(idprodutoNew.getClass(), idprodutoNew.getId());
                itenscomanda.setIdproduto(idprodutoNew);
            }
            itenscomanda = em.merge(itenscomanda);
            if (idcomandaOld != null && !idcomandaOld.equals(idcomandaNew)) {
                idcomandaOld.getItenscomandaList().remove(itenscomanda);
                idcomandaOld = em.merge(idcomandaOld);
            }
            if (idcomandaNew != null && !idcomandaNew.equals(idcomandaOld)) {
                idcomandaNew.getItenscomandaList().add(itenscomanda);
                idcomandaNew = em.merge(idcomandaNew);
            }
            if (idprodutoOld != null && !idprodutoOld.equals(idprodutoNew)) {
                idprodutoOld.getItenscomandaList().remove(itenscomanda);
                idprodutoOld = em.merge(idprodutoOld);
            }
            if (idprodutoNew != null && !idprodutoNew.equals(idprodutoOld)) {
                idprodutoNew.getItenscomandaList().add(itenscomanda);
                idprodutoNew = em.merge(idprodutoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = itenscomanda.getId();
                if (findItenscomanda(id) == null) {
                    throw new NonexistentEntityException("The itenscomanda with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Itenscomanda itenscomanda;
            try {
                itenscomanda = em.getReference(Itenscomanda.class, id);
                itenscomanda.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The itenscomanda with id " + id + " no longer exists.", enfe);
            }
            Comanda idcomanda = itenscomanda.getIdcomanda();
            if (idcomanda != null) {
                idcomanda.getItenscomandaList().remove(itenscomanda);
                idcomanda = em.merge(idcomanda);
            }
            Itens idproduto = itenscomanda.getIdproduto();
            if (idproduto != null) {
                idproduto.getItenscomandaList().remove(itenscomanda);
                idproduto = em.merge(idproduto);
            }
            em.remove(itenscomanda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Itenscomanda> findItenscomandaEntities() {
        return findItenscomandaEntities(true, -1, -1);
    }

    public List<Itenscomanda> findItenscomandaEntities(int maxResults, int firstResult) {
        return findItenscomandaEntities(false, maxResults, firstResult);
    }

    private List<Itenscomanda> findItenscomandaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Itenscomanda.class));
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

    public Itenscomanda findItenscomanda(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Itenscomanda.class, id);
        } finally {
            em.close();
        }
    }

    public int getItenscomandaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Itenscomanda> rt = cq.from(Itenscomanda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
