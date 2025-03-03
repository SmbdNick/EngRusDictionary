package com.dictionary.commands.impl;

import com.dictionary.commands.api.Command;
import com.dictionary.dao.impl.InMemoryDictionary;
import com.dictionary.service.DictionaryService;
import com.dictionary.service.validator.impl.EngValidator;
import com.dictionary.service.validator.impl.RusValidator;
import com.dictionary.ui.api.UIState;

import java.io.Console;
import java.util.Map;
import java.util.Scanner;

import static com.dictionary.commands.Ask.ask;
import static com.dictionary.commands.Say.say;
import static com.dictionary.ui.impl.ConsoleUi.*;

public class CreationMenuCommand implements Command {

    @Override
    public void doCommand(UIState uiState, Console console, Scanner scanner) {
        uiState = UIState.CREATION_MENU;
        Map<String, DictionaryService> dictionaryServiceMap = getDictionaryMap();
        say("Please enter your desired type of Dictionary\n" +
                "1. Russian - English\n" +
                "2. English - Russian\n" +
                "3.Back to Main Menu");

        switch (ask(console, scanner)) {
            case "1":
                DictionaryService rusEngDictionary = new DictionaryService(new InMemoryDictionary(), new RusValidator(), new EngValidator());
                if (!(dictionaryServiceMap.containsKey("Rus-Eng"))) {
                    dictionaryServiceMap.put("Rus-Eng", rusEngDictionary);
                    setDictionaryMap(dictionaryServiceMap);
                } else {
                    say("This dictionary already exists, please choose different option");
                }
                setUIState(uiState);
                break;


            case "2":
                DictionaryService engRusDictionary = new DictionaryService(new InMemoryDictionary(), new EngValidator(), new RusValidator());

                if (!(dictionaryServiceMap.containsKey("Eng-Rus"))) {
                    dictionaryServiceMap.put("Eng-Rus", engRusDictionary);
                    setDictionaryMap(dictionaryServiceMap);
                } else {
                    say("This dictionary already exists, please choose different option");
                }
                setUIState(uiState);
                break;

            case "3":
                uiState = UIState.MAIN_MENU;
                setUIState(uiState);
                break;

            default:
                say("Unknown command, please reenter your command");
                setUIState(uiState);
                break;
        }

    }

}
