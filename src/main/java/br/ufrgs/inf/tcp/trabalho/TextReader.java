package br.ufrgs.inf.tcp.trabalho;

import java.util.regex.Pattern;

public class TextReader {
    private String baseText;
    private Integer pointer;

    TextReader(String baseText) {
        this.baseText = baseText;
        this.pointer = 0;
    }

    public TextReader() {
        this.baseText = "";
        this.pointer = 0;
    }

    public String getBaseText() {
        return baseText;
    }

    public void setBaseText(String baseText) {
        this.baseText = baseText;
    }


    public boolean hasNextChar() {
        return hasNextChar(1);
    }

    public boolean hasNextChar(int count) {
        if (pointer + count <= baseText.length()) {
            return true;
        } else {
            return false;
        }
    }

    public String read() {
        return read(1);
    }


    public String readPattern(String pattern) {
        String findPattern = "^(" + pattern + ").*$"; // we will replace all text with everything that is not PATTERN
        String foundText = "";
        if(baseText.matches(findPattern)){
            foundText = baseText.replaceFirst(findPattern, "$1");
            String restOfText = baseText.replaceFirst(Pattern.quote(foundText), "");
            baseText = restOfText;
            return foundText;
        } else {
            return read(1);
        }
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

    public void advancePointer(Integer value) {
        pointer += value;
    }

    public void advancePointer() {
        advancePointer(1);
    }

    public void returnPointer(Integer value) {
        pointer -= value;
    }

    public void returnPointer() {
        returnPointer(1);
    }
}
