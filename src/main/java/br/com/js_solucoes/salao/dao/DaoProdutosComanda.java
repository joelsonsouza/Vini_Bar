package br.com.js_solucoes.salao.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.js_solucoes.salao.model.ProdutosComanda;
import br.com.js_solucoes.salao.util.HibernateUtil;

public class DaoProdutosComanda extends GenericDAO<ProdutosComanda> {

	@SuppressWarnings("unchecked")
	public List<ProdutosComanda> busca(Long comanda) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		String hql = "from ProdutosComanda f where comanda_codigo=:id";
		return sessao.createQuery(hql).setParameter("id", comanda).list();
	}

	@SuppressWarnings("unchecked")
	public List<ProdutosComanda> SomaValores(Long codigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		String hql = "select  SUM(f.totalitem) from ProdutosComanda f where comanda_codigo=:id";
		return sessao.createQuery(hql).setParameter("id", codigo).list();

	}

	@SuppressWarnings("unchecked")
	public BigDecimal Total(Long codigo) {
		BigDecimal total = new BigDecimal("0.00");
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		ArrayList calculoValor = (ArrayList) sessao
				.createSQLQuery("select  SUM(f.totalitem) from ProdutosComanda f where comanda_codigo=:id")
				.setParameter("id", codigo).list();
		total = (BigDecimal) ((ArrayList) calculoValor).get(0);
		return total;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ProdutosComanda> buscaCriteria(Long codigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(ProdutosComanda.class);
			consulta.add(Restrictions.eq("comanda_codigo", codigo)); // WHERE
			List resultado = consulta.list(); // Retorna um resultado
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public void excluirByCodigo(Long codigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			String hql = "delete from ProdutosComanda f where codigo=:id";
			Query query = sessao.createQuery(hql);
			query.setParameter("id", codigo).executeUpdate();
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

	public void excluirByComanda(Long codigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			String hql = "delete from ProdutosComanda f where comanda_codigo=:id";
			Query query = sessao.createQuery(hql);
			query.setParameter("id", codigo).executeUpdate();
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
