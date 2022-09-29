package br.ufrgs.inf.tcp.trabalho;

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


    public String readPatterns(String[] patterns) {
        String workingText = baseText.substring(pointer, baseText.length());

        String findPattern = "";
        String foundText = "";
        String biggestFound = "";
        for(String pattern : patterns){
            findPattern =  "^(" + pattern + ").*$"; // we will replace all text with everything that is not PATTERN

            // We will do it this way to search for the greatest occurrence. Ex: for it not to find "B" instead of "BPM"
            if(workingText.matches(findPattern)) {
                foundText = workingText.replaceFirst(findPattern, "$1");
                if(foundText.length() > biggestFound.length()) {
                    biggestFound = foundText;
                }
            }
        }

        foundText = biggestFound;
        if(foundText != ""){
            pointer += foundText.length();
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
