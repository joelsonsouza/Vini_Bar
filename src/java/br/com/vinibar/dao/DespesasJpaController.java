/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vinibar.dao;

import br.com.vinibar.dao.exceptions.NonexistentEntityException;
import br.com.vinibar.model.Despesas;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import br.com.vinibar.model.Fornecedor;
import br.com.vinibar.model.Tipodespesa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author joels
 */
public class DespesasJpaController implements Serializable {

    public DespesasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Despesas despesas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fornecedor idfornecedor = despesas.getIdfornecedor();
            if (idfornecedor != null) {
                idfornecedor = em.getReference(idfornecedor.getClass(), idfornecedor.getId());
                despesas.setIdfornecedor(idfornecedor);
            }
            Tipodespesa idtipo = despesas.getIdtipo();
            if (idtipo != null) {
                idtipo = em.getReference(idtipo.getClass(), idtipo.getId());
                despesas.setIdtipo(idtipo);
            }
            em.persist(despesas);
            if (idfornecedor != null) {
                idfornecedor.getDespesasList().add(despesas);
                idfornecedor = em.merge(idfornecedor);
            }
            if (idtipo != null) {
                idtipo.getDespesasList().add(despesas);
                idtipo = em.merge(idtipo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Despesas despesas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Despesas persistentDespesas = em.find(Despesas.class, despesas.getId());
            Fornecedor idfornecedorOld = persistentDespesas.getIdfornecedor();
            Fornecedor idfornecedorNew = despesas.getIdfornecedor();
            Tipodespesa idtipoOld = persistentDespesas.getIdtipo();
            Tipodespesa idtipoNew = despesas.getIdtipo();
            if (idfornecedorNew != null) {
                idfornecedorNew = em.getReference(idfornecedorNew.getClass(), idfornecedorNew.getId());
                despesas.setIdfornecedor(idfornecedorNew);
            }
            if (idtipoNew != null) {
                idtipoNew = em.getReference(idtipoNew.getClass(), idtipoNew.getId());
                despesas.setIdtipo(idtipoNew);
            }
            despesas = em.merge(despesas);
            if (idfornecedorOld != null && !idfornecedorOld.equals(idfornecedorNew)) {
                idfornecedorOld.getDespesasList().remove(despesas);
                idfornecedorOld = em.merge(idfornecedorOld);
            }
            if (idfornecedorNew != null && !idfornecedorNew.equals(idfornecedorOld)) {
                idfornecedorNew.getDespesasList().add(despesas);
                idfornecedorNew = em.merge(idfornecedorNew);
            }
            if (idtipoOld != null && !idtipoOld.equals(idtipoNew)) {
                idtipoOld.getDespesasList().remove(despesas);
                idtipoOld = em.merge(idtipoOld);
            }
            if (idtipoNew != null && !idtipoNew.equals(idtipoOld)) {
                idtipoNew.getDespesasList().add(despesas);
                idtipoNew = em.merge(idtipoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = despesas.getId();
                if (findDespesas(id) == null) {
                    throw new NonexistentEntityException("The despesas with id " + id + " no longer exists.");
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
            Despesas despesas;
            try {
                despesas = em.getReference(Despesas.class, id);
                despesas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The despesas with id " + id + " no longer exists.", enfe);
            }
            Fornecedor idfornecedor = despesas.getIdfornecedor();
            if (idfornecedor != null) {
                idfornecedor.getDespesasList().remove(despesas);
                idfornecedor = em.merge(idfornecedor);
            }
            Tipodespesa idtipo = despesas.getIdtipo();
            if (idtipo != null) {
                idtipo.getDespesasList().remove(despesas);
                idtipo = em.merge(idtipo);
            }
            em.remove(despesas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Despesas> findDespesasEntities() {
        return findDespesasEntities(true, -1, -1);
    }

    public List<Despesas> findDespesasEntities(int maxResults, int firstResult) {
        return findDespesasEntities(false, maxResults, firstResult);
    }

    private List<Despesas> findDespesasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Despesas.class));
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

    public Despesas findDespesas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Despesas.class, id);
        } finally {
            em.close();
        }
    }

    public int getDespesasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Despesas> rt = cq.from(Despesas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
