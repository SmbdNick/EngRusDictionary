package com.dictionary;

import com.dictionary.service.DictionaryService;
import com.dictionary.ui.impl.ConsoleUi;

class Main{
    public static void main(String[] args){
        ConsoleUi consoleUi = new ConsoleUi();
        consoleUi.showMainMenu();
    }
}
