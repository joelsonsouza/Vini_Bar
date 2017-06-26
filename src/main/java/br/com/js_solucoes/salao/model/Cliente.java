package br.com.js_solucoes.salao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Cliente extends GenericEntity {

	@OneToOne
	@JoinColumn(nullable = false)
	private Pessoa pessoa;
	@Column(length = 15)
	private String dtreg;
	@Column(length = 15)
	private String hrreg;
	private Boolean ativo;
	@Temporal(TemporalType.DATE)
	private Date dtcadastro;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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
	
	
	
}
