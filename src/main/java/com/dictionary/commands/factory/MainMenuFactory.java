package com.dictionary.commands.factory;

import com.dictionary.commands.api.Command;
import com.dictionary.commands.api.CommandFactory;
import com.dictionary.commands.impl.MainMenuCommand;

public class MainMenuFactory extends CommandFactory {
    @Override
    public Command createCommand() {
        return new MainMenuCommand();
    }
}
