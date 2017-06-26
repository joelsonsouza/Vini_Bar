package br.com.js_solucoes.salao.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.js_solucoes.salao.dao.DaoCliente;
import br.com.js_solucoes.salao.dao.DaoComanda;
import br.com.js_solucoes.salao.dao.DaoFuncionario;
import br.com.js_solucoes.salao.dao.DaoProdutos;
import br.com.js_solucoes.salao.dao.DaoProdutosComanda;
import br.com.js_solucoes.salao.model.Cliente;
import br.com.js_solucoes.salao.model.Comanda;
import br.com.js_solucoes.salao.model.Funcionario;
import br.com.js_solucoes.salao.model.Produto;
import br.com.js_solucoes.salao.model.ProdutosComanda;
import br.com.js_solucoes.salao.util.Log;





@ViewScoped
@ManagedBean
public class BeanComanda {

	Log log = new Log();
	private List<Funcionario> listaFuncionarios = new ArrayList<>();
	private List<Cliente> listaClientes = new ArrayList<>();
	private List<Produto> listaprodutos = new ArrayList<>();
	private List<ProdutosComanda> listaProdutoscomanda = new ArrayList<>();
	private List<ProdutosComanda> somaItens;
	private List<Comanda> listaComanda = new ArrayList<>(); // listar comandas
															// no p:dialog

	Comanda comanda = new Comanda();
	Comanda edcomanda = new Comanda();
	ProdutosComanda produtoscomanda = new ProdutosComanda();
	BigDecimal totalItens;
	Long idc = 0L, flag, pegaIditem = 0L; // idc: variável local para pegar a id
											// da
											// comanda

	@PostConstruct
	public void init() {
		listaFuncionarios = new DaoFuncionario().listar();
		listaClientes = new DaoCliente().listar();
		listaprodutos = new DaoProdutos().produtosComanda();
		somaItens = new ArrayList<>();
		listaComanda = new DaoComanda().listar();
		idc = 0L;
		comanda = new Comanda();
		produtoscomanda = new ProdutosComanda();
		Date hoje = new Date();
		comanda.setDtcomanda(hoje);
		flag = 0L;
		comanda.setCodigo(0L);
		//listaProdutoscomanda = new DaoProdutosComanda().produtosComanda(comanda.getCodigo());// JPQL com lista
		// filtrada por id
		// da comanda
		listaProdutoscomanda = new DaoProdutosComanda().busca(comanda.getCodigo());
		//listaProdutoscomanda = new DaoProdutosComanda().listar();
		
	}

	public void abrir() {
		log.getPegaDataHoraAtual();
		comanda.setDtreg(log.getData1());
		comanda.setHrreg(log.getHora1());
		try {
			new DaoComanda().salvar(comanda);
			Messages.addGlobalInfo("COMANDA ABERTA");
			idc = comanda.getCodigo(); // seta o id da comanda aberta na
										// variável idc
			produtoscomanda.setQnt(1); // Regra de negócio: O item adicionado
										// por
										// padrão é 1

		} catch (Exception ex) {
			Messages.addGlobalError("ERRO AO ABRIR A COMANDA");
		}
	}

	public void addItem() {
		log.getPegaDataHoraAtual();
		produtoscomanda.setDtreg(log.getData1());
		produtoscomanda.setHrreg(log.getHora1());

		if (comanda.getCodigo() == 0) { // verifica se a comanda foi
			// aberta
			Messages.addGlobalError("A COMANDA NÃO FOI ABERTA");

		} else {
			produtoscomanda.setComanda(comanda);

			if (produtoscomanda.getProduto() == null) { // verifica se um item
				// foi selecionado
				Messages.addGlobalError("SELECIONE UM ITEM");
			} else {
				if (produtoscomanda.getCodigo() == null) {
					totalItens = produtoscomanda.getProduto().getPreco().multiply(new BigDecimal(produtoscomanda.getQnt()));
					produtoscomanda.setTotalitem(totalItens);
					produtoscomanda.setPrecoparcial(produtoscomanda.getProduto().getPreco());
					new DaoProdutosComanda().salvar(produtoscomanda);
					Messages.addGlobalInfo("ITEM ADICIONADO");
					flag = 1L;// Controlar método salvar, impedir que salve a
					// comanda caso não seja adicionados itens a ela

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
	}
	
	public void excluirItem() {

		if (produtoscomanda.getCodigo() != null) {
			new DaoProdutosComanda().excluirByCodigo(produtoscomanda.getCodigo());
			Messages.addGlobalInfo("REGISTRO EXCLUÍDO");
			produtoscomanda = new ProdutosComanda();
			listaProdutoscomanda = new DaoProdutosComanda().busca(comanda.getCodigo());
			somaItens = new DaoProdutosComanda().SomaValores(idc);
		} else {
			Messages.addGlobalError("FALHA NA EXCLUSÃO");
		}
	}
	
	public void salvar() {

		if (comanda.getCodigo() == 0L) {
			Messages.addGlobalError("A COMANDA NÃO FOI ABERTA");
		}
		if (flag == 0L) {
			Messages.addGlobalError("NÃO FORAM ADICIONADOS ITENS A COMANDA");
		} else {
			BigDecimal total = new BigDecimal("0.00");
			total = new DaoProdutosComanda().Total(idc);
			//somaItens = new DaoProdutosComanda().SomaValores(idc);
			comanda.setTotal(total);
			comanda.setStatus("ABERTA");
			comanda.setSalva(true);
			new DaoComanda().merge(comanda);
			Messages.addGlobalInfo("COMANDA SALVA");
			init();
		}
	}
	
	public void excluir() {
		produtoscomanda.setComanda(comanda);
		if (comanda.getCodigo() != 0L) {
			new DaoProdutosComanda().excluirByComanda(comanda.getCodigo());
			new DaoComanda().excluir(comanda);
			Messages.addGlobalInfo("COMANDA CANCELADA");
		}
		init();
	}
	
	public void ListaComandas() {
		//somaItens = new DaoProdutosComanda().SomaValores(idc); // desenvolvimento
		listaComanda = new DaoComanda().listar();

	}
	
	public void updateComanda() {
		log.getPegaDataHoraAtual();
		edcomanda.setDtreg(log.getData1());
		edcomanda.setHrreg(log.getHora1());
		edcomanda.setCodigo(edcomanda.getCodigo());
		edcomanda.setStatus(edcomanda.getStatus());
		try {
			new DaoComanda().merge(edcomanda);
			Messages.addGlobalInfo("REGISTRO ALTERADO");
			listaComanda = new DaoComanda().listar();
			init();
		} catch (Exception ex) {
			Messages.addGlobalError("FALHA NA ALTERAÇÃO");
		}

	}

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<Produto> getListaprodutos() {
		return listaprodutos;
	}

	public void setListaprodutos(List<Produto> listaprodutos) {
		this.listaprodutos = listaprodutos;
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

	public List<Comanda> getListaComanda() {
		return listaComanda;
	}

	public void setListaComanda(List<Comanda> listaComanda) {
		this.listaComanda = listaComanda;
	}

	public Comanda getComanda() {
		return comanda;
	}

	public void setComanda(Comanda comanda) {
		this.comanda = comanda;
	}

	public Comanda getEdcomanda() {
		return edcomanda;
	}

	public void setEdcomanda(Comanda edcomanda) {
		this.edcomanda = edcomanda;
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

	public Long getFlag() {
		return flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	public Long getPegaIditem() {
		return pegaIditem;
	}

	public void setPegaIditem(Long pegaIditem) {
		this.pegaIditem = pegaIditem;
	}

	
}
