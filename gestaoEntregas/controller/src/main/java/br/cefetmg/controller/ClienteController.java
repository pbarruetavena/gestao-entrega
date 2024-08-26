
package br.cefetmg.controller;

import br.cefetmg.entidades.Cliente;
import br.cefetmg.dao.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Pedro Gabriel
 */
public class ClienteController {
    private ClienteDAO daoc;
    
    public ClienteController() {
        daoc = new ClienteDAO();
    }
    
    public boolean cadastrar(Cliente c) {
        daoc.create(c);
        return true;
    }
    
    public boolean remover(int id) {
        daoc.delete(id);
        return true;
    }
    
    public boolean atualizar(Cliente c) {
        daoc.update(c);
        return true;
    }
    
    public List<Cliente> listar() {
        return daoc.listAll();
    }
    
    public List<Cliente> pesquisar(Cliente c) {
        return daoc.pesquisarNome(c.getNome());
    }
    
    public List<Cliente> pesquisarNome(String nome) {
        return daoc.pesquisarNome(nome);
    }
    
    public Cliente selecionar(int id) {
        return daoc.selecionar(id);
    }
}
