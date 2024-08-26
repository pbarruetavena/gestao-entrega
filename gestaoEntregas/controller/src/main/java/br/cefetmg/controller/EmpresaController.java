
package br.cefetmg.controller;

import br.cefetmg.entidades.Empresa;
import br.cefetmg.entidades.Relatorio;
import br.cefetmg.entidades.Pedido;
import br.cefetmg.dao.*;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Pedro Gabriel
 */
public class EmpresaController {
    private EmpresaDAO daoc;
    private PedidoDAO dp;
    
    public EmpresaController() {
        daoc = new EmpresaDAO();
        dp = new PedidoDAO();
    }
    
    public boolean cadastrar(Empresa x) {
        daoc.create(x);
        return true;
    }
    
    public boolean remover(int id) {
        daoc.delete(id);
        return true;
    }
    
    public boolean atualizar(Empresa x) {
        daoc.update(x);
        return true;
    }
    
    public List<Empresa> listar() {
        return daoc.listAll();
    }
    
    public List<Empresa> pesquisar(Empresa x) {
        return daoc.pesquisarNome(x.getNome());
    }
    
    public List<Empresa> pesquisarNome(String nome) {
        return daoc.pesquisarNome(nome);
    }
    
    public Empresa selecionar(int id) {
        return daoc.selecionar(id);
    }
    
    public double getComissao(int id) {
        return daoc.selecionar(id).getPorcentagemComissaoEntregador();
    }
    
    public Relatorio getRelatorio(int id, Date inicio, Date fim) {
        return getRelatorio(selecionar(id), inicio, fim);
    }
    
    public Relatorio getRelatorio(Empresa empresa, Date inicio, Date fim) {
        List<Pedido> pedidos = dp.pesquisarPeriodo(empresa, inicio, fim);
        return new Relatorio(empresa, inicio, fim, pedidos);
    }
}
