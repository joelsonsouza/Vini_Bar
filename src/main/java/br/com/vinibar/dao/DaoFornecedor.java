package br.com.vinibar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.vinibar.model.Fornecedor;


public class DaoFornecedor {

	private static DaoFornecedor instance;
	protected EntityManager entityManager;

	public static DaoFornecedor getInstance() {
		if (instance == null) {
			instance = new DaoFornecedor();
		}

		return instance;
	}

	public DaoFornecedor() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Vini_BarPU");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	public Fornecedor getById(final int id) {
		return entityManager.find(Fornecedor.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Fornecedor> ListaFornecedor() {
		String jpql = "select f from Fornecedor f";
		Query q = getEntityManager().createQuery(jpql);
		return q.getResultList();
	}

	public void persist(Fornecedor fornecedor) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(fornecedor);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(Fornecedor fornecedor) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(fornecedor);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(Fornecedor fornecedor) {
		try {
			entityManager.getTransaction().begin();
			fornecedor = entityManager.find(Fornecedor.class, fornecedor.getId());
			entityManager.remove(fornecedor);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removeById(final int id) {
		try {
			Fornecedor fornecedor = getById(id);
			remove(fornecedor);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
