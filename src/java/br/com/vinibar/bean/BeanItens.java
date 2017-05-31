package br.com.vinibar.bean;

import br.com.vinibar.dao.ItensJpaController;
import br.com.vinibar.dao.exceptions.NonexistentEntityException;
import br.com.vinibar.model.Itens;
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
public class BeanItens {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Vini_BarPU");
    Itens itens = new Itens();
    Log log = new Log();
    private List<Itens> lista = new ArrayList<>();
    MessagesView msg = new MessagesView();

    @PostConstruct
    public void init() {
        lista = new ItensJpaController(emf).findItensEntities();
        itens = new Itens();

    }

    public void incluir() {
        log.getPegaDataHoraAtual();
        itens.setDtreg(log.getData1());
        itens.setHrreg(log.getHora1());

        if (itens.getId() == null) {
            new ItensJpaController(emf).create(itens);
            msg.info("REGISTRO SALVO");
            itens = new Itens();
            lista = new ItensJpaController(emf).findItensEntities();
        } else {
            try {
                new ItensJpaController(emf).edit(itens);
                msg.info("REGISTTRO ALTERADO");
                itens = new Itens();
                lista = new ItensJpaController(emf).findItensEntities();
            } catch (Exception ex) {
                msg.error("FALHA NA ALTERAÇÃO");
            }
        }
    }

    public void excluir() {
        if (itens.getId() != null) {
            try {
                new ItensJpaController(emf).destroy(itens.getId());
                msg.info("REGISTRO EXCLUÍDO");
                lista = new ItensJpaController(emf).findItensEntities();
                itens = new Itens();
            } catch (NonexistentEntityException ex) {
                msg.error("FALHA NA EXCLUSÃO");
            }
        } else {
            msg.error("SELECIONE UM REGISTRO");
        }
    }


    /* -----------GETTER E SETTERS-----------*/

    public Itens getItens() {
        return itens;
    }

    public void setItens(Itens itens) {
        this.itens = itens;
    }

    public List<Itens> getLista() {
        return lista;
    }

    public void setLista(List<Itens> lista) {
        this.lista = lista;
    }

   

    
}
