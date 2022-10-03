package br.ufrgs.inf.tcp.trabalho.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class UIView extends JFrame {

    private static final int musicInputVerticalPadding = 55;
    private static final int musicInputColumnCount = 35;
    private static final int musicInputRows = 6;
    private static final int musicInputCols = 35;
    private static final String temporaryTestMusicInitialString = "R-R-BR+BBPM+ER+BPM-ER-BPM+ER+BPM-E!R-R-BR+BBPM+ER+BPM-ER-BPM+ER+BPM-EIR-R-BR+BBPM+ER+BPM-ER-BPM+ER+BPM-E";
    private JPanel mainPanel;
    private JPanel textPanel;
    private JPanel optionsPanel;
    private JPanel controlsPanel;
    private JButton btnPlay;
    private JButton btnImportFile;
    private JTextField musicInputField;
    private JTextArea musicInputArea;

    public UIView(int width, int height) {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(getMainPanel(), BorderLayout.PAGE_START);
        this.getContentPane().add(getTextPanel(), BorderLayout.CENTER);
//        this.getContentPane().add(getOptionsPanel(), BorderLayout.CENTER);
        this.getContentPane().add(getControlsPanel(), BorderLayout.PAGE_END);

        this.setResizable(false);

        this.events();
    }

    public JPanel getMainPanel() {
        if (mainPanel == null) {
            mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            mainPanel.add(new JLabel("Music Input:"));
        }
        return mainPanel;
    }

    public JPanel getTextPanel() {
        if (textPanel == null) {
            textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, UIView.musicInputVerticalPadding));

//            musicInputField = new JTextField(temporaryTestMusicInitialString, musicInputColumnCount);
//            musicInputField = new JTextField(temporaryTestMusicInitialString, musicInputColumnCount);
//            textPanel.add(musicInputField);

            musicInputArea = new JTextArea(temporaryTestMusicInitialString, musicInputRows, musicInputCols);
            textPanel.add(new JScrollPane(musicInputArea));
        }
        return textPanel;
    }

    public JPanel getOptionsPanel() {
        if (optionsPanel == null) {
            optionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

            btnImportFile = new JButton("Import File..");

            optionsPanel.add(btnImportFile);
        }
        return optionsPanel;
    }


    protected abstract void playButtonClick(ActionEvent ev);

    private void events() {
        btnPlay.addActionListener(this::playButtonClick);
    }

    public JPanel getControlsPanel() {
        if (controlsPanel == null) {
            controlsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

            btnPlay = new JButton("Play");

            controlsPanel.add(btnPlay);
        }
        return controlsPanel;
    }

    public JTextField getMusicInputField() {
        return musicInputField;
    }

    public String getMusicText() {
        return musicInputArea.getText();
    }
}