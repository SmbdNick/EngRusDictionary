package com.dictionary;

import com.dictionary.ui.impl.console.ConsoleUi;

import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
        ConsoleUi consoleUi = new ConsoleUi();
        consoleUi.start();
    }
}
