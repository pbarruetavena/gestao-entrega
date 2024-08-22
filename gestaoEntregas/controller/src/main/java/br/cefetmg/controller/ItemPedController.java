
package br.cefetmg.controller;

import br.cefetmg.entidades.ItemPed;
import br.cefetmg.dao.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Pedro Gabriel
 */
public class ItemPedController {
    private ItemPedDAO daoc;
    
    public boolean cadastrar(ItemPed x) {
        daoc.create(x);
        return true;
    }
    
    public boolean remover(int id) {
        daoc.delete(id);
        return true;
    }
    
    public boolean atualizar(ItemPed x) {
        daoc.update(x);
        return true;
    }
    
    public List<ItemPed> listar() {
        return daoc.listAll();
    }
    
    public ItemPed selecionar(int id) {
        return daoc.selecionar(id);
    }
}
