package br.com.vinibar.bean;

import br.com.vinibar.dao.DaoFornecedor;
import br.com.vinibar.model.Fornecedor;
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
public class BeanFornecedor {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Vini_BarPU");
	Fornecedor fornecedor = new Fornecedor();
	Log log = new Log();
	private List<Fornecedor> lista = new ArrayList<>();
	MessagesView msg = new MessagesView();

	@PostConstruct
	public void init() {
		lista = new DaoFornecedor().ListaFornecedor();
		fornecedor = new Fornecedor();
	}

	public void incluir() {
		log.getPegaDataHoraAtual();
		fornecedor.setDtreg(log.getData1());
		fornecedor.setHrreg(log.getHora1());

		if (fornecedor.getId() == null) {
			new DaoFornecedor().persist(fornecedor);
			msg.info("REGISTRO SALVO");
		} else {
			try {
				new DaoFornecedor().merge(fornecedor);
				msg.info("REGISTTRO ALTERADO");
			} catch (Exception ex) {
				msg.info("FALHA NA ALTERAÇÃO");
			}
		}
		new DaoFornecedor().ListaFornecedor();
		fornecedor = new Fornecedor();
	}

	public void excluir() {

		if (fornecedor.getId() != null) {

			new DaoFornecedor().removeById(fornecedor.getId());
			msg.info("REGISTRO EXCLUÍDO");
			fornecedor = new Fornecedor();
			new DaoFornecedor().ListaFornecedor();
		} else {
			msg.info("SELECIONE UM REGISTRO");
		}
	}

	/* -----------GETTER E SETTERS----------- */
	public List<Fornecedor> getLista() {
		return lista;
	}

	public void setLista(List<Fornecedor> lista) {
		this.lista = lista;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

}
