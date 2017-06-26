package br.com.js_solucoes.salao.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Tipodespesa extends GenericEntity {

	@Basic(optional = false)
	@Column(nullable = false, length = 30)
	private String tipo;
	@Basic(optional = false)
	@Column(nullable = false, length = 30)
	private String periodo;
	@Column(length = 15)
	private String dtreg;
	@Column(length = 15)
	private String hrreg;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
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
