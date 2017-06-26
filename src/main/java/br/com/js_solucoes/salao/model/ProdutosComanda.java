package br.com.js_solucoes.salao.model;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class ProdutosComanda extends GenericEntity{

	
	@Basic(optional = false)
	@Column(nullable = false)
	private int qnt;
	@Basic(optional = false)
	@Column(precision = 6, scale = 2 ,nullable = false)
	private BigDecimal totalitem;
	@Column(precision = 6, scale = 2 ,nullable = true)
	private BigDecimal precoparcial;
	@JoinColumn(nullable = false)
	@ManyToOne
	private Comanda comanda;
	@JoinColumn(nullable = true)
	@ManyToOne
	private Produto produto;
	@Column(length = 15)
	private String dtreg;
	@Column(length = 15)
	private String hrreg;
	
	public int getQnt() {
		return qnt;
	}
	public void setQnt(int qnt) {
		this.qnt = qnt;
	}
	public BigDecimal getTotalitem() {
		return totalitem;
	}
	public void setTotalitem(BigDecimal totalitem) {
		this.totalitem = totalitem;
	}
	public Comanda getComanda() {
		return comanda;
	}
	public void setComanda(Comanda comanda) {
		this.comanda = comanda;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
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
	public BigDecimal getPrecoparcial() {
		return precoparcial;
	}
	public void setPrecoparcial(BigDecimal precoparcial) {
		this.precoparcial = precoparcial;
	}
		
}
