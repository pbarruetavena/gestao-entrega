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
    private EntityManagerFactory emf;
    private EntityManager em;

    public FuncionarioDAO() {
        emf = Persistence.createEntityManagerFactory("br.cefetmg_dao_jar_0.1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
    }

    public void create(Funcionario funcionario) {
        em.getTransaction().begin();
        em.persist(funcionario);
        em.getTransaction().commit();
    }

    public Funcionario read(int id) {
        return em.find(Funcionario.class, id);
    }

    public void update(Funcionario funcionario) {
        em.getTransaction().begin();
        em.merge(funcionario);
        em.getTransaction().commit();
    }

    public void delete(int id) {
        em.getTransaction().begin();
        Funcionario funcionario = em.find(Funcionario.class, id);
        if (funcionario != null) {
            em.remove(funcionario);
        }
        em.getTransaction().commit();
    }

    public List<Funcionario> listAll() {
        return em.createQuery("SELECT f FROM Funcionario f", Funcionario.class).getResultList();
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
    
    public Funcionario selecionar(String email) {
        String jpql = "SELECT f FROM Funcionario f WHERE f.email = :email";
        TypedQuery<Funcionario> query = em.createQuery(jpql, Funcionario.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }
    
    public Funcionario validarLogin(Funcionario funcionario) {

        String jpql = "SELECT f FROM Funcionario f WHERE f.email = :email AND f.senha = :senha";
        TypedQuery<Funcionario> query = em.createQuery(jpql, Funcionario.class);
        query.setParameter("email", funcionario.getEmail());
        query.setParameter("senha", funcionario.getSenha());
        Funcionario ret = query.getResultStream().findFirst().orElse(null);
        return ret;
    }
}

