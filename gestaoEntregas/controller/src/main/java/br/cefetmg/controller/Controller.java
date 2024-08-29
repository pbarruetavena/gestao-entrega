

package br.cefetmg.controller;

import br.cefetmg.dao.Dao;
import br.cefetmg.dao.EmpresaDAO;
import br.cefetmg.dao.FuncionarioDAO;
import br.cefetmg.entidades.Empresa;
import br.cefetmg.entidades.Funcionario;
import br.cefetmg.entidades.Perfil;
import br.cefetmg.entidades.TipoPerfil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro Gabriel
 */
public class Controller {

    public static void main(String[] args) {
        EmpresaController daoe = new EmpresaController();
        if(daoe.listar().isEmpty()) {
            System.out.println("entrou no in");
            Empresa empresa = new Empresa();
            empresa.setCnpj("11122233300000");
            empresa.setNome("Empresa Default");
            empresa.setPorcentagemComissaoEntregador(1);

            daoe.cadastrar(empresa);
            System.out.println("cadastrou a empresa");

            Funcionario funcionario = new Funcionario();
            funcionario.setEmail("meu.funcionario.legal@gmail.com");
            funcionario.setEmpresa(empresa);
            funcionario.setNome("Funcionário Default");
            funcionario.setSenha("123456");
            funcionario.setTelefone("31912345678");

            List<Perfil> perfis = new ArrayList<>();
            Perfil p = new Perfil();
            p.setFuncionario(funcionario);
            p.setTipo(TipoPerfil.ATENDENTE);
            perfis.add(p);
            Perfil p2 = new Perfil();
            p2.setFuncionario(funcionario);
            p2.setTipo(TipoPerfil.ENTREGADOR);
            perfis.add(p2);
            Perfil p3 = new Perfil();
            p3.setFuncionario(funcionario);
            p3.setTipo(TipoPerfil.ADMINISTRADOR);
            perfis.add(p3);
            funcionario.setPerfis((ArrayList<Perfil>) perfis);
            
            FuncionarioController f = new FuncionarioController();
            f.cadastrar(funcionario);
            System.out.println("cadastrou func e perfil");
        }
    }
}
