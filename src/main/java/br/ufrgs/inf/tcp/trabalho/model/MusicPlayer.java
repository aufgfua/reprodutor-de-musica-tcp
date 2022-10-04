package br.ufrgs.inf.tcp.trabalho.model;

import org.jfugue.player.Player;

public class MusicPlayer {

    public static final int VOLUME_MAX = 127;
    public static final int VOLUME_MIN = 16;
    public static final int VOLUME_DEF = 32;
    public static final int BPM_MAX = 300;
    public static final int BPM_MIN = 50;
    public static final int BPM_DEF = 100;
    public static final int OCTAVE_MAX = 10; // Min and max values according to JFUGUE documentation (http://www.jfugue.org/articles/differences.html)
    public static final int OCTAVE_MIN = 0;
    public static final int OCTAVE_DEF = 5;

    public static final int INST_PIANO = 0;
    public static final int INST_HARPSICHORD = 7;
    public static final int INST_TUBULAR_BELLS = 16;
    public static final int INST_PAN_FLUTE = 76;
    public static final int INST_CHURCH_ORGAN = 20;
    public static final int INST_AGOGO = 114;
    public static final int DEFAULT_INSTRUMENT = INST_PIANO;

    private int octave;
    private int volume;
    private int bpm;
    private int currentInstrument;
    private String lastNote;
    private String currentMusic;

    private Player player = new Player();

    public MusicPlayer(int octave, int volume, int bpm, int instrument) {
        setOctave(octave);
        setVolume(volume);
        setBpm(bpm);
        this.currentInstrument = instrument;
        this.lastNote = "";
        this.currentMusic = ":CON(7, " + VOLUME_DEF + ") I" + DEFAULT_INSTRUMENT + " ";
    }

    public MusicPlayer() {
        this(MusicPlayer.OCTAVE_DEF, MusicPlayer.VOLUME_DEF, MusicPlayer.BPM_DEF, MusicPlayer.DEFAULT_INSTRUMENT);
    }

    public int getOctave() {
        return octave;
    }

    public void setOctave(int octave) {
        int filteredOctave = octave;
        if (filteredOctave > MusicPlayer.OCTAVE_MAX) {
            filteredOctave = MusicPlayer.OCTAVE_MAX;
        }
        if (filteredOctave < MusicPlayer.OCTAVE_MIN) {
            filteredOctave = MusicPlayer.OCTAVE_MIN;
        }
        this.octave = filteredOctave;
    }

    public void setDefaultOctave() {
        setOctave(this.OCTAVE_DEF);
    }

    public void setDefaultVolume() {
        setVolume(this.VOLUME_DEF);
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        int filteredVolume = volume;
        if (filteredVolume > MusicPlayer.VOLUME_MAX) {
            filteredVolume = MusicPlayer.VOLUME_MAX;
        }
        if (filteredVolume < MusicPlayer.VOLUME_MIN) {
            filteredVolume = MusicPlayer.VOLUME_MIN;
        }
        this.volume = filteredVolume;
        appendCommand(":CON(7, " + this.volume + ")");
    }

    public void increaseVolume(Integer value) {
        setVolume(this.volume + value);
    }

    public void increaseVolume() {
        increaseVolume(1);
    }

    public void decreaseVolume(Integer value) {
        setVolume(this.volume - value);
    }

    public void decreaseVolume() {
        decreaseVolume(1);
    }


    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {

        int filteredBpm = bpm;
        if (filteredBpm > MusicPlayer.BPM_MAX) {
            filteredBpm = MusicPlayer.BPM_MAX;
        }
        if (filteredBpm < MusicPlayer.BPM_MIN) {
            filteredBpm = MusicPlayer.BPM_MIN;
        }
        this.bpm = filteredBpm;
        appendCommand("T" + this.bpm);
    }

    public int getCurrentInstrument() {
        return currentInstrument;
    }

    public void setCurrentInstrument(int instrument) {
        this.currentInstrument = instrument;
        appendCommand("I" + this.currentInstrument);
    }

    public void processNote(String note) {
        appendCommand(note + octave);
    }

    public void play() {
        System.out.println(currentMusic);
        player.play(currentMusic);
        currentMusic = "";
    }

    public void increaseBpm(Integer value) {
        setBpm(this.bpm + value);
    }

    public void increaseBpm() {
        increaseBpm(1);
    }

    public void decreaseBpm(Integer value) {
        setBpm(this.bpm - value);
    }

    public void decreaseBpm() {
        decreaseBpm(1);
    }


    public void increaseOctave(Integer value) {
        setOctave(this.octave + value);
    }

    public void increaseOctave() {
        increaseOctave(1);
    }

    public void decreaseOctave(Integer value) {
        setOctave(this.octave - value);
    }

    public void decreaseOctave() {
        decreaseOctave(1);
    }

    public void appendCommand(String command) {
        this.currentMusic += command + " ";
    }

}
