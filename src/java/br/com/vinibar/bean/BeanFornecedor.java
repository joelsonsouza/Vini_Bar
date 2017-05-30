package br.com.vinibar.bean;

import br.com.vinibar.dao.FornecedorJpaController;
import br.com.vinibar.dao.exceptions.NonexistentEntityException;
import br.com.vinibar.model.Fornecedor;
import br.com.vinibar.util.Log;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.primefaces.event.SelectEvent;

//@RequestScoped //para  apenas enviar o formulário
@SessionScoped //deixa o os objetos instanciados até o fim do timeout da sessão
@ManagedBean
public class BeanFornecedor {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Vini_BarPU");
    Fornecedor fornecedor = new Fornecedor();
    Log log = new Log();
    private List<Fornecedor> lista = new ArrayList<>();
    MessagesView msg = new MessagesView();

    @PostConstruct
    public void init() {
        lista = new FornecedorJpaController(emf).findFornecedorEntities();
        fornecedor = new Fornecedor();
    }

    public void incluir() {
        log.getPegaDataHoraAtual();
        fornecedor.setDtreg(log.getData1());
        fornecedor.setHrreg(log.getHora1());

        if (fornecedor.getId() == null) {
            new FornecedorJpaController(emf).create(fornecedor);
            msg.info("REGISTRO SALVO");
            lista = new FornecedorJpaController(emf).findFornecedorEntities();
        } else {
            try {
                new FornecedorJpaController(emf).edit(fornecedor);
                msg.info("REGISTTRO ALTERADO");
                lista = new FornecedorJpaController(emf).findFornecedorEntities();
            } catch (Exception ex) {
                msg.info("FALHA NA ALTERAÇÃO");

            }
        }
        fornecedor = new Fornecedor();
    }


    public void excluir() {

        if (fornecedor.getId() != null) {
            try {
                new FornecedorJpaController(emf).destroy(fornecedor.getId());
                msg.info("REGISTRO EXCLUÍDO");
                fornecedor = new Fornecedor();
                lista = new FornecedorJpaController(emf).findFornecedorEntities();
            } catch (NonexistentEntityException ex) {
                msg.info("FALHA NA EXCLUSÃO");
            }
        } else {
            msg.info("SELECIONE UM REGISTRO");
        }
    }
    


    /* -----------GETTER E SETTERS-----------*/
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
