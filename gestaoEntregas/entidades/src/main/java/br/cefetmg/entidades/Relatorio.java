// dado para armazenar todos os dados relatórios
package br.cefetmg.entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Pedro Gabriel
 */
public class Relatorio {
    private final Empresa empresa;
    private final List<Pedido> listaPedidos;
    private final Date inicio;
    private final Date fim;
    private Map<Funcionario, List<Pedido>> mapPedidos;
    private double total;
    private Map<Funcionario, Double> totais;
    private Map<Funcionario, Double> comissao;
    
    public Relatorio(Empresa empresa, Date inicio, Date fim, List<Pedido> pedidos) {
        this.empresa = empresa;
        this.inicio = inicio;
        this.fim = fim;
        this.listaPedidos = pedidos;
        
        double c = empresa.getPorcentagemComissaoEntregador();

        for(Funcionario funcionario : empresa.getFuncionarios()) {
            mapPedidos.put(funcionario, new ArrayList<>());
            totais.put(funcionario, Double.valueOf(0));
        }
        for(Pedido pedido : pedidos) {
            if(pedido.getAtendente()!=null) {
                mapPedidos.get(pedido.getAtendente()).add(pedido);
                Double novoValor = totais.get(pedido.getAtendente()) + pedido.getValorTotal();
                totais.put(pedido.getAtendente(), novoValor);
            }
            if(pedido.getEntregador()!=null) {
                mapPedidos.get(pedido.getEntregador()).add(pedido);
                Double novoValor = totais.get(pedido.getEntregador()) + pedido.getValorTotal();
                totais.put(pedido.getEntregador(), novoValor);
            }
        }
        for(Funcionario f : empresa.getFuncionarios()) {
            comissao.put(f, totais.get(f) * c);
        }
        
    }
    
    public List<Pedido> listaPedidos() {
        return listaPedidos;
    }
    
    public Map<Funcionario, List<Pedido>> getPedidos() {
        return mapPedidos;
    }
    
    public Map<Funcionario, Double> getTotais() {
        return totais;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public Date getInicio() {
        return inicio;
    }

    public Date getFim() {
        return fim;
    }

    public double getTotal() {
        return total;
    }

    public Map<Funcionario, Double> getComissao() {
        return comissao;
    }
}
