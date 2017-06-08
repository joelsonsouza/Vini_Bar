package br.com.vinibar.model;

import br.com.vinibar.model.Itenscomanda;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-08T18:02:45")
@StaticMetamodel(Itens.class)
public class Itens_ { 

    public static volatile SingularAttribute<Itens, Double> preco;
    public static volatile SingularAttribute<Itens, String> tipo;
    public static volatile SingularAttribute<Itens, Boolean> ativo;
    public static volatile SingularAttribute<Itens, String> dtreg;
    public static volatile SingularAttribute<Itens, String> medida;
    public static volatile SingularAttribute<Itens, String> hrreg;
    public static volatile SingularAttribute<Itens, Integer> id;
    public static volatile ListAttribute<Itens, Itenscomanda> itenscomandaList;
    public static volatile SingularAttribute<Itens, String> descricao;

}