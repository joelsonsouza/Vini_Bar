package br.com.vinibar.model;

import br.com.vinibar.model.Comanda;
import br.com.vinibar.model.Funcionario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-08T18:02:45")
@StaticMetamodel(Caixa.class)
public class Caixa_ { 

    public static volatile SingularAttribute<Caixa, Double> outrosdescontos;
    public static volatile SingularAttribute<Caixa, String> observacoes;
    public static volatile SingularAttribute<Caixa, String> dtreg;
    public static volatile SingularAttribute<Caixa, Funcionario> idfuncionariocaixa;
    public static volatile SingularAttribute<Caixa, String> hrreg;
    public static volatile SingularAttribute<Caixa, Comanda> idcomanda;
    public static volatile SingularAttribute<Caixa, Integer> idcaixa;
    public static volatile SingularAttribute<Caixa, String> formapagamento;

}