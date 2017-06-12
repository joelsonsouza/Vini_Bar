package br.com.vinibar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.vinibar.model.Caixa;

public class DaoCaixa {

	private static DaoCaixa instance;
	protected EntityManager entityManager;

	public static DaoCaixa getInstance() {
		if (instance == null) {
			instance = new DaoCaixa();
		}

		return instance;
	}

	public DaoCaixa() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Vini_BarPU");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	public Caixa getById(final int id) {
		return entityManager.find(Caixa.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Caixa> ListaCaixa() {
		String jpql = "select f from Caixa f";
		Query q = getEntityManager().createQuery(jpql);
		return q.getResultList();
	}

	public void persist(Caixa caixa) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(caixa);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(Caixa caixa) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(caixa);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(Caixa caixa) {
		try {
			entityManager.getTransaction().begin();
			caixa = entityManager.find(Caixa.class, caixa.getIdcaixa());
			entityManager.remove(caixa);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removeById(final int id) {
		try {
			Caixa caixa = getById(id);
			remove(caixa);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
