package br.com.vinibar.model;

import br.com.vinibar.model.Comanda;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-01T14:47:31")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, String> telefone;
    public static volatile SingularAttribute<Cliente, String> cidade;
    public static volatile SingularAttribute<Cliente, Boolean> ativo;
    public static volatile ListAttribute<Cliente, Comanda> comandaList;
    public static volatile SingularAttribute<Cliente, Integer> numero;
    public static volatile SingularAttribute<Cliente, String> hrreg;
    public static volatile SingularAttribute<Cliente, String> bairro;
    public static volatile SingularAttribute<Cliente, String> nome;
    public static volatile SingularAttribute<Cliente, String> cep;
    public static volatile SingularAttribute<Cliente, String> uf;
    public static volatile SingularAttribute<Cliente, String> complemento;
    public static volatile SingularAttribute<Cliente, String> dtreg;
    public static volatile SingularAttribute<Cliente, Date> dtnascimento;
    public static volatile SingularAttribute<Cliente, String> logradouro;
    public static volatile SingularAttribute<Cliente, String> cpf;
    public static volatile SingularAttribute<Cliente, String> celular;
    public static volatile SingularAttribute<Cliente, Integer> id;
    public static volatile SingularAttribute<Cliente, String> email;

}