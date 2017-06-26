package br.com.js_solucoes.salao.bean;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.js_solucoes.salao.dao.DaoFuncionario;
import br.com.js_solucoes.salao.dao.DaoPessoa;
import br.com.js_solucoes.salao.model.Funcionario;
import br.com.js_solucoes.salao.model.Pessoa;
import br.com.js_solucoes.salao.util.Log;




@ViewScoped
@ManagedBean
public class BeanFuncionario {
	
	private Funcionario funcionario;
	
	private List<Funcionario> lista = new ArrayList<>();
	private List<Pessoa> listaPessoa = new ArrayList<>();
	Log log = new Log();
	
	@PostConstruct
	public void init(){
		funcionario = new Funcionario();
		lista = new DaoFuncionario().listar();
		listaPessoa = new DaoPessoa().listar();
		
		
	}
	
	public void salvar(){
		
			log.getPegaDataHoraAtual();
			funcionario.setDtreg(log.getData1());
			funcionario.setHrreg(log.getHora1());

			if (funcionario.getCodigo() == null) {
				new DaoFuncionario().salvar(funcionario);
				Messages.addGlobalInfo("SALVO COM SUCESSO");
				init();

			} else {
				new DaoFuncionario().merge(funcionario);
				Messages.addGlobalInfo("ALTERADO COM SUCESSO");
				init();
			}
	
	}
	
	
	
	public void excluir() {

		if (funcionario.getCodigo() != null) {
			new DaoFuncionario().excluir(funcionario);
			Messages.addGlobalInfo("EXCLUÍDO EXCLUÍDO");
			init();
		} else {
			Messages.addGlobalError("FALHA NA EXCLUSÃO");
		}
	}

	
	public List<Pessoa> getListaPessoa() {
		return listaPessoa;
	}

	public void setListaPessoa(List<Pessoa> listaPessoa) {
		this.listaPessoa = listaPessoa;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getLista() {
		return lista;
	}

	public void setLista(List<Funcionario> lista) {
		this.lista = lista;
	}

}
