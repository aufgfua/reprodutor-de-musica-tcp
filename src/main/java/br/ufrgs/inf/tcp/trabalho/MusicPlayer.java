package br.ufrgs.inf.tcp.trabalho;

import org.jfugue.player.Player;

public class MusicPlayer {
    private int octave;
    private int volume;
    private int bpm;
    private String instrument;

    private Player player = new Player();

    public MusicPlayer(int octave, int volume, int bpm, String instrument) {
        this.octave = octave;
        this.volume = volume;
        this.bpm = bpm;
        this.instrument = instrument;
    }

    public MusicPlayer() {
        this.octave = 2;
        this.volume = 2;
        this.bpm = 100;
        this.instrument = "PIANO"; // TODO change
    }

    public int getOctave() {
        return octave;
    }

    public void setOctave(int octave) {
        this.octave = octave;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String play(String note) {
        // play note

        player.play(note);

        return note;
    }

    public void increaseVolume(Integer value) {
        this.volume += value;
    }

    public void increaseVolume() {
        increaseVolume(1);
    }

    public void decreaseVolume(Integer value) {
        this.volume -= value;
    }

    public void decreaseVolume() {
        decreaseVolume(1);
    }

    public void increaseBpm(Integer value) {
        this.bpm += value;
    }

    public void increaseBpm() {
        increaseBpm(1);
    }

    public void decreaseBpm(Integer value) {
        this.bpm -= value;
    }

    public void decreaseBpm() {
        decreaseBpm(1);
    }


}
