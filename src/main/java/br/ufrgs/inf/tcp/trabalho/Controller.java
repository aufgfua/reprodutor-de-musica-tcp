package br.ufrgs.inf.tcp.trabalho;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class Controller {

    private TextReader textReader;
    private MusicPlayer player;
    private static final String[][] patterns = new String[][]{
            {"A|a",TextCommand.A.name()},
            {"B|b",TextCommand.B.name()},
            {"C|c",TextCommand.C.name()},
            {"D|d",TextCommand.D.name()},
            {"E|e",TextCommand.E.name()},
            {"F|f",TextCommand.F.name()},
            {"G|g",TextCommand.G.name()},
            {" ",TextCommand.SPACE.name()},
            {"\\+",TextCommand.INC_VOLUME.name()},
            {"\\-",TextCommand.DEC_VOLUME.name()},
            {"O|o|I|i|U|u",TextCommand.OTHER_VOWEL.name()},
            {"R\\+", TextCommand.INC_OCTAVE.name()},
            {"R\\-", TextCommand.DEC_OCTAVE.name()},
            {"\\?", TextCommand.RANDOM.name()},
            {"\n", TextCommand.NEW_LINE.name()},
            {"BPM\\+", TextCommand.INC_BPM.name()},
            {";", TextCommand.SHUFFLE_BPM.name()},
    }; // Array of [PATTERN,FUNCTION]
    private static final String patternNotFound = TextCommand.NOP.name();

    private static final int baseBpmIncrease = 80;

    private static final String processedPattern = Arrays.stream(patterns)
            .map(pattern -> pattern[0])
            .collect(
                    Collectors.joining("|")
            ); // processed pattern to find next function


    public static void main(String[] args) {

        Controller controller = new Controller();

        controller.getTextReader().setBaseText("R-R-BR+BER+ER-ER+E");

        controller.run();

    }


    public Controller() {
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
        String[][] mappedPatterns = Controller.patterns;
        TextCommand command = null;

        for(String[] pattern : mappedPatterns) {
            String searchCommand = "^" + pattern[0] + "$"; // now we search between the static mapped commands to find its functionality
            // this time we want ^text$ (to find which mapped command is exactly between line beginning (^) and line end ($)

            if(text.matches(searchCommand)) {
                command = TextCommand.valueOf(pattern[1]);
            }
        }
        if(command == null){
            command = TextCommand.valueOf(Controller.patternNotFound);
        }
        return command;
    }

    private void processCommand(TextCommand command){

        switch(command) {
            case A:
            case B:
            case C:
            case D:
            case E:
            case F:
            case G:
                player.processNote(command.toString());
                break;
            case SPACE:
                // TODO discover how to pause. For now, will just send space
                player.processNote(" ");
                break;
            case INC_VOLUME:
                player.setVolume(player.getVolume() * 2);
                break;
            case DEC_VOLUME:
                player.setVolume(MusicPlayer.VOLUME_DEF);
                break;
            case OTHER_VOWEL:
                // TODO Make that weird function
                break;
            case INC_OCTAVE:
                player.increaseOctave();
                break;
            case DEC_OCTAVE:
                player.decreaseOctave();
                break;
            case INC_BPM:
                player.increaseBpm(Controller.baseBpmIncrease);
                break;
            case RANDOM:
                String[] notes = new String[]{"A","B","C","D","E","F","G"};
                int randomNote = new Random().nextInt(notes.length);
                player.processNote(notes[randomNote]);
                break;
            case SHUFFLE_BPM:
                int randomRange = MusicPlayer.BPM_MAX - MusicPlayer.BPM_MIN;
                int randomBpm = MusicPlayer.BPM_MIN + new Random().nextInt(randomRange);
                player.setBpm(randomBpm);
                break;
            case NEW_LINE:
                // TODO make instrument selection
                break;
            case NOP:
                break;

        }
    }

    public void run() {
        while (textReader.hasNextChar()) {
            String rawText = textReader.readPattern(Controller.processedPattern);

            TextCommand processedCommand = processText(rawText);

            processCommand(processedCommand);

        }
        player.play();
    }
}