
package br.cefetmg.controller;

import br.cefetmg.entidades.*;
import br.cefetmg.dao.*;

/**
 *
 * @author Aluno
 */
public class SessionContext {
    private static Funcionario currentUser;

    public static Funcionario getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(Funcionario user) {
        currentUser = user;
    }
}
