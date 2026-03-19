package com.example.miniproyecto1.controller.adapter;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * Adapter class that bridges JavaFX keyboard events with the {@link GameKeyAdapter} interface.
 * Implements {@link EventHandler} to be compatible with JavaFX nodes,
 * and delegates each event type to the corresponding method defined in {@link GameKeyAdapter}.
 * Subclasses only need to override the keyboard event methods they require.
 *
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 * @see GameKeyAdapter
 */
public class GameKeyHandler implements EventHandler<KeyEvent>, GameKeyAdapter {

    /**
     * Handles the raw JavaFX keyboard event and delegates it
     * to the appropriate method based on the event type.
     *
     * @param event the key event received from JavaFX.
     */
    @Override
    public void handle(KeyEvent event) {
        switch (event.getEventType().getName()) {
            case "KEY_PRESSED" -> onKeyPressed(event);
            case "KEY_RELEASED" -> onKeyReleased(event);
            case "KEY_TYPED" -> onKeyTyped(event);
        }
    }

    /**
     * Called when a key is pressed.
     * Override this method to handle key press events.
     *
     * @param event the key event triggered when a key is pressed.
     */
    @Override
    public void onKeyPressed(KeyEvent event) {}

    /**
     * Called when a key is released.
     * Override this method to handle key release events.
     *
     * @param event the key event triggered when a key is released.
     */
    @Override
    public void onKeyReleased(KeyEvent event) {}

    /**
     * Called when a key is typed.
     * Override this method to handle key typed events.
     *
     * @param event the key event triggered when a key is typed.
     */
    @Override
    public void onKeyTyped(KeyEvent event) {}
}