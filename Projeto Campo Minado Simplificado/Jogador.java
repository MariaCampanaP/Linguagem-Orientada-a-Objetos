package com.mycompany.campominadosimplificado;

public class Jogador {
    private int vida;
    private int acertos;
    
    public Jogador(){
        this.vida = 3;
        this.acertos = 0;
    }

    public int getVida() {
        return vida;
    }

    public int getAcertos() {
        return acertos;
    }
    
    public void perderVida(){
        vida--;
    }
    
    public void novoAcerto(){
        acertos++;
    }
}
