package br.com.vinibar.bean;

import br.com.vinibar.dao.DaoCliente;
import br.com.vinibar.dao.DaoComanda;
import br.com.vinibar.dao.DaoFuncionario;
import br.com.vinibar.dao.DaoItens;
import br.com.vinibar.dao.DaoItenscomanda;
import br.com.vinibar.model.Cliente;
import br.com.vinibar.model.Comanda;
import br.com.vinibar.model.Funcionario;
import br.com.vinibar.model.Itens;
import br.com.vinibar.model.Itenscomanda;
import br.com.vinibar.util.Log;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SessionScoped // deixa o os objetos instanciados até o fim do timeout da sessão
@ManagedBean
public class BeanComanda {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Vini_BarPU");
	Log log = new Log();
	private List<Funcionario> listaFuncionarios = new ArrayList<>();
	private List<Cliente> listaClientes = new ArrayList<>();
	private List<Itens> listaItens = new ArrayList<>();
	private List<Itenscomanda> lista = new ArrayList<>();
	private List<Itenscomanda> somaItens;
	private List<Comanda> listaComanda = new ArrayList<>(); // listar comandas
															// no p:dialog
	MessagesView msg = new MessagesView();
	Comanda comanda = new Comanda();
	Comanda edcomanda = new Comanda();
	Itenscomanda itenscomanda;
	double totalItens;
	int idc = 0, flag, pegaIditem = 0; // idc: variável local para pegar a id da
										// comanda

	@PostConstruct
	public void init() {
		listaFuncionarios = new DaoFuncionario().ListaFuncionarios();
		listaClientes = new DaoCliente().ListaClientes();
		listaItens = new DaoItens().ListaItens();
		somaItens = new ArrayList<>();
		listaComanda = new DaoComanda().ListaComandas();
		idc = 0;
		lista = new DaoItenscomanda().ItensPorComanda(idc);// JPQL com lista
															// filtrada por id
															// da comanda
		comanda = new Comanda();
		itenscomanda = new Itenscomanda();
		Date hoje = new Date();
		comanda.setDtcomanda(hoje);
		flag = 0;

	}

	public void abrir() {
		log.getPegaDataHoraAtual();
		comanda.setDtreg(log.getData1());
		comanda.setHrreg(log.getHora1());
		if (comanda.getIdcomanda() == null) {
			DaoComanda.getInstance().persist(comanda);
			msg.info("COMANDA ABERTA");
			idc = comanda.getIdcomanda(); // seta o id da comanda aberta na
											// variável idc
			itenscomanda.setQnt(1); // Regra de negócio: O item adicionado por
									// padrão é 1
		} else {
			try {
				DaoComanda.getInstance().merge(comanda);
				msg.info("COMANDA ALTERADA");
				itenscomanda.setQnt(1);
				idc = comanda.getIdcomanda();
				log = new Log();
			} catch (Exception ex) {
				msg.info("FALHA NA ALTERAÇÃO");
			}
		}
	}

	public void addItem() {
		log.getPegaDataHoraAtual();
		itenscomanda.setDtreg(log.getData1());
		itenscomanda.setHrreg(log.getHora1());

		if (comanda.getIdcomanda() == null) { // verifica se a comanda foi
												// aberta
			msg.error("A COMANDA NÃO FOI ABERTA");
		} else {
			itenscomanda.setIdcomanda(idc); // pega o valor do id da comada
			// armazanado na variável local idc

			if (itenscomanda.getIdproduto() == null) { // verifica se um item
														// foi selecionado
				msg.error("SELECIONE UM ITEM");
			} else {
				if (itenscomanda.getIditenscomanda() == null) {
					totalItens = itenscomanda.getIdproduto().getPreco() * itenscomanda.getQnt();
					itenscomanda.setTotalitem(totalItens);
					new DaoItenscomanda().persist(itenscomanda);
					msg.info("ITEM ADICIONADO");
					flag = 1;// Controlar método salvar, impedir que salve a
								// comanda caso não seja adicionados itens a ela

				} else {
					try {
						totalItens = itenscomanda.getIdproduto().getPreco() * itenscomanda.getQnt();
						itenscomanda.setTotalitem(totalItens);
						new DaoItenscomanda().merge(itenscomanda);
						msg.info("REGISTTRO ALTERADO");
					} catch (Exception ex) {
						msg.info("FALHA NA ALTERAÇÃO");
					}
				}
				lista = new DaoItenscomanda().ItensPorComanda(idc);
				somaItens = new DaoItenscomanda().SomaValores(idc);
				itenscomanda = new Itenscomanda();
				itenscomanda.setQnt(1);
			}
		}
	}

