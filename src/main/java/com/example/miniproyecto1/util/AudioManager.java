package com.example.miniproyecto1.util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Utility class that manages background music playback for Escritura Rapida.
 * Ensures only one audio track plays at a time across all scenes.
 * Uses a static MediaPlayer to maintain a single global audio instance.
 *
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 */
public class AudioManager {

    /** The currently active MediaPlayer instance. */
    private static MediaPlayer currentMusic;

    /**
     * Plays the audio file at the given path.
     * Stops and disposes any currently playing audio before starting the new one.
     *
     * @param path the external form URL path to the audio file.
     * @param loop true to loop the audio indefinitely, false to play once.
     */
    public static void play(String path, boolean loop) {
        stopCurrent();
        Media media = new Media(path);
        currentMusic = new MediaPlayer(media);
        currentMusic.setCycleCount(loop ? MediaPlayer.INDEFINITE : 1);
        currentMusic.play();
    }

    /**
     * Stops and disposes the currently playing audio, freeing memory resources.
     * Does nothing if no audio is currently playing.
     */
    public static void stopCurrent() {
        if (currentMusic != null) {
            currentMusic.stop();
            currentMusic.dispose();
            currentMusic = null;
        }
    }

    /**
     * Returns the currently active MediaPlayer instance.
     *
     * @return the current {@link MediaPlayer}, or null if no audio is playing.
     */
    public static MediaPlayer getCurrent() {
        return currentMusic;
    }
}