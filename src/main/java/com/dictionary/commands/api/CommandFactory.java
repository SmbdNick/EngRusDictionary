package com.dictionary.commands.api;

import com.dictionary.commands.api.Command;
import com.dictionary.ui.api.UIState;

import java.io.Console;
import java.util.Scanner;

public abstract class CommandFactory {
    public void command(UIState uiState, Console console, Scanner scanner){
        Command commandItem = createCommand();
        commandItem.doCommand(uiState, console, scanner);
    }

    public abstract Command createCommand();
}
