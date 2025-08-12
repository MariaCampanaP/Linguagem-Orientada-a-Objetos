package com.mycompany.yellowblockgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class BlockGame extends JFrame {
    
    private final int TAM = 10; //Tamanho da grade: 10 x 10 células
    private final int TAM_CELULA = 50; //Tamanho de cada célula em pixels
    
    private int linhaAlvo = 0; //Linha atual do bloco amarelo
    private int colunaAlvo = 0; //Coluna atual do bloco amarelo
    private int pontos = 0; //Pontuação do jogador
    
    private JLabel labelPontos; //Rótulo para exibir os pontos
    private Random random = new Random(); //Gerador de posições aleatórias
    private PainelGrade painel; //Painel onde a grade será desenhada
    
    public BlockGame(){
        super("Clique no bloc amarelo"); //Título da janela
        
        //Cria o painel personalizado onde a grade será desenhada
        painel = new PainelGrade();
        painel.setPreferredSize(new Dimension(TAM * TAM_CELULA, TAM * TAM_CELULA));
        painel.setBackground(Color.WHITE); //Fundo Branco
        
        //Adiciona evento de clique do mouse
        painel.addMouseListener(new MouseAdapter(){
           @Override
           public void mousePressed(MouseEvent e){
               //Converte a posição do clique em coordenadas da grade
               int x = e.getX() / TAM_CELULA;
               int y = e.getY() / TAM_CELULA;
               
               //Verifica se o clique foi no bloco amarelo
               if(x == colunaAlvo && y == linhaAlvo){
                   pontos++; //Acertou!
               }else{
                   pontos--; //Errou!
               }
               
               //Atualiza o texto de pontuação
               labelPontos.setText("Pontos:" + pontos);
           }
        });
        
        //Cria o rótulo de pontuação
        labelPontos = new JLabel("Pontos: 0", SwingConstants.CENTER);
        labelPontos.setFont(new Font("Arial", Font.BOLD, 18));
        
        //Define o layout da janela principal
        setLayout(new BorderLayout());
        add(labelPontos, BorderLayout.NORTH); //Pontuação no topo
        add(painel, BorderLayout.CENTER); //Painel com a grade no centro
        
        //Timer que move o bloco amarelo para uma nova posição a cada 1 segundo
        Timer timer = new Timer(1000, e -> {
            linhaAlvo = random.nextInt(TAM); //Nova linha aleatória
            colunaAlvo = random.nextInt(TAM); //Nova coluna aleatória
            painel.repaint(); //Redesenha o painel com nova posição
        });
        timer.start(); //Inicia o timer
        
        //Finaliza configurações da janela
        pack(); //Ajusta o tamanho da janela ao conteúdo
        setLocationRelativeTo(null); //Centraliza a janela na tela
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Fecha o app ao fechar a janela
        setVisible(true); //Torna a janela visível
    }
    
     // Classe interna personalizada para desenhar a grade e o bloco amarelo
    private class PainelGrade extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Desenha as linhas da grade
            g.setColor(Color.BLACK);
            for (int i = 0; i <= TAM; i++) {
                // Linhas horizontais
                g.drawLine(0, i * TAM_CELULA, TAM * TAM_CELULA, i * TAM_CELULA);
                // Linhas verticais
                g.drawLine(i * TAM_CELULA, 0, i * TAM_CELULA, TAM * TAM_CELULA);
            }

            // Desenha o bloco amarelo na célula sorteada
            g.setColor(Color.YELLOW);
            g.fillRect(colunaAlvo * TAM_CELULA + 1, linhaAlvo * TAM_CELULA + 1,
                       TAM_CELULA - 1, TAM_CELULA - 1);
        }
    }
    
}
