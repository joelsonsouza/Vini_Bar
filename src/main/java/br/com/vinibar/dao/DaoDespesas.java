package br.com.vinibar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.vinibar.model.Despesas;


public class DaoDespesas {

	private static DaoDespesas instance;
	protected EntityManager entityManager;

	public static DaoDespesas getInstance() {
		if (instance == null) {
			instance = new DaoDespesas();
		}

		return instance;
	}

	public DaoDespesas() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Vini_BarPU");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	public Despesas getById(final int id) {
		return entityManager.find(Despesas.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Despesas> ListaDespesas() {
		String jpql = "select f from Despesas f";
		Query q = getEntityManager().createQuery(jpql);
		return q.getResultList();
	}

	public void persist(Despesas despesa) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(despesa);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(Despesas despesa) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(despesa);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(Despesas despesa) {
		try {
			entityManager.getTransaction().begin();
			despesa = entityManager.find(Despesas.class, despesa.getId());
			entityManager.remove(despesa);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removeById(final int id) {
		try {
			Despesas despesa = getById(id);
			remove(despesa);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
