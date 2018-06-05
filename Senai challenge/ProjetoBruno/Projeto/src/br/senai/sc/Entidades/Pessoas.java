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
 * @author bruno_verbinnen
 */
@Entity
@Table(name = "pessoas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pessoas.find10", query ="SELECT p FROM Pessoas p ORDER BY p.pontuacao DESC"),
    @NamedQuery(name = "Pessoas.findAll", query = "SELECT p FROM Pessoas p"),
    @NamedQuery(name = "Pessoas.findByIdPessoa", query = "SELECT p FROM Pessoas p WHERE p.idPessoa = :idPessoa"),
    @NamedQuery(name = "Pessoas.findByNome", query = "SELECT p FROM Pessoas p WHERE p.nome = :nome"),
    @NamedQuery(name = "Pessoas.findByLogin", query = "SELECT p FROM Pessoas p WHERE p.login = :login"),
    @NamedQuery(name = "Pessoas.findBySenha", query = "SELECT p FROM Pessoas p WHERE p.senha = :senha"),
    @NamedQuery(name = "Pessoas.findByMoedas", query = "SELECT p FROM Pessoas p WHERE p.moedas = :moedas"),
    @NamedQuery(name = "Pessoas.findByPontuacao", query = "SELECT p FROM Pessoas p WHERE p.pontuacao = :pontuacao"),
    @NamedQuery(name = "Pessoas.findByAdm", query = "SELECT p FROM Pessoas p WHERE p.adm = :adm")})
public class Pessoas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPessoa")
    private Integer idPessoa;
    @Column(name = "Nome")
    private String nome;
    @Column(name = "Login")
    private String login;
    @Column(name = "Senha")
    private String senha;
    @Column(name = "Moedas")
    private Integer moedas;
    @Column(name = "Pontuacao")
    private Integer pontuacao;
    @Column(name = "ADM")
    private Boolean adm;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPessoa")
    private Collection<Personagenspessoa> personagenspessoaCollection;

    public Pessoas() {
    }

    public Pessoas(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getMoedas() {
        return moedas;
    }

    public void setMoedas(Integer moedas) {
        this.moedas = moedas;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Boolean getAdm() {
        return adm;
    }

    public void setAdm(Boolean adm) {
        this.adm = adm;
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
        hash += (idPessoa != null ? idPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoas)) {
            return false;
        }
        Pessoas other = (Pessoas) object;
        if ((this.idPessoa == null && other.idPessoa != null) || (this.idPessoa != null && !this.idPessoa.equals(other.idPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.sc.senai.Entidades.Pessoas[ idPessoa=" + idPessoa + " ]";
    }
    
}
