/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.Entidades;

/**
 *
 * @author bruno_verbinnen
 */
public class InfoPessoa {
    
    private static int Id;
    private static String Nome;
    private static Boolean Adm; 
    private static int resolucao;
    // 1 = 1920x1080           2 = 1366x768 
    public static int getId() {
        return Id;
    }

    public static void setId(int id) {
        InfoPessoa.Id = id;
    }

    public static String getNome() {
        return Nome;
    }

    public static void setNome(String Nome) {
        InfoPessoa.Nome = Nome;
    }

    public static Boolean getAdm() {
        return Adm;
    }

    public static void setAdm(Boolean Adm) {
        InfoPessoa.Adm = Adm;
    }

    public static int getResolucao() {
        return resolucao;
    }

    public static void setResolucao(int Resolucao) {
        resolucao = Resolucao;
    }
    
}
