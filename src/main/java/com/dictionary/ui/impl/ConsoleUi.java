package com.dictionary.ui.impl;

import com.dictionary.service.api.Dictionary;
import com.dictionary.ui.api.Ui;

import java.io.Console;
import java.util.Scanner;

public class ConsoleUi implements Ui {
    private final Dictionary dictionary; // TODO не совсем правильно
    private final Scanner scanner = new Scanner(System.in);
    private final Console console = System.console();

    public ConsoleUi(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public void showMenu() {

    }

    @Override
    public void showWord() {

    }

    @Override
    public void showWordList() {

    }

    @Override
    public void addWord() {

    }

    @Override
    public void deleteWord() {

    }

    private void say(String message) {
        System.out.println(message);
    }

    private void askCommand(String outputMessage) {
        say(outputMessage);
        try {
            String command;

            if (console == null) {
                command = scanner.nextLine();
            } else {
                command = console.readLine();
            }
        } catch (Exception e) {

        }
    }
}
