package com.mycompany.yellowblockgame;

import javax.swing.SwingUtilities;

public class YellowBlockGame {

    public static void main(String[] args) {
       SwingUtilities.invokeLater(BlockGame::new);
    }
}
