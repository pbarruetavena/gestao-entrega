/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.entidades;

import java.sql.Date;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double valorTotal;
    private Date data;
    private StatusPedido status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente_cliente")
    private Cliente cliente;
    
    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST,
            mappedBy = "pedido")
    private ArrayList<ItemPed> itens;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

   
}


enum StatusPedido {
    EM_PREPARO("Em preparo"),
    EM_ENTREGA("Em entrega"),
    ENTREGUE("Entregue");
    
    private String status;
    
    StatusPedido(String status) {
        this.status = status;
    }
    
    public String getStatus() {
        return status;
    }
}