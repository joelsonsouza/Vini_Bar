package br.com.js_solucoes.salao.main;

import org.hibernate.Session;

import br.com.js_solucoes.salao.util.HibernateUtil;



public class HibernateUtilTest {
	public static void main(String[] args) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		sessao.close();
		HibernateUtil.getFabricaDeSessoes().close();
	}
}
