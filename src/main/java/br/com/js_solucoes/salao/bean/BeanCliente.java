package br.com.js_solucoes.salao.bean;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.js_solucoes.salao.dao.DaoCliente;
import br.com.js_solucoes.salao.dao.DaoPessoa;
import br.com.js_solucoes.salao.model.Cliente;
import br.com.js_solucoes.salao.model.Pessoa;
import br.com.js_solucoes.salao.util.Log;




@ViewScoped
@ManagedBean
public class BeanCliente {
	
	private Cliente cliente;
	
	private List<Cliente> lista = new ArrayList<>();
	private List<Pessoa> listaPessoa = new ArrayList<>();
	Log log = new Log();
	
	@PostConstruct
	public void init(){
		cliente = new Cliente();
		lista = new DaoCliente().listar();
		listaPessoa = new DaoPessoa().listar();
		
		
	}
	
	public void salvar(){
		
			log.getPegaDataHoraAtual();
			cliente.setDtreg(log.getData1());
			cliente.setHrreg(log.getHora1());

			if (cliente.getCodigo() == null) {
				new DaoCliente().salvar(cliente);
				Messages.addGlobalInfo("SALVO COM SUCESSO");
				init();

			} else {
				new DaoCliente().merge(cliente);
				Messages.addGlobalInfo("ALTERADO COM SUCESSO");
				init();
			}
	
	}
	
	
	
	public void excluir() {

		if (cliente.getCodigo() != null) {
			new DaoCliente().excluir(cliente);
			Messages.addGlobalInfo("EXCLUÍDO EXCLUÍDO");
			init();
		} else {
			Messages.addGlobalError("FALHA NA EXCLUSÃO");
		}
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	

	public List<Pessoa> getListaPessoa() {
		return listaPessoa;
	}

	public void setListaPessoa(List<Pessoa> listaPessoa) {
		this.listaPessoa = listaPessoa;
	}

	public List<Cliente> getLista() {
		return lista;
	}

	public void setLista(List<Cliente> lista) {
		this.lista = lista;
	}


	

	
}
