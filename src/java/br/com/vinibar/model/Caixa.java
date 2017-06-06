/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vinibar.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author joels
 */
@Entity
@Table(catalog = "barbearia", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Caixa.findAll", query = "SELECT c FROM Caixa c")
    , @NamedQuery(name = "Caixa.findByIdcaixa", query = "SELECT c FROM Caixa c WHERE c.idcaixa = :idcaixa")
    , @NamedQuery(name = "Caixa.findByOutrosdescontos", query = "SELECT c FROM Caixa c WHERE c.outrosdescontos = :outrosdescontos")
    , @NamedQuery(name = "Caixa.findByFormapagamento", query = "SELECT c FROM Caixa c WHERE c.formapagamento = :formapagamento")
    , @NamedQuery(name = "Caixa.findByObservacoes", query = "SELECT c FROM Caixa c WHERE c.observacoes = :observacoes")
    , @NamedQuery(name = "Caixa.findByDtreg", query = "SELECT c FROM Caixa c WHERE c.dtreg = :dtreg")
    
//    , @NamedQuery(name = "Caixa.ComandaSalva.ComandaAberta", query = "SELECT ca FROM Caixa ca INNER JOIN ca.idcomanda co WHERE ca.status='ABERTA' and co.salva=true")
    , @NamedQuery(name = "Caixa.findByHrreg", query = "SELECT c FROM Caixa c WHERE c.hrreg = :hrreg")})
public class Caixa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idcaixa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 17, scale = 17)
    private Double outrosdescontos;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String formapagamento;
    @Column(length = 150)
    private String observacoes;
    @Basic(optional = false)
    @Column(nullable = false, length = 15)
    private String dtreg;
    @Basic(optional = false)
    @Column(nullable = false, length = 15)
    private String hrreg;
    @JoinColumn(name = "idcomanda", referencedColumnName = "idcomanda", nullable = false)
    @ManyToOne(optional = false)
    private Comanda idcomanda;
    @JoinColumn(name = "idfuncionariocaixa", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Funcionario idfuncionariocaixa;

    public Caixa() {
    }

    public Caixa(Integer idcaixa) {
        this.idcaixa = idcaixa;
    }

    public Caixa(Integer idcaixa, String formapagamento, String dtreg, String hrreg) {
        this.idcaixa = idcaixa;
        this.formapagamento = formapagamento;
        
        this.dtreg = dtreg;
        this.hrreg = hrreg;
    }

    public Integer getIdcaixa() {
        return idcaixa;
    }

    public void setIdcaixa(Integer idcaixa) {
        this.idcaixa = idcaixa;
    }

    public Double getOutrosdescontos() {
        return outrosdescontos;
    }

    public void setOutrosdescontos(Double outrosdescontos) {
        this.outrosdescontos = outrosdescontos;
    }

    public String getFormapagamento() {
        return formapagamento;
    }

    public void setFormapagamento(String formapagamento) {
        this.formapagamento = formapagamento;
    }

    

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
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

    public Comanda getIdcomanda() {
        return idcomanda;
    }

    public void setIdcomanda(Comanda idcomanda) {
        this.idcomanda = idcomanda;
    }

    public Funcionario getIdfuncionariocaixa() {
        return idfuncionariocaixa;
    }

    public void setIdfuncionariocaixa(Funcionario idfuncionariocaixa) {
        this.idfuncionariocaixa = idfuncionariocaixa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcaixa != null ? idcaixa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caixa)) {
            return false;
        }
        Caixa other = (Caixa) object;
        if ((this.idcaixa == null && other.idcaixa != null) || (this.idcaixa != null && !this.idcaixa.equals(other.idcaixa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.vinibar.model.Caixa[ idcaixa=" + idcaixa + " ]";
    }
    
}
