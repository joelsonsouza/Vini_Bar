package br.com.js_solucoes.salao.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Pessoa extends GenericEntity {

	@Basic(optional = false)
	@Column(nullable = false, length = 15)
	private String nome;
	@Column(length = 15)
	private String cpf;
	@Temporal(TemporalType.DATE)
	private Date dtnascimento;
	@Column(length = 15)
	private String telefone;
	@Column(length = 15)
	private String celular;
	@Column(length = 30)
	private String email;
	@Column(length = 30)
	private String logradouro;
	private Short numero;
	@Column(length = 30)
	private String complemento;
	@Column(length = 25)
	private String bairro;
	@Column(length = 10)
	private String cep;
	@Column(length = 25)
	private String cidade;
	@Column(length = 2)
	private String uf;
	private Boolean ativo;
	@Column(length = 100)
	private String observacoes;
	@Column(length = 15)
	private String status;
	@Column(length = 15)
	private String dtreg;
	@Column(length = 15)
	private String hrreg;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDtnascimento() {
		return dtnascimento;
	}
	public void setDtnascimento(Date dtnascimento) {
		this.dtnascimento = dtnascimento;
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
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
