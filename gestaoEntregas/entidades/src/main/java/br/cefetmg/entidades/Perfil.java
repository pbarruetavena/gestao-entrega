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
public class Perfil {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private TipoPerfil tipo;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_funcionario_funcionario")
    private Funcionario funcionario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoPerfil getTipo() {
        return tipo;
    }

    public void setTipo(TipoPerfil tipo) {
        this.tipo = tipo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
}


enum TipoPerfil {
    ADMINISTRADOR("administrador"),
    ATENDENTE("atendente"),
    ENTREGADOR("entregador");
    
    private String tipo;
    
    TipoPerfil(String tipo) {
        this.tipo = tipo;
    }
    
    public String getTipo() {
        return tipo;
    }
}