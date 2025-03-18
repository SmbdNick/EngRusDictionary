package com.dictionary.ui.impl.console.commands.api;

import com.dictionary.ui.impl.console.commands.ConsoleInteractions;

import java.io.Console;
import java.util.Scanner;

public interface Command {
    ConsoleInteractions consoleInteractions = new ConsoleInteractions();

    void execute(Console console, Scanner scanner);
}
