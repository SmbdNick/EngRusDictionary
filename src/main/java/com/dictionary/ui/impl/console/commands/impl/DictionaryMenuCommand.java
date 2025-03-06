package com.dictionary.ui.impl.console.commands.impl;

import com.dictionary.service.DictionaryService;
import com.dictionary.ui.impl.console.UIState;
import com.dictionary.ui.impl.console.commands.api.Command;

import java.io.Console;
import java.util.*;

import static com.dictionary.ui.impl.console.ConsoleUIRunner.setUIState;
import static com.dictionary.ui.impl.console.ConsoleUi.*;

public class DictionaryMenuCommand implements Command {
    @Override
    public void execute(UIState uiState, Console console, Scanner scanner) {
        uiState = UIState.DICTIONARY_MENU;
        Map<String, DictionaryService> dictionaryServiceMap = getDictionaryMap();
        String currentDictionaryKey = getCurrentDictionaryKey();
        int i = 1;

        consoleInteractions.say("Your created dictionaries:");
        for (String key : dictionaryServiceMap.keySet()) {
            consoleInteractions.say(i + key + " Dictionary");
            i++;
        }
        consoleInteractions.say(i + " Go back");
        consoleInteractions.say("Select a dictionary to work with");

        int intCommand = Integer.parseInt((consoleInteractions.ask(console, scanner)));

        Set<String> set = dictionaryServiceMap.keySet();
        List<String> list = new ArrayList<>(set);

        if (list == null || list.isEmpty()) {
            consoleInteractions.say("Your dictionary map is empty, backing to main menu");
            uiState = UIState.MAIN_MENU;
            setUIState(uiState);
        } else {
            if (intCommand - 1 == list.size()) {
                uiState = UIState.MAIN_MENU;
                setUIState(uiState);
            }

            if (intCommand - 1 > list.size()) {
                consoleInteractions.say("Unknown command, please reenter your command");
                uiState = UIState.DICTIONARY_MENU;
                setUIState(uiState);
            } else {
                currentDictionaryKey = list.get(intCommand - 1);
                setCurrentDictionaryKey(currentDictionaryKey);

                uiState = UIState.DICTIONARY_EDIT_MENU;
                setUIState(uiState);

            }
        }
    }
}
