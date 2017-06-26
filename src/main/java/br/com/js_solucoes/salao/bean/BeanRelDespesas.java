package br.com.js_solucoes.salao.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.js_solucoes.salao.dao.DaoDespesa;
import br.com.js_solucoes.salao.dao.DaoRelDespesas;
import br.com.js_solucoes.salao.model.Despesas;

@ViewScoped
@ManagedBean
public class BeanRelDespesas {

	private Date dtin, dtfin;
	private String mensalEventual;
	private Boolean pago = true;
	private String tipo;
	private List<Despesas> listaDespesa = new ArrayList<>();

	DaoRelDespesas rel = new DaoRelDespesas();

	@PostConstruct
	public void init() {
		listaDespesa = new DaoDespesa().listar();
	}

	public void RelGastos() {
		if (mensalEventual == "") {
			try {
				rel.PorPeriodo(dtin, dtin);
			} catch (Exception e) {
				Messages.addGlobalError("NÃO FORAM ENCONTRADOS REGISTROS PARA O FILTRO APLICADO");
			}
		} else {
			try {
				rel.MesalEventual(dtin, dtfin, mensalEventual);
			} catch (Exception e) {
				Messages.addGlobalError("NÃO FORAM ENCONTRADOS REGISTROS PARA O FILTRO APLICADO");
			}
		}

	}

	public void PagasNaoPagas() {
		try {
			rel.PagasNaoPagas(dtin, dtfin, pago);
		} catch (Exception e) {
			Messages.addGlobalError("NÃO FORAM ENCONTRADOS REGISTROS PARA O FILTRO APLICADO");
		}
	}

	public void PorTipo() {
		try {
			rel.PorTipo(dtin, dtfin, tipo);
		} catch (Exception e) {
			Messages.addGlobalError("NÃO FORAM ENCONTRADOS REGISTROS PARA O FILTRO APLICADO");
		}

	}

	public Date getDtin() {
		return dtin;
	}

	public void setDtin(Date dtin) {
		this.dtin = dtin;
	}

	public Date getDtfin() {
		return dtfin;
	}

	public void setDtfin(Date dtfin) {
		this.dtfin = dtfin;
	}

	public String getMensalEventual() {
		return mensalEventual;
	}

	public void setMensalEventual(String mensalEventual) {
		this.mensalEventual = mensalEventual;
	}

	public Boolean getPago() {
		return pago;
	}

	public void setPago(Boolean pago) {
		this.pago = pago;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Despesas> getListaDespesa() {
		return listaDespesa;
	}

	public void setListaDespesa(List<Despesas> listaDespesa) {
		this.listaDespesa = listaDespesa;
	}

}
