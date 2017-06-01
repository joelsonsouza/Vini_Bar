package br.com.vinibar.bean;

import br.com.vinibar.dao.ClienteJpaController;
import br.com.vinibar.dao.ComandaDao;
import br.com.vinibar.dao.FuncionarioJpaController;
import br.com.vinibar.dao.ItensJpaController;
import br.com.vinibar.dao.ItenscomandaJpaController;
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

//@RequestScoped //para  apenas enviar o formulário
@SessionScoped //deixa o os objetos instanciados até o fim do timeout da sessão
@ManagedBean
public class BeanAtendimento {
    

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Vini_BarPU");
    Log log = new Log();
    private List<Funcionario> listaFuncionarios = new ArrayList<>();
    private List<Cliente> listaClientes = new ArrayList<>();
    private List<Itens> listaItens = new ArrayList<>();
    private List<Itenscomanda> lista = new ArrayList<>();
    MessagesView msg = new MessagesView();
    Comanda comanda = new Comanda();
    Itens item = new Itens();
    Itenscomanda itscomanda = new Itenscomanda();
    double totalItens;
    int idc;

    @PostConstruct
    public void init() {
        listaFuncionarios = new FuncionarioJpaController(emf).findFuncionarioEntities();
        listaClientes = new ClienteJpaController(emf).findClienteEntities();
        listaItens = new ItensJpaController(emf).findItensEntities();
        lista = new ItenscomandaJpaController(emf).findItenscomandaEntities();
        comanda = new Comanda();
        comanda.setQnt(1);
        itscomanda = new Itenscomanda();
    }

    public void abrir() {

        log.getPegaDataHoraAtual();
        comanda.setDtreg(log.getData1());
        comanda.setHrreg(log.getHora1());
//        totalItens = comanda.getIditem().getPreco() * comanda.getQnt();
        if (comanda.getId() == null) {
            ComandaDao.getInstance().persist(comanda);
            msg.info("COMANDA ABERTA");
            idc = comanda.getId();
            log = new Log();
        } else {
            try {
                ComandaDao.getInstance().merge(comanda);
                msg.info("COMANDA ALTERADA");
                idc = comanda.getId();
                log = new Log();
            } catch (Exception ex) {
                msg.info("FALHA NA ALTERAÇÃO");
            }
        } 
    }


    public void addItem()  {
        log.getPegaDataHoraAtual();
        itscomanda.setDtreg(log.getData1());
        itscomanda.setHrreg(log.getHora1());
        //itscomanda.setIdcomanda(idc);
        itscomanda.setIdcomanda(comanda.getId());
        
        totalItens = itscomanda.getIdproduto().getPreco() * itscomanda.getQnt();
        itscomanda.setTotalitem(totalItens);

        if (itscomanda.getId() == null) {
            new ItenscomandaJpaController(emf).create(itscomanda);
            msg.info("ITEM ADICIONADO");
            itscomanda = new Itenscomanda();
            lista = new ItenscomandaJpaController(emf).findItenscomandaEntities();
        } else {
            try {
                new ItenscomandaJpaController(emf).edit(itscomanda);
                msg.info("REGISTTRO ALTERADO");
                lista = new ItenscomandaJpaController(emf).findItenscomandaEntities();
            } catch (Exception ex) {
                msg.info("FALHA NA ALTERAÇÃO");
            }
        }
        itscomanda = new Itenscomanda();
    }

    

    public void excluir() {

    }


    /* -----------GETTER E SETTERS-----------*/
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

    public Itens getItem() {
        return item;
    }

    public void setItem(Itens item) {
        this.item = item;
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

    public Itenscomanda getItscomanda() {
        return itscomanda;
    }

    public void setItscomanda(Itenscomanda itscomanda) {
        this.itscomanda = itscomanda;
    }

    
    

}
