package br.com.js_solucoes.salao.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.js_solucoes.salao.model.Comanda;
import br.com.js_solucoes.salao.util.HibernateUtil;

public class DaoComanda extends GenericDAO<Comanda> {

	@SuppressWarnings("unchecked")
	public List<Comanda> ComandasAbertas() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		String hql = "from Comanda f where status='ABERTA'";
		return sessao.createQuery(hql).list();
	}
	
	public void UpdateComanda(String status, Long codigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			String hql = "update from Comanda f set status = :st where codigo=:id";
			Query query = sessao.createQuery(hql);
			query.setParameter("id", codigo).setParameter("st", status).executeUpdate();
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}

	}
	
	public void UpdateComandaTotal(Long codigo, BigDecimal total) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			String hql = "update from Comanda f set total = :to where codigo=:id";
			Query query = sessao.createQuery(hql);
			query.setParameter("id", codigo).setParameter("to", total).executeUpdate();
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}

	}
	
}
