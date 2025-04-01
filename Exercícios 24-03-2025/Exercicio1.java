/* Crie um programa que lê inteiros da entrada padrão e imprima:
    1. O maior número;
    2. O menor númeor;
    3. A soma dos três números;
    4. A média dos três números
*/
package com.mycompany.exercicio1;

import java.util.Scanner;

public class Exercicio1 {

    public static void main(String[] args) {
        Integer num1 = 0, num2 = 0, num3 = 0;
        Integer maior = num1, menor = num3, soma;
        Double media;
        
        System.out.println("Digite um tres numeros:");
        Scanner scanner = new Scanner(System.in);
        num1 = scanner.nextInt();
        num2 = scanner.nextInt();
        num3 = scanner.nextInt();
        
        if(num2 > maior){
            maior = num2;
        }
        
        if(num3 > maior){
            maior = num3;
        }else{
            maior = num1;
        }
        
        if(num3 < menor){
            menor = num3;
        }
        
        if(num2 < menor){
            menor = num2;
        }else{
            menor = num1;
        }
        
        System.out.println("O maior numero eh:" + maior);
        System.out.println("O menor numero eh:" + menor);
        soma = num1 + num2 + num3;
        System.out.println("A soma dos numeros eh:" + soma);
        media = (num1 + num2 + num3) / 3.0;
        System.out.println("A media eh:" + media);
    }
}
