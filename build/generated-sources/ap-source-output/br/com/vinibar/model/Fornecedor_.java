package br.com.vinibar.model;

import br.com.vinibar.model.Despesas;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-01T14:47:31")
@StaticMetamodel(Fornecedor.class)
public class Fornecedor_ { 

    public static volatile SingularAttribute<Fornecedor, String> complemeto;
    public static volatile SingularAttribute<Fornecedor, String> telefone;
    public static volatile SingularAttribute<Fornecedor, String> cidade;
    public static volatile SingularAttribute<Fornecedor, Boolean> ativo;
    public static volatile SingularAttribute<Fornecedor, Integer> numero;
    public static volatile SingularAttribute<Fornecedor, String> hrreg;
    public static volatile SingularAttribute<Fornecedor, String> bairro;
    public static volatile SingularAttribute<Fornecedor, String> cnpj;
    public static volatile SingularAttribute<Fornecedor, String> cep;
    public static volatile SingularAttribute<Fornecedor, String> reponsavel;
    public static volatile SingularAttribute<Fornecedor, String> uf;
    public static volatile SingularAttribute<Fornecedor, String> dtreg;
    public static volatile ListAttribute<Fornecedor, Despesas> despesasList;
    public static volatile SingularAttribute<Fornecedor, String> logradouro;
    public static volatile SingularAttribute<Fornecedor, String> celular;
    public static volatile SingularAttribute<Fornecedor, Integer> id;
    public static volatile SingularAttribute<Fornecedor, String> empresa;
    public static volatile SingularAttribute<Fornecedor, String> email;

}