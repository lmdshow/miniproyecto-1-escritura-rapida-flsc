package com.example.miniproyecto1.model.game;

/**
 * Interface that defines the contract for the game logic of Escritura Rapida.
 * Provides methods for managing game state, level progression, and word validation.
 *
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 */
public interface IGame {

    /**
     * Starts the game from the beginning, resetting the level to 1.
     */
    void startGame();

    /**
     * Advances the game to the next level.
     */
    void nextLevel();

    /**
     * Restarts the game, resetting the current level to 1.
     */
    void restart();

    /**
     * Returns the current level of the game.
     *
     * @return the current level as an integer.
     */
    int getCurrentLevel();

    /**
     * Calculates and returns the time allowed for the current level.
     * Time decreases every 5 levels, with a minimum of 2 seconds.
     *
     * @return the time in seconds for the current level.
     */
    int getTimeForLevel();

    /**
     * Validates whether the given word matches the current word.
     *
     * @param word the word typed by the player.
     * @return true if the word is correct, false otherwise.
     */
    boolean validateWord(String word);

    /**
     * Generates and returns a random word based on the current level.
     *
     * @return a randomly selected word as a String.
     */
    String generateWord();

    /**
     * Returns the maximum number of levels in the game.
     *
     * @return the maximum level as an integer.
     */
    int getMaxLevel();

    /**
     * Checks whether the player has reached the maximum level.
     *
     * @return true if the current level is equal to or greater than the maximum level.
     */
    boolean isMaxLevel();
}