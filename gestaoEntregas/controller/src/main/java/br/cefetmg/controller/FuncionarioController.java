
package br.cefetmg.controller;

import br.cefetmg.entidades.*;
import br.cefetmg.dao.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Pedro Gabriel
 */
public class FuncionarioController {
    private FuncionarioDAO daof;
    private PerfilDAO daop;
    
    public FuncionarioController() {
        daof = new FuncionarioDAO();
        daop = new PerfilDAO();
    }
    
    public boolean cadastrar(Funcionario f) {
        System.out.println("chegou no controller de funcionario");
        daof.create(f);
        System.out.println("cadastrou o func");
        for(int i = 0; i < f.getPerfis().size(); i++) {
            daop.create(f.getPerfis().get(i));
        }
        System.out.println("cadastrou os perfis");
        return true;
    }
    
    public boolean remover(int id) {
        daof.delete(id);
        return true;
    }
    
    public boolean atualizar(Funcionario f) {
        daof.update(f);
        return true;
    }
    
    public List<Funcionario> listar() {
        return daof.listAll();
    }
    
    public List<Funcionario> pesquisar(Funcionario f) {
        return daof.pesquisarNome(f.getNome());
    }
    
    public List<Funcionario> pesquisarNome(String nome) {
        return daof.pesquisarNome(nome);
    }
    
    public Funcionario selecionar(int id) {
        return daof.selecionar(id);
    }
    
    public Funcionario selecionar(String email) {
        return daof.selecionar(email);
    }
    
    public Funcionario validarLogin(String email, String senha) {
        Funcionario f = new Funcionario();
        f.setEmail(email);
        f.setSenha(senha);
        return daof.validarLogin(f);
    }
}
