/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.DAO;

import br.senai.sc.Entidades.Personagenspessoa;
import br.senai.sc.DAO.BasicoDAO;
import br.senai.sc.Entidades.InfoPessoa;
import br.senai.sc.Entidades.Pessoas;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bruno_verbinnen
 */
public class PersonagenspessoaDAO extends BasicoDAO{
    
    public void cadastrar(Personagenspessoa perpess) throws Exception{
        super.cadastrar(perpess);
    }    
    public Personagenspessoa getPersonagenspessoaPorID(int id) throws Exception{
        EntityManager em = getEntityManager();
        return em.find(Personagenspessoa.class, id);
    }
    public void alterar(Personagenspessoa perpess) throws Exception{
        super.alterar(perpess);
    }
    public void deletar(int id) throws Exception{
        super.excluir(Personagenspessoa.class, id);
    }
    
    public int AcharPersonagem(Personagenspessoa pp) throws Exception{
        String sql="select * from Personagenspessoa pp where pp.idpessoa = '" + pp.getIdPessoa().getIdPessoa()+ 
                "' and pp.Uso = '" + 1 + "'";
        
        java.sql.PreparedStatement sqlPrep = Conexao.getConnection().prepareStatement(sql);
        ResultSet rs = sqlPrep.executeQuery();
        
        rs.first();
        if(rs.getRow()==0){
            int i = -1;
//            JOptionPane.showMessageDialog(null, i);
            return i;
        }else{
            int cod = rs.getInt("IdPersonagem");
//            JOptionPane.showMessageDialog(null, cod);
            return cod;
        }
    }
    
    public int AcharPessPers(Personagenspessoa pp) throws Exception{
//        String sql="select * from Personagenspessoa pp where pp.idpersonagem = '" + pp.getIdPersonagem().getIdPersonagem()+" and pp.IdPessoa = '"+ pp.getIdPessoa().getIdPessoa()+"'";
        String sql="select * from Personagenspessoa pp where pp.idpessoa = '"+pp.getIdPessoa().getIdPessoa()+"' and pp.idpersonagem = '"+ pp.getIdPersonagem().getIdPersonagem()+"'";
//        String sql="select * from usuariopatos up where up.usuarioID = '" + up.getUsuarioID().getIdusuario()+"' and up.patoID = '"+ up.getPatoID().getIdpato()+"'";
        
        java.sql.PreparedStatement sqlPrep = Conexao.getConnection().prepareStatement(sql);
        ResultSet rs = sqlPrep.executeQuery();
        
        rs.first();
        int cod = rs.getInt("IdPersonagensPessoa");
        return cod;
    }
    
    public DefaultTableModel getTabelaComprados() throws Exception {
        InfoPessoa IP =  new InfoPessoa();
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Cod");
        modelo.addColumn("Nome");
        modelo.addColumn("Velocidade");
        modelo.addColumn("Vida");
        modelo.addColumn("Altura");
        modelo.addColumn("Uso");
        modelo.addColumn("Gif");
       
        List<Personagenspessoa> lista;

        EntityManager em = getEntityManager();
        em.getEntityManagerFactory().getCache().evictAll();
//        em.getTransaction().begin();    

        Query query = em.createQuery("select p from Pessoas p where p.idPessoa= :pessoaId");
        query.setParameter("pessoaId", IP.getId());
        lista = new ArrayList<>();
        for(Personagenspessoa pp : ((Pessoas)query.getSingleResult()).getPersonagenspessoaCollection()){
            lista.add(pp);
        }
        
        //select p.IdPersonagem, p.Nome, p.Velocidade, p.Vida, p.Altura, p.Preco from Personagens p, Personagenspessoa pp, pessoas pe where '1'=pp.IdPessoa and pp.IdPersonagem!=p.IdPersonagem
//        for(int i = 0; i < lista.size(); i++){
//            lista.get(i).getIdPersonagem();
//        }
        for (Personagenspessoa pp : lista) {
            
            Object[] linha = {
                pp.getIdPersonagem().getIdPersonagem(),
                pp.getIdPersonagem().getNome(),
                pp.getIdPersonagem().getVelocidade(),
                pp.getIdPersonagem().getVida(),
                pp.getIdPersonagem().getAltura()*-1,
                pp.getUso(),
                pp.getIdPersonagem().getGif()
            };
    
            
            modelo.addRow(linha);

        }
        return modelo;
    }
    
}
