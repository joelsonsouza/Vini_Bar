package br.com.vinibar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.vinibar.bean.MessagesView;
import br.com.vinibar.model.Itenscomanda;

public class DaoItenscomanda {

	private static DaoItenscomanda instance;
	protected EntityManager entityManager;

	public static DaoItenscomanda getInstance() {
		if (instance == null) {
			instance = new DaoItenscomanda();
		}

		return instance;
	}

	public DaoItenscomanda() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Vini_BarPU");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	public Itenscomanda getById(final int id) {
		return entityManager.find(Itenscomanda.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Itenscomanda> ListaIntensComanda() {
		String jpql = "select f from Itenscomanda f";
		Query q = getEntityManager().createQuery(jpql);
		return q.getResultList();
	}

	public void persist(Itenscomanda itenscomanda) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(itenscomanda);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(Itenscomanda itenscomanda) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(itenscomanda);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(Itenscomanda itenscomanda) {
		try {
			entityManager.getTransaction().begin();
			itenscomanda = entityManager.find(Itenscomanda.class, itenscomanda.getIditenscomanda());
			entityManager.remove(itenscomanda);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removeById(final int id) {
		try {
			Itenscomanda itenscomanda = getById(id);
			remove(itenscomanda);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Itenscomanda> ItensPorComanda(int idcomanda) {
		String jpql = "select f from Itenscomanda f  where f.idcomanda = :id ";
		//String jpql = "select f from Itenscomanda f INNER JOIN f.idcomanda co where co.idcomanda = :id ";
		Query q = getEntityManager().createQuery(jpql);

		q.setParameter("id", idcomanda);

		return q.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Itenscomanda> SomaValores(int idcomanda) {
		String jpql = "select  SUM(f.totalitem) from Itenscomanda f where " + "f.idcomanda = :id ";
		Query q = getEntityManager().createQuery(jpql);

		q.setParameter("id", idcomanda);
		return q.getResultList();
	}

	public void DeleteIdItemcomanda(int iditemcomanda) {
		EntityManager em = getEntityManager();

		try {
			Query q = em.createNamedQuery("Itenscomanda.DeleteIdItemComanda");
			em.getTransaction().begin();
			q.setParameter("iditemcomanda", iditemcomanda);
			q.executeUpdate();
			em.getTransaction().commit();

		} catch (Exception e) {
			MessagesView msg = new MessagesView();
			em.getTransaction().rollback();
			msg.error(e.getMessage());

		} finally {
			em.close();
		}

	}

	public void DeleteIdComanda(int idcomanda) {
		EntityManager em = getEntityManager();

		try {
			Query q = em.createNamedQuery("Itenscomanda.DeleteIdComandaporComanda");
			em.getTransaction().begin();
			q.setParameter("idcomanda", idcomanda);
			q.executeUpdate();
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}

	}
}
