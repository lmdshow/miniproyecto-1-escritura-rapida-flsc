package com.example.miniproyecto1.model.words;

/**
 * Interface that defines the contract for word management in Escritura Rapida.
 * Provides methods for generating and validating words based on the game level.
 *
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 */
public interface IWords {

    /**
     * Generates a random word based on the given level.
     *
     * @param level the current game level used to determine word difficulty.
     * @return a randomly selected word as a String.
     */
    String generateWord(int level);

    /**
     * Validates whether the given word matches the current word.
     *
     * @param word the word typed by the player.
     * @return true if the word matches, false otherwise.
     */
    Boolean validateWord(String word);
}