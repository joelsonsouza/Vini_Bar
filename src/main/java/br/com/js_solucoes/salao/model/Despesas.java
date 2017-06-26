package br.com.js_solucoes.salao.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Despesas extends GenericEntity {

	@Basic(optional = false)
	@Column(nullable = false, length = 75)
	private String descricao;
	@Basic(optional = false)
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dtdespesa;
	@Basic(optional = false)
	@Column(nullable = false)
	private BigDecimal valor;
	private Boolean pago;
	@Column(length = 15)
	private String dtreg;
	@Column(length = 15)
	private String hrreg;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Tipodespesa tipodespesa;
	@ManyToOne
	@JoinColumn(nullable = true)
	private Fornecedor fornecedor;
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
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Boolean getPago() {
		return pago;
	}
	public void setPago(Boolean pago) {
		this.pago = pago;
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
	public Tipodespesa getTipodespesa() {
		return tipodespesa;
	}
	public void setTipodespesa(Tipodespesa tipodespesa) {
		this.tipodespesa = tipodespesa;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
}
