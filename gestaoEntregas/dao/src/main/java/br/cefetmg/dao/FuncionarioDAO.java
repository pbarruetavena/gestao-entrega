/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.dao;


import br.cefetmg.entidades.Funcionario;
import javax.persistence.*;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;

public class FuncionarioDAO {
    private EntityManager em;

    public FuncionarioDAO(EntityManager em) {
        this.em = em;
    }

    public void create(Funcionario Funcionario) {
        em.getTransaction().begin();
        em.persist(Funcionario);
        em.getTransaction().commit();
    }

    public Funcionario read(int id) {
        return em.find(Funcionario.class, id);
    }

    public void update(Funcionario Funcionario) {
        em.getTransaction().begin();
        em.merge(Funcionario);
        em.getTransaction().commit();
    }

    public void delete(int id) {
        em.getTransaction().begin();
        Funcionario Funcionario = em.find(Funcionario.class, id);
        if (Funcionario != null) {
            em.remove(Funcionario);
        }
        em.getTransaction().commit();
    }

    public List<Funcionario> listAll() {
        return em.createQuery("FROM Funcionario", Funcionario.class).getResultList();
    }
    
    public Funcionario selecionar(int id) {
        em.getTransaction().begin();
        Funcionario f = em.find(Funcionario.class, id);
        return f;
    }
    
    public List<Funcionario> pesquisarNome(String nome) {
        var cb = em.getCriteriaBuilder();
        CriteriaQuery<Funcionario> criteria = cb.createQuery(Funcionario.class);
        var root = criteria.from(Funcionario.class);
        criteria.select(root).where(cb.like(root.get("nome"), "%"+nome+"%"));
        List<Funcionario> lista = em.createQuery(criteria).getResultList();
        return lista;
    }
}

