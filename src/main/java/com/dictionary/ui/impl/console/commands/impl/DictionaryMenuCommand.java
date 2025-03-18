package com.dictionary.ui.impl.console.commands.impl;

import com.dictionary.service.DictionaryService;
import com.dictionary.ui.impl.console.UIState;
import com.dictionary.ui.impl.console.commands.api.Command;

import java.io.Console;
import java.util.*;

import static com.dictionary.ui.impl.console.ConsoleUIRunner.setUIState;
import static com.dictionary.ui.impl.console.ConsoleUi.getDictionaryMap;
import static com.dictionary.ui.impl.console.ConsoleUi.setCurrentDictionaryKey;

public class DictionaryMenuCommand implements Command {
    Map<String, DictionaryService> dictionaryServiceMap = getDictionaryMap();
    String currentDictionaryKey;

    @Override
    public void execute(Console console, Scanner scanner) {
        setUIState(UIState.DICTIONARY_MENU);
        showCreatedDictionaryMenu();
        dictionaryMenuInteractions(consoleInteractions.ask(console, scanner));
    }

    private void showCreatedDictionaryMenu() {
        int i = 1;
        consoleInteractions.say("Your created dictionaries:");
        for (String key : dictionaryServiceMap.keySet()) {
            consoleInteractions.say(i + key + " Dictionary");
            i++;
        }
        consoleInteractions.say(i + " Go back");
        consoleInteractions.say("Select a dictionary to work with");
    }

    private void dictionaryMenuInteractions(String command) {
        int intCommand = 0;
        try {
            intCommand = Integer.parseInt((command));
        } catch (NumberFormatException e) {
            consoleInteractions.say("Exception caught! Input numbers to use menu!\n" +
                    "Backing to Main Menu");
            setUIState(UIState.MAIN_MENU);
        }

        Set<String> set = dictionaryServiceMap.keySet();
        List<String> list = new ArrayList<>(set);

        if (!isDictionaryMapEmpty(list) & !isCommandOutOfBorder(intCommand, list)) {
            menuSelection(intCommand, list);
        }
    }

    private boolean isDictionaryMapEmpty(List list) {
        if (list == null || list.isEmpty()) {
            consoleInteractions.say("Your dictionary map is empty, backing to main menu");
            setUIState(UIState.MAIN_MENU);
            return true;
        }
        return false;
    }

    private void menuSelection(int intCommand, List list) {
//        Selects Main Menu, otherwise corresponding Dictionary
        if (intCommand - 1 == list.size()) {
            setUIState(UIState.MAIN_MENU);
        } else {
            selectRedactDictionary(intCommand, list);
        }
    }

    private boolean isCommandOutOfBorder(int intCommand, List list) {
        if (intCommand - 1 > list.size()) {
            consoleInteractions.say("Unknown command, please reenter your command");
            setUIState(UIState.DICTIONARY_MENU);
            return true;
        }
        return false;
    }

    private void selectRedactDictionary(int intCommand, List list) {
        currentDictionaryKey = list.get(intCommand - 1).toString();
        setCurrentDictionaryKey(currentDictionaryKey);
        setUIState(UIState.DICTIONARY_EDIT_MENU);
    }
}
