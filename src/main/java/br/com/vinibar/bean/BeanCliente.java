package br.com.vinibar.bean;

import br.com.vinibar.model.Cliente;
import br.com.vinibar.dao.DaoCliente;

import br.com.vinibar.util.Log;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SessionScoped
@ManagedBean
public class BeanCliente {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Vini_BarPU");
	Cliente cliente = new Cliente();
	Log log = new Log();
	private List<Cliente> lista = new ArrayList<>();
	MessagesView msg = new MessagesView();

	@PostConstruct
	public void init() {
		lista = new DaoCliente().ListaClientes();
		cliente = new Cliente();

	}

	public void incluir() {
		log.getPegaDataHoraAtual();
		cliente.setDtreg(log.getData1());
		cliente.setHrreg(log.getHora1());

		if (cliente.getId() == null) {
			new DaoCliente().persist(cliente);
			msg.info("REGISTRO SALVO");
		} else {
			try {
				new DaoCliente().merge(cliente);
				msg.info("REGISTTRO ALTERADO");
			} catch (Exception ex) {
				msg.info("FALHA NA ALTERAÇÃO");
			}
		}
		cliente = new Cliente();
		lista = new DaoCliente().ListaClientes();
	}

	public void excluir() {

		if (cliente.getId() != null) {
			new DaoCliente().removeById(cliente.getId());
			msg.info("REGISTRO EXCLUÍDO");
			lista = new DaoCliente().ListaClientes();
			cliente = new Cliente();
		} else {
			msg.info("SELECIONE UM REGISTRO");
		}
	}

	/* -----------GETTER E SETTERS----------- */

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getLista() {
		return lista;
	}

	public void setLista(List<Cliente> lista) {
		this.lista = lista;
	}

}
