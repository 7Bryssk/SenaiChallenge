 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.Telas;

import br.senai.sc.Entidades.Inimigo;
import br.senai.sc.DAO.InimigosDAO;
import br.senai.sc.DAO.PersonagensDAO;
import br.senai.sc.DAO.PersonagenspessoaDAO;
import br.senai.sc.DAO.PessoasDAO;
import br.senai.sc.Entidades.InfoPessoa;
import br.senai.sc.Entidades.Inimigos;
import br.senai.sc.Entidades.Personagens;
import br.senai.sc.Entidades.Personagenspessoa;
import br.senai.sc.Entidades.Pessoas;
import br.senai.sc.Entidades.Pontuacoes;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author bruno_verbinnen
 */
public class TelaJogo extends javax.swing.JFrame {

    Personagens perso = new Personagens();
    PersonagensDAO persoDAO = new PersonagensDAO();
    Pessoas pess = new Pessoas();
    PessoasDAO pessDAO = new PessoasDAO();
    Personagenspessoa perpess = new Personagenspessoa();
    PersonagenspessoaDAO perpessDAO = new PersonagenspessoaDAO();
    InfoPessoa IP = new InfoPessoa();
    Inimigos ini = new Inimigos();
    InimigosDAO iniDAO = new InimigosDAO();
    JLabel cenario2 = new JLabel();
    int cod = IP.getId();
    //int cod = 1;
    int c=1;
    int idp;
    int alt = 150;
    int vida = 100;
    int IniM = 0;
    int moedas = 0;
    int metros = 0;
    List<Inimigo> Listainimigo = new ArrayList<>();
    List<JLabel> Listamoedas = new ArrayList<>();
    int i = 0, j = 0;
    int proximoini = 5;
    int nascer = 0;
    double nasce = 33;
    int dano = 0;
    int matar=0;
    Random gerador = new Random();
    int tempo = 3;
    Timer loop;

    int y = 0;
    int vmax = -12;
    float velocidadePulo = vmax;
    int auc = 0;
    int velocidadeini = 2;
    int contador = 0;
    int contagem = 75;
    int vp=0;
    int cont=0;
    int aumentar=0;
    int aumentador=25;
    public TelaJogo() {
        initComponents();
        setLocationRelativeTo(null);
        met.setText(metros + "  Metros");
        Iniciar();
        iniciarcenario2();
        y = Player.getLocation().y;

        loop = new Timer(10, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(aumentar>=aumentador){
                    contagem = contagem-3;
                    velocidadeini++;
                    aumentar=0;
                    gerarbandeira();
                }
                
                contador++;
                inim.setText(velocidadeini+" Velo Ini");
                inim.setText(IniM+"");
                moe.setText(moedas+"");
                v1.setText(""+Life.getValue());
                if (contador > contagem) {
                    aumentar++;
                    metros++;
                    contador = 0;
                    nascer++;
                }
                met.setText(metros + "  Metros");
                if (nascer > nasce) {
                    CriarIni();
                    nascer = 0;
                }
                
                if (Life.getValue() <= 0) {
                    TelaPontuacoes abrir = new TelaPontuacoes();
                    loop.stop();
                    abrir.setVisible(true);

                    Pontuacoes pont = new Pontuacoes();
                    pont.setInimigos(IniM);
                    pont.setMetros(metros);
                    pont.setMoedas(moedas);
                    SubstituirPontuacao();

                    dispose();

                }
                
//                vp++;
//                if(vp==2){
                    pular();
                    //vp=0;
//                }
                mover(Listainimigo);
                remover(Listainimigo);
                Player.setLocation(Player.getLocation().x, y);
                movermoeda(Listamoedas);
                removermoeda(Listamoedas);
                pegarmoeda(Listamoedas);
                movercenario();
                
//                JOptionPane.showMessageDialog(null, cont+" = "+tempo);
                if(cont==tempo*100){
                    aparecermoedas();
                    cont=0;
                }
                cont++;
            }

            private void JLabel() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        requestFocus();
        loop.start();

    }
    
