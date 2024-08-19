/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.dao;


import br.cefetmg.entidades.Empresa;
import javax.persistence.*;
import java.util.List;

public class EmpresaDAO {
    private EntityManager em;

    public EmpresaDAO(EntityManager em) {
        this.em = em;
    }

    public void create(Empresa Empresa) {
        em.getTransaction().begin();
        em.persist(Empresa);
        em.getTransaction().commit();
    }

    public Empresa read(int id) {
        return em.find(Empresa.class, id);
    }

    public void update(Empresa Empresa) {
        em.getTransaction().begin();
        em.merge(Empresa);
        em.getTransaction().commit();
    }

    public void delete(int id) {
        em.getTransaction().begin();
        Empresa Empresa = em.find(Empresa.class, id);
        if (Empresa != null) {
            em.remove(Empresa);
        }
        em.getTransaction().commit();
    }

    public List<Empresa> listAll() {
        return em.createQuery("FROM Empresa", Empresa.class).getResultList();
    }
}

