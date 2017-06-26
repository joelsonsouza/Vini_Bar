package br.com.js_solucoes.salao.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.js_solucoes.salao.dao.DaoDespesa;
import br.com.js_solucoes.salao.dao.DaoRelComandas;
import br.com.js_solucoes.salao.dao.DaoRelDespesas;
import br.com.js_solucoes.salao.model.Despesas;

@ViewScoped
@ManagedBean
public class BeanRelComandas {

	private Date dtin, dtfin;
	private String pagaAberta;
	private String cliente;
	private String funcionario;
	
	private String tipo;
	private List<Despesas> listaDespesa = new ArrayList<>();

	DaoRelComandas rel = new DaoRelComandas();

	@PostConstruct
	public void init() {
		//listaDespesa = new DaoDespesa().listar();
		funcionario = null;
		cliente = null;
	}

	public void RelComandasPorPeriodo() {
		if (pagaAberta == "") {
			try {
				rel.PorPeriodo(dtin, dtfin);
			} catch (Exception e) {
				Messages.addGlobalError("NÃO FORAM ENCONTRADOS REGISTROS PARA O FILTRO APLICADO");
			}
		} else {
			try {
				rel.PagasAbertas(dtin, dtfin, pagaAberta);
			} catch (Exception e) {
				Messages.addGlobalError("NÃO FORAM ENCONTRADOS REGISTROS PARA O FILTRO APLICADO PAGO/ABERTO");
			}
		}

	}
	
	public void RelComandasPorCliente() {
		if (cliente == "") {
			try {
				rel.AgrupadasPorCliente(dtin, dtfin);
			} catch (Exception e) {
				Messages.addGlobalError("NÃO FORAM ENCONTRADOS REGISTROS PARA O FILTRO APLICADO");
			}
		} else {
			try {
				rel.PorClienteSelecionado(dtin, dtfin, cliente);
			} catch (Exception e) {
				Messages.addGlobalError("NÃO FORAM ENCONTRADOS REGISTROS PARA O FILTRO APLICADO PAGO/ABERTO");
			}
		}

	}
	
	public void RelComandasPorFuncionario() {
		if (funcionario == "") {
			try {
				rel.AgrupadasPorFuncionario(dtin, dtfin);
			} catch (Exception e) {
				Messages.addGlobalError("NÃO FORAM ENCONTRADOS REGISTROS PARA O FILTRO APLICADO");
			}
		} else {
			try {
				rel.PorFuncionarioSelecionado(dtin, dtfin, funcionario);
			} catch (Exception e) {
				Messages.addGlobalError("NÃO FORAM ENCONTRADOS REGISTROS PARA O FILTRO APLICADO PAGO/ABERTO");
			}
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


	public String getPagaAberta() {
		return pagaAberta;
	}

	public void setPagaAberta(String pagaAberta) {
		this.pagaAberta = pagaAberta;
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

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}
	
	

	
}
