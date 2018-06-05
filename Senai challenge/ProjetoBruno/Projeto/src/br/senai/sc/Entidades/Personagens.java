/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.Entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bruni
 */
@Entity
@Table(name = "personagens")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personagens.findAll", query = "SELECT p FROM Personagens p"),
    @NamedQuery(name = "Personagens.findByIdPersonagem", query = "SELECT p FROM Personagens p WHERE p.idPersonagem = :idPersonagem"),
    @NamedQuery(name = "Personagens.findByNome", query = "SELECT p FROM Personagens p WHERE p.nome = :nome"),
    @NamedQuery(name = "Personagens.findByVelocidade", query = "SELECT p FROM Personagens p WHERE p.velocidade = :velocidade"),
    @NamedQuery(name = "Personagens.findByVida", query = "SELECT p FROM Personagens p WHERE p.vida = :vida"),
    @NamedQuery(name = "Personagens.findByAltura", query = "SELECT p FROM Personagens p WHERE p.altura = :altura"),
    @NamedQuery(name = "Personagens.findByPreco", query = "SELECT p FROM Personagens p WHERE p.preco = :preco"),
    @NamedQuery(name = "Personagens.findByFoto", query = "SELECT p FROM Personagens p WHERE p.foto = :foto")})
public class Personagens implements Serializable {
    @Column(name = "gif")
    private String gif;
    @Column(name = "pulo")
    private String pulo;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPersonagem")
    private Integer idPersonagem;
    @Column(name = "Nome")
    private String nome;
    @Column(name = "Velocidade")
    private Integer velocidade;
    @Column(name = "Vida")
    private Integer vida;
    @Column(name = "Altura")
    private Integer altura;
    @Column(name = "Preco")
    private Integer preco;
    @Column(name = "foto")
    private String foto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersonagem")
    private Collection<Personagenspessoa> personagenspessoaCollection;

    public Personagens() {
    }

    public Personagens(Integer idPersonagem) {
        this.idPersonagem = idPersonagem;
    }

    public Integer getIdPersonagem() {
        return idPersonagem;
    }

    public void setIdPersonagem(Integer idPersonagem) {
        this.idPersonagem = idPersonagem;
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

    public Integer getVida() {
        return vida;
    }

    public void setVida(Integer vida) {
        this.vida = vida;
    }

    public Integer getAltura() {
        return altura;
    }

    public void setAltura(Integer altura) {
        this.altura = altura;
    }

    public Integer getPreco() {
        return preco;
    }

    public void setPreco(Integer preco) {
        this.preco = preco;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @XmlTransient
    public Collection<Personagenspessoa> getPersonagenspessoaCollection() {
        return personagenspessoaCollection;
    }

    public void setPersonagenspessoaCollection(Collection<Personagenspessoa> personagenspessoaCollection) {
        this.personagenspessoaCollection = personagenspessoaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersonagem != null ? idPersonagem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personagens)) {
            return false;
        }
        Personagens other = (Personagens) object;
        if ((this.idPersonagem == null && other.idPersonagem != null) || (this.idPersonagem != null && !this.idPersonagem.equals(other.idPersonagem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.senai.sc.Entidades.Personagens[ idPersonagem=" + idPersonagem + " ]";
    }

    public String getGif() {
        return gif;
    }

    public void setGif(String gif) {
        this.gif = gif;
    }

    public String getPulo() {
        return pulo;
    }

    public void setPulo(String pulo) {
        this.pulo = pulo;
    }
    
}
