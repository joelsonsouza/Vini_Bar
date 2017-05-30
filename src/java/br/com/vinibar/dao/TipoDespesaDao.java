package br.com.vinibar.dao;

import br.com.vinibar.bean.MessagesView;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.vinibar.model.Tipodespesa;

public class TipoDespesaDao {

          private static TipoDespesaDao instance;
          protected EntityManager entityManager;
          
          public static TipoDespesaDao getInstance(){
                    if (instance == null){
                             instance = new TipoDespesaDao();
                    }
                    
                    return instance;
          }
  
          public TipoDespesaDao() {
                    entityManager = getEntityManager();
          }
  
          private EntityManager getEntityManager() {
                    EntityManagerFactory factory = Persistence.createEntityManagerFactory("Vini_BarPU");
                    if (entityManager == null) {
                             entityManager = factory.createEntityManager();
                    }
  
                    return entityManager;
          }
  
          public Tipodespesa getById(final int id) {
                    return entityManager.find(Tipodespesa.class, id);
          }
  
          @SuppressWarnings("unchecked")
          public List<Tipodespesa> findAll() {
                    return entityManager.createQuery("FROM " + Tipodespesa.class.getName()).getResultList();
          }
  
          public void persist(Tipodespesa tipodespesa) {
                    try {
                             entityManager.getTransaction().begin();
                             entityManager.persist(tipodespesa);
                             entityManager.getTransaction().commit();
                    } catch (Exception ex) {
                             ex.printStackTrace();
                             entityManager.getTransaction().rollback();
        
                    }
          }
  
          public void merge(Tipodespesa tipodespesa) {
                    try {
                             entityManager.getTransaction().begin();
                             entityManager.merge(tipodespesa);
                             entityManager.getTransaction().commit();
                    } catch (Exception ex) {
                             ex.printStackTrace();
                             entityManager.getTransaction().rollback();
                    }
          }
  
          public void remove(Tipodespesa tipodespesa) {
                    try {
                             entityManager.getTransaction().begin();
                             tipodespesa = entityManager.find(Tipodespesa.class, tipodespesa.getId());
                             entityManager.remove(tipodespesa);
                             entityManager.getTransaction().commit();
                    } catch (Exception ex) {
                             ex.printStackTrace();
                             entityManager.getTransaction().rollback();
                    }
          }
  
          public void removeById(final int id) {
                    try {
                             Tipodespesa tipodespesa = getById(id);
                             remove(tipodespesa);
                    } catch (Exception ex) {
                             ex.printStackTrace();
                    }
          }
  
 }
	
	



