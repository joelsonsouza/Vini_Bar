package br.com.js_solucoes.salao.model;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Caixa extends GenericEntity{
	
	@Column(nullable = false, length = 50)
	private String formapagamento;
	@Column(length = 150)
	private String observacoes;
	@Column(precision = 6, scale = 2)
	private BigDecimal outrosdescontos;
	
//	@JoinColumn(nullable = false)
//	@ManyToOne
//	private Comanda comanda;
	
	private Long comanda_codigo;
	
	@JoinColumn(nullable = false)
	@ManyToOne
	private Funcionario funcionariocaixa;
	@Column(nullable = false, length = 15)
	private String dtreg;
	@Basic(optional = false)
	@Column(nullable = false, length = 15)
	private String hrreg;
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
	public BigDecimal getOutrosdescontos() {
		return outrosdescontos;
	}
	public void setOutrosdescontos(BigDecimal outrosdescontos) {
		this.outrosdescontos = outrosdescontos;
	}
	public Long getComanda_codigo() {
		return comanda_codigo;
	}
	public void setComanda_codigo(Long comanda_codigo) {
		this.comanda_codigo = comanda_codigo;
	}
	public Funcionario getFuncionariocaixa() {
		return funcionariocaixa;
	}
	public void setFuncionariocaixa(Funcionario funcionariocaixa) {
		this.funcionariocaixa = funcionariocaixa;
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
	
//	@Transient
//	public Long geComandaInt(Comanda comanda){
//		Long comandaInt = 0L;
//		if(comanda != null){
//			comanda.getClass().cast(comandaInt); 
//		}else{
//			
//		}
//		return comandaInt;
//	}

	
}
