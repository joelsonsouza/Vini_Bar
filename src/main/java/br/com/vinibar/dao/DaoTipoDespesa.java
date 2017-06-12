package br.com.vinibar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import br.com.vinibar.model.Tipodespesa;

public class DaoTipoDespesa {

	private static DaoTipoDespesa instance;
	protected EntityManager entityManager;

	public static DaoTipoDespesa getInstance() {
		if (instance == null) {
			instance = new DaoTipoDespesa();
		}

		return instance;
	}

	public DaoTipoDespesa() {
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
	public List<Tipodespesa> ListaTipos() {
		String jpql = "select f from Tipodespesa f";
		Query q = getEntityManager().createQuery(jpql);
		return q.getResultList();
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
