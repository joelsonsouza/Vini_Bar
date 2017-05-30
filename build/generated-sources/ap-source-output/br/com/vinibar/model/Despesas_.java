package br.com.vinibar.model;

import br.com.vinibar.model.Fornecedor;
import br.com.vinibar.model.Tipodespesa;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-29T11:34:58")
@StaticMetamodel(Despesas.class)
public class Despesas_ { 

    public static volatile SingularAttribute<Despesas, Date> dtdespesa;
    public static volatile SingularAttribute<Despesas, Fornecedor> idfornecedor;
    public static volatile SingularAttribute<Despesas, Integer> ano;
    public static volatile SingularAttribute<Despesas, String> dtreg;
    public static volatile SingularAttribute<Despesas, String> hrreg;
    public static volatile SingularAttribute<Despesas, Double> valor;
    public static volatile SingularAttribute<Despesas, String> mes;
    public static volatile SingularAttribute<Despesas, Integer> id;
    public static volatile SingularAttribute<Despesas, Tipodespesa> idtipo;
    public static volatile SingularAttribute<Despesas, Boolean> pago;
    public static volatile SingularAttribute<Despesas, String> descricao;

}