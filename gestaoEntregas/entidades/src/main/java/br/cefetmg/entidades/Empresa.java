
package br.cefetmg.entidades;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * 
 * @author User
 */
@Entity
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public ArrayList<Funcionario> getAtendentes() {
        return atendentes;
    }

    public void setAtendentes(ArrayList<Funcionario> atendentes) {
        this.atendentes = atendentes;
    }
    private String nome;
    private String cnpj;
    private double porcentagemComissaoEntregador;
    
    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST,
            mappedBy = "empresa")
    private ArrayList<Funcionario> atendentes;
    
    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST,
            mappedBy = "empresa")
    private ArrayList<Cliente> clientes;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    public double getPorcentagemComissaoEntregador() {
        return porcentagemComissaoEntregador;
    }

    public void setPorcentagemComissaoEntregador(double porcentagemComissaoEntregador) {
        this.porcentagemComissaoEntregador = porcentagemComissaoEntregador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }
}
