/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.cefetmg.entidades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import br.cefetmg.entidades.Empresa;

/**
 *
 * @author User
 */
public class Entidades {

    public static void main(String[] args) {
        EntityManagerFactory emf;
        EntityManager em;
        emf = Persistence.createEntityManagerFactory("br.cefetmg_entidades_jar_0.1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Empresa empresa = new Empresa();
        empresa.setCnpj("111111111111");
        empresa.setNome("nome");
        empresa.setPorcentagemComissaoEntregador(3);
        
        
        em.persist(empresa);
        em.getTransaction().commit();
    }
}
