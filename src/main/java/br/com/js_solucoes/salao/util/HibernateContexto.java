package br.com.js_solucoes.salao.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//Melhorar Performace para o carregamento do Hibernate
//Esta classe realiza o carregamento do Hibernate junto a inicialização do Tomcat
//Adicionar Listener ao web.xml
public class HibernateContexto implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		HibernateUtil.getFabricaDeSessoes().close();
		
	}

	//Inicio do Tomcat
	@Override
	public void contextInitialized(ServletContextEvent event) {
		HibernateUtil.getFabricaDeSessoes().openSession();
		
	}

}
