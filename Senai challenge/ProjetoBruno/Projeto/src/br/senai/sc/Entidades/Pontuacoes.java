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
public class Pontuacoes {
    
    static int metros = 0;
    static int moedas = 0;
    static int inimigos = 0;

    public static int getMetros() {
        return metros;
    }

    public static void setMetros(int metros) {
        Pontuacoes.metros = metros;
    }

    public static int getMoedas() {
        return moedas;
    }

    public static void setMoedas(int moedas) {
        Pontuacoes.moedas = moedas;
    }

    public static int getInimigos() {
        return inimigos;
    }

    public static void setInimigos(int inimigos) {
        Pontuacoes.inimigos = inimigos;
    }
    
    
}
