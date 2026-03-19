package com.example.miniproyecto1.model.game;

import com.example.miniproyecto1.model.words.Words;

/**
 * Represents the core game logic for Escritura Rapida.
 * Manages level progression, time calculation, and word validation.
 * Implements the {@link IGame} interface.
 *
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 */
public class Game implements IGame {

    /** Initial time in seconds for each level. */
    private static final int INITIAL_TIME = 20;

    /** Minimum time in seconds allowed per level. */
    private static final int MIN_TIME = 2;

    /** Time reduction in seconds applied every 5 levels. */
    private static final int TIME_REDUCTION = 2;

    /** Maximum number of levels in the game. */
    private static final int MAX_LEVEL = 45;

    /** The current level the player is on. */
    private int currentLevel;

    /** The word manager used to generate and validate words. */
    private Words words;

    /**
     * Constructs a new Game instance, initializing the level to 1
     * and creating a new Words manager.
     */
    public Game() {
        currentLevel = 1;
        words = new Words();
    }

    /**
     * Starts the game by resetting the level to 1 and generating the first word.
     */
    @Override
    public void startGame() {
        currentLevel = 1;
        words.generateWord(currentLevel);
    }

    /**
     * Advances the game to the next level by incrementing the current level.
     */
    @Override
    public void nextLevel() {
        currentLevel++;
    }

    /**
     * Restarts the game by resetting the current level to 1.
     */
    @Override
    public void restart() {
        currentLevel = 1;
    }

    /**
     * Returns the current level of the game.
     *
     * @return the current level as an integer.
     */
    @Override
    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Calculates the time allowed for the current level.
     * Every 5 levels, the time is reduced by 2 seconds, with a minimum of 2 seconds.
     *
     * @return the time in seconds for the current level.
     */
    @Override
    public int getTimeForLevel() {
        int reduction = ((currentLevel - 1) / 5) * TIME_REDUCTION;
        return Math.max(INITIAL_TIME - reduction, MIN_TIME);
    }

    /**
     * Validates whether the given word matches the current word.
     *
     * @param word the word typed by the player.
     * @return true if the word matches, false otherwise.
     */
    @Override
    public boolean validateWord(String word) {
        return words.validateWord(word);
    }

    /**
     * Generates a random word based on the current level difficulty.
     *
     * @return a randomly selected word as a String.
     */
    @Override
    public String generateWord() {
        return words.generateWord(currentLevel);
    }

    /**
     * Returns the maximum number of levels in the game.
     *
     * @return the maximum level as an integer.
     */
    @Override
    public int getMaxLevel() {
        return MAX_LEVEL;
    }

    /**
     * Checks whether the player has reached the maximum level.
     *
     * @return true if the current level is equal to or greater than the maximum level.
     */
    @Override
    public boolean isMaxLevel() {
        return (currentLevel >= MAX_LEVEL);
    }
}