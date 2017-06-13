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
@NamedQueries({ @NamedQuery(name = "Comanda.findAll", query = "SELECT c FROM Comanda c"),
		@NamedQuery(name = "Comanda.ComandaAberta", query = "SELECT co FROM Comanda co  WHERE co.status='ABERTA'"),
		@NamedQuery(name = "Comanda.update", query = "UPDATE  Comanda c SET c.status = :st WHERE c.idcomanda = :idcomanda"),
		@NamedQuery(name = "Comanda.findBySalva", query = "SELECT c FROM Comanda c WHERE c.salva = :salva") })
public class Comanda implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(nullable = false)
	private Integer idcomanda;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(precision = 17, scale = 17)
	private Double desconto;
	@Basic(optional = false)
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dtcomanda;
	@Basic(optional = false)
	@Column(nullable = false, length = 15)
	private String dtreg;
	@Basic(optional = false)
	@Column(nullable = false, length = 15)
	private String hrreg;
	private Boolean salva;
	@Column(length = 255)
	private String status;
	@JoinColumn(name = "idcliente", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false)
	private Cliente idcliente;
	@JoinColumn(name = "idfuncionario", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false)
	private Funcionario idfuncionario;
	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "idproduto")
	// private List<Itenscomanda> itenscomandaList;

	public Comanda() {
	}

	public Comanda(Integer idcomanda) {
		this.idcomanda = idcomanda;
	}

	public Comanda(Integer idcomanda, Date dtcomanda, String dtreg, String hrreg) {
		this.idcomanda = idcomanda;
		this.dtcomanda = dtcomanda;
		this.dtreg = dtreg;
		this.hrreg = hrreg;
	}

	public Integer getIdcomanda() {
		return idcomanda;
	}

	public void setIdcomanda(Integer idcomanda) {
		this.idcomanda = idcomanda;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Date getDtcomanda() {
		return dtcomanda;
	}

	public void setDtcomanda(Date dtcomanda) {
		this.dtcomanda = dtcomanda;
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

	public Boolean getSalva() {
		return salva;
	}

	public void setSalva(Boolean salva) {
		this.salva = salva;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	// public List<Itenscomanda> getItenscomandaList() {
	// return itenscomandaList;
	// }
	//
	// public void setItenscomandaList(List<Itenscomanda> itenscomandaList) {
	// this.itenscomandaList = itenscomandaList;
	// }

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idcomanda != null ? idcomanda.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Comanda)) {
			return false;
		}
		Comanda other = (Comanda) object;
		if ((this.idcomanda == null && other.idcomanda != null)
				|| (this.idcomanda != null && !this.idcomanda.equals(other.idcomanda))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.vinibar.model.Comanda[ idcomanda=" + idcomanda + " ]";
	}

}