	public void excluirItem() {

		if (itenscomanda.getIditenscomanda() != null) {
			new DaoItenscomanda().DeleteIdItemcomanda(itenscomanda.getIditenscomanda());
			msg.info("REGISTRO EXCLUÍDO");
			itenscomanda = new Itenscomanda();
			lista = new DaoItenscomanda().ItensPorComanda(idc);
			somaItens = new DaoItenscomanda().SomaValores(idc);
		} else {
			msg.error("FALHA NA EXCLUSÃO");
		}
	}

	public void salvar() {

		if (comanda.getIdcomanda() == null) {
			msg.error("A COMANDA NÃO FOI ABERTA");
		}
		if (flag == 0) {
			msg.error("NÃO FORAM ADICIONADOS ITENS A COMANDA");
		} else {
			comanda.setStatus("ABERTA");
			comanda.setSalva(true);
			DaoComanda.getInstance().merge(comanda);
			msg.info("COMANDA SALVA");
			init();
		}
	}

	public void excluir() {
		itenscomanda.setIdcomanda(idc);
		if (comanda.getIdcomanda() != null) {
			new DaoComanda().removeById(comanda.getIdcomanda());
			new DaoItenscomanda().DeleteIdComanda(comanda.getIdcomanda());
			msg.info("COMANDA CANCELADA");
		}
		init();
	}

	public void ListaComandas() {
		somaItens = new DaoItenscomanda().SomaValores(idc); // desenvolvimento
		listaComanda = new DaoComanda().ListaComandas();

	}

	public void updateComanda() {
		log.getPegaDataHoraAtual();
		edcomanda.setDtreg(log.getData1());
		edcomanda.setHrreg(log.getHora1());
		edcomanda.setIdcomanda(edcomanda.getIdcomanda());
		edcomanda.setStatus(edcomanda.getStatus());
		try {
			new DaoComanda().merge(edcomanda);
			msg.info("REGISTRO ALTERADO");
			listaComanda = new DaoComanda().ListaComandas();
			init();
		} catch (Exception ex) {
			msg.error("FALHA NA ALTERAÇÃO");
		}

	}

	/* -----------GETTER E SETTERS----------- */
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

	public List<Itens> getListaItens() {
		return listaItens;
	}

	public void setListaItens(List<Itens> listaItens) {
		this.listaItens = listaItens;
	}

	public Comanda getComanda() {
		return comanda;
	}

	public void setComanda(Comanda comanda) {
		this.comanda = comanda;
	}

	public double getTotalItens() {
		return totalItens;
	}

	public void setTotalItens(double totalItens) {
		this.totalItens = totalItens;
	}

	public List<Itenscomanda> getLista() {
		return lista;
	}

	public void setLista(List<Itenscomanda> lista) {
		this.lista = lista;
	}

	public Itenscomanda getItenscomanda() {
		return itenscomanda;
	}

	public void setItenscomanda(Itenscomanda itenscomanda) {
		this.itenscomanda = itenscomanda;
	}

	public List<Itenscomanda> getSomaItens() {
		return somaItens;
	}

	public void setSomaItens(List<Itenscomanda> somaItens) {
		this.somaItens = somaItens;
	}

	public int getPegaIditem() {
		return pegaIditem;
	}

	public void setPegaIditem(int pegaIditem) {
		this.pegaIditem = pegaIditem;
	}

	public List<Comanda> getListaComanda() {
		return listaComanda;
	}

	public void setListaComanda(List<Comanda> listaComanda) {
		this.listaComanda = listaComanda;
	}

	public Comanda getEdcomanda() {
		return edcomanda;
	}

	public void setEdcomanda(Comanda edcomanda) {
		this.edcomanda = edcomanda;
	}

}
