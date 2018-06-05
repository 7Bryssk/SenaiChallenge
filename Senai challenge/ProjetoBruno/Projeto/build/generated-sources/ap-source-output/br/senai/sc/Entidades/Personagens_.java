package br.senai.sc.Entidades;

import br.senai.sc.Entidades.Personagenspessoa;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-03T01:29:57")
@StaticMetamodel(Personagens.class)
public class Personagens_ { 

    public static volatile CollectionAttribute<Personagens, Personagenspessoa> personagenspessoaCollection;
    public static volatile SingularAttribute<Personagens, Integer> vida;
    public static volatile SingularAttribute<Personagens, Integer> preco;
    public static volatile SingularAttribute<Personagens, Integer> velocidade;
    public static volatile SingularAttribute<Personagens, Integer> idPersonagem;
    public static volatile SingularAttribute<Personagens, String> nome;
    public static volatile SingularAttribute<Personagens, String> pulo;
    public static volatile SingularAttribute<Personagens, String> foto;
    public static volatile SingularAttribute<Personagens, Integer> altura;
    public static volatile SingularAttribute<Personagens, String> gif;

}