package br.com.vinibar.bean;

import br.com.vinibar.dao.exceptions.NonexistentEntityException;
import br.com.vinibar.model.Cliente;
import br.com.vinibar.dao.ClienteJpaController;
import br.com.vinibar.dao.exceptions.IllegalOrphanException;
import br.com.vinibar.util.Log;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//@RequestScoped //para  apenas enviar o formulário
@SessionScoped //deixa o os objetos instanciados até o fim do timeout da sessão
@ManagedBean
public class BeanCliente {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Vini_BarPU");
    Cliente cliente = new Cliente();
    Log log = new Log();
    private List<Cliente> lista = new ArrayList<>();
    MessagesView msg = new MessagesView();

    @PostConstruct
    public void init() {
        lista = new ClienteJpaController(emf).findClienteEntities();
        cliente = new Cliente();

    }

    public void incluir() {
        log.getPegaDataHoraAtual();
        cliente.setDtreg(log.getData1());
        cliente.setHrreg(log.getHora1());

        if (cliente.getId() == null) {
            new ClienteJpaController(emf).create(cliente);
            msg.info("REGISTRO SALVO");
            lista = new ClienteJpaController(emf).findClienteEntities();
        } else {
            try {
                new ClienteJpaController(emf).edit(cliente);
                msg.info("REGISTTRO ALTERADO");
                lista = new ClienteJpaController(emf).findClienteEntities();
            } catch (Exception ex) {
                msg.info("FALHA NA ALTERAÇÃO");
            }
        }
        cliente = new Cliente();
    }

    public void excluir() throws IllegalOrphanException {

        if (cliente.getId() != null) {
            try {
                new ClienteJpaController(emf).destroy(cliente.getId());
                msg.info("REGISTRO EXCLUÍDO");
                lista = new ClienteJpaController(emf).findClienteEntities();
                cliente = new Cliente();
            } catch (NonexistentEntityException ex) {
                msg.info("FALHA NA EXCLUSÃO");
            }
        } else {
            msg.info("SELECIONE UM REGISTRO");
        }
    }


    /* -----------GETTER E SETTERS-----------*/

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
