package com.example.miniproyecto1.controller.adapter;

import javafx.scene.input.KeyEvent;

public interface GameKeyAdapter {
    default void onKeyPressed(KeyEvent event) {}
    default void onKeyReleased(KeyEvent event) {}
    default void onKeyTyped(KeyEvent event) {}
}