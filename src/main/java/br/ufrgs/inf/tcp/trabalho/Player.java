package br.ufrgs.inf.tcp.trabalho;

public class Player {
    private int octave;
    private int volume;
    private int bpm;
    private String instrument;

    public Player(int octave, int volume, int bpm, String instrument) {
        this.octave = octave;
        this.volume = volume;
        this.bpm = bpm;
        this.instrument = instrument;
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

        return note;
    };

    public void increaseVolume() {
        this.volume++;
    }

    public void decreaseVolume() {
        this.volume--;
    }

    public void increaseBpm() {
        this.bpm++;
    }

    public void decreaseBpm() {
        this.bpm--;
    }


}
