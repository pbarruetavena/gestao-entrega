
package br.cefetmg.controller;

import br.cefetmg.entidades.Pedido;
import br.cefetmg.dao.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Pedro Gabriel
 */
public class PedidoController {
    private PedidoDAO daoc;
    
    public PedidoController() {
        daoc = new PedidoDAO();
    }
    
    public boolean cadastrar(Pedido x) {
        daoc.create(x);
        return true;
    }
    
    public boolean remover(int id) {
        daoc.delete(id);
        return true;
    }
    
    public boolean atualizar(Pedido x) {
        daoc.update(x);
        return true;
    }
    
    public List<Pedido> listar() {
        return daoc.listAll();
    }
    
    public List<Pedido> pesquisar(Pedido c) {
        return daoc.pesquisarData(c.getData().toString());
    }
    
    public List<Pedido> pesquisarNome(String data) {
        return daoc.pesquisarData(data);
    }
    
    public Pedido selecionar(int id) {
        return daoc.selecionar(id);
    }
}
