package com.dictionary.commands.factory;

import com.dictionary.commands.api.Command;
import com.dictionary.commands.api.CommandFactory;
import com.dictionary.commands.impl.DictionaryEditMenuCommand;

public class DictionaryEditFactory extends CommandFactory {
    @Override
    public Command createCommand() {
        return new DictionaryEditMenuCommand();
    }
}
