package br.com.vinibar.model;

import br.com.vinibar.model.Caixa;
import br.com.vinibar.model.Cliente;
import br.com.vinibar.model.Funcionario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-08T18:02:45")
@StaticMetamodel(Comanda.class)
public class Comanda_ { 

    public static volatile SingularAttribute<Comanda, Funcionario> idfuncionario;
    public static volatile SingularAttribute<Comanda, String> dtreg;
    public static volatile SingularAttribute<Comanda, Float> desconto;
    public static volatile SingularAttribute<Comanda, String> hrreg;
    public static volatile ListAttribute<Comanda, Caixa> caixaList;
    public static volatile SingularAttribute<Comanda, Integer> idcomanda;
    public static volatile SingularAttribute<Comanda, Boolean> salva;
    public static volatile SingularAttribute<Comanda, Cliente> idcliente;
    public static volatile SingularAttribute<Comanda, Date> dtcomanda;
    public static volatile SingularAttribute<Comanda, String> status;

}