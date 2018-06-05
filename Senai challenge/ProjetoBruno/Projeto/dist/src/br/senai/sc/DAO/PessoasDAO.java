
package br.senai.sc.DAO;
import br.senai.sc.Entidades.Pessoas;
import br.senai.sc.DAO.BasicoDAO;
import br.senai.sc.Entidades.InfoPessoa;
import java.sql.ResultSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bruno_verbinnen
 */
public class PessoasDAO extends BasicoDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPU");
    
    public void cadastrar(Pessoas pessoas) throws Exception{
        super.cadastrar(pessoas);
    }    
    public Pessoas getPessoaPorID(int id) throws Exception{
        EntityManager em = getEntityManager();
        return em.find(Pessoas.class, id);
    }
    public void alterar(Pessoas pessoa) throws Exception{
        super.alterar(pessoa);
    }
    public void deletar(int id) throws Exception{
        super.excluir(Pessoas.class, id);
    }
    
    public DefaultTableModel getTabelaPesquisa(String pesquisa) throws Exception{
        DefaultTableModel modelo = new DefaultTableModel();
        Pessoas pess = new Pessoas();
        modelo.addColumn("Código Pessoa");
        modelo.addColumn("Nome");
        modelo.addColumn("Login");
        modelo.addColumn("Senha");
        modelo.addColumn("Pontuação");
        modelo.addColumn("Moedas");
        modelo.addColumn("ADM");
        
        List<Pessoas> lista = null;
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        
        lista = em.createQuery("select * from Pessoas where Cliente.Pessoa_ID=Pessoas.ID_Pessoa and pessoas.nome 'like :"+"%"+pesquisa+"%'").getResultList();
        for(Pessoas pessoas : lista){
            Object [] linha = { pessoas.getIdPessoa(),
                                pessoas.getNome(),
                                pessoas.getLogin(),
                                pessoas.getSenha(),
                                pessoas.getPontuacao(),
                                pessoas.getMoedas(),
                                pessoas.getAdm()
                              };
                modelo.addRow(linha);
            }
        return modelo;
    }
    
    public DefaultTableModel getMd10() throws Exception{
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nome");
        modelo.addColumn("Pontuação");
        
        List<Pessoas> lista = null;
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        
        lista = em.createNamedQuery("Pessoas.find10").setMaxResults(10).getResultList();
        
        for(Pessoas pessoas : lista){
            Object [] linha = { pessoas.getNome(),
                                pessoas.getPontuacao()
                              };
                modelo.addRow(linha);
            }
        return modelo;
    }
    
    
    
    public int validarLogin(Pessoas pess) throws Exception{
        String sql="select * from Pessoas p where p.login = '" + pess.getLogin() + 
                "' and p.senha = '" + pess.getSenha() + "'";
        
        java.sql.PreparedStatement sqlPrep = Conexao.getConnection().prepareStatement(sql);
        ResultSet rs = sqlPrep.executeQuery();
        
        rs.first();
        
        if(rs.getRow() == 0)        
            return 0;
        else{
            InfoPessoa.setAdm(false);
            InfoPessoa.setId(rs.getInt("IdPessoa"));
            InfoPessoa.setNome(rs.getString("Nome"));
            
            return 1;
        }
    }  
}
