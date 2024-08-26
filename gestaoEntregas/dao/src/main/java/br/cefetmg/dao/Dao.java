/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.cefetmg.dao;

import br.cefetmg.entidades.Empresa;
import br.cefetmg.entidades.Funcionario;

/**
 *
 * @author User
 */
public class Dao {

    public static void main(String[] args) {
        Empresa empresa = new Empresa();
        empresa.setCnpj("6464");
        empresa.setNome("fdsfsdfsaf");
        empresa.setPorcentagemComissaoEntregador(3);
        
        EmpresaDAO dao = new EmpresaDAO();
        dao.create(empresa);
        
        Funcionario funcionario = new Funcionario();
        funcionario.setEmail("@gghfghdh");
        funcionario.setEmpresa(empresa);
        funcionario.setNome("fsadfsaf");
        funcionario.setSenha("123456");
        funcionario.setTelefone("12345678");
        
        FuncionarioDAO f = new FuncionarioDAO();
        f.create(funcionario);
    }
}
