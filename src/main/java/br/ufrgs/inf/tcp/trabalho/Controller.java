package br.ufrgs.inf.tcp.trabalho;

public class Controller {

    private TextReader textReader;
    private MusicPlayer player;


    public Controller() {
        this.textReader = new TextReader(); // TODO get data
        this.player = new MusicPlayer();
    }


    public static void main(String[] args) {

        Controller controller = new Controller();

        controller.getTextReader().setBaseText("C D E F G A B C6");

        controller.run();

    }

    public String processText(String text) {
        return "";
    }

    public TextReader getTextReader() {
        return textReader;
    }

    public void setTextReader(TextReader textReader) {
        this.textReader = textReader;
    }

    public void run() {
        while (textReader.hasNextChar()) {
            String note = textReader.read();
            player.processNote(note);
        }
        player.play();
    }
}