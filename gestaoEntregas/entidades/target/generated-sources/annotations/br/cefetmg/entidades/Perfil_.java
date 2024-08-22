package br.cefetmg.entidades;

import br.cefetmg.entidades.Funcionario;
import br.cefetmg.entidades.TipoPerfil;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-08-21T21:30:21", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Perfil.class)
public class Perfil_ { 

    public static volatile SingularAttribute<Perfil, TipoPerfil> tipo;
    public static volatile SingularAttribute<Perfil, Integer> id;
    public static volatile SingularAttribute<Perfil, Funcionario> funcionario;

}