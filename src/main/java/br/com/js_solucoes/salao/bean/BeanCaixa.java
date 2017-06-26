package br.com.js_solucoes.salao.bean;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Messages;

import br.com.js_solucoes.salao.dao.DaoCaixa;
import br.com.js_solucoes.salao.dao.DaoComanda;
import br.com.js_solucoes.salao.dao.DaoFuncionario;
import br.com.js_solucoes.salao.dao.DaoProdutos;
import br.com.js_solucoes.salao.dao.DaoProdutosComanda;
import br.com.js_solucoes.salao.model.Caixa;
import br.com.js_solucoes.salao.model.Comanda;
import br.com.js_solucoes.salao.model.Funcionario;
import br.com.js_solucoes.salao.model.Produto;
import br.com.js_solucoes.salao.model.ProdutosComanda;
import br.com.js_solucoes.salao.util.Log;

@SessionScoped
@ManagedBean
public class BeanCaixa {

	Log log = new Log();
	Caixa caixa = new Caixa();
	private List<Funcionario> listaFuncionarios = new ArrayList<>();
	private List<Comanda> listaComandas = new ArrayList<>();
	Boolean filtroComanda = true;
	String aberta = "ABERTA";
	private List<Produto> listaprodutos = new ArrayList<>();
	String desconto = "PROMOÇÃO";
	private List<ProdutosComanda> listaProdutoscomanda = new ArrayList<>();
	private List<ProdutosComanda> somaItens;
	Comanda comanda = new Comanda();
	ProdutosComanda produtoscomanda = new ProdutosComanda();
	BigDecimal totalItens;
	Long idc = 0L, pegaIditem = 0L; // idc: variável local para pegar a id da
									// comanda.
	String status;

	@PostConstruct
	public void init() {
		listaFuncionarios = new DaoFuncionario().listar();
//
		listaComandas = new DaoComanda().ComandasAbertas();
		listaprodutos = new DaoProdutos().produtosCaixa();
		somaItens = new ArrayList<>();
		idc = 0L;
		listaProdutoscomanda = new DaoProdutosComanda().busca(idc);// JPQL com lista
//															// filtrada por id
//		
		comanda = new Comanda();// da comanda
		caixa = new Caixa();
		produtoscomanda = new ProdutosComanda();
		status = ("ABERTA");
	}

	public void carregaComanda() {	
		idc = caixa.getComanda_codigo();
		listaProdutoscomanda = new DaoProdutosComanda().busca(idc);// JPQL com lista
															// filtrada por id
															// da comanda
		somaItens = new DaoProdutosComanda().SomaValores(idc);

	}

	public void addItem() {
		log.getPegaDataHoraAtual();
		produtoscomanda.setDtreg(log.getData1());
		produtoscomanda.setHrreg(log.getHora1());//
		produtoscomanda.setComanda(comanda); // pega o valor do id da comada
//										// armazanado na variável local idc
		comanda.setCodigo(idc);
		if (produtoscomanda.getProduto() == null) { // verifica se um item
			// foi selecionado
			Messages.addGlobalError("SELECIONE UM ITEM");
		} else {
			if (produtoscomanda.getCodigo() == null) {
				produtoscomanda.setQnt(1);
				totalItens = produtoscomanda.getProduto().getPreco().multiply(new BigDecimal(produtoscomanda.getQnt()));
				produtoscomanda.setTotalitem(totalItens);
				produtoscomanda.setPrecoparcial(produtoscomanda.getProduto().getPreco());
				new DaoProdutosComanda().salvar(produtoscomanda);
				Messages.addGlobalInfo("DESCONTO ADICIONADO");
				

			} else {
				try {
					totalItens = produtoscomanda.getProduto().getPreco().multiply(new BigDecimal(produtoscomanda.getQnt()));
					produtoscomanda.setTotalitem(totalItens);
					new DaoProdutosComanda().merge(produtoscomanda);
					Messages.addGlobalInfo("REGISTTRO ALTERADO");
				} catch (Exception ex) {
					Messages.addGlobalError("FALHA NA ALTERAÇÃO");
				}
			}
			listaProdutoscomanda = new DaoProdutosComanda().busca(comanda.getCodigo());
			somaItens = new DaoProdutosComanda().SomaValores(idc);
			produtoscomanda = new ProdutosComanda();
			produtoscomanda.setQnt(1);
		}
	}


