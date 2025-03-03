package com.dictionary.commands.factory;

import com.dictionary.commands.api.Command;
import com.dictionary.commands.api.CommandFactory;
import com.dictionary.commands.impl.CreationMenuCommand;

public class CreationMenuFactory extends CommandFactory {
    @Override
    public Command createCommand() {
        return new CreationMenuCommand();
    }
}
