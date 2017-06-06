package br.com.vinibar.bean;

import br.com.vinibar.dao.ClienteJpaController;
import br.com.vinibar.dao.ComandaDao;
import br.com.vinibar.dao.ComandaJpaController;
import br.com.vinibar.dao.FuncionarioJpaController;
import br.com.vinibar.dao.ItensJpaController;
import br.com.vinibar.dao.ItenscomandaJpaController;
import br.com.vinibar.dao.exceptions.NonexistentEntityException;
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
public class BeanComanda {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Vini_BarPU");
    Log log = new Log();
    private List<Funcionario> listaFuncionarios = new ArrayList<>();
    private List<Cliente> listaClientes = new ArrayList<>();
    private List<Itens> listaItens = new ArrayList<>();
    private List<Itenscomanda> lista = new ArrayList<>();
    private List<Itenscomanda> somaItens;
    MessagesView msg = new MessagesView();
    Comanda comanda = new Comanda();
    Itenscomanda itenscomanda = new Itenscomanda();
    double totalItens;
    int idc = 0, flag, pegaIditem = 0; //idc: variável local para pegar a id da comanda

    @PostConstruct
    public void init() {
        listaFuncionarios = new FuncionarioJpaController(emf).findFuncionarioEntities();
        listaClientes = new ClienteJpaController(emf).findClienteEntities();
        listaItens = new ItensJpaController(emf).findItensEntities();
        somaItens = new ArrayList<>();
        idc = 0;
        lista = new ItenscomandaJpaController(emf).ItensPorComanda(idc);//JPQL com lista filtrada por id da comanda
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
            ComandaDao.getInstance().persist(comanda);
            msg.info("COMANDA ABERTA");
            idc = comanda.getIdcomanda(); //seta o id da comanda aberta na variável idc
            itenscomanda.setQnt(1); //Regra de negócio: O item adicionado por padrão é 1
        } else {
            try {
                ComandaDao.getInstance().merge(comanda);
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

        if (comanda.getIdcomanda() == null) { //verifica se a comanda foi aberta
            msg.error("A COMANDA NÃO FOI ABERTA");
        } else {
            itenscomanda.setIdcomanda(idc); // pega o valor do id da comada armazanado na variável local idc
            if (itenscomanda.getIdproduto() == null) { // verifica se um item foi selecionado
                msg.error("SELECIONE UM ITEM");
            } else {
                if (itenscomanda.getIditenscomanda() == null) {
                    totalItens = itenscomanda.getIdproduto().getPreco() * itenscomanda.getQnt();
                    itenscomanda.setTotalitem(totalItens);
                    new ItenscomandaJpaController(emf).create(itenscomanda);
                    msg.info("ITEM ADICIONADO");
                    flag = 1;// Controlar método salvar, impedir que salve a comanda caso não seja adicionados itens a ela
                } else {
                    try {
                        totalItens = itenscomanda.getIdproduto().getPreco() * itenscomanda.getQnt();
                        itenscomanda.setTotalitem(totalItens);
                        new ItenscomandaJpaController(emf).edit(itenscomanda);
                        msg.info("REGISTTRO ALTERADO");
                    } catch (Exception ex) {
                        msg.info("FALHA NA ALTERAÇÃO");
                    }
                }
                lista = new ItenscomandaJpaController(emf).ItensPorComanda(idc);
                somaItens = new ItenscomandaJpaController(emf).SomaValores(idc);
                itenscomanda = new Itenscomanda();
                itenscomanda.setQnt(1);
            }
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
            ComandaDao.getInstance().merge(comanda);
            msg.info("COMANDA SALVA");
            init();
        }
    }

    public void excluirItem() {

        if (itenscomanda.getIditenscomanda() != null) {
            try {
                new ItenscomandaJpaController(emf).destroy(itenscomanda.getIditenscomanda());
                msg.info("REGISTRO EXCLUÍDO");
                itenscomanda = new Itenscomanda();
                lista = new ItenscomandaJpaController(emf).ItensPorComanda(idc);
                somaItens = new ItenscomandaJpaController(emf).SomaValores(idc);
            } catch (NonexistentEntityException ex) {
                msg.error("FALHA NA EXCLUSÃO");
            }
        } else {
            msg.info("SELECIONE UM ITEM");
        }
    }

    public void excluir() {
        itenscomanda.setIdcomanda(idc);
        if (comanda.getIdcomanda() != null) {
            try {
                new ComandaJpaController(emf).destroy(comanda.getIdcomanda());
                msg.info("COMANDA CANCELADA");
            } catch (NonexistentEntityException ex) {
                msg.info("FALHA NA EXCLUSÃO");
            }
            itenscomanda.setIdcomanda(idc);
            if (idc != 0) {
                new ItenscomandaJpaController(emf).excluirPorIdComanda(idc);
                //msg.info("REGISTRO EXCLUÍDO");
            }
        }
        init();
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

}
