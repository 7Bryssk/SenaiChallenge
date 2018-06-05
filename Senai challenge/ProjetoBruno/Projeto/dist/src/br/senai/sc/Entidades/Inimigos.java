/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bruni
 */
@Entity
@Table(name = "inimigos")
@XmlRootElement
@NamedQueries({
    //@NamedQuery(name = "Inimigos.findByNomeInimigo", query = "SELECT i FROM Inimigos i WHERE i.Nome = :Nome"),
    @NamedQuery(name = "Inimigos.findAll", query = "SELECT i FROM Inimigos i"),
    @NamedQuery(name = "Inimigos.findByIdInimigo", query = "SELECT i FROM Inimigos i WHERE i.idInimigo = :idInimigo"),
    @NamedQuery(name = "Inimigos.findByNome", query = "SELECT i FROM Inimigos i WHERE i.nome = :nome"),
    @NamedQuery(name = "Inimigos.findByVelocidade", query = "SELECT i FROM Inimigos i WHERE i.velocidade = :velocidade"),
    @NamedQuery(name = "Inimigos.findByDano", query = "SELECT i FROM Inimigos i WHERE i.dano = :dano"),
    @NamedQuery(name = "Inimigos.findByFoto", query = "SELECT i FROM Inimigos i WHERE i.foto = :foto")})
public class Inimigos implements Serializable {
    @Column(name = "altura")
    private Integer altura;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idInimigo")
    private Integer idInimigo;
    @Column(name = "Nome")
    private String nome;
    @Column(name = "Velocidade")
    private Integer velocidade;
    @Column(name = "Dano")
    private Integer dano;
    @Column(name = "foto")
    private String foto;

    public Inimigos() {
    }

    public Inimigos(Integer idInimigo) {
        this.idInimigo = idInimigo;
    }

    public Integer getIdInimigo() {
        return idInimigo;
    }

    public void setIdInimigo(Integer idInimigo) {
        this.idInimigo = idInimigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(Integer velocidade) {
        this.velocidade = velocidade;
    }

    public Integer getDano() {
        return dano;
    }

    public void setDano(Integer dano) {
        this.dano = dano;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInimigo != null ? idInimigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inimigos)) {
            return false;
        }
        Inimigos other = (Inimigos) object;
        if ((this.idInimigo == null && other.idInimigo != null) || (this.idInimigo != null && !this.idInimigo.equals(other.idInimigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.senai.sc.Entidades.Inimigos[ idInimigo=" + idInimigo + " ]";
    }

    public Integer getAltura() {
        return altura;
    }

    public void setAltura(Integer altura) {
        this.altura = altura;
    }
    
}
