package com.dictionary.commands.factory;

import com.dictionary.commands.api.Command;
import com.dictionary.commands.api.CommandFactory;
import com.dictionary.commands.impl.DictionaryMenuCommand;

public class DictionaryMenuFactory extends CommandFactory {
    @Override
    public Command createCommand() {
        return new DictionaryMenuCommand();
    }
}
