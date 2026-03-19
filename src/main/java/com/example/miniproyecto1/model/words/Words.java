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
        easyWords.add("Luffy");
        easyWords.add("Zoro");
        easyWords.add("Nami");
        easyWords.add("Robin");
        easyWords.add("Sanji");
        easyWords.add("Chopper");
        easyWords.add("Brook");
        easyWords.add("Usopp");
        easyWords.add("Franky");
        easyWords.add("Jinbe");
        easyWords.add("Ace");
        easyWords.add("Sabo");
        easyWords.add("Vivi");
        easyWords.add("Coby");
        easyWords.add("Carrot");

        mediumWords.add("OnePiece");
        mediumWords.add("Shirohige");
        mediumWords.add("Luffytaro");
        mediumWords.add("GreenGiant");
        mediumWords.add("Vibecoding");
        mediumWords.add("Shanks");
        mediumWords.add("Blackbeard");
        mediumWords.add("Kaido");
        mediumWords.add("BigMom");
        mediumWords.add("Doflamingo");
        mediumWords.add("Trafalgar");
        mediumWords.add("Rayleigh");
        mediumWords.add("Hancock");
        mediumWords.add("Crocodile");
        mediumWords.add("Enel");

        hardWords.add("MissAllSunday");
        hardWords.add("GodValleyIncident");
        hardWords.add("ImperioTenryubito");
        hardWords.add("FruitoDiabloAkuma");
        hardWords.add("PunkHazardIsland");
        hardWords.add("MarineFordWar");
        hardWords.add("RogerPiratesCrew");
        hardWords.add("NikaGomuGomu");
        hardWords.add("WarlordSystem");
        hardWords.add("RevolutionaryArmy");
        hardWords.add("YonkoAlliance");
        hardWords.add("ImsamaWorld");
        hardWords.add("TwentyKingdoms");
        hardWords.add("VoidCentury");
        hardWords.add("JoyBoyLegacy");
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