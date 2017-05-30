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
import br.com.vinibar.model.Despesas;
import br.com.vinibar.model.Fornecedor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author joels
 */
public class FornecedorJpaController implements Serializable {

    public FornecedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fornecedor fornecedor) {
        if (fornecedor.getDespesasList() == null) {
            fornecedor.setDespesasList(new ArrayList<Despesas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Despesas> attachedDespesasList = new ArrayList<Despesas>();
            for (Despesas despesasListDespesasToAttach : fornecedor.getDespesasList()) {
                despesasListDespesasToAttach = em.getReference(despesasListDespesasToAttach.getClass(), despesasListDespesasToAttach.getId());
                attachedDespesasList.add(despesasListDespesasToAttach);
            }
            fornecedor.setDespesasList(attachedDespesasList);
            em.persist(fornecedor);
            for (Despesas despesasListDespesas : fornecedor.getDespesasList()) {
                Fornecedor oldIdfornecedorOfDespesasListDespesas = despesasListDespesas.getIdfornecedor();
                despesasListDespesas.setIdfornecedor(fornecedor);
                despesasListDespesas = em.merge(despesasListDespesas);
                if (oldIdfornecedorOfDespesasListDespesas != null) {
                    oldIdfornecedorOfDespesasListDespesas.getDespesasList().remove(despesasListDespesas);
                    oldIdfornecedorOfDespesasListDespesas = em.merge(oldIdfornecedorOfDespesasListDespesas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fornecedor fornecedor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fornecedor persistentFornecedor = em.find(Fornecedor.class, fornecedor.getId());
            List<Despesas> despesasListOld = persistentFornecedor.getDespesasList();
            List<Despesas> despesasListNew = fornecedor.getDespesasList();
            List<Despesas> attachedDespesasListNew = new ArrayList<Despesas>();
            for (Despesas despesasListNewDespesasToAttach : despesasListNew) {
                despesasListNewDespesasToAttach = em.getReference(despesasListNewDespesasToAttach.getClass(), despesasListNewDespesasToAttach.getId());
                attachedDespesasListNew.add(despesasListNewDespesasToAttach);
            }
            despesasListNew = attachedDespesasListNew;
            fornecedor.setDespesasList(despesasListNew);
            fornecedor = em.merge(fornecedor);
            for (Despesas despesasListOldDespesas : despesasListOld) {
                if (!despesasListNew.contains(despesasListOldDespesas)) {
                    despesasListOldDespesas.setIdfornecedor(null);
                    despesasListOldDespesas = em.merge(despesasListOldDespesas);
                }
            }
            for (Despesas despesasListNewDespesas : despesasListNew) {
                if (!despesasListOld.contains(despesasListNewDespesas)) {
                    Fornecedor oldIdfornecedorOfDespesasListNewDespesas = despesasListNewDespesas.getIdfornecedor();
                    despesasListNewDespesas.setIdfornecedor(fornecedor);
                    despesasListNewDespesas = em.merge(despesasListNewDespesas);
                    if (oldIdfornecedorOfDespesasListNewDespesas != null && !oldIdfornecedorOfDespesasListNewDespesas.equals(fornecedor)) {
                        oldIdfornecedorOfDespesasListNewDespesas.getDespesasList().remove(despesasListNewDespesas);
                        oldIdfornecedorOfDespesasListNewDespesas = em.merge(oldIdfornecedorOfDespesasListNewDespesas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fornecedor.getId();
                if (findFornecedor(id) == null) {
                    throw new NonexistentEntityException("The fornecedor with id " + id + " no longer exists.");
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
            Fornecedor fornecedor;
            try {
                fornecedor = em.getReference(Fornecedor.class, id);
                fornecedor.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fornecedor with id " + id + " no longer exists.", enfe);
            }
            List<Despesas> despesasList = fornecedor.getDespesasList();
            for (Despesas despesasListDespesas : despesasList) {
                despesasListDespesas.setIdfornecedor(null);
                despesasListDespesas = em.merge(despesasListDespesas);
            }
            em.remove(fornecedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fornecedor> findFornecedorEntities() {
        return findFornecedorEntities(true, -1, -1);
    }

    public List<Fornecedor> findFornecedorEntities(int maxResults, int firstResult) {
        return findFornecedorEntities(false, maxResults, firstResult);
    }

    private List<Fornecedor> findFornecedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Fornecedor.class));
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

    public Fornecedor findFornecedor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fornecedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getFornecedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Fornecedor> rt = cq.from(Fornecedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
