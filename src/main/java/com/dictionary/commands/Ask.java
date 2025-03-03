package com.dictionary.commands;

import java.io.Console;
import java.util.Scanner;

import static com.dictionary.commands.Say.say;

public class Ask {
    public static String ask(String outputMessage,Console console, Scanner scanner){
        say(outputMessage);
        String command = null;
        try {
            if (console == null) {
                command = scanner.nextLine();
            } else {
                command = console.readLine();

            }
        } catch (Exception e) {
        }
        return command;
    }

    public static String ask(Console console, Scanner scanner){
        return ask("->", console, scanner);
    }
}
