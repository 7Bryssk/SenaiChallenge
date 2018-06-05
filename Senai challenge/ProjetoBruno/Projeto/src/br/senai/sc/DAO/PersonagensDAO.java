/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.DAO;

import br.senai.sc.Entidades.InfoPessoa;
import br.senai.sc.Entidades.Personagens;
import br.senai.sc.Entidades.Personagenspessoa;
import br.senai.sc.Entidades.Pessoas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bruno_verbinnen
 */
public class PersonagensDAO extends BasicoDAO {
    
    public void cadastrar(Personagens pers) throws Exception{
        super.cadastrar(pers);
    }    
    public Personagens getPersonagemPorID(int id) throws Exception{
        EntityManager em = getEntityManager();
        return em.find(Personagens.class, id);
    }
    public void alterar(Personagens pers) throws Exception{
        super.alterar(pers);
    }
    public void deletar(int id) throws Exception{
        super.excluir(Personagens.class, id);
    }
    
    public DefaultTableModel getTabela() throws Exception {

        DefaultTableModel modelo = new DefaultTableModel();

       
        modelo.addColumn("Nome");
        modelo.addColumn("Velocidade");
        modelo.addColumn("Vida");
        modelo.addColumn("Altura");
        modelo.addColumn("Preço");
       
        List<Personagens> lista = null;

        EntityManager em = getEntityManager();
        em.getTransaction().begin();

        lista = em.createNamedQuery("Personagens.findLessZero")
                .getResultList(); 
       
        for(int i = 0; i < lista.size(); i++){
            lista.get(i).getIdPersonagem();
        }
        for (Personagens personagem : lista) {
            
            Object[] linha = {
                personagem.getNome(),
                personagem.getVelocidade(),
                personagem.getVida(),
                personagem.getAltura(),
                personagem.getPreco()
            };
    
            
            modelo.addRow(linha);

        }
        return modelo;
    }
    
    public DefaultTableModel getTabelaNComprados() throws Exception {
        InfoPessoa IP = new InfoPessoa();
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Cod");
        modelo.addColumn("Nome");
        modelo.addColumn("Velocidade");
        modelo.addColumn("Vida");
        modelo.addColumn("Altura");
        modelo.addColumn("Preço");
        modelo.addColumn("icone");
       
        List<Personagens> lista;

        EntityManager em = getEntityManager();
        em.getEntityManagerFactory().getCache().evictAll();  

        Query query = em.createQuery("select p from Pessoas p where p.idPessoa = :idpessoa");
        query.setParameter("idpessoa", IP.getId());
        Pessoas pess = (Pessoas)query.getSingleResult();
//        Usuarios user = (Usuarios)query.getSingleResult();
        
        Query query2 = em.createQuery("select p from Personagens p");
        lista = query2.getResultList();
        for(Personagenspessoa pp : pess.getPersonagenspessoaCollection()){
            for(Personagens pers : lista){
                if(pp.getIdPersonagem().getIdPersonagem() == pers.getIdPersonagem()){
                    lista.remove(pers);
                    break;
                }
            }
        }
        
        
        
        //select p.IdPersonagem, p.Nome, p.Velocidade, p.Vida, p.Altura, p.Preco from Personagens p, Personagenspessoa pp, pessoas pe where '1'=pp.IdPessoa and pp.IdPersonagem!=p.IdPersonagem
//        for(int i = 0; i < lista.size(); i++){
//            lista.get(i).getIdPersonagem();
//        }
        for (Personagens personagem : lista) {
            
            Object[] linha = {
                personagem.getIdPersonagem(),
                personagem.getNome(),
                personagem.getVelocidade(),
                personagem.getVida(),
                personagem.getAltura()*-1,
                personagem.getPreco(),
                personagem.getGif()
            };
    
            
            modelo.addRow(linha);

        }
        return modelo;
    }
    
    
    
    
}
