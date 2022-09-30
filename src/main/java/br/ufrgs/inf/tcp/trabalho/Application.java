package br.ufrgs.inf.tcp.trabalho;

import br.ufrgs.inf.tcp.trabalho.controller.PlayerUI;

import javax.swing.*;

public class Application {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            PlayerUI playerUI = new PlayerUI();
            playerUI.setVisible(true);
        });

    }
}
