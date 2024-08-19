/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.dao;


import br.cefetmg.entidades.Produto;
import javax.persistence.*;
import java.util.List;

public class ProdutoDAO {
    private EntityManager em;

    public ProdutoDAO(EntityManager em) {
        this.em = em;
    }

    public void create(Produto Produto) {
        em.getTransaction().begin();
        em.persist(Produto);
        em.getTransaction().commit();
    }

    public Produto read(int id) {
        return em.find(Produto.class, id);
    }

    public void update(Produto Produto) {
        em.getTransaction().begin();
        em.merge(Produto);
        em.getTransaction().commit();
    }

    public void delete(int id) {
        em.getTransaction().begin();
        Produto Produto = em.find(Produto.class, id);
        if (Produto != null) {
            em.remove(Produto);
        }
        em.getTransaction().commit();
    }

    public List<Produto> listAll() {
        return em.createQuery("FROM Produto", Produto.class).getResultList();
    }
}


