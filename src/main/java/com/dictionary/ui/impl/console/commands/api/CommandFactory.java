package com.dictionary.ui.impl.console.commands.api;

import com.dictionary.ui.impl.console.UIState;

import java.io.Console;
import java.util.Scanner;

public abstract class CommandFactory {
    public void construct(Console console, Scanner scanner){
        Command commandItem = createCommand();
        commandItem.execute(console, scanner);
    }

    public abstract Command createCommand();
}
