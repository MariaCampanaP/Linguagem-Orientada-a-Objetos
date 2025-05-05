package com.mycompany.campominadosimplificado;

public abstract class Itens {
    protected boolean visivel;
    
    public Itens(){
        visivel = false;
    }
    
    public boolean isVisivel(){
        return visivel;
    }
    
    public abstract void revelar(Jogador jogador);
    
    @Override
    public String toString(){
        if(!visivel){
            return "*";
        }
        return simbolo();
    }
    
    public abstract String simbolo();
    
}
