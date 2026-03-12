package com.example.miniproyecto1.util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioManager {

    private static MediaPlayer currentMusic;

    public static void play(String path, boolean loop) {
        stopCurrent();
        Media media = new Media(path);
        currentMusic = new MediaPlayer(media);
        currentMusic.setCycleCount(loop ? MediaPlayer.INDEFINITE : 1);
        currentMusic.play();
    }

    public static void stopCurrent() {
        if (currentMusic != null) {
            currentMusic.stop();
            currentMusic.dispose();
            currentMusic = null;
        }
    }

    public static MediaPlayer getCurrent() {
        return currentMusic;
    }
}