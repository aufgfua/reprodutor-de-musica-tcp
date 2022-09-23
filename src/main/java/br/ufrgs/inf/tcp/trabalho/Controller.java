package br.ufrgs.inf.tcp.trabalho;

public class Controller {

    private TextReader textReader;
    private Player player;
    private String lastNote;


    public Controller() {
        this.textReader = new TextReader(); // TODO get data
        this.player = new Player();
        this.lastNote = "";
    }


    public static void main(String[] args) {

        Controller controller = new Controller();

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
        while (textReader.hasNextChar()) {
            System.out.print(textReader.read());
        }
    }
}