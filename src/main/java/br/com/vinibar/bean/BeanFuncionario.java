package br.com.vinibar.bean;

import br.com.vinibar.dao.DaoFuncionario;
import br.com.vinibar.model.Funcionario;
import br.com.vinibar.util.Log;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//@RequestScoped //para  apenas enviar o formulário
@SessionScoped // deixa o os objetos instanciados até o fim do timeout da sessão
@ManagedBean
public class BeanFuncionario {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Vini_BarPU");
	Funcionario funcionario = new Funcionario();
	Log log = new Log();
	private List<Funcionario> lista = new ArrayList<>();
	MessagesView msg = new MessagesView();

	@PostConstruct
	public void init() {
		lista = new DaoFuncionario().ListaFuncionarios();
		funcionario = new Funcionario();

	}

	public void incluir() {
		log.getPegaDataHoraAtual();
		funcionario.setDtreg(log.getData1());
		funcionario.setHrreg(log.getHora1());

		if (funcionario.getId() == null) {
			new DaoFuncionario().persist(funcionario);
			msg.info("REGISTRO SALVO");
		} else {
			try {
				new DaoFuncionario().merge(funcionario);
				msg.info("REGISTTRO ALTERADO");
			} catch (Exception ex) {
				msg.info("FALHA NA ALTERAÇÃO");
			}
		}
		funcionario = new Funcionario();
		lista = new DaoFuncionario().ListaFuncionarios();
	}

	public void excluir() {
		if (funcionario.getId() != null) {

			new DaoFuncionario().removeById(funcionario.getId());
			msg.info("REGISTRO EXCLUÍDO");
			lista = new DaoFuncionario().ListaFuncionarios();
			funcionario = new Funcionario();
		} else {
			msg.info("SELECIONE UM REGISTRO");
		}
	}

	/* -----------GETTER E SETTERS----------- */
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
