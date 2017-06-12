package br.com.vinibar.dao;

import br.com.vinibar.model.Comanda;
import br.com.vinibar.model.Itenscomanda;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DaoComanda {

	private static DaoComanda instance;
	protected EntityManager entityManager;

	public static DaoComanda getInstance() {
		if (instance == null) {
			instance = new DaoComanda();
		}
		return instance;
	}

	public DaoComanda() {
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
	public List<Comanda> ListaComandas() {
		String jpql = "select f from Comanda f";
		Query q = getEntityManager().createQuery(jpql);
		return q.getResultList();
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
			comanda = entityManager.find(Comanda.class, comanda.getIdcomanda());
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Comanda> ComandasAbertas() {
		EntityManager em = getEntityManager();
		List<Comanda> comanda;
		try {
			Query q = em.createNamedQuery("Comanda.ComandaAberta");
			comanda = q.getResultList();
		} catch (Exception e) {
			comanda = new ArrayList(); // retorna uma lista vazia
		} finally {
			em.close();
		}
		return comanda;
	}

	public void Update(String status, int idcomanda) {
		EntityManager em = getEntityManager();

		try {
			Query q = em.createNamedQuery("Comanda.update");
			em.getTransaction().begin();
			q.setParameter("st", status);
			q.setParameter("idcomanda", idcomanda);
			q.executeUpdate();
			em.getTransaction().commit();

		} catch (Exception e) {
			// em.getTransaction().rollback();
		} finally {
			em.close();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Itenscomanda> SomaItensPorcomanda() { // desenvolvimento
		String jpql = "select SUM(f.totalitem) from Itenscomanda f group by f.idcomanda";
		Query q = getEntityManager().createQuery(jpql);

		return q.getResultList();

	}

}
