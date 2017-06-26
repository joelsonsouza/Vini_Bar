package br.com.js_solucoes.salao.model;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Produto extends GenericEntity{
	
	private Boolean ativo;
	@Column(nullable = false, length = 100)
	private String descricao;
	@Column(length = 5)
	private String medida; //unidade de medida
	@Basic(optional = false)
	@Column(precision = 6, scale = 2 , nullable = false)
	private BigDecimal preco;
	@Basic(optional = false)
	@Column(nullable = false, length = 100)
	private String tipo;
	@Column(length = 15)
	private String dtreg;
	@Column(length = 15)
	private String hrreg;
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getMedida() {
		return medida;
	}
	public void setMedida(String medida) {
		this.medida = medida;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
