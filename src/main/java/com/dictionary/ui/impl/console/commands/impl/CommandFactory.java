package com.dictionary.ui.impl.console.commands.impl;

import com.dictionary.service.exception.MenuException;
import com.dictionary.ui.impl.console.UIState;
import com.dictionary.ui.impl.console.commands.ConsoleInteractions;
import com.dictionary.ui.impl.console.commands.api.Command;

public class CommandFactory {
    ConsoleInteractions consoleInteractions;

    public Command createCommand(UIState uiState) {

        switch (uiState) {
            case MAIN_MENU:
                return new MainMenuCommand();
            case WORDS:
                return new WordsCommand();
            case DICTIONARIES:
                return new DictionariesCommand();
            case TRANSLATIONS:
                return new TranslationsCommand();

            default:
                try {
                    return new MainMenuCommand();
                } catch (MenuException e) {
                    consoleInteractions.say("Oh-oh! You poopy-head, you ran into exception!\nThat's okay! ~Backing to Main Menu~");
                    return new MainMenuCommand();
                }
        }
    }
}
