/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.dao;


import br.cefetmg.entidades.Perfil;
import javax.persistence.*;
import java.util.List;

public class PerfilDAO {
    private EntityManager em;

    public PerfilDAO(EntityManager em) {
        this.em = em;
    }

    public void create(Perfil Perfil) {
        em.getTransaction().begin();
        em.persist(Perfil);
        em.getTransaction().commit();
    }

    public Perfil read(int id) {
        return em.find(Perfil.class, id);
    }

    public void update(Perfil Perfil) {
        em.getTransaction().begin();
        em.merge(Perfil);
        em.getTransaction().commit();
    }

    public void delete(int id) {
        em.getTransaction().begin();
        Perfil Perfil = em.find(Perfil.class, id);
        if (Perfil != null) {
            em.remove(Perfil);
        }
        em.getTransaction().commit();
    }

    public List<Perfil> listAll() {
        return em.createQuery("FROM Perfil", Perfil.class).getResultList();
    }
}