    public void CriarIni() {
        Random gerador = new Random();
        String foto;
        String nomeini="";
        int inii=0;
        while(inii<1){
            inii = gerador.nextInt(6);
        } 
        
        switch (inii){
            case 1:
                nomeini="Chuveiro";
                break;
            case 2:
                nomeini="Ferro";
                break;
            case 3:
                nomeini="Cafeteira";
                break;
            case 4:
                nomeini="Computador";
                break;
            case 5:
                nomeini="Ventilador";
                break;
        }
        
        try {
            int codini = iniDAO.getInimigosPorNome(nomeini);
            ini = iniDAO.getInimigosPorID(codini);
        } catch (Exception ex) {
            Logger.getLogger(TelaJogo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        dano = ini.getDano();
        Inimigo inimigo = new Inimigo();
        inimigo.setIcon(new javax.swing.ImageIcon(getClass().getResource(ini.getFoto())));
        inimigo.setDoubleBuffered(true);
        inimigo.setVelocidade(ini.getVelocidade());
        inimigo.setDano(ini.getDano());
        inimigo.setRequestFocusEnabled(false);
        inimigo.setBounds(1000, 425+(100-ini.getAltura()), 100, ini.getAltura());
//        inimigo.
        Listainimigo.add(inimigo);
        for (JLabel inimigoAtual : Listainimigo) {
            cen.add(inimigoAtual);
            cen.setComponentZOrder(inimigoAtual, 1);
        }
        inimigo.setVisible(true);

        while (proximoini < 2) {
            
            proximoini = gerador.nextInt(7);
        }
        nasce = proximoini;
        proximoini = 0;

    }

    public void mover(List<Inimigo> inimigos) {
        for (Inimigo inimigo : inimigos) {
            inimigo.setLocation((int) inimigo.getLocation().getX() - (velocidadeini+inimigo.getVelocidade()), (int) inimigo.getLocation().getY());
        }
    }

    public void remover(List<Inimigo> inimigos) {

        for (Inimigo inimigo : inimigos) {
            if(Player.getLocation().getX()+70 > inimigo.getLocation().getX() && Player.getLocation().getX() < inimigo.getLocation().getX()+25 &&
                Player.getLocation().getY()+100 >= inimigo.getLocation().getY() && Player.getLocation().getY()+100 <= inimigo.getLocation().getY()+10 && matar==1) {
//                JOptionPane.showMessageDialog(null, "Matou");  
                pulando = true;
                velocidadePulo*=-1;
//                space = true;
                IniM++;
                Listainimigo.remove(inimigo);
                cen.remove(inimigo);
                break;
            }else if(Player.getLocation().getX()+70 > inimigo.getLocation().getX() && Player.getLocation().getX() < inimigo.getLocation().getX()+25 &&
                Player.getLocation().getY()+90 > inimigo.getLocation().getY()) {
//                JOptionPane.showMessageDialog(null, "Dano");
//                dano = inimigo.getComponentZOrder(cen);
//                JOptionPane.showMessageDialog(null, dano);
                int valor=0;
                if(c==1){
                    valor = inimigo.getDano();
                }else if(c==3){
                    valor = (int) (inimigo.getDano()*2);
                }else{
                    valor = (int) (inimigo.getDano()+(inimigo.getDano()*0.25));
                }
                Life.setValue(Life.getValue() - valor);
//                JOptionPane.showMessageDialog(null, "DANO:"+valor+"\nDANO:"+dano);
                Listainimigo.remove(inimigo);
                cen.remove(inimigo);
                break;
            }else if(inimigo.getLocation().getX()<-100){
//                JOptionPane.showMessageDialog(null, "Final");
                Listainimigo.remove(inimigo);
                cen.remove(inimigo);
                break;
            }
        }
    }
    
    public void gerarbandeira(){
        int b = gerador.nextInt(3);
        switch(b){
            case 0:
                cenario1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Arquivos/mapaverde.png")));
                cenario2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Arquivos/mapaverde.png")));
                c=1;
                break;
            case 1:
                cenario1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Arquivos/mapaamarelo.png")));
                cenario2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Arquivos/mapaamarelo.png")));
                c=2;
                break;
            case 2:
                cenario1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Arquivos/mapavermelho.png")));
                cenario2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Arquivos/mapavermelho.png")));
                c=3;
                break;
        }
        //JOptionPane.showMessageDialog(null, jPanel1.getBackground());
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cen = new javax.swing.JPanel();
        Player = new javax.swing.JLabel();
        Life = new javax.swing.JProgressBar();
        met = new javax.swing.JLabel();
        v1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        moe = new javax.swing.JLabel();
        inim = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cenario1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cen.setBackground(new java.awt.Color(153, 255, 255));
        cen.setMaximumSize(new java.awt.Dimension(800, 600));
        cen.setMinimumSize(new java.awt.Dimension(800, 600));
        cen.setPreferredSize(new java.awt.Dimension(800, 600));
        cen.setLayout(null);

        Player.setMaximumSize(new java.awt.Dimension(50, 120));
        Player.setMinimumSize(new java.awt.Dimension(50, 120));
        Player.setPreferredSize(new java.awt.Dimension(50, 100));
        cen.add(Player);
        Player.setBounds(30, 425, 80, 100);

        Life.setBackground(new java.awt.Color(255, 255, 51));
        Life.setForeground(new java.awt.Color(255, 255, 51));
        cen.add(Life);
        Life.setBounds(10, 10, 200, 20);

        met.setFont(new java.awt.Font("Rockwell Condensed", 1, 25)); // NOI18N
        met.setText("0      metros");
        cen.add(met);
        met.setBounds(644, 10, 150, 30);

        v1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        v1.setText("0");
        cen.add(v1);
        v1.setBounds(220, 10, 140, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Arquivos/chao_1.gif"))); // NOI18N
        cen.add(jLabel1);
        jLabel1.setBounds(0, 525, 800, 75);

        moe.setFont(new java.awt.Font("Rockwell Condensed", 1, 25)); // NOI18N
        moe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Arquivos/moeda.gif"))); // NOI18N
        moe.setText("0 ");
        cen.add(moe);
        moe.setBounds(510, 0, 110, 40);

        inim.setFont(new java.awt.Font("Rockwell Condensed", 1, 25)); // NOI18N
        inim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Arquivos/cavera.png"))); // NOI18N
        inim.setText("0");
        cen.add(inim);
        inim.setBounds(420, 0, 90, 40);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Arquivos/nuvens.gif"))); // NOI18N
        jLabel2.setText("jLabel2");
        cen.add(jLabel2);
        jLabel2.setBounds(0, 0, 800, 196);

        cenario1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Arquivos/mapaverde.png"))); // NOI18N
        cen.add(cenario1);
        cenario1.setBounds(0, 0, 800, 525);

        getContentPane().add(cen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean space = false;
    private boolean pulando = false;

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_W){
            pulando=true;
            space = true;
        }
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            pulando = true;
            space = true;
        }
        if (evt.getKeyCode() == 80 && loop.isRunning()==true) {
            loop.stop();
        }else if (evt.getKeyCode() == 80 && loop.isRunning()==false) {
            loop.start();
        }
    }//GEN-LAST:event_formKeyPressed
    
    
    public void pular(){
        
        if(pulando){
            if(velocidadePulo<=1 && velocidadePulo>=0){
                velocidadePulo=2;
            }
            if(velocidadePulo>=11){
                y+=10;
            }else if(velocidadePulo<=-11){
                y+=-10;
            }else{
                y+=velocidadePulo;
            }
            velocidadePulo+=0.5f;
            
        }
        
        if(y <= (425 - 100)){
            matar=1;
        }
        
        if(y > 425){
            
            pulando = false;
            velocidadePulo = vmax;
            y = 425;
            matar=0;
        }
        if(Player.getLocation().getY()<380){
            PersoPulando();
        }else{
            IniciarPersonagem();
        }
    }
    
    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            space = false;
        }
        if (evt.getKeyCode() == KeyEvent.VK_W) {
            space = false;
        }
    }//GEN-LAST:event_formKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        i = 0;
        CriarIni();

    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaJogo().setVisible(true);
            }
        });
    }

    public void Iniciar() {
        try {
            pess = pessDAO.getPessoaPorID(cod);
            perpess.setIdPessoa(pess);
            idp = perpessDAO.AcharPersonagem(perpess);
            perso = persoDAO.getPersonagemPorID(idp);
            vmax = perso.getAltura();
            velocidadePulo = vmax;
            vida = perso.getVida();
            Life.setMaximum(vida);
            Life.setValue(vida);
            Life.setMinimum(0);
            IniciarPersonagem();
            cenario1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Arquivos/mapaverde.png")));
            cenario2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Arquivos/mapaverde.png")));
            

        } catch (Exception ex) {
            Logger.getLogger(TelaJogo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public void IniciarPersonagem(){
        String foto;
        foto = perso.getGif();
        Player.setIcon(new javax.swing.ImageIcon(getClass().getResource(foto)));
//        Player.setDoubleBuffered(true);
        
    }
    public void PersoPulando(){
        String foto;
        foto = perso.getPulo();
        Player.setIcon(new javax.swing.ImageIcon(getClass().getResource(foto)));
    }

    public void SubstituirPontuacao() {
        try {
            pess = pessDAO.getPessoaPorID(cod);
            int total = ((metros * 2) + (IniM * 3) + (moedas * 1));
            if (pess.getPontuacao() < total) {
                pess.setPontuacao(total);
                pess.setMoedas(pess.getMoedas() + moedas);
                pessDAO.alterar(pess);
            } else {
                pess.setMoedas(pess.getMoedas() + moedas);
                pessDAO.alterar(pess);
            }

        } catch (Exception ex) {
            Logger.getLogger(TelaJogo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void aparecermoedas(){
        int escolhe = gerador.nextInt(100);
        if(escolhe>=0 && escolhe<=40){
            tipomoeda(1);
        }else if(escolhe>=41 && escolhe<=80){
            tipomoeda(2);
        }else if(escolhe>=81 && escolhe<=100){
            tipomoeda(3);
        }
        
    }
    public void tipomoeda(int tipo){
//        tipo=3;
        switch(tipo){
            case 1:
                criamoeda(1000, 475);
                tempo = 4;
                break;
            case 2:
                criamoeda(1000, 465);
                criamoeda(1030, 435);
                criamoeda(1030, 465);
                criamoeda(1030, 495);
                criamoeda(1060, 465);
                tempo =10;
                break;
            case 3:
                tempo=15;
                criamoeda(1100, 390);
                criamoeda(1150, 360);
                criamoeda(1200, 330);
                criamoeda(1255, 300);
                criamoeda(1300, 330);
                criamoeda(1350, 360);
                criamoeda(1400, 390);
                
                criamoeda(1120, 350);
                criamoeda(1135, 320);
                criamoeda(1155, 290);
                criamoeda(1380, 350);
                criamoeda(1365, 320);
                criamoeda(1345, 290);
                
                criamoeda(1125, 250);
                criamoeda(1095, 225);
                criamoeda(1065, 200);
                criamoeda(1375, 250);
                criamoeda(1405, 225);
                criamoeda(1435, 200);
                
                criamoeda(1110, 200);
                criamoeda(1150, 200);
                criamoeda(1190, 200);
                criamoeda(1390, 200);
                criamoeda(1350, 200);
                criamoeda(1310, 200);
                
                criamoeda(1215, 160);
                criamoeda(1238, 120);
                criamoeda(1255, 80);
                criamoeda(1287, 160);
                criamoeda(1268, 120);
                break;
        }
    }
    public void criamoeda(int x, int y){
        JLabel moeda = new JLabel();
        moeda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Arquivos/moeda.gif")));
        moeda.setRequestFocusEnabled(false);
        moeda.setBounds(x, y, 30, 30);
        Listamoedas.add(moeda);
        for (JLabel MoedaAtual : Listamoedas) {
            cen.add(MoedaAtual);
            cen.setComponentZOrder(MoedaAtual, 2);
        }
        moeda.setVisible(true);
    }
    public void movermoeda(List<JLabel> moedas){
        for (JLabel moeda : moedas) {
            moeda.setLocation((int) moeda.getLocation().getX()-velocidadeini, (int) moeda.getLocation().getY());
        }
    }
    public void removermoeda(List<JLabel> moedas){
        for (JLabel moeda : moedas) {
            if (moeda.getLocation().getX()<-100) {
                Listamoedas.remove(moeda);
                cen.remove(moeda);
                break;
            }
        }
    }
    public void pegarmoeda(List<JLabel> moedasl){
        for (JLabel moeda : moedasl) {
//            if (Player.getLocation().getX()<=moeda.getLocation().getX() && Player.getLocation().getX()+71>=moeda.getLocation().getX() &&
//                Player.getLocation().getY()<=moeda.getLocation().getY() && Player.getLocation().getY()+100>=moeda.getLocation().getY()){
                
              if (Player.getLocation().getX()<=moeda.getLocation().getX()+30 && Player.getLocation().getX()+71>=moeda.getLocation().getX() &&
                  Player.getLocation().getY()<=moeda.getLocation().getY()+30 && Player.getLocation().getY()+100>=moeda.getLocation().getY()){
                
                moedas++;
                Listamoedas.remove(moeda);
                cen.remove(moeda);
                break;
            }
        }
    }
    
    public void movercenario(){
        cenario1.setLocation((int)cenario1.getLocation().getX()-1, 0);
        cenario2.setLocation((int)cenario2.getLocation().getX()-1, -50);
        cen.add(cenario1);
        cen.add(cenario2);
        if(cenario1.getLocation().getX()<=-800){
            cenario1.setLocation(800, 0);
        }
        if(cenario2.getLocation().getX()<=-800){
            cenario2.setLocation(800, 0);
        }
    }
    public void iniciarcenario2(){
        cenario2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Arquivos/mapaverde.png")));
        cenario2.setRequestFocusEnabled(false);
        cenario2.setBounds(800, 0, 800, 625);
        cen.add(cenario2);
        cenario2.setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar Life;
    private javax.swing.JLabel Player;
    private javax.swing.JPanel cen;
    private javax.swing.JLabel cenario1;
    private javax.swing.JLabel inim;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel met;
    private javax.swing.JLabel moe;
    private javax.swing.JLabel v1;
    // End of variables declaration//GEN-END:variables
}
