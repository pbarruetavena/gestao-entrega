/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.entidades;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


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
    
    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST,
            mappedBy = "funcionario")
    private ArrayList<Perfil> perfis;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_empresa_empresa")
    private Empresa empresa;

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
}
