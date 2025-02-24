package com.dictionary.ui.api;

import java.util.List;

public interface Ui {
    // TODO не все комманды, а некоторые мб лишние
    void showMainMenu();
    void showCreationMenu();
    void showDictionaryMenu();
    void showDictionaryEditMenu();
    void showWord(String key);
    void showWordList(List<String> keyList);
    void showAllWords();
    void addWord(String entry);
    void deleteWord();
}
