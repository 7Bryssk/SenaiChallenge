package br.senai.sc.Entidades;

import br.senai.sc.Entidades.Personagenspessoa;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-03T01:29:57")
@StaticMetamodel(Pessoas.class)
public class Pessoas_ { 

    public static volatile SingularAttribute<Pessoas, Integer> idPessoa;
    public static volatile CollectionAttribute<Pessoas, Personagenspessoa> personagenspessoaCollection;
    public static volatile SingularAttribute<Pessoas, Boolean> adm;
    public static volatile SingularAttribute<Pessoas, Integer> moedas;
    public static volatile SingularAttribute<Pessoas, String> nome;
    public static volatile SingularAttribute<Pessoas, String> login;
    public static volatile SingularAttribute<Pessoas, String> senha;
    public static volatile SingularAttribute<Pessoas, Integer> pontuacao;

}