package br.cefetmg.entidades;

import br.cefetmg.entidades.Empresa;
import br.cefetmg.entidades.Funcionario;
import br.cefetmg.entidades.Pedido;
import br.cefetmg.entidades.Perfil;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-08-23T08:56:35", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Funcionario.class)
public class Funcionario_ { 

    public static volatile ListAttribute<Funcionario, Perfil> perfis;
    public static volatile ListAttribute<Funcionario, Funcionario> pedidosEntregues;
    public static volatile SingularAttribute<Funcionario, String> senha;
    public static volatile SingularAttribute<Funcionario, String> telefone;
    public static volatile SingularAttribute<Funcionario, String> nome;
    public static volatile ListAttribute<Funcionario, Pedido> pedidosAtendidos;
    public static volatile SingularAttribute<Funcionario, Integer> id;
    public static volatile SingularAttribute<Funcionario, Empresa> empresa;

}