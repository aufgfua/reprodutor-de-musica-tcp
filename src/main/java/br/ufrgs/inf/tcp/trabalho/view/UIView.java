package br.ufrgs.inf.tcp.trabalho.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class UIView extends JFrame {

    private static final int musicInputVerticalPadding = 0;
    private static final int musicInputColumnCount = 35;
    private static final int musicInputRows = 6;
    private static final int musicInputCols = 35;
    private static final String temporaryTestMusicInitialString = "ABCDEFG";
    private static final int TITLE_FONT_SIZE = 24;
    private JPanel mainPanel;
    private JPanel textPanel;
    private JPanel optionsPanel;
    private JPanel controlsPanel;
    private JButton btnPlay;
    private JButton btnExport;
    private JButton btnImportFile;
    private JButton btnHelp;
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

    public void setMusicInput(String text) {
        this.musicInputArea.setText(text);
    }

    public JPanel getMainPanel() {
        if (mainPanel == null) {
            mainPanel = new JPanel(new BorderLayout());
            JLabel title = new JLabel("Music Player");
            title.setFont(new Font("Arial", Font.BOLD, UIView.TITLE_FONT_SIZE));

            JPanel downPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            downPanel.add(title);
            mainPanel.add(downPanel, BorderLayout.SOUTH);
            btnHelp = new JButton("?");
            mainPanel.add(btnHelp, BorderLayout.EAST);
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

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void showMessage(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public String showInput(String message) {
        return (String) JOptionPane.showInputDialog(this, message);
    }

    public String showInput(String message, String title, String defaultValue) {
        String result = (String) JOptionPane.showInputDialog(
                this,
                message,
                title,
                JOptionPane.PLAIN_MESSAGE,
                null,
                null, defaultValue);
        return result;
    }

    protected abstract void playButtonClick(ActionEvent ev);

    protected abstract void exportButtonClick(ActionEvent ev);

    protected abstract void importButtonClick(ActionEvent ev);

    protected abstract void helpButtonClick(ActionEvent ev);

    private void events() {
        btnPlay.addActionListener(this::playButtonClick);
        btnExport.addActionListener(this::exportButtonClick);
        btnImportFile.addActionListener(this::importButtonClick);
        btnHelp.addActionListener(this::helpButtonClick);
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