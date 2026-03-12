package com.example.miniproyecto1.controller.adapter;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class GameKeyHandler implements EventHandler<KeyEvent>, GameKeyAdapter {

    @Override
    public void handle(KeyEvent event) {
        switch (event.getEventType().getName()) {
            case "KEY_PRESSED" -> onKeyPressed(event);
            case "KEY_RELEASED" -> onKeyReleased(event);
            case "KEY_TYPED" -> onKeyTyped(event);
        }
    }

    public void onKeyPressed(KeyEvent event) {}
    public void onKeyReleased(KeyEvent event) {}
    public void onKeyTyped(KeyEvent event) {}
}