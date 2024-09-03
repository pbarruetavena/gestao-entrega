/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.entidades;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double valorTotal;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dt;
    private StatusPedido status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente_cliente")
    private Cliente cliente;
    
    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "pedido")
    private ArrayList<ItemPed> itens;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_entregador_entregador")
    private Funcionario entregador;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_atendente_atendente")
    private Funcionario atendente;

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
        return dt;
    }

    public void setData(Date data) {
        this.dt = data;
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

    public ArrayList<ItemPed> getItens() {
        return itens;
    }

    public void setItens(ArrayList<ItemPed> itens) {
        this.itens = itens;
    }

    public Funcionario getAtendente() {
        return atendente;
    }

    public void setAtendente(Funcionario atendente) {
        this.atendente = atendente;
    }

    public Funcionario getEntregador() {
        return entregador;
    }

    public void setEntregador(Funcionario entregador) {
        this.entregador = entregador;
    }

   
}