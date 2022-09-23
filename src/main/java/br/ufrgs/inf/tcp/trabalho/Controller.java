package br.ufrgs.inf.tcp.trabalho;

public class Controller {

    private TextReader textReader;
    private MusicPlayer player;
    private String lastNote;


    public Controller() {
        this.textReader = new TextReader(); // TODO get data
        this.player = new MusicPlayer();
        this.lastNote = "";
    }


    public static void main(String[] args) {

        Controller controller = new Controller();

        controller.getTextReader().setBaseText("C D E F G A B C6");

        controller.run();

        System.out.println("Hello world!");

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
        String note = textReader.getBaseText();
        lastNote = player.play(note);
    }
}