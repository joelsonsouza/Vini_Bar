package br.com.js_solucoes.salao.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.js_solucoes.salao.dao.DaoDespesa;
import br.com.js_solucoes.salao.dao.DaoFornecedor;
import br.com.js_solucoes.salao.dao.DaoTipodespesa;
import br.com.js_solucoes.salao.model.Despesas;
import br.com.js_solucoes.salao.model.Fornecedor;
import br.com.js_solucoes.salao.model.Tipodespesa;
import br.com.js_solucoes.salao.util.Log;

@ViewScoped
@ManagedBean
public class BeanDespesas {

	private Despesas despesa;
	private Despesas eddespesa;

	private List<Tipodespesa> listaTipodespesa = new ArrayList<>();
	private List<Fornecedor> listaFornecedor = new ArrayList<>();
	private List<Despesas> listaDespesa = new ArrayList<>();
	Log log = new Log();

	@PostConstruct
	public void init() {
		despesa = new Despesas();
		eddespesa = new Despesas();
		listaTipodespesa = new DaoTipodespesa().listar();
		listaFornecedor = new DaoFornecedor().listar();
		listaDespesa = new DaoDespesa().listar();

	}

	public void salvar() {

		log.getPegaDataHoraAtual();
		despesa.setDtreg(log.getData1());
		despesa.setHrreg(log.getHora1());

		if (despesa.getCodigo() == null) {
			new DaoDespesa().salvar(despesa);
			Messages.addGlobalInfo("SALVO COM SUCESSO");
			init();

		} else {
			new DaoDespesa().merge(despesa);
			Messages.addGlobalInfo("ALTERADO COM SUCESSO");
			init();
		}

	}

	public void excluir() {

		if (despesa.getCodigo() != null) {
			new DaoDespesa().excluir(despesa);
			Messages.addGlobalInfo("REGISTRO EXCLUÍDO");
			init();
		} else {
			Messages.addGlobalError("FALHA NA EXCLUSÃO");
		}
	}

	public Despesas getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesas despesa) {
		this.despesa = despesa;
	}

	public Despesas getEddespesa() {
		return eddespesa;
	}

	public void setEddespesa(Despesas eddespesa) {
		this.eddespesa = eddespesa;
	}

	public List<Tipodespesa> getListaTipodespesa() {
		return listaTipodespesa;
	}

	public void setListaTipodespesa(List<Tipodespesa> listaTipodespesa) {
		this.listaTipodespesa = listaTipodespesa;
	}

	public List<Despesas> getListaDespesa() {
		return listaDespesa;
	}

	public void setListaDespesa(List<Despesas> listaDespesa) {
		this.listaDespesa = listaDespesa;
	}

	public List<Fornecedor> getListaFornecedor() {
		return listaFornecedor;
	}

	public void setListaFornecedor(List<Fornecedor> listaFornecedor) {
		this.listaFornecedor = listaFornecedor;
	}

}
