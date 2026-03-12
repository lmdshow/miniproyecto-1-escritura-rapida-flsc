package com.example.miniproyecto1.model.words;

import java.util.List;
import java.util.ArrayList;

public class Words implements IWords {

    private final List<String> easyWords;
    private final List<String> mediumWords;
    private final List<String> hardWords;
    private final List<String> usedWords;
    private List<String> lastSelectedList;

    public Words(){
        easyWords = new ArrayList<>();
        mediumWords = new ArrayList<>();
        hardWords = new ArrayList<>();
        usedWords = new ArrayList<>();
        loadWords();
    }

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

    private String currentWord;

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

    @Override
    public Boolean validateWord(String words) {
        return words.equals(currentWord);
    }
}
