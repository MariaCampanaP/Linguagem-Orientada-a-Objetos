package com.mycompany.campominadosimplificado;

public class Tabuleiro {

    Itens[][] campo;
    Jogador jogador;

    public Tabuleiro(Itens[][] campo) {
        this.campo = campo;
        jogador = new Jogador();
    }

    public boolean revelar(int linha, int coluna) {
        campo[linha][coluna].revelar(jogador);
        return jogador.getVida() <= 0;
    }

    @Override
    public String toString() {
        String retorno = "Número de vidas: " + jogador.getVida() + "\n";
        retorno += "Número de acertos: " + jogador.getAcertos() + "\n";
        for (int i = 0; i < campo.length; i++) {
            for (int j = 0; j < campo[i].length; j++) {
                retorno += campo[i][j].toString() + " ";
            }
            retorno += "\n";
        }
        return retorno;
    }

    public String gameOver() {
        return "Game Over! Sua pontuação é: " + jogador.getAcertos();    }
}
