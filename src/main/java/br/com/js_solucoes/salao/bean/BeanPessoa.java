package br.com.js_solucoes.salao.bean;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.js_solucoes.salao.dao.DaoPessoa;
import br.com.js_solucoes.salao.model.Pessoa;
import br.com.js_solucoes.salao.util.Log;




@ViewScoped
@ManagedBean
public class BeanPessoa {
	
	private Pessoa pessoa;
	
	
	private List<Pessoa> lista = new ArrayList<>();
	Log log = new Log();
	
	@PostConstruct
	public void init(){
		pessoa = new Pessoa();
		lista = new DaoPessoa().listar();
		
	}
	
	public void salvar(){
		
			log.getPegaDataHoraAtual();
			pessoa.setDtreg(log.getData1());
			pessoa.setHrreg(log.getHora1());

			if (pessoa.getCodigo() == null) {
				new DaoPessoa().salvar(pessoa);
				Messages.addGlobalInfo("SALVO COM SUCESSO");
				init();

			} else {
				new DaoPessoa().merge(pessoa);
				Messages.addGlobalInfo("ALTERADO COM SUCESSO");
				init();
			}
	
	}
	
	
	
	public void excluir() {

		if (pessoa.getCodigo() != null) {
			new DaoPessoa().excluir(pessoa);
			Messages.addGlobalInfo("EXCLUÍDO EXCLUÍDO");
			init();
		} else {
			Messages.addGlobalError("FALHA NA EXCLUSÃO");
		}
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getLista() {
		return lista;
	}

	public void setLista(List<Pessoa> lista) {
		this.lista = lista;
	}

	


	

	
}
