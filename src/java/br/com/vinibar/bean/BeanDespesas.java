package br.com.vinibar.bean;

import br.com.vinibar.dao.DespesasJpaController;
import br.com.vinibar.dao.FornecedorJpaController;
import br.com.vinibar.dao.TipodespesaJpaController;
import br.com.vinibar.dao.exceptions.NonexistentEntityException;
import br.com.vinibar.model.Despesas;
import br.com.vinibar.model.Fornecedor;
import br.com.vinibar.model.Tipodespesa;
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
public class BeanDespesas {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Vini_BarPU");
    Despesas despesa = new Despesas();
    Log log = new Log();
    private List<Tipodespesa> listaTipo = new ArrayList<>();
    private List<Fornecedor> listaFornecedor = new ArrayList<>();
    private List<Despesas> listaDespesas = new ArrayList<>();
    MessagesView msg = new MessagesView();

    @PostConstruct
    public void init() {
        listaTipo = new TipodespesaJpaController(emf).findTipodespesaEntities();
        listaFornecedor = new FornecedorJpaController(emf).findFornecedorEntities();
        listaDespesas = new DespesasJpaController(emf).findDespesasEntities();
        despesa = new Despesas();

    }

    public void incluir() {
        log.getPegaDataHoraAtual();
        despesa.setDtreg(log.getData1());
        despesa.setHrreg(log.getHora1());

        if (despesa.getId() == null) {
            new DespesasJpaController(emf).create(despesa);
            msg.info("REGISTRO SALVO");
            listaDespesas = new DespesasJpaController(emf).findDespesasEntities();
        } else {
            try {
                new DespesasJpaController(emf).edit(despesa);
                msg.info("REGISTTRO ALTERADO");
                listaDespesas = new DespesasJpaController(emf).findDespesasEntities();
            } catch (Exception ex) {
                msg.info("FALHA NA ALTERAÇÃO");
            }
        }
        despesa = new Despesas();
    }

    public void excluir() {

        if (despesa.getId() != null) {
            try {
                new DespesasJpaController(emf).destroy(despesa.getId());
                msg.info("REGISTRO EXCLUÍDO");
                despesa = new Despesas();
                listaDespesas = new DespesasJpaController(emf).findDespesasEntities();
            } catch (NonexistentEntityException ex) {
                msg.info("FALHA NA EXCLUSÃO");
            }
        } else {
            msg.info("SELECIONE UM REGISTRO");
        }
    }


    /* -----------GETTER E SETTERS-----------*/
    public List<Tipodespesa> getListaTipo() {
        return listaTipo;
    }

    public void setListaTipo(List<Tipodespesa> listaTipo) {
        this.listaTipo = listaTipo;
    }

    public List<Fornecedor> getListaFornecedor() {
        return listaFornecedor;
    }

    public void setListaFornecedor(List<Fornecedor> listaFornecedor) {
        this.listaFornecedor = listaFornecedor;

    }

    public List<Despesas> getListaDespesas() {
        return listaDespesas;
    }

    public void setListaDespesas(List<Despesas> listaDespesas) {
        this.listaDespesas = listaDespesas;
    }

    public Despesas getDespesa() {
        return despesa;
    }

    public void setDespesa(Despesas despesa) {
        this.despesa = despesa;
    }

}
