package com.mycompany.colorrace;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ColorRace {

    public static void main(String[] args) {
        
        /*Usa SwingUtilities.invokeLater para garantir que a GUI seja criada
        na thread de despacho de eventos (EDT), que é a prática segura pra Swing.
        */
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                
                //Mostra uma mensagem inicial antes de iniciar o jogo.
                JOptionPane.showMessageDialog(null, "Pressione OK para começar!");
                
                /*Cria uma instância da tela do jogo com uma grade de 15 x 15. O 
                primeiro argumento é a quantidade de linhas e o segundo, de colunas.
                */
                new TelaColorRace(15, 15);
            }
        });
    }
}
