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
    @NamedQuery(name = "Itenscomanda.findAll", query = "SELECT i FROM Itenscomanda i")
    , @NamedQuery(name = "Itenscomanda.findById", query = "SELECT i FROM Itenscomanda i WHERE i.id = :id")
    , @NamedQuery(name = "Itenscomanda.findByQnt", query = "SELECT i FROM Itenscomanda i WHERE i.qnt = :qnt")
    , @NamedQuery(name = "Itenscomanda.findByTotalitem", query = "SELECT i FROM Itenscomanda i WHERE i.totalitem = :totalitem")
    , @NamedQuery(name = "Itenscomanda.findByDtreg", query = "SELECT i FROM Itenscomanda i WHERE i.dtreg = :dtreg")
    , @NamedQuery(name = "Itenscomanda.findByHrreg", query = "SELECT i FROM Itenscomanda i WHERE i.hrreg = :hrreg")})
public class Itenscomanda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false)
    private int qnt;
    @Basic(optional = false)
    @Column(nullable = false)
    private double totalitem;
    @Column(length = 15)
    private String dtreg;
    @Column(length = 15)
    private String hrreg;
    @JoinColumn(name = "idcomanda", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Comanda idcomanda;
    @JoinColumn(name = "idproduto", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Itens idproduto;

    public Itenscomanda() {

        super();
    
    }

    public Itenscomanda(Integer id) {
        this.id = id;
    }

    public Itenscomanda(Integer id, int qnt, double totalitem) {
        this.id = id;
        this.qnt = qnt;
        this.totalitem = totalitem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQnt() {
        return qnt;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
    }

    public double getTotalitem() {
        return totalitem;
    }

    public void setTotalitem(double totalitem) {
        this.totalitem = totalitem;
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

    public Itens getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(Itens idproduto) {
        this.idproduto = idproduto;
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
        if (!(object instanceof Itenscomanda)) {
            return false;
        }
        Itenscomanda other = (Itenscomanda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.vinibar.model.Itenscomanda[ id=" + id + " ]";
    }

    public void setIdcomanda(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
