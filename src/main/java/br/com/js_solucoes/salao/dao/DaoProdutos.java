package br.com.js_solucoes.salao.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.js_solucoes.salao.model.Produto;
import br.com.js_solucoes.salao.util.HibernateUtil;

public class DaoProdutos extends GenericDAO<Produto> {

	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	public List<Produto> produtosComanda() {
		List<Produto> lista = null;
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		
			List criteriaList=new ArrayList<>();
			criteriaList.add("PRODUTO");
			criteriaList.add("SERVIÇO");
			Criteria consulta = sessao.createCriteria(Produto.class);
			consulta.add(Restrictions.in("tipo", criteriaList)); //ou consulta.add(Restrictions.in("tipo", new String[]{"PRODUTO","SERVIÇO"}));
			lista =consulta.list();

			sessao.close();

		return lista;
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	public List<Produto> produtosCaixa() {
		List<Produto> lista = null;
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		
			Criteria consulta = sessao.createCriteria(Produto.class);
			consulta.add(Restrictions.eq("tipo", "PROMOÇÃO"));
			lista =consulta.list();

			sessao.close();

		return lista;
	}
}
