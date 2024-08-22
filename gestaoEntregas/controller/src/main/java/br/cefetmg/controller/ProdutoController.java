
package br.cefetmg.controller;

import br.cefetmg.entidades.Produto;
import br.cefetmg.dao.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Pedro Gabriel
 */
public class ProdutoController {
    private ProdutoDAO dao;
    
    public boolean cadastrar(Produto x) {
        dao.create(x);
        return true;
    }
    
    public boolean remover(int id) {
        dao.delete(id);
        return true;
    }
    
    public boolean atualizar(Produto x) {
        dao.update(x);
        return true;
    }
    
    public List<Produto> listar() {
        return dao.listAll();
    }
    
    public List<Produto> pesquisar(Produto p) {
        return dao.pesquisarNome(p.getNome());
    }
    
    public List<Produto> pesquisarNome(String nome) {
        return dao.pesquisarNome(nome);
    }
    
    public Produto selecionar(int id) {
        return dao.selecionar(id);
    }
}
