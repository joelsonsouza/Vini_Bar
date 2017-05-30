/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vinibar.model;

import java.io.Serializable;
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
    @NamedQuery(name = "Tipodespesa.findAll", query = "SELECT t FROM Tipodespesa t")
    , @NamedQuery(name = "Tipodespesa.findById", query = "SELECT t FROM Tipodespesa t WHERE t.id = :id")
    , @NamedQuery(name = "Tipodespesa.findByTipo", query = "SELECT t FROM Tipodespesa t WHERE t.tipo = :tipo")
    , @NamedQuery(name = "Tipodespesa.findByPeriodo", query = "SELECT t FROM Tipodespesa t WHERE t.periodo = :periodo")
    , @NamedQuery(name = "Tipodespesa.findByDtreg", query = "SELECT t FROM Tipodespesa t WHERE t.dtreg = :dtreg")
    , @NamedQuery(name = "Tipodespesa.findByHrreg", query = "SELECT t FROM Tipodespesa t WHERE t.hrreg = :hrreg")})
public class Tipodespesa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String tipo;
    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String periodo;
    @Column(length = 15)
    private String dtreg;
    @Column(length = 15)
    private String hrreg;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtipo")
    private List<Despesas> despesasList;

    public Tipodespesa() {
    }

    public Tipodespesa(Integer id) {
        this.id = id;
    }

    public Tipodespesa(Integer id, String tipo, String periodo) {
        this.id = id;
        this.tipo = tipo;
        this.periodo = periodo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
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
        if (!(object instanceof Tipodespesa)) {
            return false;
        }
        Tipodespesa other = (Tipodespesa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.vinibar.model.Tipodespesa[ id=" + id + " ]";
    }
    
}
