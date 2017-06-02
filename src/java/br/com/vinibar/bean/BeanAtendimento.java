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
import java.text.SimpleDateFormat;
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
    private List<Itenscomanda> somaItens;
    MessagesView msg = new MessagesView();
    Comanda comanda = new Comanda();
    Itens item = new Itens();
    Itenscomanda itscomanda = new Itenscomanda();
    double totalItens;
    int idc = 0, flag;

    @PostConstruct
    public void init() {
        listaFuncionarios = new FuncionarioJpaController(emf).findFuncionarioEntities();
        listaClientes = new ClienteJpaController(emf).findClienteEntities();
        listaItens = new ItensJpaController(emf).findItensEntities();
        somaItens = new ArrayList<>();
        idc = 0;
        lista = new ItenscomandaJpaController(emf).ItensPorComanda(idc);
        comanda = new Comanda();
        itscomanda = new Itenscomanda();
        Date hoje = new Date();
        comanda.setDtcomanda(hoje);
        flag = 0;

    }

    public void abrir() {

        log.getPegaDataHoraAtual();
        comanda.setDtreg(log.getData1());
        comanda.setHrreg(log.getHora1());
        if (comanda.getId() == null) {
            ComandaDao.getInstance().persist(comanda);
            msg.info("COMANDA ABERTA");
            idc = comanda.getId();
            itscomanda.setQnt(1);
            log = new Log();
        } else {
            try {
                ComandaDao.getInstance().merge(comanda);
                msg.info("COMANDA ALTERADA");
                itscomanda.setQnt(1);
                idc = comanda.getId();
                log = new Log();
            } catch (Exception ex) {
                msg.info("FALHA NA ALTERAÇÃO");
            }
        }
    }

    public void addItem() {
        log.getPegaDataHoraAtual();
        itscomanda.setDtreg(log.getData1());
        itscomanda.setHrreg(log.getHora1());

        if (comanda.getId() == null) {
            msg.error("A COMANDA NÃO FOI ABERTA");
        } else {
            itscomanda.setIdcomanda(comanda.getId());
            if (itscomanda.getIdproduto() == null) {
                msg.error("SELECIONE UM ITEM");
            } else {
                if (itscomanda.getId() == null) {
                    totalItens = itscomanda.getIdproduto().getPreco() * itscomanda.getQnt();
                    itscomanda.setTotalitem(totalItens);
                    new ItenscomandaJpaController(emf).create(itscomanda);
                    msg.info("ITEM ADICIONADO");
                    flag = 1;
                } else {
                    try {
                        totalItens = itscomanda.getIdproduto().getPreco() * itscomanda.getQnt();
                        itscomanda.setTotalitem(totalItens);
                        new ItenscomandaJpaController(emf).edit(itscomanda);
                        msg.info("REGISTTRO ALTERADO");
                    } catch (Exception ex) {
                        msg.info("FALHA NA ALTERAÇÃO");
                    }
                }
                lista = new ItenscomandaJpaController(emf).ItensPorComanda(idc);
                somaItens = new ItenscomandaJpaController(emf).SomaValores(idc);
                itscomanda = new Itenscomanda();
                itscomanda.setQnt(1);
            }
        }
    }

    public void salvar() {

        if (comanda.getId() == null) {
            msg.error("A COMANDA NÃO FOI ABERTA");
        }
        if (flag == 0) {
            msg.error("NÃO FORAM ADICIONADOS ITENS A COMANDA");
        } else {
            msg.info("COMANDA SALVA");
            init();
        }
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

    public List<Itenscomanda> getSomaItens() {
        return somaItens;
    }

    public void setSomaItens(List<Itenscomanda> somaItens) {
        this.somaItens = somaItens;
    }

}
