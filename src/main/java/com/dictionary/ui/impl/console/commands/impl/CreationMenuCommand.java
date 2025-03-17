package com.dictionary.ui.impl.console.commands.impl;

import com.dictionary.dao.impl.InMemoryDictionary;
import com.dictionary.service.DictionaryService;
import com.dictionary.service.validator.impl.EngValidator;
import com.dictionary.service.validator.impl.RusValidator;
import com.dictionary.ui.impl.console.UIState;
import com.dictionary.ui.impl.console.commands.MenuInteractionState;
import com.dictionary.ui.impl.console.commands.api.Command;

import java.io.Console;
import java.util.Map;
import java.util.Scanner;

import static com.dictionary.ui.impl.console.ConsoleUIRunner.setUIState;
import static com.dictionary.ui.impl.console.ConsoleUi.getDictionaryMap;
import static com.dictionary.ui.impl.console.ConsoleUi.setDictionaryMap;

public class CreationMenuCommand implements Command {
    MenuInteractionState menuInteractionState;
    Map<String, DictionaryService> dictionaryServiceMap = getDictionaryMap();

    @Override
    public void execute(Console console, Scanner scanner) {
        setUIState(UIState.CREATION_MENU);

        consoleInteractions.say("Please enter your desired type of Dictionary\n" +
                "1. Russian - English\n" +
                "2. English - Russian\n" +
                "3.Back to Main Menu");

        String com = consoleInteractions.ask(console, scanner);
        for (MenuInteractionState menuInteractionState : MenuInteractionState.values()) {
            if (menuInteractionState.getFirst().equals(com) || menuInteractionState.getSecond().equals(com)) {
                this.menuInteractionState = menuInteractionState;
                break;
            }
        }

        switch (menuInteractionState) {
            case FIRST_OPTION:
                createRusEngDictionary();
                break;

            case SECOND_OPTION:
                createEngRusDictionary();
                break;

            case THIRD_OPTION:
                setUIState(UIState.MAIN_MENU);
                break;

            default:
                consoleInteractions.say("Unknown command, please reenter your command");
                break;
        }

    }

    private void createRusEngDictionary() {
        DictionaryService rusEngDictionary = new DictionaryService(new InMemoryDictionary(), new RusValidator(), new EngValidator());
        if (!(dictionaryServiceMap.containsKey("Rus-Eng"))) {
            dictionaryServiceMap.put("Rus-Eng", rusEngDictionary);
            setDictionaryMap(dictionaryServiceMap);
        } else {
            consoleInteractions.say("This dictionary already exists, please choose different option");
        }
    }

    private void createEngRusDictionary() {
        DictionaryService engRusDictionary = new DictionaryService(new InMemoryDictionary(), new EngValidator(), new RusValidator());

        if (!(dictionaryServiceMap.containsKey("Eng-Rus"))) {
            dictionaryServiceMap.put("Eng-Rus", engRusDictionary);
            setDictionaryMap(dictionaryServiceMap);
        } else {
            consoleInteractions.say("This dictionary already exists, please choose different option");
        }
    }

}
