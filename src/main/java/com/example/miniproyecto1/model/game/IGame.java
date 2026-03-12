package com.example.miniproyecto1.model.game;

public interface IGame {
    public void startGame();
    public void nextLevel();
    public void restart();
    int getCurrentLevel();
    int getTimeForLevel();
    boolean validateWord(String word);
    String generateWord();
    int getMaxLevel();
    boolean isMaxLevel();
}
