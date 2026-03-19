package com.example.miniproyecto1.controller.adapter;

import javafx.scene.input.KeyEvent;

/**
 * Interface that defines the contract for keyboard event handling in Escritura Rapida.
 * Provides default empty implementations for each keyboard event type,
 * allowing subclasses to override only the methods they need.
 *
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 */
public interface GameKeyAdapter {

    /**
     * Called when a key is pressed.
     * Default implementation does nothing.
     *
     * @param event the key event triggered when a key is pressed.
     */
    default void onKeyPressed(KeyEvent event) {}

    /**
     * Called when a key is released.
     * Default implementation does nothing.
     *
     * @param event the key event triggered when a key is released.
     */
    default void onKeyReleased(KeyEvent event) {}

    /**
     * Called when a key is typed.
     * Default implementation does nothing.
     *
     * @param event the key event triggered when a key is typed.
     */
    default void onKeyTyped(KeyEvent event) {}
}