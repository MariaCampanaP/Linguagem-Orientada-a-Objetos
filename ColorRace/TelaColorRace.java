package com.mycompany.colorrace;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class TelaColorRace extends JFrame {
   
    //Componentes da interface
    private final JPanel painelMatriz; //Painel que contém o grid de células
    private final JLabel[][] matrizLabels; //Matriz de labels que representam as células
    private final JLabel labelPontosJogador; //Label que mostra os pontos do jogador
    private final JLabel labelPontosBot; //Label que mostra os pontos do bot
    private final JLabel labelTempo; //Label que mostra o tempo restante 
    
    //Variáveis do jogo
    private final int linhas; //Número de linhas do tabuleiro
    private final int colunas; //Número de colunas do tabuleiro
    private int linhaAtualJogador; //Linha atual do jogador
    private int colunaAtualJogador; //Coluna atual do jogador
    private int linhaAtualBot; //Linha atual do bot
    private int colunaAtualBot; //Coluna atual do bot
    private int pontosJogador; //Pontuação atual do jogador
    private int pontosBot; //Pontuação atual do bot
    private int tempoRestante; //Tempo restante em segundos
    
    //Timers para controle do jogo
    private final Timer gameTimer; //Timer para contagem regressiva
    private final Timer botTimer; //Timer para movimentação do bot
    private final Random random = new Random(); //Gerador de número aleatórios
    
    //Inicializa o jogo
    public TelaColorRace(int linhas, int colunas){
        this.linhas = linhas;
        this.colunas = colunas;
        this.pontosJogador = 0;
        this.tempoRestante = 45;
    
    //Configuração da janela principal
        setTitle("Color Race");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    
    //Inicializa o painel da matriz (tabuleiro)
        painelMatriz = new JPanel(new GridLayout(linhas, colunas));
        matrizLabels = new JLabel[linhas][colunas];
      
    //Preenche o tabuleiro com células brancas
    for(int i = 0; i < linhas; i++){
        for(int j = 0; j < colunas; j++){
            JLabel celula = new JLabel();
            celula.setOpaque(true); //Permite definir cor de fundo
            celula.setBackground(Color.WHITE); //Cor inicial branca
            celula.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //Borda preta
            matrizLabels[i][j] = celula;
            painelMatriz.add(celula); //Adiciona célula ao painel
        }
    }
    add(painelMatriz, BorderLayout.CENTER); //Adiciona tabuleiro ao centro
    
    //Configuração do painel inferior (placar e tempo)
    JPanel painelInferior = new JPanel();
    Font fontePainel = new Font("Arial", Font.BOLD, 16); //Fonte para os textos
    
    //Componentes do placar
    JLabel nomeJogador = new JLabel("Voce (Ciano):");
    nomeJogador.setFont(fontePainel);
    labelPontosJogador = new JLabel("0");
    labelPontosJogador.setFont(fontePainel);
    
    JLabel nomeBot = new JLabel ("  |  Bot(Magenta):");
    nomeBot.setFont(fontePainel);
    labelPontosBot = new JLabel("0");
    labelPontosBot.setFont(fontePainel);
    
    JLabel nomeTempo = new JLabel("  |  Tempo:");
    nomeTempo.setFont(fontePainel);
    labelTempo = new JLabel(String.valueOf(tempoRestante));
    labelTempo.setFont(fontePainel);
    
    //Adiciona componentes ao painel inferior
    painelInferior.add(nomeJogador);
    painelInferior.add(labelPontosJogador);
    painelInferior.add(nomeBot);
    painelInferior.add(labelPontosBot);
    painelInferior.add(nomeTempo);
    painelInferior.add(labelTempo);
    add(painelInferior, BorderLayout.SOUTH); //Adiciona painel na parte inferior
    
    posicionarPersonagens(); //Posioiona jogador e bot no tabuleiro
    
    //Configura o timer do jogo (contagem regressiva)
    gameTimer = new Timer(1000, e -> {
        tempoRestante--;
        labelTempo.setText(String.valueOf(tempoRestante));
        if(tempoRestante <= 0){
            finalizarJogo();
        }
    });
    gameTimer.start(); //Inicia a contagem regressiva
    
    //Configura o timer do bot (movimentação automática)
    botTimer = new Timer(100, e -> moverBot()); //Move o bot a cada 100ms
    botTimer.start(); //Inicia movimentação do bot
    
    //Configura os listeners de teclado
    addKeyListener(new KeyAdapter(){
        @Override
        public void keyPressed(KeyEvent e){
            moverJogador(e.getKeyCode()); //Move jogador conforme tecla pressionada
        }
    });
    
    //Configurações finais da janela
    setLocationRelativeTo(null); //Centraliza janela
    setVisible(true); //Torna visível
    setFocusable(true); //Permite receber eventos de teclado
    requestFocusInWindow(); //Garante foco na janela
    }
    
    /*Posiciona o jogador e o bot em cantos opostos do tabuleiro*/
    private void posicionarPersonagens(){
        //Jogador no canto inferior direito (azul)
        linhaAtualJogador = linhas - 1;
        colunaAtualJogador = colunas - 1;
        matrizLabels[linhaAtualJogador][colunaAtualJogador].setBackground(Color.BLUE);
        
        //Bot no canto superior esquerdo (vermelho)
        linhaAtualBot = 0;
        colunaAtualBot = 0;
        matrizLabels[linhaAtualBot][colunaAtualBot].setBackground(Color.RED);
        
        atualizarPlacar(); //Atualizar placar inicial
        
    }
    
    /*Move o jogador conforme a tecla pressionada*/
    private void moverJogador(int keyCode){
        int proximaLinha = linhaAtualJogador;
        int proximaColuna = colunaAtualJogador;
        
        //Calcula nova posição conforme tecla pressionada
        switch (keyCode){
            case KeyEvent.VK_UP:
                if(linhaAtualJogador > 0) proximaLinha--; //Move para cima
                break;
            case KeyEvent.VK_DOWN:
                if(linhaAtualJogador < linhas - 1) proximaLinha++; //Move para baixo
                break;
            case KeyEvent.VK_LEFT:
                if(colunaAtualJogador > 0) proximaColuna--; //Move para esquerda
                break;
            case KeyEvent.VK_RIGHT:
                if(colunaAtualJogador < colunas - 1) proximaColuna++; //Move para direita
                break;
        }
        //Se a posição mudou, atualiza
        if(proximaLinha != linhaAtualJogador || proximaColuna != colunaAtualJogador){
            //Pinta a célula anterior com ciano (marca território)
            colorirCelula(linhaAtualJogador, colunaAtualJogador, Color.CYAN);
            
            //Atualiza posição atual
            linhaAtualJogador = proximaLinha;
            colunaAtualJogador = proximaColuna;
            
            //Marca nova posição com azul (jogador atual)
            matrizLabels[linhaAtualJogador][colunaAtualJogador].setBackground(Color.BLUE);
        }
        
    }
    
    /*Move o bot aleatoriamente pelo tabuleiro*/
    private void moverBot(){
        
        int direcao = random.nextInt(4); //Direção aleatória (0-3)
        
        int proximaLinha = linhaAtualBot;
        int proximaColuna = colunaAtualBot;
        
        //Calcula nova posição conforme direção
        switch(direcao){
            case 0:
                if(linhaAtualBot > 0) proximaLinha--; //Move para cima
                break;
            case 1:
                if(linhaAtualBot < linhas - 1) proximaLinha++; //Move para baixo
                break;
            case 2:
                if(colunaAtualBot > 0) proximaColuna--; //Move para esquerda
                break;
            case 3:
                if(colunaAtualBot < colunas - 1) proximaColuna++; //Move para direita
                break;
        }
        
        //Se a posição mudou, atualiza
        if(proximaLinha != linhaAtualBot || proximaColuna != colunaAtualBot){
            //Pinta a célula anterior com magenta (marca território)
            colorirCelula(linhaAtualBot, colunaAtualBot, Color.MAGENTA);
            
            //Atualiza posição atual
            linhaAtualBot = proximaLinha;
            colunaAtualBot = proximaColuna;
            
            //Marca nova posição com vermelho (bot atual)
            matrizLabels[linhaAtualBot][colunaAtualBot].setBackground(Color.RED);
        }
    }
    
    /*Colore uma célula específica e atualiza o placar*/
    private void colorirCelula(int linha, int coluna, Color novaCor){
        matrizLabels[linha][coluna].setBackground(novaCor);
        atualizarPlacar(); //Atualiza contagem de territórios
    }
    
    /*Atualiza o placar contando as células coloridas*/
    private void atualizarPlacar(){
        int contadorJogador = 0;
        int contadorBot = 0;
        
        //Percorre todo o tabuleito contando células coloridas
        for(int i = 0; i < linhas; i++){
            for(int j = 0; j < colunas; j++){
                Color cor = matrizLabels[i][j].getBackground();
                if(cor.equals(Color.CYAN)){
                    contadorJogador++; //Células do jogador
                }else if(cor.equals(Color.MAGENTA)){
                    contadorBot++; //Células do jogador
                }else if(cor.equals(Color.MAGENTA)){
                    contadorBot++; //Células do bot
                }
            }
        }
        
        //Atualiza pontuações
        pontosJogador = contadorJogador;
        pontosBot = contadorBot;
        
        //Atualiza labels
        labelPontosJogador.setText(String.valueOf(pontosJogador));
        labelPontosBot.setText(String.valueOf(pontosBot));
    }
    
    /*Finaliza o jogo e mostra o resultado*/
    private void finalizarJogo(){
        gameTimer.stop(); //Para timer do jogo
        botTimer.stop(); //Para timer do bot
        
        String mensagemFinal;
        if(pontosJogador > pontosBot){
            mensagemFinal = "Você Venceu!";
        }else if (pontosBot > pontosJogador){
            mensagemFinal = "O computador venceu!";
        }else{
            mensagemFinal = "Empate!";
        }
        
        JOptionPane.showMessageDialog(this, String.format("%s\n\nPlacar Final:\nVocê: %d\nBot: %d", mensagemFinal, pontosJogador, pontosBot));
        
        dispose(); //Fecha a janela
    }
    
}
