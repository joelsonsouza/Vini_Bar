package br.com.js_solucoes.salao.bean;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.js_solucoes.salao.dao.DaoTipodespesa;
import br.com.js_solucoes.salao.model.Tipodespesa;
import br.com.js_solucoes.salao.util.Log;




@ViewScoped
@ManagedBean
public class BeanTipodespesa {
	
	private Tipodespesa tipodespesa;
	private Tipodespesa edtipodespesa;
	
	private List<Tipodespesa> lista = new ArrayList<>();
	Log log = new Log();
	
	@PostConstruct
	public void init(){
		tipodespesa = new Tipodespesa();
		edtipodespesa = new Tipodespesa();
		lista = new DaoTipodespesa().listar();
		
	}
	
	public void salvar(){
		
			log.getPegaDataHoraAtual();
			tipodespesa.setDtreg(log.getData1());
			tipodespesa.setHrreg(log.getHora1());

			if (tipodespesa.getTipo() == "" || tipodespesa.getPeriodo() == "") {
				Messages.addGlobalError("PRENCHA OS CAMPOS OBRIGATÓRIOS");
				
			} else {
				new DaoTipodespesa().merge(tipodespesa);
				Messages.addGlobalInfo(tipodespesa.getTipo() + ": SALVO COM SUCESSO");
				init();
			}
	
	}
	
	public void editar(){
		
		log.getPegaDataHoraAtual();
		edtipodespesa.setDtreg(log.getData1());
		edtipodespesa.setHrreg(log.getHora1());

		if (edtipodespesa.getTipo() == "" || edtipodespesa.getPeriodo() == "") {
			Messages.addGlobalError("PRENCHA OS CAMPOS OBRIGATÓRIOS");
			
		} else {
			new DaoTipodespesa().merge(edtipodespesa);
			Messages.addGlobalInfo("REGISTRO ALTERADO");
			init();
		}

}
	
	public void excluir() {

		if (tipodespesa.getCodigo() != null) {
			new DaoTipodespesa().excluir(tipodespesa);
			Messages.addGlobalInfo("EXCLUÍDO COM SUCESSO: " +tipodespesa.getTipo());
			init();
		} else {
			Messages.addGlobalError("FALHA NA EXCLUSÃO");
		}
	}
	

	public Tipodespesa getTipodespesa() {
		return tipodespesa;
	}

	public void setTipodespesa(Tipodespesa tipodespesa) {
		this.tipodespesa = tipodespesa;
	}

	public List<Tipodespesa> getLista() {
		return lista;
	}

	public void setLista(List<Tipodespesa> lista) {
		this.lista = lista;
	}

	public Tipodespesa getEdtipodespesa() {
		return edtipodespesa;
	}

	public void setEdtipodespesa(Tipodespesa edtipodespesa) {
		this.edtipodespesa = edtipodespesa;
	}

}
