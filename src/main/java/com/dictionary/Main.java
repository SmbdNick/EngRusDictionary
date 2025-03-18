package com.dictionary;

import com.dictionary.ui.impl.console.ConsoleUi;

import java.io.File;
import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File("B://Study//EngRusDictionary//src//resources//"+"text.txt");
//        file.createNewFile();
        ConsoleUi consoleUi = new ConsoleUi();
        consoleUi.start();
    }
}
