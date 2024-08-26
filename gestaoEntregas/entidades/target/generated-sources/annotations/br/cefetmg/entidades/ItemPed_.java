package br.cefetmg.entidades;

import br.cefetmg.entidades.Pedido;
import br.cefetmg.entidades.Produto;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-08-23T08:56:35", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(ItemPed.class)
public class ItemPed_ { 

    public static volatile ListAttribute<ItemPed, Produto> produtos;
    public static volatile SingularAttribute<ItemPed, Pedido> pedido;
    public static volatile SingularAttribute<ItemPed, Integer> id;
    public static volatile SingularAttribute<ItemPed, Integer> quantidade;
    public static volatile SingularAttribute<ItemPed, Double> valorUnitario;

}