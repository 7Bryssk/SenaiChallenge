/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.Entidades;

import br.senai.sc.DAO.PersonagensDAO;
import br.senai.sc.DAO.PersonagenspessoaDAO;
import br.senai.sc.DAO.PessoasDAO;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.swing.JOptionPane;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bruni
 */
@Entity
@Table(name = "personagenspessoa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personagenspessoa.findAll", query = "SELECT p FROM Personagenspessoa p"),
    @NamedQuery(name = "Personagenspessoa.findByIdPersonagensPessoa", query = "SELECT p FROM Personagenspessoa p WHERE p.idPersonagensPessoa = :idPersonagensPessoa"),
    @NamedQuery(name = "Personagenspessoa.findByUso", query = "SELECT p FROM Personagenspessoa p WHERE p.uso = :uso")})
public class Personagenspessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPersonagensPessoa")
    private Integer idPersonagensPessoa;
    @Column(name = "Uso")
    private Boolean uso;
    @JoinColumn(name = "IdPessoa", referencedColumnName = "idPessoa")
    @ManyToOne(optional = false)
    private Pessoas idPessoa;
    @JoinColumn(name = "IdPersonagem", referencedColumnName = "idPersonagem")
    @ManyToOne(optional = false)
    private Personagens idPersonagem;

    public Personagenspessoa() {
    }

    public Personagenspessoa(Integer idPersonagensPessoa) {
        this.idPersonagensPessoa = idPersonagensPessoa;
    }

    public Integer getIdPersonagensPessoa() {
        return idPersonagensPessoa;
    }

    public void setIdPersonagensPessoa(Integer idPersonagensPessoa) {
        this.idPersonagensPessoa = idPersonagensPessoa;
    }

    public Boolean getUso() {
        return uso;
    }

    public void setUso(Boolean uso) {
        this.uso = uso;
    }

    public Pessoas getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Pessoas idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Personagens getIdPersonagem() {
        return idPersonagem;
    }

    public void setIdPersonagem(Personagens idPersonagem) {
        this.idPersonagem = idPersonagem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersonagensPessoa != null ? idPersonagensPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personagenspessoa)) {
            return false;
        }
        Personagenspessoa other = (Personagenspessoa) object;
        if ((this.idPersonagensPessoa == null && other.idPersonagensPessoa != null) || (this.idPersonagensPessoa != null && !this.idPersonagensPessoa.equals(other.idPersonagensPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.senai.sc.Entidades.Personagenspessoa[ idPersonagensPessoa=" + idPersonagensPessoa + " ]";
    }

    public void Comprar(int id) {
        Pessoas pess = new Pessoas();
        PessoasDAO pessDAO = new PessoasDAO();
        Personagens pers = new Personagens();
        PersonagensDAO persDAO = new PersonagensDAO();
        Personagenspessoa pp = new Personagenspessoa();
        PersonagenspessoaDAO ppDAO = new PersonagenspessoaDAO();
        InfoPessoa IP = new InfoPessoa();
        
        try {
            pess = pessDAO.getPessoaPorID(IP.getId());
            pers = persDAO.getPersonagemPorID(id);
        } catch (Exception ex) {
            Logger.getLogger(Personagenspessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(pess.getMoedas()>=pers.getPreco()){
            pess.setMoedas(pess.getMoedas()-pers.getPreco());
            pp.setIdPersonagem(pers);
            pp.setIdPessoa(pess);
            try {
                if(ppDAO.AcharPersonagem(pp)!=-1){
                    pp.setUso(false);
                }else{
                    pp.setUso(true);
                }
            } catch (Exception ex) {
                Logger.getLogger(Personagenspessoa.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                pessDAO.alterar(pess);
                ppDAO.cadastrar(pp);
                JOptionPane.showMessageDialog(null, "Comprado com Sucesso!");
            } catch (Exception ex) {
                Logger.getLogger(Personagenspessoa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Não há Moedas Suficientes para Comprar esse Personagem!");
        }
        
        
    }

    public void Escolher(int id) {
        Pessoas pess = new Pessoas();
        PessoasDAO pessDAO = new PessoasDAO();
        Personagens pers = new Personagens();
        PersonagensDAO persDAO = new PersonagensDAO();
        Personagenspessoa pp = new Personagenspessoa();
        PersonagenspessoaDAO ppDAO = new PersonagenspessoaDAO();
        InfoPessoa IP = new InfoPessoa();
        
        try {
            pess.setIdPessoa(IP.getId());
            pp.setIdPessoa(pess);
            pers.setIdPersonagem(ppDAO.AcharPersonagem(pp));
            pp.setIdPersonagem(pers);
            int idpp = ppDAO.AcharPessPers(pp);
            pp = ppDAO.getPersonagenspessoaPorID(idpp);
            pp.setUso(false);
            ppDAO.alterar(pp);
            pers.setIdPersonagem(id);
            pp.setIdPersonagem(pers);
            idpp = ppDAO.AcharPessPers(pp);
            pp = ppDAO.getPersonagenspessoaPorID(idpp);
            pp.setUso(true);
            ppDAO.alterar(pp);
            
            
            
            
            
            
            
            
            
//            JOptionPane.showMessageDialog(null, "Escolhido com Sucesso!");
            
        } catch (Exception ex) {
            Logger.getLogger(Personagenspessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
