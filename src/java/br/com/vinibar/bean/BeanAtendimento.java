package br.com.vinibar.bean;

import br.com.vinibar.dao.ClienteJpaController;
import br.com.vinibar.dao.DespesasJpaController;
import br.com.vinibar.dao.FornecedorJpaController;
import br.com.vinibar.dao.FuncionarioJpaController;
import br.com.vinibar.dao.ItensJpaController;
import br.com.vinibar.dao.TipodespesaJpaController;
import br.com.vinibar.dao.exceptions.NonexistentEntityException;
import br.com.vinibar.model.Cliente;
import br.com.vinibar.model.Despesas;
import br.com.vinibar.model.Fornecedor;
import br.com.vinibar.model.Funcionario;
import br.com.vinibar.model.Itens;
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
public class BeanAtendimento {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Vini_BarPU"); 
    Log log = new Log();
    private List<Funcionario> listaFuncionarios = new ArrayList<>();
    private List<Cliente> listaClientes = new ArrayList<>();
    private List<Itens> listaItens = new ArrayList<>();
    MessagesView msg = new MessagesView();

    @PostConstruct
    public void init() {
        listaFuncionarios = new FuncionarioJpaController(emf).findFuncionarioEntities();
        listaClientes = new ClienteJpaController(emf).findClienteEntities();
        listaItens = new ItensJpaController(emf).findItensEntities();
        

    }

    public void incluir() {
        log.getPegaDataHoraAtual();
        
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
    
    
    

}
