package br.com.vinibar.bean;

import br.com.vinibar.dao.CaixaJpaController;
import br.com.vinibar.dao.ComandaJpaController;
import br.com.vinibar.dao.FuncionarioJpaController;
import br.com.vinibar.dao.ItensJpaController;
import br.com.vinibar.dao.ItenscomandaJpaController;
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
        listaFuncionarios = new FuncionarioJpaController(emf).findFuncionarioEntities();
        
        listaComandas = new ComandaJpaController(emf).ComandasAbertas();
        //listaComandas = new ComandaJpaController(emf).ComandasAbertas(filtroComanda);
        listaItens = new ItensJpaController(emf).ListaDesconto(desconto);
        somaItens = new ArrayList<>();
        idc = 0;
        lista = new ItenscomandaJpaController(emf).ItensPorComanda(idc);//JPQL com lista filtrada por id da comanda
        caixa = new Caixa();
        itenscomanda = new Itenscomanda();
        status=("ABERTA");
    }

    public void carregaComanda() {
        idc = caixa.getIdcomanda().getIdcomanda();
        lista = new ItenscomandaJpaController(emf).ItensPorComanda(idc);//JPQL com lista filtrada por id da comanda
        somaItens = new ItenscomandaJpaController(emf).SomaValores(idc);

    }

    public void addItem() {
        log.getPegaDataHoraAtual();
        itenscomanda.setDtreg(log.getData1());
        itenscomanda.setHrreg(log.getHora1());

        itenscomanda.setIdcomanda(idc); // pega o valor do id da comada armazanado na variável local idc
        if (itenscomanda.getIdproduto() == null) { // verifica se um item foi selecionado no combo
            msg.error("SELECIONE UM ITEM");
        } else {
            if (itenscomanda.getIditenscomanda() == null) { //verifica seleção de item via tabela
                itenscomanda.setQnt(1);
                itenscomanda.setTotalitem(itenscomanda.getIdproduto().getPreco());
                new ItenscomandaJpaController(emf).create(itenscomanda);
                msg.info("DESCONTO ADICIONADO");
            } else {
                try {
                    itenscomanda.setQnt(1);
                    itenscomanda.setTotalitem(itenscomanda.getIdproduto().getPreco());
                    new ItenscomandaJpaController(emf).edit(itenscomanda);
                    msg.info("REGISTTRO ALTERADO");
                } catch (Exception ex) {
                    msg.info("FALHA NA ALTERAÇÃO");
                }
            }
            lista = new ItenscomandaJpaController(emf).ItensPorComanda(idc);
            somaItens = new ItenscomandaJpaController(emf).SomaValores(idc);
            itenscomanda = new Itenscomanda();
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
            new CaixaJpaController(emf).create(caixa);
            new ComandaJpaController(emf).Update(status,caixa.getIdcomanda().getIdcomanda());
            msg.info("COMANDA PAGA");
            init();
        }
    }

//
//    public void excluirItem() {
//
//        if (itenscomanda.getIditenscomanda() != null) {
//            try {
//                new ItenscomandaJpaController(emf).destroy(itenscomanda.getIditenscomanda());
//                msg.info("REGISTRO EXCLUÍDO");
//                itenscomanda = new Itenscomanda();
//                lista = new ItenscomandaJpaController(emf).ItensPorComanda(idc);
//                somaItens = new ItenscomandaJpaController(emf).SomaValores(idc);
//            } catch (NonexistentEntityException ex) {
//                msg.error("FALHA NA EXCLUSÃO");
//            }
//        } else {
//            msg.info("SELECIONE UM ITEM");
//        }
//    }
//
//    public void excluir() {
//        itenscomanda.setIdcomanda(idc);
//        if (caixa.getIdcomanda() != null) {
//            try {
//                new ComandaJpaController(emf).destroy(caixa.getIdcomanda());
//                msg.info("COMANDA CANCELADA");
//            } catch (NonexistentEntityException ex) {
//                msg.info("FALHA NA EXCLUSÃO");
//            }
//            itenscomanda.setIdcomanda(idc);
//            if (idc != 0) {
//                new ItenscomandaJpaController(emf).excluirPorIdComanda(idc);
//                //msg.info("REGISTRO EXCLUÍDO");
//            }
//        }
//        init();
//    }

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
