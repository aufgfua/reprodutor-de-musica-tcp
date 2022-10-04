package br.ufrgs.inf.tcp.trabalho.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MusicPlayerTest {
    @Test
    void testConstructor() {
        MusicPlayer actualMusicPlayer = new MusicPlayer(1, 1, 1, 1);

        assertEquals(MusicPlayer.BPM_MIN, actualMusicPlayer.getBpm());
        assertEquals(1, actualMusicPlayer.getCurrentInstrument());
        assertEquals(1, actualMusicPlayer.getOctave());
        assertEquals(":CON(7, 63) I0 ", actualMusicPlayer.getCurrentMusic());
    }

    @Test
    void testConstructor2() {
        MusicPlayer actualMusicPlayer = new MusicPlayer(MusicPlayer.OCTAVE_MAX, MusicPlayer.VOLUME_MAX,
                MusicPlayer.BPM_MAX, 1);

        assertEquals(MusicPlayer.BPM_MAX, actualMusicPlayer.getBpm());
        assertEquals(MusicPlayer.VOLUME_MAX, actualMusicPlayer.getVolume());
        assertEquals(MusicPlayer.OCTAVE_MAX, actualMusicPlayer.getOctave());
        assertEquals(":CON(7, 63) I0 ", actualMusicPlayer.getCurrentMusic());
        assertEquals(1, actualMusicPlayer.getCurrentInstrument());
    }


    @Test
    void testSetOctave() {
        MusicPlayer musicPlayer = new MusicPlayer();
        musicPlayer.setOctave(1);
        assertEquals(1, musicPlayer.getOctave());
    }

    @Test
    void testSetOctave2() {
        MusicPlayer musicPlayer = new MusicPlayer();
        musicPlayer.setOctave(-1);
        assertEquals(0, musicPlayer.getOctave());
    }

    @Test
    void testSetOctave3() {
        MusicPlayer musicPlayer = new MusicPlayer();
        musicPlayer.setOctave(MusicPlayer.INST_TUBULAR_BELLS);
        assertEquals(MusicPlayer.OCTAVE_MAX, musicPlayer.getOctave());
    }

    @Test
    void testSetVolume() {
        MusicPlayer musicPlayer = new MusicPlayer();
        musicPlayer.setVolume(1);
        assertEquals(MusicPlayer.VOLUME_MIN, musicPlayer.getVolume());
        assertEquals(":CON(7, 63) I0 :CON(7, 16) ", musicPlayer.getCurrentMusic());
    }

    @Test
    void testSetVolume2() {
        MusicPlayer musicPlayer = new MusicPlayer();
        musicPlayer.setVolume(MusicPlayer.VOLUME_MAX);
        assertEquals(MusicPlayer.VOLUME_MAX, musicPlayer.getVolume());
        assertEquals(":CON(7, 63) I0 :CON(7, 127) ", musicPlayer.getCurrentMusic());
    }

    @Test
    void testSetBpm() {
        MusicPlayer musicPlayer = new MusicPlayer();
        musicPlayer.setBpm(1);
        assertEquals(MusicPlayer.BPM_MIN, musicPlayer.getBpm());
        assertEquals(":CON(7, 63) I0 T50 ", musicPlayer.getCurrentMusic());
    }

    @Test
    void testSetBpm2() {
        MusicPlayer musicPlayer = new MusicPlayer();
        musicPlayer.setBpm(MusicPlayer.BPM_MAX);
        assertEquals(MusicPlayer.BPM_MAX, musicPlayer.getBpm());
        assertEquals(":CON(7, 63) I0 T300 ", musicPlayer.getCurrentMusic());
        musicPlayer.setBpm(musicPlayer.getBpm() + 1);
        assertEquals(MusicPlayer.BPM_MAX, musicPlayer.getBpm());
        assertEquals(":CON(7, 63) I0 T300 T300 ", musicPlayer.getCurrentMusic());
    }

}

