package br.com.vinibar.dao;

import br.com.vinibar.model.Comanda;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ComandaDao {

          private static ComandaDao instance;
          protected EntityManager entityManager;
          
          public static ComandaDao getInstance(){
                    if (instance == null){
                             instance = new ComandaDao();
                    }
                    
                    return instance;
          }
  
          public ComandaDao() {
                    entityManager = getEntityManager();
          }
  
          private EntityManager getEntityManager() {
                    EntityManagerFactory factory = Persistence.createEntityManagerFactory("Vini_BarPU");
                    if (entityManager == null) {
                             entityManager = factory.createEntityManager();
                    }
  
                    return entityManager;
          }
  
          public Comanda getById(final int id) {
                    return entityManager.find(Comanda.class, id);
          }
  
          @SuppressWarnings("unchecked")
          public List<Comanda> findAll() {
                    return entityManager.createQuery("FROM " + Comanda.class.getName()).getResultList();
          }
  
          public void persist(Comanda comanda) {
                    try {
                             entityManager.getTransaction().begin();
                             entityManager.persist(comanda);
                             entityManager.getTransaction().commit();
                    } catch (Exception ex) {
                             ex.printStackTrace();
                             entityManager.getTransaction().rollback();
        
                    }
          }
  
          public void merge(Comanda comanda) {
                    try {
                             entityManager.getTransaction().begin();
                             entityManager.merge(comanda);
                             entityManager.getTransaction().commit();
                    } catch (Exception ex) {
                             ex.printStackTrace();
                             entityManager.getTransaction().rollback();
                    }
          }
  
          public void remove(Comanda comanda) {
                    try {
                             entityManager.getTransaction().begin();
                             comanda = entityManager.find(Comanda.class, comanda.getId());
                             entityManager.remove(comanda);
                             entityManager.getTransaction().commit();
                    } catch (Exception ex) {
                             ex.printStackTrace();
                             entityManager.getTransaction().rollback();
                    }
          }
  
          public void removeById(final int id) {
                    try {
                             Comanda comanda = getById(id);
                             remove(comanda);
                    } catch (Exception ex) {
                             ex.printStackTrace();
                    }
          }
  
 }
	
	



