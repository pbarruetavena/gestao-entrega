/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.dao;


import br.cefetmg.entidades.ItemPed;
import javax.persistence.*;
import java.util.List;

public class ItemPedDAO {
    private EntityManager em;

    public ItemPedDAO(EntityManager em) {
        this.em = em;
    }

    public void create(ItemPed ItemPed) {
        em.getTransaction().begin();
        em.persist(ItemPed);
        em.getTransaction().commit();
    }

    public ItemPed read(int id) {
        return em.find(ItemPed.class, id);
    }

    public void update(ItemPed ItemPed) {
        em.getTransaction().begin();
        em.merge(ItemPed);
        em.getTransaction().commit();
    }

    public void delete(int id) {
        em.getTransaction().begin();
        ItemPed ItemPed = em.find(ItemPed.class, id);
        if (ItemPed != null) {
            em.remove(ItemPed);
        }
        em.getTransaction().commit();
    }

    public List<ItemPed> listAll() {
        return em.createQuery("FROM ItemPed", ItemPed.class).getResultList();
    }
}

