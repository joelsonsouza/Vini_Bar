package br.com.vinibar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.vinibar.model.Funcionario;

public class DaoFuncionario {

	private static DaoFuncionario instance;
	protected EntityManager entityManager;

	public static DaoFuncionario getInstance() {
		if (instance == null) {
			instance = new DaoFuncionario();
		}

		return instance;
	}

	public DaoFuncionario() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Vini_BarPU");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	public Funcionario getById(final int id) {
		return entityManager.find(Funcionario.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> ListaFuncionarios() {
		String jpql = "select f from Funcionario f";
		Query q = getEntityManager().createQuery(jpql);
		return q.getResultList();
	}

	public void persist(Funcionario funcionario) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(funcionario);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(Funcionario funcionario) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(funcionario);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(Funcionario funcionario) {
		try {
			entityManager.getTransaction().begin();
			funcionario = entityManager.find(Funcionario.class, funcionario.getId());
			entityManager.remove(funcionario);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removeById(final int id) {
		try {
			Funcionario funcionario = getById(id);
			remove(funcionario);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
