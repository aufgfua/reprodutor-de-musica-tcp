package br.ufrgs.inf.tcp.trabalho.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextReaderTest {
    @Test
    void testConstructor() {
        TextReader actualTextReader = new TextReader("Base Text");
        assertEquals("Base Text", actualTextReader.getBaseText());
        assertTrue(actualTextReader.hasNextChar());
    }

    @Test
    void testSetBaseText() {
        TextReader textReader = new TextReader("Base Text");
        textReader.setBaseText("Base Text");
        assertEquals("Base Text", textReader.getBaseText());
        assertTrue(textReader.hasNextChar());
    }

    @Test
    void testHasNextChar() {
        assertTrue((new TextReader("Base Text")).hasNextChar(3));
        assertFalse((new TextReader("")).hasNextChar(3));
        assertTrue((new TextReader("Base Text")).hasNextChar());
        assertFalse((new TextReader("")).hasNextChar());
    }


    @Test
    void testRead() {
        TextReader textReader = new TextReader("Base Text");
        assertEquals("B", textReader.read(1));
        assertTrue(textReader.hasNextChar());
    }

}

