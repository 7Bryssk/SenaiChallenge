/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author bruno_verbinnen
 */
public class BasicoDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPU");
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void cadastrar(Object o) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();
        em.persist(o);
        em.getTransaction().commit();
        em.close();
    }

    public void alterar(Object o) {
        EntityManager em = getEntityManager();
        
        em.getTransaction().begin();
        em.merge(o);
        em.getTransaction().commit();
        em.close();
    }
    
    public void excluir(Class <?> classe, int id){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(classe, id));
        em.getTransaction().commit();
        em.close();       
    }
}
