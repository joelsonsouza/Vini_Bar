package br.com.vinibar.bean;

import br.com.vinibar.dao.FuncionarioJpaController;
import br.com.vinibar.dao.exceptions.IllegalOrphanException;
import br.com.vinibar.dao.exceptions.NonexistentEntityException;
import br.com.vinibar.model.Funcionario;
import br.com.vinibar.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//@RequestScoped //para  apenas enviar o formulário
@SessionScoped //deixa o os objetos instanciados até o fim do timeout da sessão
@ManagedBean
public class BeanFuncionario {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Vini_BarPU");
    Funcionario funcionario = new Funcionario();
    Log log = new Log();
    private List<Funcionario> lista = new ArrayList<>();
    MessagesView msg = new MessagesView();

    @PostConstruct
    public void init() {
        lista = new FuncionarioJpaController(emf).findFuncionarioEntities();
        funcionario = new Funcionario();

    }

    public void incluir() {
        log.getPegaDataHoraAtual();
        funcionario.setDtreg(log.getData1());
        funcionario.setHrreg(log.getHora1());

        if (funcionario.getId() == null) {
            new FuncionarioJpaController(emf).create(funcionario);
            msg.info("REGISTRO SALVO");
            funcionario = new Funcionario();
            lista = new FuncionarioJpaController(emf).findFuncionarioEntities();
        } else {
            try {
                new FuncionarioJpaController(emf).edit(funcionario);
                msg.info("REGISTTRO ALTERADO");
                funcionario = new Funcionario();
                lista = new FuncionarioJpaController(emf).findFuncionarioEntities();
            } catch (Exception ex) {
                msg.info("FALHA NA ALTERAÇÃO");
            }
        }
    }

    public void excluir() throws IllegalOrphanException {
        if (funcionario.getId() != null) {
            try {
                new FuncionarioJpaController(emf).destroy(funcionario.getId());
                msg.info("REGISTRO EXCLUÍDO");
                lista = new FuncionarioJpaController(emf).findFuncionarioEntities();
                funcionario = new Funcionario();
            } catch (NonexistentEntityException ex) {
                msg.info("FALHA NA EXCLUSÃO");
            }
        } else {
            msg.info("SELECIONE UM REGISTRO");
        }
    }


    /* -----------GETTER E SETTERS-----------*/
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
