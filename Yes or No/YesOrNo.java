package com.mycompany.yesorno;

import javax.swing.SwingUtilities;

public class YesOrNo {

   public static void main(String[] args) {
        // Cria e exibe a janela na thread correta (padrão Swing)
        SwingUtilities.invokeLater(yesOrNoButton::new);
    }
}
