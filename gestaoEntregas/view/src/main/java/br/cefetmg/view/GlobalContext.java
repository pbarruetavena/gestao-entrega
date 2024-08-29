
package br.cefetmg.view;

import br.cefetmg.entidades.Funcionario;

/**
 *
 * @author Pedro Gabriel
 * Classe global para armazenar o login da sessão
 */
public class GlobalContext {
    private static Funcionario currentFuncionario;

    public static Funcionario getCurrentFuncionario() {
        return currentFuncionario;
    }

    public static void setCurrentFuncionario(Funcionario funcionario) {
        currentFuncionario = funcionario;
    }

}
