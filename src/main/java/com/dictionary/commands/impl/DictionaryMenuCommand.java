package com.dictionary.commands.impl;

import com.dictionary.commands.api.Command;
import com.dictionary.service.DictionaryService;
import com.dictionary.ui.api.UIState;

import java.io.Console;
import java.util.*;

import static com.dictionary.commands.Ask.ask;
import static com.dictionary.commands.Say.say;
import static com.dictionary.ui.impl.ConsoleUi.*;

public class DictionaryMenuCommand implements Command {
    @Override
    public void doCommand(UIState uiState, Console console, Scanner scanner) {
        uiState = UIState.DICTIONARY_MENU;
        Map<String, DictionaryService> dictionaryServiceMap = getDictionaryMap();
        String currentDictionaryKey = getCurrentDictionaryKey();
        int i = 1;

        say("Your created dictionaries:");
        for (String key : dictionaryServiceMap.keySet()) {
            say(i + key + " Dictionary");
            i++;
        }
        say(i + " Go back");
        say("Select a dictionary to work with");

        int intCommand = Integer.parseInt((ask(console, scanner)));

        Set<String> set = dictionaryServiceMap.keySet();
        List<String> list = new ArrayList<>(set);

        if (list == null || list.isEmpty()) {
            say("Your dictionary map is empty, backing to main menu");
            uiState = UIState.MAIN_MENU;
            setuIstate(uiState);
        } else {
            if (intCommand - 1 == list.size()) {
                uiState = UIState.MAIN_MENU;
                setuIstate(uiState);
            }

            if (intCommand - 1 > list.size()) {
                say("Unknown command, please reenter your command");
                uiState = UIState.DICTIONARY_MENU;
                setuIstate(uiState);
            } else {
                currentDictionaryKey = list.get(intCommand - 1);
                setCurrentDictionaryKey(currentDictionaryKey);

                uiState = UIState.DICTIONARY_EDIT_MENU;
                setuIstate(uiState);

            }
        }
    }
}
