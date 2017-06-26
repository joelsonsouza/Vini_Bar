package br.com.js_solucoes.salao.dao;

import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.js_solucoes.salao.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class DaoRelComandas {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void PorPeriodo(Date dtinicial, Date dtfinal) {
		HashMap parametros = new HashMap(); // HashMap:Mapeamento do diretório / JRBean.. passa por parametro a base de dados a qual o relatório será preenchido.	
		parametros.put("dtin", dtinicial);
		parametros.put("dtfin", dtfinal);
		
		Connection conexao = HibernateUtil.getConexao();
		
				String caminho = Faces.getRealPath("/reports/relComandasPorPeriodo.jasper");
				
				JasperPrint jpPrint;
				try {
					jpPrint = JasperFillManager.fillReport(caminho, parametros, conexao);
					JasperViewer jv = new JasperViewer(jpPrint, false); // seta falso para o exit_on_close
					jv.setVisible(true);// chama para visualização
					jv.toFront();// caso o relatório saia atras do programa, este comando tras o relatório para frete da aplicação
				} catch (JRException e) {
					Messages.addGlobalError("FALHA AO GERAR O RELATÓRIO:\n" + e);
					e.printStackTrace();
				}								
				//JasperPrintManager.printReport(jpPrint, true); //Chamar o relatório diretamente na impressora
				
			}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void PagasAbertas(Date dtinicial, Date dtfinal, String pagaAberta) {
		HashMap parametros = new HashMap(); // HashMap:Mapeamento do diretório / JRBean.. passa por parametro a base de dados a qual o relatório será preenchido.	
		parametros.put("dtin", dtinicial);
		parametros.put("dtfin", dtfinal);
		parametros.put("pagaAberta", pagaAberta);
		
		Connection conexao = HibernateUtil.getConexao();
		
				String caminho = Faces.getRealPath("/reports/relComandasPagasAbertas.jasper");
				
				JasperPrint jpPrint;
				try {
					jpPrint = JasperFillManager.fillReport(caminho, parametros, conexao);
					JasperViewer jv = new JasperViewer(jpPrint, false); // seta falso para o exit_on_close
					jv.setVisible(true);// chama para visualização
					jv.toFront();// caso o relatório saia atras do programa, este comando tras o relatório para frete da aplicação
				} catch (JRException e) {
					Messages.addGlobalError("FALHA AO GERAR O RELATÓRIO:\n" + e);
					e.printStackTrace();
				}								
				//JasperPrintManager.printReport(jpPrint, true); //Chamar o relatório diretamente na impressora
				
			}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void AgrupadasPorCliente(Date dtinicial, Date dtfinal) {
		HashMap parametros = new HashMap(); // HashMap:Mapeamento do diretório / JRBean.. passa por parametro a base de dados a qual o relatório será preenchido.	
		parametros.put("dtin", dtinicial);
		parametros.put("dtfin", dtfinal);
		
		
		Connection conexao = HibernateUtil.getConexao();
		
				String caminho = Faces.getRealPath("/reports/relComandasPorCliente.jasper");
				
				JasperPrint jpPrint;
				try {
					jpPrint = JasperFillManager.fillReport(caminho, parametros, conexao);
					JasperViewer jv = new JasperViewer(jpPrint, false); // seta falso para o exit_on_close
					jv.setVisible(true);// chama para visualização
					jv.toFront();// caso o relatório saia atras do programa, este comando tras o relatório para frete da aplicação
				} catch (JRException e) {
					Messages.addGlobalError("FALHA AO GERAR O RELATÓRIO:\n" + e);
					e.printStackTrace();
				}								
				//JasperPrintManager.printReport(jpPrint, true); //Chamar o relatório diretamente na impressora
				
			}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void PorClienteSelecionado(Date dtinicial, Date dtfinal, String cliente) {
		HashMap parametros = new HashMap(); // HashMap:Mapeamento do diretório / JRBean.. passa por parametro a base de dados a qual o relatório será preenchido.	
		parametros.put("dtin", dtinicial);
		parametros.put("dtfin", dtfinal);
		parametros.put("Cliente", cliente);
		
		Connection conexao = HibernateUtil.getConexao();
		
				String caminho = Faces.getRealPath("/reports/relComandasPorClienteSelecionado.jasper");
				
				JasperPrint jpPrint;
				try {
					jpPrint = JasperFillManager.fillReport(caminho, parametros, conexao);
					JasperViewer jv = new JasperViewer(jpPrint, false); // seta falso para o exit_on_close
					jv.setVisible(true);// chama para visualização
					jv.toFront();// caso o relatório saia atras do programa, este comando tras o relatório para frete da aplicação
				} catch (JRException e) {
					Messages.addGlobalError("FALHA AO GERAR O RELATÓRIO:\n" + e);
					e.printStackTrace();
				}								
				//JasperPrintManager.printReport(jpPrint, true); //Chamar o relatório diretamente na impressora
				
			}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void AgrupadasPorFuncionario(Date dtinicial, Date dtfinal) {
		HashMap parametros = new HashMap(); // HashMap:Mapeamento do diretório / JRBean.. passa por parametro a base de dados a qual o relatório será preenchido.	
		parametros.put("dtin", dtinicial);
		parametros.put("dtfin", dtfinal);
		
		
		Connection conexao = HibernateUtil.getConexao();
		
				String caminho = Faces.getRealPath("/reports/relComandasPorFuncionario.jasper");
				
				JasperPrint jpPrint;
				try {
					jpPrint = JasperFillManager.fillReport(caminho, parametros, conexao);
					JasperViewer jv = new JasperViewer(jpPrint, false); // seta falso para o exit_on_close
					jv.setVisible(true);// chama para visualização
					jv.toFront();// caso o relatório saia atras do programa, este comando tras o relatório para frete da aplicação
				} catch (JRException e) {
					Messages.addGlobalError("FALHA AO GERAR O RELATÓRIO:\n" + e);
					e.printStackTrace();
				}								
				//JasperPrintManager.printReport(jpPrint, true); //Chamar o relatório diretamente na impressora
				
			}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void PorFuncionarioSelecionado(Date dtinicial, Date dtfinal, String funcionario) {
		HashMap parametros = new HashMap(); // HashMap:Mapeamento do diretório / JRBean.. passa por parametro a base de dados a qual o relatório será preenchido.	
		parametros.put("dtin", dtinicial);
		parametros.put("dtfin", dtfinal);
		parametros.put("funcionario", funcionario);
		
		Connection conexao = HibernateUtil.getConexao();
		
				String caminho = Faces.getRealPath("/reports/relComandasPorFuncionarioSelecionado.jasper");
				
				JasperPrint jpPrint;
				try {
					jpPrint = JasperFillManager.fillReport(caminho, parametros, conexao);
					JasperViewer jv = new JasperViewer(jpPrint, false); // seta falso para o exit_on_close
					jv.setVisible(true);// chama para visualização
					jv.toFront();// caso o relatório saia atras do programa, este comando tras o relatório para frete da aplicação
				} catch (JRException e) {
					Messages.addGlobalError("FALHA AO GERAR O RELATÓRIO:\n" + e);
					e.printStackTrace();
				}								
				//JasperPrintManager.printReport(jpPrint, true); //Chamar o relatório diretamente na impressora
				
			}
	
	

}


