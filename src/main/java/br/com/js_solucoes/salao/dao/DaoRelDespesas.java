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

public class DaoRelDespesas {
	
	public void PorPeriodo(Date dtinicial, Date dtfinal) {
		HashMap parametros = new HashMap(); // HashMap:Mapeamento do diretório / JRBean.. passa por parametro a base de dados a qual o relatório será preenchido.	
		parametros.put("dtin", dtinicial);
		parametros.put("dtfin", dtfinal);
		
		Connection conexao = HibernateUtil.getConexao();
		
				String caminho = Faces.getRealPath("/reports/relDespesasPorPeriodo.jasper");
				
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
	
	public void MesalEventual(Date dtinicial, Date dtfinal, String mensalEventual) {
		HashMap parametros = new HashMap(); // HashMap:Mapeamento do diretório / JRBean.. passa por parametro a base de dados a qual o relatório será preenchido.	
		parametros.put("dtin", dtinicial);
		parametros.put("dtfin", dtfinal);
		parametros.put("mensalEventual", mensalEventual);
		
		Connection conexao = HibernateUtil.getConexao();
		
				String caminho = Faces.getRealPath("/reports/relDespesasPorPeriodoFiltroMensalEventual.jasper");
				
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
	
	public void PagasNaoPagas(Date dtinicial, Date dtfinal, Boolean paga) {
		HashMap parametros = new HashMap(); // HashMap:Mapeamento do diretório / JRBean.. passa por parametro a base de dados a qual o relatório será preenchido.	
		parametros.put("dtin", dtinicial);
		parametros.put("dtfin", dtfinal);
		parametros.put("pagoNaoPago", paga);
		
		Connection conexao = HibernateUtil.getConexao();
		
				String caminho = Faces.getRealPath("/reports/relDespesasPorPeriodoPagasNaoPagas.jasper");
				
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
	
	public void PorTipo(Date dtinicial, Date dtfinal, String tipo) {
		HashMap parametros = new HashMap(); // HashMap:Mapeamento do diretório / JRBean.. passa por parametro a base de dados a qual o relatório será preenchido.	
		parametros.put("dtin", dtinicial);
		parametros.put("dtfin", dtfinal);
		parametros.put("tipo", tipo);
		
		Connection conexao = HibernateUtil.getConexao();
		
				String caminho = Faces.getRealPath("/reports/relDespesasPorTipo.jasper");
				
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


