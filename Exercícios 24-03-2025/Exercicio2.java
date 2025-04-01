/* Crie um programa que leia o raio de um círculo e imprima a sua circuferência na tela.
Dica: A classe Math possui uma constante pública PI com o valor aproximado de pi.
*/

package com.mycompany.exercicio2;

import java.util.Scanner;

public class Exercicio2 {

    public static void main(String[] args) {
        Double circunferencia;
        Double raio;
        
        System.out.println("Digite o raio e a circunferencia:");
        Scanner scanner = new Scanner(System.in);
        circunferencia = scanner.nextDouble();
        raio = scanner.nextDouble();
        
        circunferencia = 2 * raio * Math.PI;
        
        System.out.println("O resultado da circunferencia eh:%.2f" + circunferencia);
    }
}
