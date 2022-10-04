package br.ufrgs.inf.tcp.trabalho.controller;

import br.ufrgs.inf.tcp.trabalho.view.UIView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PlayerUI extends UIView {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 300;
    private PlayerController controller;

    public PlayerUI() {
        super(PlayerUI.WIDTH, PlayerUI.HEIGHT);

        this.controller = new PlayerController();

    }

    public PlayerController getController() {
        return controller;
    }

    public void setController(PlayerController controller) {
        this.controller = controller;
    }

    @Override
    protected void playButtonClick(ActionEvent ev) {
        controller.getTextReader().setBaseText(getMusicText());
        controller.run();
    }

    @Override
    protected void exportButtonClick(ActionEvent ev) {

    }

    @Override
    protected void importButtonClick(ActionEvent ev) {
//        if (ev.getSource() == btnImportFile)

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
        int response = fileChooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            Scanner fileIn = null;

            try {
                fileIn = new Scanner(file);

                if (file.isFile()) {
                    String text = "";
                    while (fileIn.hasNextLine()) {
                        text += fileIn.nextLine()+"\n";
                    }
                    this.setMusicInput(text);
                }
            } catch(FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }
}
