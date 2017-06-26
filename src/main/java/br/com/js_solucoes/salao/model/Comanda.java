package br.com.js_solucoes.salao.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@SuppressWarnings("serial")
@Entity
public class Comanda extends GenericEntity {
	@Column(precision = 6, scale = 2)
	private BigDecimal desconto;
	@Column(precision = 7, scale = 2)
	private BigDecimal total;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dtcomanda;
	private Boolean salva;
	@Column(length = 255)
	private String status;
	@JoinColumn(nullable = true) //vendas sem cliente cadastrado
	@ManyToOne
	private Cliente cliente;
	@JoinColumn(nullable = false)
	@ManyToOne
	private Funcionario funcionario;
	
	public BigDecimal getDesconto() {
		return desconto;
	}
	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}
	public Date getDtcomanda() {
		return dtcomanda;
	}
	public void setDtcomanda(Date dtcomanda) {
		this.dtcomanda = dtcomanda;
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
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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
	@Column(nullable = false, length = 15)
	private String dtreg;
	@Column(nullable = false, length = 15)
	private String hrreg;

	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	
}
