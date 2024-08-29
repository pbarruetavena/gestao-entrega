/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.dao;


import br.cefetmg.entidades.Produto;
import javax.persistence.*;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;

public class ProdutoDAO {
    private EntityManagerFactory emf;
    private EntityManager em;

    public ProdutoDAO() {
        emf = Persistence.createEntityManagerFactory("br.cefetmg_dao_jar_0.1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
    }

    public void create(Produto produto) {
        em.getTransaction().begin();
        em.persist(produto);
        em.getTransaction().commit();
    }

    public Produto read(int id) {
        return em.find(Produto.class, id);
    }

    public void update(Produto produto) {
        em.getTransaction().begin();
        em.merge(produto);
        em.getTransaction().commit();
    }

    public void delete(int id) {
        em.getTransaction().begin();
        Produto produto = em.find(Produto.class, id);
        if (produto != null) {
            em.remove(produto);
        }
        em.getTransaction().commit();
    }

    public List<Produto> listAll() {
        return em.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
    }
    
    public Produto selecionar(int id) {
        em.getTransaction().begin();
        Produto x = em.find(Produto.class, id);
        return x;
    }
    
    public List<Produto> pesquisarNome(String nome) {
        var cb = em.getCriteriaBuilder();
        CriteriaQuery<Produto> criteria = cb.createQuery(Produto.class);
        var root = criteria.from(Produto.class);
        criteria.select(root).where(cb.like(root.get("nome"), "%"+nome+"%"));
        List<Produto> lista = em.createQuery(criteria).getResultList();
        return lista;
    }
}


