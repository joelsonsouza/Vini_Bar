/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vinibar.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author joels
 */
@Entity
@Table(catalog = "barbearia", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f")
    , @NamedQuery(name = "Funcionario.findById", query = "SELECT f FROM Funcionario f WHERE f.id = :id")
    , @NamedQuery(name = "Funcionario.findByNome", query = "SELECT f FROM Funcionario f WHERE f.nome = :nome")
    , @NamedQuery(name = "Funcionario.findByCpf", query = "SELECT f FROM Funcionario f WHERE f.cpf = :cpf")
    , @NamedQuery(name = "Funcionario.findByDtnascimento", query = "SELECT f FROM Funcionario f WHERE f.dtnascimento = :dtnascimento")
    , @NamedQuery(name = "Funcionario.findByCargo", query = "SELECT f FROM Funcionario f WHERE f.cargo = :cargo")
    , @NamedQuery(name = "Funcionario.findBySalario", query = "SELECT f FROM Funcionario f WHERE f.salario = :salario")
    , @NamedQuery(name = "Funcionario.findByTelefone", query = "SELECT f FROM Funcionario f WHERE f.telefone = :telefone")
    , @NamedQuery(name = "Funcionario.findByCelular", query = "SELECT f FROM Funcionario f WHERE f.celular = :celular")
    , @NamedQuery(name = "Funcionario.findByEmail", query = "SELECT f FROM Funcionario f WHERE f.email = :email")
    , @NamedQuery(name = "Funcionario.findByLogradouro", query = "SELECT f FROM Funcionario f WHERE f.logradouro = :logradouro")
    , @NamedQuery(name = "Funcionario.findByNumero", query = "SELECT f FROM Funcionario f WHERE f.numero = :numero")
    , @NamedQuery(name = "Funcionario.findByComplemento", query = "SELECT f FROM Funcionario f WHERE f.complemento = :complemento")
    , @NamedQuery(name = "Funcionario.findByBairro", query = "SELECT f FROM Funcionario f WHERE f.bairro = :bairro")
    , @NamedQuery(name = "Funcionario.findByCep", query = "SELECT f FROM Funcionario f WHERE f.cep = :cep")
    , @NamedQuery(name = "Funcionario.findByCidade", query = "SELECT f FROM Funcionario f WHERE f.cidade = :cidade")
    , @NamedQuery(name = "Funcionario.findByUf", query = "SELECT f FROM Funcionario f WHERE f.uf = :uf")
    , @NamedQuery(name = "Funcionario.findByAtivo", query = "SELECT f FROM Funcionario f WHERE f.ativo = :ativo")
    , @NamedQuery(name = "Funcionario.findByObservacoes", query = "SELECT f FROM Funcionario f WHERE f.observacoes = :observacoes")
    , @NamedQuery(name = "Funcionario.findByStatus", query = "SELECT f FROM Funcionario f WHERE f.status = :status")
    , @NamedQuery(name = "Funcionario.findByDtadmissao", query = "SELECT f FROM Funcionario f WHERE f.dtadmissao = :dtadmissao")
    , @NamedQuery(name = "Funcionario.findByDtdemissao", query = "SELECT f FROM Funcionario f WHERE f.dtdemissao = :dtdemissao")
    , @NamedQuery(name = "Funcionario.findByPis", query = "SELECT f FROM Funcionario f WHERE f.pis = :pis")
    , @NamedQuery(name = "Funcionario.findByBanco", query = "SELECT f FROM Funcionario f WHERE f.banco = :banco")
    , @NamedQuery(name = "Funcionario.findByAgencia", query = "SELECT f FROM Funcionario f WHERE f.agencia = :agencia")
    , @NamedQuery(name = "Funcionario.findByConta", query = "SELECT f FROM Funcionario f WHERE f.conta = :conta")
    , @NamedQuery(name = "Funcionario.findByTipocontrato", query = "SELECT f FROM Funcionario f WHERE f.tipocontrato = :tipocontrato")
    , @NamedQuery(name = "Funcionario.findByDtreg", query = "SELECT f FROM Funcionario f WHERE f.dtreg = :dtreg")
    , @NamedQuery(name = "Funcionario.findByHrreg", query = "SELECT f FROM Funcionario f WHERE f.hrreg = :hrreg")})
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 2147483647)
    private String nome;
    @Column(length = 15)
    private String cpf;
    @Temporal(TemporalType.DATE)
    private Date dtnascimento;
    @Column(length = 25)
    private String cargo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 17, scale = 17)
    private Double salario;
    @Column(length = 15)
    private String telefone;
    @Column(length = 15)
    private String celular;
    @Column(length = 30)
    private String email;
    @Column(length = 30)
    private String logradouro;
    private Integer numero;
    @Column(length = 30)
    private String complemento;
    @Column(length = 25)
    private String bairro;
    @Column(length = 10)
    private String cep;
    @Column(length = 25)
    private String cidade;
    @Column(length = 2)
    private String uf;
    private Boolean ativo;
    @Column(length = 100)
    private String observacoes;
    @Column(length = 15)
    private String status;
    @Temporal(TemporalType.DATE)
    private Date dtadmissao;
    @Temporal(TemporalType.DATE)
    private Date dtdemissao;
    @Column(length = 20)
    private String pis;
    @Column(length = 20)
    private String banco;
    @Column(length = 5)
    private String agencia;
    @Column(length = 15)
    private String conta;
    @Column(length = 15)
    private String tipocontrato;
    @Column(length = 15)
    private String dtreg;
    @Column(length = 15)
    private String hrreg;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idfuncionariocaixa")
    private List<Caixa> caixaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idfuncionario")
    private List<Comanda> comandaList;

    public Funcionario() {
    }

    public Funcionario(Integer id) {
        this.id = id;
    }

    public Funcionario(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDtnascimento() {
        return dtnascimento;
    }

    public void setDtnascimento(Date dtnascimento) {
        this.dtnascimento = dtnascimento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDtadmissao() {
        return dtadmissao;
    }

    public void setDtadmissao(Date dtadmissao) {
        this.dtadmissao = dtadmissao;
    }

    public Date getDtdemissao() {
        return dtdemissao;
    }

    public void setDtdemissao(Date dtdemissao) {
        this.dtdemissao = dtdemissao;
    }

    public String getPis() {
        return pis;
    }

    public void setPis(String pis) {
        this.pis = pis;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getTipocontrato() {
        return tipocontrato;
    }

    public void setTipocontrato(String tipocontrato) {
        this.tipocontrato = tipocontrato;
    }

    public String getDtreg() {
        return dtreg;
    }

    public void setDtreg(String dtreg) {
        this.dtreg = dtreg;
    }

    public String getHrreg() {
        return hrreg;
    }

    public void setHrreg(String hrreg) {
        this.hrreg = hrreg;
    }

    @XmlTransient
    public List<Caixa> getCaixaList() {
        return caixaList;
    }

    public void setCaixaList(List<Caixa> caixaList) {
        this.caixaList = caixaList;
    }

    @XmlTransient
    public List<Comanda> getComandaList() {
        return comandaList;
    }

    public void setComandaList(List<Comanda> comandaList) {
        this.comandaList = comandaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionario)) {
            return false;
        }
        Funcionario other = (Funcionario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.vinibar.model.Funcionario[ id=" + id + " ]";
    }
    
}
