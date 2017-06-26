package br.com.js_solucoes.salao.bean;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.js_solucoes.salao.dao.DaoFornecedor;
import br.com.js_solucoes.salao.model.Fornecedor;
import br.com.js_solucoes.salao.util.Log;




@ViewScoped
@ManagedBean
public class BeanFornecedor {
	
	private Fornecedor fornecedor;
	private Fornecedor edfornecedor;
	
	private List<Fornecedor> lista = new ArrayList<>();
	Log log = new Log();
	
	@PostConstruct
	public void init(){
		fornecedor = new Fornecedor();
		edfornecedor = new Fornecedor();
		lista = new DaoFornecedor().listar();
		
	}
	
	public void salvar(){
		
			log.getPegaDataHoraAtual();
			fornecedor.setDtreg(log.getData1());
			fornecedor.setHrreg(log.getHora1());

			if (fornecedor.getCodigo() == null) {
				new DaoFornecedor().salvar(fornecedor);
				Messages.addGlobalInfo("SALVO COM SUCESSO");
				init();

			} else {
				new DaoFornecedor().merge(fornecedor);
				Messages.addGlobalInfo("ALTERADO COM SUCESSO");
				init();
			}
	
	}
	
	
	
	public void excluir() {

		if (fornecedor.getCodigo() != null) {
			new DaoFornecedor().excluir(fornecedor);
			Messages.addGlobalInfo("EXCLUÍDO EXCLUÍDO");
			init();
		} else {
			Messages.addGlobalError("FALHA NA EXCLUSÃO");
		}
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Fornecedor getEdfornecedor() {
		return edfornecedor;
	}

	public void setEdfornecedor(Fornecedor edfornecedor) {
		this.edfornecedor = edfornecedor;
	}

	public List<Fornecedor> getLista() {
		return lista;
	}

	public void setLista(List<Fornecedor> lista) {
		this.lista = lista;
	}
	

	
}
