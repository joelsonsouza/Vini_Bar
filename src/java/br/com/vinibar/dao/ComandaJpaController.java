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
import br.com.vinibar.model.Cliente;
import br.com.vinibar.model.Comanda;
import br.com.vinibar.model.Funcionario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author joels
 */
public class ComandaJpaController implements Serializable {

    public ComandaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Comanda comanda) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente idcliente = comanda.getIdcliente();
            if (idcliente != null) {
                idcliente = em.getReference(idcliente.getClass(), idcliente.getId());
                comanda.setIdcliente(idcliente);
            }
            Funcionario idfuncionario = comanda.getIdfuncionario();
            if (idfuncionario != null) {
                idfuncionario = em.getReference(idfuncionario.getClass(), idfuncionario.getId());
                comanda.setIdfuncionario(idfuncionario);
            }
            em.persist(comanda);
            if (idcliente != null) {
                idcliente.getComandaList().add(comanda);
                idcliente = em.merge(idcliente);
            }
            if (idfuncionario != null) {
                idfuncionario.getComandaList().add(comanda);
                idfuncionario = em.merge(idfuncionario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Comanda comanda) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comanda persistentComanda = em.find(Comanda.class, comanda.getId());
            Cliente idclienteOld = persistentComanda.getIdcliente();
            Cliente idclienteNew = comanda.getIdcliente();
            Funcionario idfuncionarioOld = persistentComanda.getIdfuncionario();
            Funcionario idfuncionarioNew = comanda.getIdfuncionario();
            if (idclienteNew != null) {
                idclienteNew = em.getReference(idclienteNew.getClass(), idclienteNew.getId());
                comanda.setIdcliente(idclienteNew);
            }
            if (idfuncionarioNew != null) {
                idfuncionarioNew = em.getReference(idfuncionarioNew.getClass(), idfuncionarioNew.getId());
                comanda.setIdfuncionario(idfuncionarioNew);
            }
            comanda = em.merge(comanda);
            if (idclienteOld != null && !idclienteOld.equals(idclienteNew)) {
                idclienteOld.getComandaList().remove(comanda);
                idclienteOld = em.merge(idclienteOld);
            }
            if (idclienteNew != null && !idclienteNew.equals(idclienteOld)) {
                idclienteNew.getComandaList().add(comanda);
                idclienteNew = em.merge(idclienteNew);
            }
            if (idfuncionarioOld != null && !idfuncionarioOld.equals(idfuncionarioNew)) {
                idfuncionarioOld.getComandaList().remove(comanda);
                idfuncionarioOld = em.merge(idfuncionarioOld);
            }
            if (idfuncionarioNew != null && !idfuncionarioNew.equals(idfuncionarioOld)) {
                idfuncionarioNew.getComandaList().add(comanda);
                idfuncionarioNew = em.merge(idfuncionarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = comanda.getId();
                if (findComanda(id) == null) {
                    throw new NonexistentEntityException("The comanda with id " + id + " no longer exists.");
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
            Comanda comanda;
            try {
                comanda = em.getReference(Comanda.class, id);
                comanda.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comanda with id " + id + " no longer exists.", enfe);
            }
            Cliente idcliente = comanda.getIdcliente();
            if (idcliente != null) {
                idcliente.getComandaList().remove(comanda);
                idcliente = em.merge(idcliente);
            }
            Funcionario idfuncionario = comanda.getIdfuncionario();
            if (idfuncionario != null) {
                idfuncionario.getComandaList().remove(comanda);
                idfuncionario = em.merge(idfuncionario);
            }
            em.remove(comanda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Comanda> findComandaEntities() {
        return findComandaEntities(true, -1, -1);
    }

    public List<Comanda> findComandaEntities(int maxResults, int firstResult) {
        return findComandaEntities(false, maxResults, firstResult);
    }

    private List<Comanda> findComandaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Comanda.class));
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

    public Comanda findComanda(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comanda.class, id);
        } finally {
            em.close();
        }
    }

    public int getComandaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Comanda> rt = cq.from(Comanda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
