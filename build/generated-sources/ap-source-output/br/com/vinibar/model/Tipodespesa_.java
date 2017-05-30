package br.com.vinibar.model;

import br.com.vinibar.model.Despesas;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-29T11:34:58")
@StaticMetamodel(Tipodespesa.class)
public class Tipodespesa_ { 

    public static volatile SingularAttribute<Tipodespesa, String> tipo;
    public static volatile SingularAttribute<Tipodespesa, String> dtreg;
    public static volatile ListAttribute<Tipodespesa, Despesas> despesasList;
    public static volatile SingularAttribute<Tipodespesa, String> periodo;
    public static volatile SingularAttribute<Tipodespesa, String> hrreg;
    public static volatile SingularAttribute<Tipodespesa, Integer> id;

}