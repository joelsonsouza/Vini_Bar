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
    @NamedQuery(name = "Itenscomanda.findAll", query = "SELECT i FROM Itenscomanda i")})
public class Itenscomanda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer iditenscomanda;
    @Basic(optional = false)
    @Column(nullable = false)
    private int idcomanda;
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
    @JoinColumn(name = "idproduto", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Itens idproduto;

    public Itenscomanda() {
    }

    public Itenscomanda(Integer id) {
        this.iditenscomanda = id;
    }

    public Itenscomanda(Integer id, int idcomanda, int qnt, double totalitem) {
        this.iditenscomanda = id;
        this.idcomanda = idcomanda;
        this.qnt = qnt;
        this.totalitem = totalitem;
    }

    public Integer getIditenscomanda() {
        return iditenscomanda;
    }

    public void setIditenscomanda(Integer iditenscomanda) {
        this.iditenscomanda = iditenscomanda;
    }

    public int getIdcomanda() {
        return idcomanda;
    }

    public void setIdcomanda(int idcomanda) {
        this.idcomanda = idcomanda;
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

    public Itens getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(Itens idproduto) {
        this.idproduto = idproduto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iditenscomanda != null ? iditenscomanda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itenscomanda)) {
            return false;
        }
        Itenscomanda other = (Itenscomanda) object;
        if ((this.iditenscomanda == null && other.iditenscomanda != null) || (this.iditenscomanda != null && !this.iditenscomanda.equals(other.iditenscomanda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.vinibar.model.Itenscomanda[ id=" + iditenscomanda + " ]";
    }
    
}
