/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.dao;


import br.cefetmg.entidades.Cliente;
import javax.persistence.*;
import java.util.List;

public class ClienteDAO {
    private EntityManager em;

    public ClienteDAO(EntityManager em) {
        this.em = em;
    }

    public void create(Cliente cliente) {
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
    }

    public Cliente read(int id) {
        return em.find(Cliente.class, id);
    }

    public void update(Cliente cliente) {
        em.getTransaction().begin();
        em.merge(cliente);
        em.getTransaction().commit();
    }

    public void delete(int id) {
        em.getTransaction().begin();
        Cliente cliente = em.find(Cliente.class, id);
        if (cliente != null) {
            em.remove(cliente);
        }
        em.getTransaction().commit();
    }

    public List<Cliente> listAll() {
        return em.createQuery("FROM Cliente", Cliente.class).getResultList();
    }
}
