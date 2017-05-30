/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vinibar.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author joels
 */
@Entity
@Table(catalog = "barbearia", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Despesas.findAll", query = "SELECT d FROM Despesas d")
    , @NamedQuery(name = "Despesas.findById", query = "SELECT d FROM Despesas d WHERE d.id = :id")
    , @NamedQuery(name = "Despesas.findByDescricao", query = "SELECT d FROM Despesas d WHERE d.descricao = :descricao")
    , @NamedQuery(name = "Despesas.findByDtdespesa", query = "SELECT d FROM Despesas d WHERE d.dtdespesa = :dtdespesa")
    , @NamedQuery(name = "Despesas.findByValor", query = "SELECT d FROM Despesas d WHERE d.valor = :valor")
    , @NamedQuery(name = "Despesas.findByDtreg", query = "SELECT d FROM Despesas d WHERE d.dtreg = :dtreg")
    , @NamedQuery(name = "Despesas.findByHrreg", query = "SELECT d FROM Despesas d WHERE d.hrreg = :hrreg")
    , @NamedQuery(name = "Despesas.findByPago", query = "SELECT d FROM Despesas d WHERE d.pago = :pago")})
public class Despesas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 75)
    private String descricao;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dtdespesa;
    @Basic(optional = false)
    @Column(nullable = false)
    private double valor;
    @Column(length = 15)
    private String dtreg;
    @Column(length = 15)
    private String hrreg;
    private Boolean pago;
    @JoinColumn(name = "idfornecedor", referencedColumnName = "id")
    @ManyToOne
    private Fornecedor idfornecedor;
    @JoinColumn(name = "idtipo", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Tipodespesa idtipo;

    public Despesas() {
    }

    public Despesas(Integer id) {
        this.id = id;
    }

    public Despesas(Integer id, String descricao, Date dtdespesa, double valor) {
        this.id = id;
        this.descricao = descricao;
        this.dtdespesa = dtdespesa;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDtdespesa() {
        return dtdespesa;
    }

    public void setDtdespesa(Date dtdespesa) {
        this.dtdespesa = dtdespesa;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    public Fornecedor getIdfornecedor() {
        return idfornecedor;
    }

    public void setIdfornecedor(Fornecedor idfornecedor) {
        this.idfornecedor = idfornecedor;
    }

    public Tipodespesa getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(Tipodespesa idtipo) {
        this.idtipo = idtipo;
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
        if (!(object instanceof Despesas)) {
            return false;
        }
        Despesas other = (Despesas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.vinibar.model.Despesas[ id=" + id + " ]";
    }
    
}
