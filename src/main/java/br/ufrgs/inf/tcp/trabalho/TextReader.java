package br.ufrgs.inf.tcp.trabalho;

public class TextReader {
    private String baseText;
    private Integer pointer;

    public TextReader(String baseText) {
        this.baseText = baseText;
        this.pointer = 0;
    }

    public String getBaseText() {
        return baseText;
    }

    public void setBaseText(String baseText) {
        this.baseText = baseText;
    }

    public boolean hasNextChar() {
        if (pointer < baseText.length()) {
            return true;
        } else {
            return false;
        }
    }

    public String read() {
        return read(1);
    }

    public String read(Integer numChars) {
        String nextChars;

        if (hasNextChar()) {
            nextChars = baseText.substring(pointer, pointer + numChars);
            pointer += numChars;
            return nextChars;
        }

        return "";
    }
}
