package com.mycompany.campominadosimplificado;

public class Bomba extends Itens {

    @Override
    public void revelar(Jogador jogador) {
        if(!visivel){
            jogador.perderVida();
            visivel = true;
        }
    }

    @Override
    public String simbolo() {
        return "@";
    }
    
}
