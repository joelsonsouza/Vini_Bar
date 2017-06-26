package br.com.js_solucoes.salao.bean;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.js_solucoes.salao.dao.DaoProdutos;
import br.com.js_solucoes.salao.model.Produto;
import br.com.js_solucoes.salao.util.Log;




@ViewScoped
@ManagedBean
public class BeanProduto {
	
	private Produto produto;
	
	private List<Produto> lista = new ArrayList<>();
	Log log = new Log();
	
	@PostConstruct
	public void init(){
		produto = new Produto();
		lista = new DaoProdutos().listar();
		
		
		
	}
	
	public void salvar(){
		
			log.getPegaDataHoraAtual();
			produto.setDtreg(log.getData1());
			produto.setHrreg(log.getHora1());

			if (produto.getCodigo() == null) {
				new DaoProdutos().salvar(produto);
				Messages.addGlobalInfo("SALVO COM SUCESSO");
				init();

			} else {
				new DaoProdutos().merge(produto);
				Messages.addGlobalInfo("ALTERADO COM SUCESSO");
				init();
			}
	
	}
	
	
	
	public void excluir() {

		if (produto.getCodigo() != null) {
			new DaoProdutos().excluir(produto);
			Messages.addGlobalInfo("EXCLUÍDO EXCLUÍDO");
			init();
		} else {
			Messages.addGlobalError("FALHA NA EXCLUSÃO");
		}
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getLista() {
		return lista;
	}

	public void setLista(List<Produto> lista) {
		this.lista = lista;
	}

	
}
