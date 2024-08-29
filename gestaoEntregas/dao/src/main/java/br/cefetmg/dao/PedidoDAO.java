/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.dao;


import br.cefetmg.entidades.Empresa;
import br.cefetmg.entidades.Pedido;
import java.util.Date;
import javax.persistence.*;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;

public class PedidoDAO {
    private EntityManagerFactory emf;
    private EntityManager em;

    public PedidoDAO() {
        emf = Persistence.createEntityManagerFactory("br.cefetmg_dao_jar_0.1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
    }

    public void create(Pedido pedido) {
        em.getTransaction().begin();
        em.persist(pedido);
        em.getTransaction().commit();
    }

    public Pedido read(int id) {
        return em.find(Pedido.class, id);
    }

    public void update(Pedido pedido) {
        em.getTransaction().begin();
        em.merge(pedido);
        em.getTransaction().commit();
    }

    public void delete(int id) {
        em.getTransaction().begin();
        Pedido pedido = em.find(Pedido.class, id);
        if (pedido != null) {
            em.remove(pedido);
        }
        em.getTransaction().commit();
    }

    public List<Pedido> listAll() {
        return em.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();
    }
    
    public Pedido selecionar(int id) {
        em.getTransaction().begin();
        Pedido x = em.find(Pedido.class, id);
        return x;
    }
    
    public List<Pedido> pesquisarData(String data) {
        var cb = em.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteria = cb.createQuery(Pedido.class);
        var root = criteria.from(Pedido.class);
        criteria.select(root).where(cb.like(root.get("data"), "%"+data+"%"));
        List<Pedido> lista = em.createQuery(criteria).getResultList();
        return lista;
    }
    
    public List<Pedido> pesquisarPeriodo(Empresa empresa, Date startDate, Date endDate) {
        String jpql = "SELECT p FROM Pedido p JOIN p.cliente c WHERE c.empresa = :empresa AND p.dt BETWEEN :startDate AND :endDate";
        TypedQuery<Pedido> query = em.createQuery(jpql, Pedido.class);
        query.setParameter("empresa", empresa);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }
}

