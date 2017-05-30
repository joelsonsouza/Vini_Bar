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
import br.com.vinibar.model.Despesas;
import br.com.vinibar.model.Tipodespesa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author joels
 */
public class TipodespesaJpaController implements Serializable {

    public TipodespesaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipodespesa tipodespesa) {
        if (tipodespesa.getDespesasList() == null) {
            tipodespesa.setDespesasList(new ArrayList<Despesas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Despesas> attachedDespesasList = new ArrayList<Despesas>();
            for (Despesas despesasListDespesasToAttach : tipodespesa.getDespesasList()) {
                despesasListDespesasToAttach = em.getReference(despesasListDespesasToAttach.getClass(), despesasListDespesasToAttach.getId());
                attachedDespesasList.add(despesasListDespesasToAttach);
            }
            tipodespesa.setDespesasList(attachedDespesasList);
            em.persist(tipodespesa);
            for (Despesas despesasListDespesas : tipodespesa.getDespesasList()) {
                Tipodespesa oldIdtipoOfDespesasListDespesas = despesasListDespesas.getIdtipo();
                despesasListDespesas.setIdtipo(tipodespesa);
                despesasListDespesas = em.merge(despesasListDespesas);
                if (oldIdtipoOfDespesasListDespesas != null) {
                    oldIdtipoOfDespesasListDespesas.getDespesasList().remove(despesasListDespesas);
                    oldIdtipoOfDespesasListDespesas = em.merge(oldIdtipoOfDespesasListDespesas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipodespesa tipodespesa) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipodespesa persistentTipodespesa = em.find(Tipodespesa.class, tipodespesa.getId());
            List<Despesas> despesasListOld = persistentTipodespesa.getDespesasList();
            List<Despesas> despesasListNew = tipodespesa.getDespesasList();
            List<String> illegalOrphanMessages = null;
            for (Despesas despesasListOldDespesas : despesasListOld) {
                if (!despesasListNew.contains(despesasListOldDespesas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Despesas " + despesasListOldDespesas + " since its idtipo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Despesas> attachedDespesasListNew = new ArrayList<Despesas>();
            for (Despesas despesasListNewDespesasToAttach : despesasListNew) {
                despesasListNewDespesasToAttach = em.getReference(despesasListNewDespesasToAttach.getClass(), despesasListNewDespesasToAttach.getId());
                attachedDespesasListNew.add(despesasListNewDespesasToAttach);
            }
            despesasListNew = attachedDespesasListNew;
            tipodespesa.setDespesasList(despesasListNew);
            tipodespesa = em.merge(tipodespesa);
            for (Despesas despesasListNewDespesas : despesasListNew) {
                if (!despesasListOld.contains(despesasListNewDespesas)) {
                    Tipodespesa oldIdtipoOfDespesasListNewDespesas = despesasListNewDespesas.getIdtipo();
                    despesasListNewDespesas.setIdtipo(tipodespesa);
                    despesasListNewDespesas = em.merge(despesasListNewDespesas);
                    if (oldIdtipoOfDespesasListNewDespesas != null && !oldIdtipoOfDespesasListNewDespesas.equals(tipodespesa)) {
                        oldIdtipoOfDespesasListNewDespesas.getDespesasList().remove(despesasListNewDespesas);
                        oldIdtipoOfDespesasListNewDespesas = em.merge(oldIdtipoOfDespesasListNewDespesas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipodespesa.getId();
                if (findTipodespesa(id) == null) {
                    throw new NonexistentEntityException("The tipodespesa with id " + id + " no longer exists.");
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
            Tipodespesa tipodespesa;
            try {
                tipodespesa = em.getReference(Tipodespesa.class, id);
                tipodespesa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipodespesa with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Despesas> despesasListOrphanCheck = tipodespesa.getDespesasList();
            for (Despesas despesasListOrphanCheckDespesas : despesasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tipodespesa (" + tipodespesa + ") cannot be destroyed since the Despesas " + despesasListOrphanCheckDespesas + " in its despesasList field has a non-nullable idtipo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipodespesa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipodespesa> findTipodespesaEntities() {
        return findTipodespesaEntities(true, -1, -1);
    }

    public List<Tipodespesa> findTipodespesaEntities(int maxResults, int firstResult) {
        return findTipodespesaEntities(false, maxResults, firstResult);
    }

    private List<Tipodespesa> findTipodespesaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipodespesa.class));
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

    public Tipodespesa findTipodespesa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipodespesa.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipodespesaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipodespesa> rt = cq.from(Tipodespesa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
