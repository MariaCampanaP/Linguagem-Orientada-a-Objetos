package com.mycompany.campominadosimplificado;

public class Tesouro extends Itens {

    @Override
    public void revelar(Jogador jogador) {
        if(!visivel){
            jogador.novoAcerto();
            visivel = true;
        }
    }

    @Override
    public String simbolo() {
        return "$";
    }
    
    
    
}
