/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.dao;


import br.cefetmg.entidades.Empresa;
import javax.persistence.*;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;

public class EmpresaDAO {
    private EntityManagerFactory emf;
    private EntityManager em;

    public EmpresaDAO() {
        emf = Persistence.createEntityManagerFactory("br.cefetmg_dao_jar_0.1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
    }

    public void create(Empresa empresa) {
        System.out.println(em.toString());;
        
        em.getTransaction().begin();
        em.persist(empresa);
        em.getTransaction().commit();
    }

    public Empresa read(int id) {
        return em.find(Empresa.class, id);
    }

    public void update(Empresa empresa) {
        em.getTransaction().begin();
        em.merge(empresa);
        em.getTransaction().commit();
    }

    public void delete(int id) {
        em.getTransaction().begin();
        Empresa empresa = em.find(Empresa.class, id);
        if (empresa != null) {
            em.remove(empresa);
        }
        em.getTransaction().commit();
    }

    public List<Empresa> listAll() {
        return em.createQuery("SELECT e FROM Empresa e", Empresa.class).getResultList();
    }
    
    public Empresa selecionar(int id) {
        em.getTransaction().begin();
        Empresa x = em.find(Empresa.class, id);
        return x;
    }
    
    public List<Empresa> pesquisarNome(String nome) {
        var cb = em.getCriteriaBuilder();
        CriteriaQuery<Empresa> criteria = cb.createQuery(Empresa.class);
        var root = criteria.from(Empresa.class);
        criteria.select(root).where(cb.like(root.get("nome"), "%"+nome+"%"));
        List<Empresa> lista = em.createQuery(criteria).getResultList();
        return lista;
    }
    
    public static void main(String[] args) {
        
        
        EmpresaDAO dao = new EmpresaDAO();
        List<Empresa> empresas = dao.listAll();
        for(Empresa e : empresas) {
            System.out.println(e.getNome());
        }
    }
}

