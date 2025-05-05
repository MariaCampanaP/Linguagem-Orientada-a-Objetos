package com.mycompany.campominadosimplificado;

import java.util.Scanner;

public class CampoMinadoSimplificado {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tabuleiro tabuleiro = new Tabuleiro(criarCampo());
        boolean gameOver;
        do {
            System.out.println(tabuleiro);
            System.out.println("Digite a linha e coluna");
            int linha = scanner.nextInt();
            int coluna = scanner.nextInt();
            gameOver = tabuleiro.revelar(linha, coluna);
        } while (!gameOver);
        System.out.println(tabuleiro.gameOver());
    }

    private static Itens[][] criarCampo() {
        Itens[][] campo = new Itens[5][5];
        for (int i = 0; i < campo.length; i++) {
            for (int j = 0; j < campo[i].length; j++) {
                if ((i + j) % 2 == 0) {
                    campo[i][j] = new Bomba();
                } else {
                    campo[i][j] = new Tesouro();
                }
            }
        }
        return campo;
    }
}
