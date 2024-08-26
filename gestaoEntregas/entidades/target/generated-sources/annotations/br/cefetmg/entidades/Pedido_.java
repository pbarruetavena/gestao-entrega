package br.cefetmg.entidades;

import br.cefetmg.entidades.Cliente;
import br.cefetmg.entidades.Funcionario;
import br.cefetmg.entidades.ItemPed;
import br.cefetmg.entidades.StatusPedido;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-08-26T08:35:01", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Pedido.class)
public class Pedido_ { 

    public static volatile SingularAttribute<Pedido, Date> dt;
    public static volatile SingularAttribute<Pedido, Cliente> cliente;
    public static volatile ListAttribute<Pedido, ItemPed> itens;
    public static volatile SingularAttribute<Pedido, Funcionario> atendente;
    public static volatile SingularAttribute<Pedido, Double> valorTotal;
    public static volatile SingularAttribute<Pedido, Integer> id;
    public static volatile SingularAttribute<Pedido, Funcionario> entregador;
    public static volatile SingularAttribute<Pedido, StatusPedido> status;

}