package br.com.js_solucoes.salao.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.js_solucoes.salao.model.Tipodespesa;

public class DaoTipodespesaTeste {

	@Test
	@Ignore
	public void salvar() {
		Tipodespesa tipodespesa = new Tipodespesa();
		tipodespesa.setPeriodo("MENSAL");
		tipodespesa.setTipo("ALUGUEL");

		DaoTipodespesa dao = new DaoTipodespesa();
		dao.salvar(tipodespesa);
		tipodespesa = new Tipodespesa();
		tipodespesa.setPeriodo("MENSAL");
		tipodespesa.setTipo("ÁGUA/LUZ/TELEFONE");
		dao.salvar(tipodespesa);
		
	}

	@Test
	@Ignore
	public void listar() {

		DaoTipodespesa tipodespesa = new DaoTipodespesa();
		List<Tipodespesa> resultado = tipodespesa.listar();
		System.out.println("Total de Registros Encontrados: " + resultado.size());

		for (Tipodespesa tip : resultado) {
			System.out.println(tip.getCodigo() + " - " + tip.getPeriodo() + " - " + tip.getTipo());
		}

	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 7L; // L=Long

		DaoTipodespesa tipodespesaDao = new DaoTipodespesa();
		Tipodespesa tipodespesa = tipodespesaDao.buscar(codigo);

		if (tipodespesa == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro encontrado:");
			System.out.println(
					tipodespesa.getCodigo() + " - " + tipodespesa.getPeriodo() + " - " + tipodespesa.getTipo());
		}

	}
	
	@Test
	@Ignore
	public void excluir(){
		Long codigo = 1L;
		DaoTipodespesa tipodespesaDao = new DaoTipodespesa();
		Tipodespesa tipodespesa = tipodespesaDao.buscar(codigo);
		
		if(tipodespesa == null){
			System.out.println("Nenhum registro encontrado");
		}else{
			tipodespesaDao.excluir(tipodespesa);
			System.out.println("Registro removido:");
			System.out.println(
					tipodespesa.getCodigo() + " - " + tipodespesa.getPeriodo() + " - " + tipodespesa.getTipo());
		}
	}
	@Test
	@Ignore
	public void editar(){
		Long codigo = 3L;
		DaoTipodespesa tipodespesaDao = new DaoTipodespesa();
		Tipodespesa tipodespesa = tipodespesaDao.buscar(codigo);
		
		if(tipodespesa == null){
			System.out.println("Nenhum registro encontrado");
		}else{
			tipodespesa.setTipo("FERRAMENTAS");
			tipodespesa.setPeriodo("EVENTUAL");
			tipodespesaDao.editar(tipodespesa);
			System.out.println("Registro editado:");
			System.out.println(
					tipodespesa.getCodigo() + " - " + tipodespesa.getPeriodo() + " - " + tipodespesa.getTipo());
		}
	}
}
