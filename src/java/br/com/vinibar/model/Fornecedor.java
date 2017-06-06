/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vinibar.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    @NamedQuery(name = "Fornecedor.findAll", query = "SELECT f FROM Fornecedor f")
    , @NamedQuery(name = "Fornecedor.findById", query = "SELECT f FROM Fornecedor f WHERE f.id = :id")
    , @NamedQuery(name = "Fornecedor.findByEmpresa", query = "SELECT f FROM Fornecedor f WHERE f.empresa = :empresa")
    , @NamedQuery(name = "Fornecedor.findByCnpj", query = "SELECT f FROM Fornecedor f WHERE f.cnpj = :cnpj")
    , @NamedQuery(name = "Fornecedor.findByReponsavel", query = "SELECT f FROM Fornecedor f WHERE f.reponsavel = :reponsavel")
    , @NamedQuery(name = "Fornecedor.findByTelefone", query = "SELECT f FROM Fornecedor f WHERE f.telefone = :telefone")
    , @NamedQuery(name = "Fornecedor.findByCelular", query = "SELECT f FROM Fornecedor f WHERE f.celular = :celular")
    , @NamedQuery(name = "Fornecedor.findByEmail", query = "SELECT f FROM Fornecedor f WHERE f.email = :email")
    , @NamedQuery(name = "Fornecedor.findByLogradouro", query = "SELECT f FROM Fornecedor f WHERE f.logradouro = :logradouro")
    , @NamedQuery(name = "Fornecedor.findByNumero", query = "SELECT f FROM Fornecedor f WHERE f.numero = :numero")
    , @NamedQuery(name = "Fornecedor.findByComplemeto", query = "SELECT f FROM Fornecedor f WHERE f.complemeto = :complemeto")
    , @NamedQuery(name = "Fornecedor.findByBairro", query = "SELECT f FROM Fornecedor f WHERE f.bairro = :bairro")
    , @NamedQuery(name = "Fornecedor.findByCep", query = "SELECT f FROM Fornecedor f WHERE f.cep = :cep")
    , @NamedQuery(name = "Fornecedor.findByCidade", query = "SELECT f FROM Fornecedor f WHERE f.cidade = :cidade")
    , @NamedQuery(name = "Fornecedor.findByUf", query = "SELECT f FROM Fornecedor f WHERE f.uf = :uf")
    , @NamedQuery(name = "Fornecedor.findByAtivo", query = "SELECT f FROM Fornecedor f WHERE f.ativo = :ativo")
    , @NamedQuery(name = "Fornecedor.findByDtreg", query = "SELECT f FROM Fornecedor f WHERE f.dtreg = :dtreg")
    , @NamedQuery(name = "Fornecedor.findByHrreg", query = "SELECT f FROM Fornecedor f WHERE f.hrreg = :hrreg")})
public class Fornecedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Column(length = 50)
    private String empresa;
    @Column(length = 2147483647)
    private String cnpj;
    @Column(length = 50)
    private String reponsavel;
    @Column(length = 15)
    private String telefone;
    @Column(length = 15)
    private String celular;
    @Column(length = 50)
    private String email;
    @Column(length = 50)
    private String logradouro;
    private Integer numero;
    @Column(length = 30)
    private String complemeto;
    @Column(length = 30)
    private String bairro;
    @Column(length = 10)
    private String cep;
    @Column(length = 30)
    private String cidade;
    @Column(length = 2)
    private String uf;
    private Boolean ativo;
    @Column(length = 15)
    private String dtreg;
    @Column(length = 10)
    private String hrreg;
    @OneToMany(mappedBy = "idfornecedor")
    private List<Despesas> despesasList;

    public Fornecedor() {
    }

    public Fornecedor(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getReponsavel() {
        return reponsavel;
    }

    public void setReponsavel(String reponsavel) {
        this.reponsavel = reponsavel;
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

    public String getComplemeto() {
        return complemeto;
    }

    public void setComplemeto(String complemeto) {
        this.complemeto = complemeto;
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
    public List<Despesas> getDespesasList() {
        return despesasList;
    }

    public void setDespesasList(List<Despesas> despesasList) {
        this.despesasList = despesasList;
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
        if (!(object instanceof Fornecedor)) {
            return false;
        }
        Fornecedor other = (Fornecedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.vinibar.model.Fornecedor[ id=" + id + " ]";
    }
    
}
