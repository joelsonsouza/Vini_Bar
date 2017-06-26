package br.com.js_solucoes.salao.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Funcionario extends GenericEntity  {
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Pessoa pessoa;
	@Column(length = 25)
	private String cargo;
	@Column(precision = 6, scale = 2)
	private BigDecimal salario;
	@Temporal(TemporalType.DATE)
	private Date dtadmissao;
	@Temporal(TemporalType.DATE)
	private Date dtdemissao;
	@Column(length = 20)
	private String pis;
	@Column(length = 20)
	private String banco;
	@Column(length = 5)
	private String agencia;
	@Column(length = 15)
	private String conta;
	@Column(length = 15)
	private String tipocontrato;
	@Column(length = 15)
	private String dtreg;
	@Column(length = 15)
	private String hrreg;
	private Boolean ativo;
	@Temporal(TemporalType.DATE)
	private Date dtcadastro;
	

	
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
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	
	public Date getDtadmissao() {
		return dtadmissao;
	}
	public void setDtadmissao(Date dtadmissao) {
		this.dtadmissao = dtadmissao;
	}
	public Date getDtdemissao() {
		return dtdemissao;
	}
	public void setDtdemissao(Date dtdemissao) {
		this.dtdemissao = dtdemissao;
	}
	public String getPis() {
		return pis;
	}
	public void setPis(String pis) {
		this.pis = pis;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getConta() {
		return conta;
	}
	public void setConta(String conta) {
		this.conta = conta;
	}
	public String getTipocontrato() {
		return tipocontrato;
	}
	public void setTipocontrato(String tipocontrato) {
		this.tipocontrato = tipocontrato;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public Date getDtcadastro() {
		return dtcadastro;
	}
	public void setDtcadastro(Date dtcadastro) {
		this.dtcadastro = dtcadastro;
	}
	
	@Transient
	public String getAtivoFormatado(){
		String ativoFormatado = null;
		if(ativo == true){
			ativoFormatado = "SIM";
		}else{
			ativoFormatado = "NÃO";
		}
		return ativoFormatado;
	}
	
	
	
}
