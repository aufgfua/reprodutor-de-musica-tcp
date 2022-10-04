package br.ufrgs.inf.tcp.trabalho.controller;

import br.ufrgs.inf.tcp.trabalho.view.UIView;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class PlayerUI extends UIView {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 300;
    private static final String DEFAULT_FILENAME = "midi_output";
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
        String filename = this.showInput("Save File", "Export", DEFAULT_FILENAME);
        // throw error if filename is null or if filename length is 0

        if (filename == null) { // user canceled input
            return;
        }

        filename = filename.length() == 0 ? DEFAULT_FILENAME : filename;

        filename += ".mid";

        controller.getTextReader().setBaseText(this.getMusicText());
        controller.getPlayer().restart();
        controller.processAllText();
        String midiText = controller.getMidiText();

        try {
            File outputFile = new File(filename);
            Pattern pattern = new Pattern();
            pattern.add(midiText);
            MidiFileManager.savePatternToMidi(pattern, outputFile);
            this.showMessage("Export successful!", "Export");
        } catch (IOException e) {
            this.showMessage("File cannot be written.\nIOException");
            e.printStackTrace();
//            throw new RuntimeException(e);
        }

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
                        text += fileIn.nextLine() + "\n";
                    }
                    this.setMusicInput(text);
                    this.showMessage("Import successful!", "Import");
                }
            } catch (FileNotFoundException e1) {
                this.showMessage("Something went wrong.\nFile not found.");
                e1.printStackTrace();
            }
        }
    }

    @Override
    protected void helpButtonClick(ActionEvent ev) {
        this.showMessage("A -> Lá\n" +
                "B -> Sí\n" +
                "C -> Dó\n" +
                "D -> Ré\n" +
                "E -> Mi\n" +
                "F -> Fá\n" +
                "G -> Sol\n" +
                "a,b,c,d,e,f,g -> Se caractere anterior era NOTA (A a G), repete nota; Caso contrário, Silêncio ou pausa\n" +
                "Caractere ESPAÇO -> Aumenta volume para o DOBRO do volume; Se não puder aumentar, volta ao volume default (de início)\n" +
                "! -> Trocar instrumento para o instrumento General MIDI #114 (Agogo)\n" +
                "O, o, I, i, U, u -> Trocar instrumento para o instrumento General MIDI #7 (Harpsichord)\n" +
                "Qualquer outra letra consoante -> Se caractere anterior era NOTA (A a G), repete nota; Caso contrário, Silêncio ou pausa\n" +
                "Dígito ímpar ou par -> Trocar instrumento para o instrumento General MIDI cujo numero é igual ao valor do instrumento ATUAL + valor do dígito\n" +
                "? -> Aumenta UMA oitava; Se não puder, aumentar, volta à oitava default (de início)\n" +
                "Nova Linha -> Trocar instrumento para o instrumento General MIDI #15 (Tubular Bells)\n" +
                "; -> Trocar instrumento para o instrumento General MIDI #76 (Pan Flute)\n" +
                ", -> Trocar instrumento para o instrumento General MIDI #20 (Church Organ)\n" +
                "Nenhum dos anteriores -> Se caractere anterior era NOTA (A a G), repete nota; Caso contrário, Silêncio ou pausa", "Help");

    }

}
