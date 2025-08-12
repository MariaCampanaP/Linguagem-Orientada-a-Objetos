package com.mycompany.yesorno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class yesOrNoButton extends JFrame {
    
    private JButton botaoSim; // Botão "Sim"
    private JButton botaoNao; // Botão "Não"

    public yesOrNoButton() {
        super("Pergunta Importante"); // Título da janela

        setSize(350, 150); // Define tamanho da janela
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        setLayout(new BorderLayout()); // Usa layout de borda para posicionar os componentes

        // Cria o texto da pergunta
        JLabel pergunta = new JLabel("Você quer sair comigo?", SwingConstants.CENTER);
        pergunta.setFont(new Font("Arial", Font.PLAIN, 16)); // Define fonte da pergunta
        add(pergunta, BorderLayout.NORTH); // Adiciona a pergunta na parte de cima da janela

        // Cria um painel para os botões
        JPanel botoesPanel = new JPanel();
        botaoSim = new JButton("Sim"); // Cria botão "Sim"
        botaoNao = new JButton("Não"); // Cria botão "Não"

        botoesPanel.add(botaoSim); // Adiciona botão "Sim" ao painel
        botoesPanel.add(botaoNao); // Adiciona botão "Não" ao painel
        add(botoesPanel, BorderLayout.CENTER); // Adiciona o painel de botões ao centro da janela

        // Define ação ao clicar no botão "Sim"
        botaoSim.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Sabia que você diria SIM!")
        );

        // Define ação ao passar o mouse por cima do botão "Não"
        botaoNao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Troca os textos dos botões "Sim" e "Não"
                String temp = botaoSim.getText();
                botaoSim.setText(botaoNao.getText());
                botaoNao.setText(temp);
            }
        });

        setLocationRelativeTo(null); // Centraliza a janela na tela
        setVisible(true); // Torna a janela visível
    }
}
