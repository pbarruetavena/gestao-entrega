package br.cefetmg.entidades;

import br.cefetmg.entidades.Cliente;
import br.cefetmg.entidades.Funcionario;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-08-26T07:52:27", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Empresa.class)
public class Empresa_ { 

    public static volatile ListAttribute<Empresa, Funcionario> atendentes;
    public static volatile SingularAttribute<Empresa, String> nome;
    public static volatile SingularAttribute<Empresa, Double> porcentagemComissaoEntregador;
    public static volatile SingularAttribute<Empresa, Integer> id;
    public static volatile SingularAttribute<Empresa, String> cnpj;
    public static volatile ListAttribute<Empresa, Cliente> clientes;

}