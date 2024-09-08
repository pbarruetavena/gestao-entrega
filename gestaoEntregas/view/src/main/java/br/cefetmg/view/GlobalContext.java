package br.cefetmg.view;

import br.cefetmg.entidades.Funcionario;
import br.cefetmg.entidades.Pedido;

public class GlobalContext {
    private static Funcionario currentFuncionario;
    private static Pedido currentPedido;

    public static Funcionario getCurrentFuncionario() {
        return currentFuncionario;
    }

    public static void setCurrentFuncionario(Funcionario funcionario) {
        currentFuncionario = funcionario;
    }

    public static Pedido getCurrentPedido() {
        return currentPedido;
    }

    public static void setCurrentPedido(Pedido pedido) {
        currentPedido = pedido;
    }
}
