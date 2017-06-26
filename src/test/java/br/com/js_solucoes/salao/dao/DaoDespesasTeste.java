package br.com.js_solucoes.salao.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.js_solucoes.salao.model.Despesas;
import br.com.js_solucoes.salao.model.Tipodespesa;

public class DaoDespesasTeste {

	@Test
	@Ignore
	public void salvar() {
		DaoTipodespesa tipoDespesaDao = new DaoTipodespesa();

		Tipodespesa tipodespesa = tipoDespesaDao.buscar(2L);

		Despesas despesas = new Despesas();
		despesas.setDescricao("CONTA DE ÁGUA");
		despesas.setDtdespesa(new Date());
		despesas.setTipodespesa(tipodespesa);

		DaoDespesa daodespesa = new DaoDespesa();
		daodespesa.salvar(despesas);
	}

	@Test
	@Ignore
	public void listar() {
		DaoDespesa daodespesa = new DaoDespesa();
		List<Despesas> resultado = daodespesa.listar();

		for (Despesas des : resultado) {
			System.out.println("Cod: " + des.getCodigo());
			System.out.println("Descrição: " + des.getDescricao());
			System.out.println("Data: " + des.getDtdespesa());
			System.out.println("Tipo: " + des.getTipodespesa().getTipo());
			System.out.println("Período: " + des.getTipodespesa().getPeriodo());
		}

	}

	@Test
	
	public void buscar() {
		Long codigo = 3L;
		DaoDespesa daodespesa = new DaoDespesa();
		Despesas despesa = daodespesa.buscar(codigo);
		if (despesa == null) {
			System.out.println("Nenhum registro encontrado");
		} else {

			System.out.println("Cod: " + despesa.getCodigo());
			System.out.println("Descrição: " + despesa.getDescricao());
			System.out.println("Valor: " + despesa.getValor());
			System.out.println("Data: " + despesa.getDtdespesa());
			System.out.println("Tipo: " + despesa.getTipodespesa().getTipo());
			System.out.println("Período: " + despesa.getTipodespesa().getPeriodo());
		}

	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 4L;
		DaoDespesa despesaDao = new DaoDespesa();
		Despesas despesas = despesaDao.buscar(codigo);

		if (despesas == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			despesaDao.excluir(despesas);
			System.out.println("Registro removido:");
			System.out.println(despesas.getCodigo() + " - " + despesas.getDescricao() + " - "
					+ despesas.getTipodespesa().getPeriodo() + " - " + despesas.getTipodespesa().getTipo());
		}
	}

	@Test
	@Ignore
	public void editar(){
		Long codigo = 3L;
		Long codTipodespesa = 2L;
		
		DaoDespesa despesaDao = new DaoDespesa();
		Despesas despesa = despesaDao.buscar(codigo);
		
		DaoTipodespesa tipoDespesaDao = new DaoTipodespesa();
		Tipodespesa tipodespesa = tipoDespesaDao.buscar(codTipodespesa);

		if (despesa == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			
			System.out.println("Cod: " + despesa.getCodigo());
			System.out.println("Descrição: " + despesa.getDescricao());
			System.out.println("Data: " + despesa.getDtdespesa());
			System.out.println("Tipo: " + despesa.getTipodespesa().getTipo());
			System.out.println("Período: " + despesa.getTipodespesa().getPeriodo());
			
			despesa.setDescricao("CONTA DE LUZ");
			despesa.setTipodespesa(tipodespesa);
			despesa.setValor(new BigDecimal("12.55"));
			despesaDao.editar(despesa);

			System.out.println("Cod: " + despesa.getCodigo());
			System.out.println("Descrição: " + despesa.getDescricao());
			System.out.println("Data: " + despesa.getDtdespesa());
			System.out.println("Tipo: " + despesa.getTipodespesa().getTipo());
			System.out.println("Período: " + despesa.getTipodespesa().getPeriodo());
			
		}
		
		
	}
}
