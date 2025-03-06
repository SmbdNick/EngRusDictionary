package com.dictionary.ui.impl.console.commands.factory;

import com.dictionary.ui.impl.console.commands.api.Command;
import com.dictionary.ui.impl.console.commands.api.CommandFactory;
import com.dictionary.ui.impl.console.commands.impl.DictionaryEditMenuCommand;

public class DictionaryEditFactory extends CommandFactory {
    @Override
    public Command createCommand() {
        return new DictionaryEditMenuCommand();
    }
}
