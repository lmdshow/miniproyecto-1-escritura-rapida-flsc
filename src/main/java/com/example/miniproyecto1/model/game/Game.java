package com.example.miniproyecto1.model.game;

import com.example.miniproyecto1.model.words.Words;

public class Game implements IGame{

    private static final int INITIAL_TIME = 20;
    private static final int MIN_TIME = 2;
    private static final int TIME_REDUCTION = 2;
    private static final int MAX_LEVEL = 45;


    private int currentLevel;
    private Words words;

    public Game(){
        currentLevel = 1;
        words = new Words();
    }

    @Override
    public void startGame() {
        currentLevel = 1;
        words.generateWord(currentLevel);
    }

    @Override
    public void nextLevel() {
        currentLevel++;
    }

    @Override
    public void restart() {
    currentLevel = 1;
    }

    @Override
    public int getCurrentLevel() {
        return currentLevel;
    }

    @Override
    public int getTimeForLevel() {
        int reduction = ((currentLevel - 1) / 5) * TIME_REDUCTION;
        return Math.max(INITIAL_TIME - reduction, MIN_TIME);
    }

    @Override
    public boolean validateWord(String word) {
        return words.validateWord(word);
    }

    @Override
    public String generateWord() {
        return words.generateWord(currentLevel);
    }

    @Override
    public int getMaxLevel() {
        return MAX_LEVEL;
    }

    @Override
    public boolean isMaxLevel() {
        return (currentLevel >= MAX_LEVEL);
    }
}
