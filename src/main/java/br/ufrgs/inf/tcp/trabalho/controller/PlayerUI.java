package br.ufrgs.inf.tcp.trabalho.controller;

import br.ufrgs.inf.tcp.trabalho.view.UIView;

import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;

public class PlayerUI extends UIView {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 300;
    private static final String DEFAULT_FILENAME = "midi_output.txt";
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
        String filename = this.showInput("Export target file", "Export", DEFAULT_FILENAME);
        // throw error if filename is null or if filename length is 0

        if (filename == null) { // user canceled input
            return;
        }

        filename = filename.length() == 0 ? DEFAULT_FILENAME : filename;

        controller.getTextReader().setBaseText(this.getMusicText());
        controller.getPlayer().restart();
        controller.processAllText();
        String midiText = controller.getMidiText();

        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(midiText);
            writer.close();
            this.showMessage("Export successful!", "Export");
        } catch (IOException e) {
            this.showMessage("File cannot be written.\nIOException");
//            throw new RuntimeException(e);
        }

    }

    @Override
    protected void importButtonClick(ActionEvent ev) {

    }
}
