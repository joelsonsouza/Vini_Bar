package br.com.vinibar.bean;

import br.com.vinibar.dao.TipoDespesaDao;
import br.com.vinibar.dao.TipodespesaJpaController;
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
public class TipoDespesaBean {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Vini_BarPU");
    Tipodespesa tipodespesa = new Tipodespesa();
    Tipodespesa edtipodespesa = new Tipodespesa();
    Log log = new Log();
    private List<Tipodespesa> lista = new ArrayList<>();
    MessagesView msg = new MessagesView();

    @PostConstruct
    public void init() {
        lista = new TipodespesaJpaController(emf).findTipodespesaEntities();
        tipodespesa = new Tipodespesa();
        edtipodespesa = new Tipodespesa();

    }

    public void incluir() {
        log.getPegaDataHoraAtual();
        tipodespesa.setDtreg(log.getData1());
        tipodespesa.setHrreg(log.getHora1());

        if (tipodespesa.getTipo() == "" || tipodespesa.getPeriodo() == "") {
            msg.error("PRENCHA OS CAMPOS OBRIGATÓRIOS");
        } else {
            new TipodespesaJpaController(emf).create(tipodespesa);
            msg.info("SALVO COM SUCESSO");
            tipodespesa = new Tipodespesa();
            lista = new TipodespesaJpaController(emf).findTipodespesaEntities();
        }
    }

    public void editar() {
        log.getPegaDataHoraAtual();
        edtipodespesa.setDtreg(log.getData1());
        edtipodespesa.setHrreg(log.getHora1());

        if (edtipodespesa.getId() != null) {
            if (edtipodespesa.getTipo() == "" || edtipodespesa.getPeriodo() == "") {
                lista = new TipodespesaJpaController(emf).findTipodespesaEntities();
                msg.error("REGISTRO NÃO ALTERADO");
                msg.warn("CAMPOS OBRIGATÓRIOS NÃO PREENCHIDOS");
            } else {
                try {
                    new TipodespesaJpaController(emf).edit(edtipodespesa);
                    msg.info("REGISTRO ALTERADO");
                    edtipodespesa = new Tipodespesa();
                } catch (Exception ex) {
                    msg.error("ERRO AO EDITAR");
                }
            }         
        }
    }

    public void excluir() {

        if (tipodespesa.getId() != null) {
            TipoDespesaDao.getInstance().remove(tipodespesa);
            msg.info("REGISTRO EXCLUÍDO");
            tipodespesa = new Tipodespesa();
            lista = new TipodespesaJpaController(emf).findTipodespesaEntities();
        } else {
            msg.info("FALHA NA EXCLUSÃO");
        }
    }
    
    public void preencherDialog(){
        edtipodespesa.setPeriodo(tipodespesa.getPeriodo());
        edtipodespesa.setTipo(tipodespesa.getTipo());
    }

    /* -----------GETTER E SETTERS-----------*/
    public List<Tipodespesa> getLista() {
        return lista;
    }

    public void setLista(List<Tipodespesa> lista) {
        this.lista = lista;
    }

    public Tipodespesa getTipodespesa() {
        return tipodespesa;
    }

    public void setTipodespesa(Tipodespesa tipodespesa) {
        this.tipodespesa = tipodespesa;
    }

    public Tipodespesa getEdtipodespesa() {
        return edtipodespesa;
    }

    public void setEdtipodespesa(Tipodespesa edtipodespesa) {
        this.edtipodespesa = edtipodespesa;
    }

}
