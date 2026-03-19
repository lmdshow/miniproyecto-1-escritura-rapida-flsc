package com.example.miniproyecto1.model.words;

import java.util.List;
import java.util.ArrayList;

/**
 * Manages the word lists for Escritura Rapida, organized by difficulty level.
 * Words are selected randomly without repetition within each difficulty tier.
 * Implements the {@link IWords} interface.
 *
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 */
public class Words implements IWords {

    /** List of easy words for levels 1 to 14. */
    private final List<String> easyWords;

    /** List of medium words for levels 15 to 29. */
    private final List<String> mediumWords;

    /** List of hard words for levels 30 and above. */
    private final List<String> hardWords;

    /** List of words already used in the current difficulty tier. */
    private final List<String> usedWords;

    /** Tracks the last selected word list to detect difficulty changes. */
    private List<String> lastSelectedList;

    /** The current word that the player must type. */
    private String currentWord;

    /**
     * Constructs a new Words instance and loads all word lists.
     */
    public Words() {
        easyWords = new ArrayList<>();
        mediumWords = new ArrayList<>();
        hardWords = new ArrayList<>();
        usedWords = new ArrayList<>();
        loadWords();
    }

    /**
     * Populates the easy, medium, and hard word lists with predefined words.
     */
    private void loadWords() {
        easyWords.add("Casa");
        easyWords.add("Perro");
        easyWords.add("Gato");
        easyWords.add("Luna");
        easyWords.add("Agua");
        easyWords.add("Fuego");
        easyWords.add("Viento");
        easyWords.add("Tierra");
        easyWords.add("Noche");
        easyWords.add("Cielo");
        easyWords.add("Playa");
        easyWords.add("Monte");
        easyWords.add("Flor");
        easyWords.add("Pez");
        easyWords.add("Sol");
        easyWords.add("Girasol");

        mediumWords.add("Mariposa");
        mediumWords.add("Cascada");
        mediumWords.add("Tormenta");
        mediumWords.add("Horizonte");
        mediumWords.add("Misterio");
        mediumWords.add("Aventura");
        mediumWords.add("Galaxia");
        mediumWords.add("Desierto");
        mediumWords.add("Volcanes");
        mediumWords.add("Cristal");
        mediumWords.add("Pantalla");
        mediumWords.add("Espejo");
        mediumWords.add("Palacio");
        mediumWords.add("Bosque");
        mediumWords.add("Relampago");
        mediumWords.add("Electricidad");

        hardWords.add("Constelacion");
        hardWords.add("Extraordinario");
        hardWords.add("Arquitectura");
        hardWords.add("Revolucionario");
        hardWords.add("Electrodomestico");
        hardWords.add("Contrarreloj");
        hardWords.add("Multiplicacion");
        hardWords.add("Supermercado");
        hardWords.add("Entretenimiento");
        hardWords.add("Transformacion");
        hardWords.add("Meteorologia");
        hardWords.add("Investigacion");
        hardWords.add("Desarrollador");
        hardWords.add("Programacion");
        hardWords.add("Computadora");
        hardWords.add("SonataClarodeLuna");
    }

    /**
     * Generates a random word based on the given level without repeating words.
     * Resets the used words list when all words in a tier have been used,
     * or when the difficulty tier changes.
     *
     * @param level the current game level used to select the difficulty tier.
     * @return a randomly selected non-repeated word as a String.
     */
    @Override
    public String generateWord(int level) {
        List<String> selectedList;
        if (level < 15) {
            selectedList = easyWords;
        } else if (level < 30) {
            selectedList = mediumWords;
        } else {
            selectedList = hardWords;
        }

        if (lastSelectedList != selectedList) {
            usedWords.clear();
            lastSelectedList = selectedList;
        }

        if (usedWords.containsAll(selectedList)) {
            usedWords.clear();
        }

        String word;
        do {
            int randomIndex = (int) (Math.random() * selectedList.size());
            word = selectedList.get(randomIndex);
        } while (usedWords.contains(word));

        usedWords.add(word);
        currentWord = word;
        return currentWord;
    }

    /**
     * Validates whether the given word matches the current word exactly.
     *
     * @param words the word typed by the player.
     * @return true if the word matches the current word, false otherwise.
     */
    @Override
    public Boolean validateWord(String words) {
        return words.equals(currentWord);
    }
}