	public void excluirItem() {

		if (produtoscomanda.getCodigo() != null) {
			new DaoProdutosComanda().excluirByCodigo(produtoscomanda.getCodigo());
			//new DaoProdutosComanda().excluir(produtoscomanda);
			Messages.addGlobalInfo("REGISTRO EXCLUÍDO");
			produtoscomanda = new ProdutosComanda();
			listaProdutoscomanda = new DaoProdutosComanda().busca(comanda.getCodigo());
			somaItens = new DaoProdutosComanda().SomaValores(idc);
		} else {
			Messages.addGlobalError("FALHA NA EXCLUSÃO");
		}
	}

	public void salvar() {
		log.getPegaDataHoraAtual();
		caixa.setDtreg(log.getData1());
		caixa.setHrreg(log.getHora1());
		idc = caixa.getComanda_codigo();
		if ("SELECIONE".equals(caixa.getFormapagamento())) {
			Messages.addGlobalError("SELECIONE UMA FORMA DE PAGAMENTO");
		} else {
			caixa.getComanda_codigo();
			new DaoCaixa().salvar(caixa);
			//ATUALIZA O STATUS DA COMANDA
			new DaoComanda().UpdateComanda(status, caixa.getComanda_codigo());
			//ATUALIZA O TOTAL DA COMANDA
			BigDecimal total = new BigDecimal("0.00");
			total = new DaoProdutosComanda().Total(idc);
			new DaoComanda().UpdateComandaTotal(caixa.getComanda_codigo(), total);
			
			Messages.addGlobalInfo("COMANDA PAGA");
			init();
		}
	}

	/* -----------GETTER E SETTERS----------- */

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public List<Comanda> getListaComandas() {
		return listaComandas;
	}

	public void setListaComandas(List<Comanda> listaComandas) {
		this.listaComandas = listaComandas;
	}

	public Boolean getFiltroComanda() {
		return filtroComanda;
	}

	public void setFiltroComanda(Boolean filtroComanda) {
		this.filtroComanda = filtroComanda;
	}

	public String getAberta() {
		return aberta;
	}

	public void setAberta(String aberta) {
		this.aberta = aberta;
	}

	public List<Produto> getListaprodutos() {
		return listaprodutos;
	}

	public void setListaprodutos(List<Produto> listaprodutos) {
		this.listaprodutos = listaprodutos;
	}

	public String getDesconto() {
		return desconto;
	}

	public void setDesconto(String desconto) {
		this.desconto = desconto;
	}

	public List<ProdutosComanda> getListaProdutoscomanda() {
		return listaProdutoscomanda;
	}

	public void setListaProdutoscomanda(List<ProdutosComanda> listaProdutoscomanda) {
		this.listaProdutoscomanda = listaProdutoscomanda;
	}

	public List<ProdutosComanda> getSomaItens() {
		return somaItens;
	}

	public void setSomaItens(List<ProdutosComanda> somaItens) {
		this.somaItens = somaItens;
	}

	public Comanda getComanda() {
		return comanda;
	}

	public void setComanda(Comanda comanda) {
		this.comanda = comanda;
	}

	public ProdutosComanda getProdutoscomanda() {
		return produtoscomanda;
	}

	public void setProdutoscomanda(ProdutosComanda produtoscomanda) {
		this.produtoscomanda = produtoscomanda;
	}

	public BigDecimal getTotalItens() {
		return totalItens;
	}

	public void setTotalItens(BigDecimal totalItens) {
		this.totalItens = totalItens;
	}

	public Long getIdc() {
		return idc;
	}

	public void setIdc(Long idc) {
		this.idc = idc;
	}

	public Long getPegaIditem() {
		return pegaIditem;
	}

	public void setPegaIditem(Long pegaIditem) {
		this.pegaIditem = pegaIditem;
	}
	
	

}
