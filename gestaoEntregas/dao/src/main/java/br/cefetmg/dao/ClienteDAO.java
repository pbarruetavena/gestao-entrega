
package br.cefetmg.dao;


import br.cefetmg.entidades.Cliente;
import javax.persistence.*;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;

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
    
    public Cliente selecionar(int id) {
        em.getTransaction().begin();
        Cliente c = em.find(Cliente.class, id);
        return c;
    }
    
    public List<Cliente> pesquisarNome(String nome) {
        var cb = em.getCriteriaBuilder();
        CriteriaQuery<Cliente> criteria = cb.createQuery(Cliente.class);
        var root = criteria.from(Cliente.class);
        criteria.select(root).where(cb.like(root.get("nome"), "%"+nome+"%"));
        List<Cliente> lista = em.createQuery(criteria).getResultList();
        return lista;
    }
}
