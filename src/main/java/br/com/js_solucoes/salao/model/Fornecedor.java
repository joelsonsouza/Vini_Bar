package br.com.js_solucoes.salao.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Fornecedor extends GenericEntity{

	@Column(length = 50)
	private String empresa;
	@Column(length = 21)
	private String cnpj;
	@Column(length = 50)
	private String reponsavel;
	@Column(length = 15)
	private String telefone;
	@Column(length = 15)
	private String celular;
	@Column(length = 50)
	private String email;
	@Column(length = 50)
	private String logradouro;
	private Short numero;
	@Column(length = 30)
	private String complemento;
	@Column(length = 30)
	private String bairro;
	@Column(length = 10)
	private String cep;
	@Column(length = 30)
	private String cidade;
	@Column(length = 2)
	private String uf;
	private Boolean ativo;
	@Column(length = 15)
	private String dtreg;
	@Column(length = 15)
	private String hrreg;
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getReponsavel() {
		return reponsavel;
	}
	public void setReponsavel(String reponsavel) {
		this.reponsavel = reponsavel;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public Short getNumero() {
		return numero;
	}
	public void setNumero(Short numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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
	
	
	
}
