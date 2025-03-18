package com.dictionary.ui.impl.console.commands.impl;

import com.dictionary.ui.impl.console.UIState;
import com.dictionary.ui.impl.console.commands.api.Command;

public class CommandFactory {
    public Command createCommand(UIState uiState) {
        switch (uiState) {
            case MAIN_MENU:
                return new MainMenuCommand();
            case DICTIONARY_MENU:
                return new DictionaryMenuCommand();
            case CREATION_MENU:
                return new CreationMenuCommand();
            case DICTIONARY_EDIT_MENU:
                return new DictionaryEditMenuCommand();
            default:
                throw new RuntimeException();//TODO кастомный эксепшион, обработка
        }
    }

}
