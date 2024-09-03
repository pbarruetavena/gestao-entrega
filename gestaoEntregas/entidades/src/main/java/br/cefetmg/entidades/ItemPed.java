/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.entidades;


import java.util.ArrayList;
import javax.persistence.*;
/**
 *
 * @author User
 */
@Entity
public class ItemPed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double valorUnitario;
    private int quantidade;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pedido_pedido")
    private Pedido pedido;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_produto_produto")
    private Produto produto;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;

    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}