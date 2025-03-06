package com.dictionary.ui.impl.console.commands.factory;

import com.dictionary.ui.impl.console.commands.api.Command;
import com.dictionary.ui.impl.console.commands.api.CommandFactory;
import com.dictionary.ui.impl.console.commands.impl.CreationMenuCommand;

public class CreationMenuFactory extends CommandFactory {
    @Override
    public Command createCommand() {
        return new CreationMenuCommand();
    }
}
