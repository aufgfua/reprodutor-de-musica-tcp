package br.ufrgs.inf.tcp.trabalho.controller;

import br.ufrgs.inf.tcp.trabalho.model.MusicPlayer;
import br.ufrgs.inf.tcp.trabalho.model.TextCommand;
import br.ufrgs.inf.tcp.trabalho.model.TextReader;

import java.util.Arrays;

public class PlayerController {


    private static final String[][] patterns = new String[][]{
            {"[A-G]", TextCommand.NOTE.name()},
            {"a|b|c|d|e|f|g", TextCommand.LOWERCASE_NOTE.name()},
            {" ", TextCommand.SPACE.name()}, // Dobrar volume or default
            {"!", TextCommand.EXCLAMATION.name()},
            {"O|o|I|i|U|u", TextCommand.OTHER_VOWEL.name()},
            {"(?=[H-Zh-z])([^IOUiou])\n", TextCommand.OTHER_CONSONANT.name()},
            {"\\d", TextCommand.NUMBER.name()},
            {"\\?", TextCommand.INTERROGATION.name()},
            {"\n", TextCommand.NEW_LINE.name()},
            {";", TextCommand.SEMI_COLON.name()},
            {",", TextCommand.COLON.name()},
    };
    private static final String patternNotFound = TextCommand.ELSE.name();
    private static final String[] stringPatterns = Arrays.stream(patterns).map(pattern -> pattern[0]).toArray(String[]::new);

    private TextReader textReader;
    private MusicPlayer player;
    private char lastChar;
    private String currentText;


    public PlayerController() {
        this.textReader = new TextReader(); // TODO get data
        this.player = new MusicPlayer();
    }


    public MusicPlayer getPlayer() {
        return player;
    }


    public TextReader getTextReader() {
        return textReader;
    }

    public void setTextReader(TextReader textReader) {
        this.textReader = textReader;
    }

    public TextCommand processText(String text) {
        String[][] mappedPatterns = PlayerController.patterns;
        TextCommand command = null;

        for (String[] pattern : mappedPatterns) {
            String searchCommand = "^" + pattern[0] + "$"; // now we search between the static mapped commands to find its functionality
            // this time we want ^text$ (to find which mapped command is exactly between line beginning (^) and line end ($)

            if (text.matches(searchCommand)) {
                command = TextCommand.valueOf(pattern[1]);
            }
        }
        if (command == null) {
            command = TextCommand.valueOf(PlayerController.patternNotFound);
        }
        return command;
    }

    private void processCommand(TextCommand command) {

        switch (command) {
            case NOTE:
                player.processNote(getCurrentText().toUpperCase());
                break;
            case SPACE:
                if (player.getVolume() * 2 <= MusicPlayer.VOLUME_MAX) {
                    player.increaseVolume(player.getVolume());
                } else {
                    player.setDefaultVolume();
                }
                break;
            case EXCLAMATION:
                player.setCurrentInstrument(MusicPlayer.INST_AGOGO);
                break;
            case OTHER_VOWEL:
                player.setCurrentInstrument(MusicPlayer.INST_HARPSICHORD);
                break;
            case NUMBER:
                int newInstrument = player.getCurrentInstrument() + Integer.parseInt(getCurrentText());
                player.setCurrentInstrument(newInstrument);
                break;
            case INTERROGATION:
                if (player.getOctave() + 1 <= MusicPlayer.OCTAVE_MAX) {
                    player.increaseOctave(1);
                } else {
                    player.setDefaultOctave();
                }
                break;
            case NEW_LINE:
                player.setCurrentInstrument(MusicPlayer.INST_TUBULAR_BELLS);
                break;
            case SEMI_COLON:
                player.setCurrentInstrument(MusicPlayer.INST_PAN_FLUTE);
                break;
            case COLON:
                player.setCurrentInstrument(MusicPlayer.INST_CHURCH_ORGAN);
                break;
            case LOWERCASE_NOTE:
            case OTHER_CONSONANT:
            case ELSE:
                String lastReadChar = String.valueOf(getLastChar());
                TextCommand lastCommand = processText(lastReadChar);
                if (lastCommand == TextCommand.NOTE) {
                    player.processNote(lastReadChar.toUpperCase());
                } else {
                    player.addPause(1);
                }
                break;
        }
    }

    public void processAllText() {
        while (textReader.hasNextChar()) {
            String rawText = textReader.readPatterns(PlayerController.stringPatterns);

            setCurrentText(rawText);

            TextCommand processedCommand = processText(rawText);

            processCommand(processedCommand);

            setLastChar(rawText.charAt(rawText.length() - 1));

        }
    }

    public void run() {
        run(true);
    }

    public void run(boolean restart) {
        if (restart) {
            player.restart();
        }

        processAllText();

        player.play();
    }

    public char getLastChar() {
        return lastChar;
    }

    public void setLastChar(char lastChar) {
        this.lastChar = lastChar;
    }

    public String getCurrentText() {
        return currentText;
    }

    public void setCurrentText(String currentText) {
        this.currentText = currentText;
    }

    public String getMidiText() {
        return player.getCurrentMusic();
    }
}