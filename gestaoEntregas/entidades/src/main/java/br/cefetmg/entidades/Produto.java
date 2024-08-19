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
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String localizacao;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_item_item")
    private ItemPed item;
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
       this.localizacao = localizacao;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}