/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.DAO;

import br.senai.sc.Entidades.Inimigos;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author bruni
 */
public class InimigosDAO extends BasicoDAO{
    public void cadastrar(Inimigos ini) throws Exception{
        super.cadastrar(ini);
    }    
    public Inimigos getInimigosPorID(int id) throws Exception{
        EntityManager em = getEntityManager();
        return em.find(Inimigos.class, id);
    }
    public int getInimigosPorNome(String nome) throws Exception{
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select i from Inimigos i where i.nome = :nome");
        query.setParameter("nome", nome);
        return ((Inimigos)query.getResultList().get(0)).getIdInimigo();    
    }
    public void alterar(Inimigos ini) throws Exception{
        super.alterar(ini);
    }
    public void deletar(int id) throws Exception{
        super.excluir(Inimigos.class, id);
    }
    
}
