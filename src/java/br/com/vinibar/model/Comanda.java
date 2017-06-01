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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Comanda.findAll", query = "SELECT c FROM Comanda c")
    , @NamedQuery(name = "Comanda.findById", query = "SELECT c FROM Comanda c WHERE c.id = :id")
    , @NamedQuery(name = "Comanda.findByDtcomanda", query = "SELECT c FROM Comanda c WHERE c.dtcomanda = :dtcomanda")
    , @NamedQuery(name = "Comanda.findByQnt", query = "SELECT c FROM Comanda c WHERE c.qnt = :qnt")
    , @NamedQuery(name = "Comanda.findByDesconto", query = "SELECT c FROM Comanda c WHERE c.desconto = :desconto")
    , @NamedQuery(name = "Comanda.findByTotal", query = "SELECT c FROM Comanda c WHERE c.total = :total")
    , @NamedQuery(name = "Comanda.findByDtreg", query = "SELECT c FROM Comanda c WHERE c.dtreg = :dtreg")
    , @NamedQuery(name = "Comanda.findByHrreg", query = "SELECT c FROM Comanda c WHERE c.hrreg = :hrreg")})
public class Comanda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dtcomanda;
    @Basic(optional = false)
    @Column(nullable = false)
    private int qnt;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 17, scale = 17)
    private Double desconto;
    @Column(precision = 17, scale = 17)
    private Double total;
    @Basic(optional = false)
    @Column(nullable = false, length = 15)
    private String dtreg;
    @Basic(optional = false)
    @Column(nullable = false, length = 15)
    private String hrreg;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcomanda")
    private List<Itenscomanda> itenscomandaList;
    @JoinColumn(name = "idcliente", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Cliente idcliente;
    @JoinColumn(name = "idfuncionario", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Funcionario idfuncionario;
    @JoinColumn(name = "iditem", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Itens iditem;

    public Comanda() {
    }

    public Comanda(Integer id) {
        this.id = id;
    }

    public Comanda(Integer id, Date dtcomanda, int qnt, String dtreg, String hrreg) {
        this.id = id;
        this.dtcomanda = dtcomanda;
        this.qnt = qnt;
        this.dtreg = dtreg;
        this.hrreg = hrreg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDtcomanda() {
        return dtcomanda;
    }

    public void setDtcomanda(Date dtcomanda) {
        this.dtcomanda = dtcomanda;
    }

    public int getQnt() {
        return qnt;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
    }

    public Double getDesc() {
        return desconto;
    }

    public void setDesc(Double desc) {
        this.desconto = desc;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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
    public List<Itenscomanda> getItenscomandaList() {
        return itenscomandaList;
    }

    public void setItenscomandaList(List<Itenscomanda> itenscomandaList) {
        this.itenscomandaList = itenscomandaList;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    public Funcionario getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(Funcionario idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public Itens getIditem() {
        return iditem;
    }

    public void setIditem(Itens iditem) {
        this.iditem = iditem;
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
        if (!(object instanceof Comanda)) {
            return false;
        }
        Comanda other = (Comanda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.vinibar.model.Comanda[ id=" + id + " ]";
    }
    
}
