package br.com.vinibar.model;

import br.com.vinibar.model.Cliente;
import br.com.vinibar.model.Funcionario;
import br.com.vinibar.model.Itens;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-01T14:47:31")
@StaticMetamodel(Comanda.class)
public class Comanda_ { 

    public static volatile SingularAttribute<Comanda, Itens> iditem;
    public static volatile SingularAttribute<Comanda, Double> total;
    public static volatile SingularAttribute<Comanda, Funcionario> idfuncionario;
    public static volatile SingularAttribute<Comanda, String> dtreg;
    public static volatile SingularAttribute<Comanda, Float> desconto;
    public static volatile SingularAttribute<Comanda, String> hrreg;
    public static volatile SingularAttribute<Comanda, Integer> qnt;
    public static volatile SingularAttribute<Comanda, Integer> id;
    public static volatile SingularAttribute<Comanda, Cliente> idcliente;
    public static volatile SingularAttribute<Comanda, Date> dtcomanda;

}