package br.com.vinibar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.vinibar.model.Itens;

public class DaoItens {

	private static DaoItens instance;
	protected EntityManager entityManager;

	public static DaoItens getInstance() {
		if (instance == null) {
			instance = new DaoItens();
		}

		return instance;
	}

	public DaoItens() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Vini_BarPU");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	public Itens getById(final int id) {
		return entityManager.find(Itens.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Itens> ListaAllItens() {
		String jpql = "select f from Itens f";
		Query q = getEntityManager().createQuery(jpql);
		return q.getResultList();
	}

	public void persist(Itens itens) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(itens);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(Itens itens) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(itens);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(Itens itens) {
		try {
			entityManager.getTransaction().begin();
			itens = entityManager.find(Itens.class, itens.getId());
			entityManager.remove(itens);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removeById(final int id) {
		try {
			Itens itens = getById(id);
			remove(itens);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Itens> ListaItens() {

		String jpql = "select f from Itens f where f.tipo='SERVIÇO' OR f.tipo='PRODUTO' ";
		Query q = getEntityManager().createQuery(jpql);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Itens> ListaDesconto(String desconto) {

		String jpql = "select f from Itens f where f.tipo like :x ";
		Query q = getEntityManager().createQuery(jpql);

		q.setParameter("x", "%" + desconto + "%");
		return q.getResultList();
	}
}
