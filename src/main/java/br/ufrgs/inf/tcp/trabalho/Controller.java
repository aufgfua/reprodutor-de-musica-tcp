package br.ufrgs.inf.tcp.trabalho;

public class Controller {

    private TextReader textReader;
    private String player;
    private String lastNote;


    public String processText(String text) {
        return "";
    }

    public Controller() {
        this.textReader = new TextReader("Teste text reader");
        this.player = "";
        this.lastNote = "";
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

    public static void main(String[] args) {

        Controller controller = new Controller();

        controller.run();

        System.out.println("Hello world!");

    }
}