package br.com.vinibar.bean;

import br.com.vinibar.dao.DaoCaixa;
import br.com.vinibar.dao.DaoComanda;
import br.com.vinibar.dao.DaoFuncionario;
import br.com.vinibar.dao.DaoItens;
import br.com.vinibar.dao.DaoItenscomanda;
import br.com.vinibar.model.Caixa;
import br.com.vinibar.model.Comanda;
import br.com.vinibar.model.Funcionario;
import br.com.vinibar.model.Itens;
import br.com.vinibar.model.Itenscomanda;
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
public class BeanCaixa {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Vini_BarPU");
    Log log = new Log();
    Caixa caixa = new Caixa();
    private List<Funcionario> listaFuncionarios = new ArrayList<>();
    //private List<Comanda> listaComandas = new ArrayList<>();
    private List<Comanda> listaComandas = new ArrayList<>();
    Boolean filtroComanda = true;
    String aberta = "ABERTA";
    private List<Itens> listaItens = new ArrayList<>();
    String desconto = "PROMOÇÃO";
    private List<Itenscomanda> lista = new ArrayList<>();
    private List<Itenscomanda> somaItens;
    MessagesView msg = new MessagesView();
    Comanda comanda = new Comanda();
    Itenscomanda itenscomanda = new Itenscomanda();
    double totalItens;
    int idc = 0, pegaIditem = 0; //idc: variável local para pegar a id da comanda. 
    String status;

    @PostConstruct
    public void init() {
        listaFuncionarios = new DaoFuncionario().ListaFuncionarios();
        
        listaComandas = new DaoComanda().ComandasAbertas();
        listaItens = new DaoItens().ListaDesconto(desconto);
        somaItens = new ArrayList<>();
        idc = 0;
        lista = new DaoItenscomanda().ItensPorComanda(idc);//JPQL com lista filtrada por id da comanda
        caixa = new Caixa();
        itenscomanda = new Itenscomanda();
        status=("ABERTA");
    }

    public void carregaComanda() {
        idc = caixa.getIdcomanda().getIdcomanda();
        lista = new DaoItenscomanda().ItensPorComanda(idc);//JPQL com lista filtrada por id da comanda
        somaItens = new DaoItenscomanda().SomaValores(idc);

    }

    public void addItem() {
        log.getPegaDataHoraAtual();
        itenscomanda.setDtreg(log.getData1());
        itenscomanda.setHrreg(log.getHora1());

        itenscomanda.setIdcomanda(idc); // pega o valor do id da comada armazanado na variável local idc
        if (itenscomanda.getIdproduto() == null ) { // verifica se um item foi selecionado no combo
            msg.error("SELECIONE UM ITEM");
        } else {
            if (itenscomanda.getIditenscomanda() == null) { //verifica seleção de item via tabela
                itenscomanda.setQnt(1);
                itenscomanda.setTotalitem(itenscomanda.getIdproduto().getPreco());
                new DaoItenscomanda().persist(itenscomanda);
                msg.info("DESCONTO ADICIONADO");
            } else {
                try {
                    itenscomanda.setQnt(1);
                    itenscomanda.setTotalitem(itenscomanda.getIdproduto().getPreco());
                    new DaoItenscomanda().merge(itenscomanda);
                    msg.info("REGISTTRO ALTERADO");
                } catch (Exception ex) {
                    msg.info("FALHA NA ALTERAÇÃO");
                }
            }
            lista = new DaoItenscomanda().ItensPorComanda(idc);
            somaItens = new DaoItenscomanda().SomaValores(idc);
            itenscomanda = new Itenscomanda();
        }

    }
        public void excluirItem() {

        if (itenscomanda.getIditenscomanda() != null) {
                new DaoItenscomanda().DeleteIdItemcomanda(itenscomanda.getIditenscomanda());
                msg.info("REGISTRO EXCLUÍDO");
                itenscomanda = new Itenscomanda();
                lista = new DaoItenscomanda().ItensPorComanda(idc);
                somaItens = new DaoItenscomanda().SomaValores(idc);
            } else {
                msg.error("FALHA NA EXCLUSÃO");
            }
    }
    

    public void salvar() {
        log.getPegaDataHoraAtual();
        caixa.setDtreg(log.getData1());
        caixa.setHrreg(log.getHora1());
        idc = caixa.getIdcomanda().getIdcomanda();
        if ("SELECIONE".equals(caixa.getFormapagamento())) {
            msg.error("SELECIONE UMA FORMA DE PAGAMENTO");
        } else {
            caixa.getIdcomanda().getIdcomanda();
            new DaoCaixa().persist(caixa);
            new DaoComanda().Update(status,caixa.getIdcomanda().getIdcomanda());
            msg.info("COMANDA PAGA");
            init();
        }
    }



    /* -----------GETTER E SETTERS-----------*/
    public List<Funcionario> getListaFuncionarios() {
        return listaFuncionarios;
    }

    public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
        this.listaFuncionarios = listaFuncionarios;
    }

    public Caixa getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }

    public List<Comanda> getListaComandas() {
        return listaComandas;
    }

    public void setListaComandas(List<Comanda> listaComandas) {
        this.listaComandas = listaComandas;
    }

    public List<Itens> getListaItens() {
        return listaItens;
    }

    public void setListaItens(List<Itens> listaItens) {
        this.listaItens = listaItens;
    }

    public List<Itenscomanda> getLista() {
        return lista;
    }

    public void setLista(List<Itenscomanda> lista) {
        this.lista = lista;
    }

    public List<Itenscomanda> getSomaItens() {
        return somaItens;
    }

    public void setSomaItens(List<Itenscomanda> somaItens) {
        this.somaItens = somaItens;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public Itenscomanda getItenscomanda() {
        return itenscomanda;
    }

    public void setItenscomanda(Itenscomanda itenscomanda) {
        this.itenscomanda = itenscomanda;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    

}
