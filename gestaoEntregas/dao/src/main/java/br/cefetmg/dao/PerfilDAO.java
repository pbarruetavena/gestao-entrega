/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.dao;


import br.cefetmg.entidades.Perfil;
import javax.persistence.*;
import java.util.List;

public class PerfilDAO {
    private EntityManagerFactory emf;
    private EntityManager em;

    public PerfilDAO() {
        emf = Persistence.createEntityManagerFactory("br.cefetmg_dao_jar_0.1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
    }

    public void create(Perfil perfil) {
        em.getTransaction().begin();
        em.persist(perfil);
        em.getTransaction().commit();
    }

    public Perfil read(int id) {
        return em.find(Perfil.class, id);
    }

    public void update(Perfil perfil) {
        em.getTransaction().begin();
        em.merge(perfil);
        em.getTransaction().commit();
    }

    public void delete(int id) {
        em.getTransaction().begin();
        Perfil perfil = em.find(Perfil.class, id);
        if (perfil != null) {
            em.remove(perfil);
        }
        em.getTransaction().commit();
    }

    public List<Perfil> listAll() {
        return em.createQuery("FROM Perfil", Perfil.class).getResultList();
    }
}

