package br.ufrgs.inf.tcp.trabalho.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class UIView extends JFrame {

    private static final int musicInputVerticalPadding = 0;
    private static final int musicInputColumnCount = 35;
    private static final int musicInputRows = 6;
    private static final int musicInputCols = 35;
    private static final String temporaryTestMusicInitialString = "CDEFGAB?C";
    private static final int TITLE_FONT_SIZE = 24;
    private JPanel mainPanel;
    private JPanel textPanel;
    private JPanel optionsPanel;
    private JPanel controlsPanel;
    private JButton btnPlay;
    private JButton btnExport;
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
        this.getControlsPanel().add(getOptionsPanel(), BorderLayout.PAGE_START);

        this.setResizable(false);

        this.events();
    }

    public JPanel getMainPanel() {
        if (mainPanel == null) {
            mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JLabel title = new JLabel("Music Player");
            title.setFont(new Font("Arial", Font.BOLD, UIView.TITLE_FONT_SIZE));
            mainPanel.add(title);
        }
        return mainPanel;
    }

    public JPanel getTextPanel() {
        if (textPanel == null) {
            textPanel = new JPanel(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            musicInputArea = new JTextArea(temporaryTestMusicInitialString, musicInputRows, musicInputCols);
            textPanel.add(new JScrollPane(musicInputArea), c);
        }
        return textPanel;
    }

    public JPanel getOptionsPanel() {
        if (optionsPanel == null) {
            optionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));


            btnImportFile = new JButton("Import File...");

            optionsPanel.add(btnImportFile);
        }
        return optionsPanel;
    }


    protected abstract void playButtonClick(ActionEvent ev);

    protected abstract void exportButtonClick(ActionEvent ev);

    protected abstract void importButtonClick(ActionEvent ev);

    private void events() {
        btnPlay.addActionListener(this::playButtonClick);
        btnExport.addActionListener(this::exportButtonClick);
        btnImportFile.addActionListener(this::importButtonClick);
    }

    public JPanel getControlsPanel() {
        if (controlsPanel == null) {
            controlsPanel = new JPanel(new BorderLayout());
            this.getContentPane().add(getControlsPanel(), BorderLayout.PAGE_END);

            JPanel playPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            btnPlay = new JButton("Play");
            btnExport = new JButton("Export File");
            playPanel.add(btnExport);
            playPanel.add(btnPlay);

            controlsPanel.add(playPanel, BorderLayout.PAGE_END);
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