package br.com.vinibar.model;

import br.com.vinibar.model.Caixa;
import br.com.vinibar.model.Comanda;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-06T02:20:13")
@StaticMetamodel(Funcionario.class)
public class Funcionario_ { 

    public static volatile SingularAttribute<Funcionario, Date> dtdemissao;
    public static volatile SingularAttribute<Funcionario, String> telefone;
    public static volatile SingularAttribute<Funcionario, String> cidade;
    public static volatile SingularAttribute<Funcionario, Boolean> ativo;
    public static volatile SingularAttribute<Funcionario, Integer> numero;
    public static volatile ListAttribute<Funcionario, Caixa> caixaList;
    public static volatile SingularAttribute<Funcionario, String> conta;
    public static volatile SingularAttribute<Funcionario, String> agencia;
    public static volatile SingularAttribute<Funcionario, String> cep;
    public static volatile SingularAttribute<Funcionario, String> uf;
    public static volatile SingularAttribute<Funcionario, String> complemento;
    public static volatile SingularAttribute<Funcionario, Date> dtnascimento;
    public static volatile SingularAttribute<Funcionario, String> cpf;
    public static volatile SingularAttribute<Funcionario, String> celular;
    public static volatile SingularAttribute<Funcionario, Integer> id;
    public static volatile SingularAttribute<Funcionario, String> pis;
    public static volatile SingularAttribute<Funcionario, String> cargo;
    public static volatile SingularAttribute<Funcionario, String> email;
    public static volatile SingularAttribute<Funcionario, String> tipocontrato;
    public static volatile ListAttribute<Funcionario, Comanda> comandaList;
    public static volatile SingularAttribute<Funcionario, String> hrreg;
    public static volatile SingularAttribute<Funcionario, String> bairro;
    public static volatile SingularAttribute<Funcionario, Double> salario;
    public static volatile SingularAttribute<Funcionario, String> banco;
    public static volatile SingularAttribute<Funcionario, String> nome;
    public static volatile SingularAttribute<Funcionario, Date> dtadmissao;
    public static volatile SingularAttribute<Funcionario, String> observacoes;
    public static volatile SingularAttribute<Funcionario, String> dtreg;
    public static volatile SingularAttribute<Funcionario, String> logradouro;
    public static volatile SingularAttribute<Funcionario, String> status;

}