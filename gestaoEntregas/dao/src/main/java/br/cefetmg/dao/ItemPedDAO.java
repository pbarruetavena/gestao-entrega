/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.dao;


import br.cefetmg.entidades.ItemPed;
import javax.persistence.*;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;

public class ItemPedDAO {
    private EntityManagerFactory emf;
    private EntityManager em;

    public ItemPedDAO() {
        emf = Persistence.createEntityManagerFactory("br.cefetmg_dao_jar_0.1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
    }

    public void create(ItemPed itemPed) {
        em.getTransaction().begin();
        em.persist(itemPed);
        em.getTransaction().commit();
    }

    public ItemPed read(int id) {
        return em.find(ItemPed.class, id);
    }

    public void update(ItemPed itemPed) {
        em.getTransaction().begin();
        em.merge(itemPed);
        em.getTransaction().commit();
    }

    public void delete(int id) {
        em.getTransaction().begin();
        ItemPed itemPed = em.find(ItemPed.class, id);
        if (itemPed != null) {
            em.remove(itemPed);
        }
        em.getTransaction().commit();
    }

    public List<ItemPed> listAll() {
        return em.createQuery("SELECT i FROM ItemPed i", ItemPed.class).getResultList();
    }
    
    public ItemPed selecionar(int id) {
        em.getTransaction().begin();
        ItemPed x = em.find(ItemPed.class, id);
        return x;
    }
}

