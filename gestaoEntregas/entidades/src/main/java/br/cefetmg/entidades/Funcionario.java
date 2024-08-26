/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.entidades;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import java.util.Objects;



/**
 *
 * @author User
 */
@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String senha;
    private String telefone;
    
    @Column(unique = true)
    private String email;
    
    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST,
            mappedBy = "funcionario")
    private ArrayList<Perfil> perfis;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_empresa_empresa")
    private Empresa empresa;
    
    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST,
            mappedBy = "entregador")
    private ArrayList<Pedido> pedidosEntregues;
    
    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST,
            mappedBy = "atendente")
    private ArrayList<Pedido> pedidosAtendidos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(ArrayList<Perfil> perfis) {
        this.perfis = perfis;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
    public boolean isAtendente() {
        for(int i = 0; i < perfis.size(); i++) {
            if(perfis.get(i).equals("atendente")) return true;
        }
        return false;
    }
    
    public boolean isEntregador() {
        for(int i = 0; i < perfis.size(); i++) {
            if(perfis.get(i).equals("entregador")) return true;
        }
        return false;
    }
    
    public boolean isAdministrador() {
        for(int i = 0; i < perfis.size(); i++) {
            if(perfis.get(i).equals("administrador")) return true;
        }
        return false;
    }

    public ArrayList<Pedido> getPedidosAtendidos() {
        return pedidosAtendidos;
    }

    public void setPedidosAtendidos(ArrayList<Pedido> pedidosAtendidos) {
        this.pedidosAtendidos = pedidosAtendidos;
    }

    public ArrayList<Pedido> getPedidosEntregues() {
        return pedidosEntregues;
    }

    public void setPedidosEntregues(ArrayList<Pedido> pedidosEntregues) {
        this.pedidosEntregues = pedidosEntregues;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.valueOf(this.getId());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
