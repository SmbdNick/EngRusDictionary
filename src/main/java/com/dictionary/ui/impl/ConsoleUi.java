package com.dictionary.ui.impl;

import com.dictionary.dao.impl.InMemoryDictionary;
import com.dictionary.model.Word;
import com.dictionary.service.DictionaryService;
import com.dictionary.service.exception.ValidationException;
import com.dictionary.service.validator.impl.EngValidator;
import com.dictionary.service.validator.impl.RusValidator;
import com.dictionary.service.validator.impl.JapValidator;
import com.dictionary.ui.api.Ui;

import java.io.Console;
import java.util.*;

public class ConsoleUi implements Ui {
    enum UIState{
        MAIN_MENU,
        CREATION_MENU,
        DICTIONARY_MENU,
        DICTIONARY_EDIT_MENU
    }
    private Map<String,DictionaryService> dictionaryMap = new HashMap();
    private UIState uIstate;
    private Integer currentDictionaryID;
    private String currentDictionaryKey;
//    private final DictionaryService dictionaryService; // TODO не совсем правильно
    private final Scanner scanner = new Scanner(System.in);
    private final Console console = System.console();

//    public ConsoleUi(DictionaryService dictionaryService) {
//        this.dictionaryService = dictionaryService;
//    }

    @Override
    public void showMainMenu() {
        uIstate = UIState.MAIN_MENU;
        say("Welcome to Dictionary Creator 3000\n" +
                "Please input your command\n" +
                "1. Create Dictionary\n" +
                "2. Existing Dictionary Menu\n");

        doCommand((askCommand("->")));
    }

    @Override
    public void showCreationMenu() {
        uIstate = UIState.CREATION_MENU;
        say("Please enter your desired type of Dictionary\n" +
                "1. Russian - English\n" +
                "2. Russian - Japanese\n" +
                "3.Back to Main Menu");

        doCommand((askCommand("->")));
    }

    @Override
    public void showDictionaryMenu() {
        uIstate = UIState.DICTIONARY_MENU;
        int i = 1;

        say("Your created dictionaries:");
        for (String key : dictionaryMap.keySet()){
            say(i + key + " Dictionary");
            i++;
        }
        say(i + " Go back");
        say("Select a dictionary to work with");
        doCommand((askCommand("->")));
    }

    @Override
    public void showDictionaryEditMenu() {
        uIstate = UIState.DICTIONARY_EDIT_MENU;
        say(currentDictionaryKey + " dictionary selected\n" +
                "Select what you want to do\n" +
                "1. Show entry by key\n" +
                "2. Show all entries\n" +
                "3. Add entry to dictionary\n" +
                "4. Remove entry from dictionary");
    }

    @Override
    public void showWord(String key) {
        say(key + " " + dictionaryMap.get(currentDictionaryKey).getWordByKey(key));
    }

    @Override
    public void showWordList(List<String> keyList) {
         List<Word> wordList = new ArrayList<>(dictionaryMap.get(currentDictionaryKey).getAllWordsByKeyList(keyList));
         say(wordList.toString());

    }

    @Override
    public void addWord() {

    }

    @Override
    public void deleteWord() {

    }

    private void say(String message) {
        System.out.println(message);
    }

    private Integer askCommand(String outputMessage) {
        say(outputMessage);
        String command = null;
        try {
            if (console == null) {
                command = scanner.nextLine();
            } else {
                command = console.readLine();
                
            }
        } catch (Exception e) {
        }
        return Integer.parseInt(command);
    }

    private void doCommand(Integer command){
        if(uIstate == UIState.MAIN_MENU){
            switch (command){
                case 1: showCreationMenu();
                    break;

                case 2: showDictionaryMenu();
                    break;

                default: say("Unknown command, please reenter your command");
                    showMainMenu();
                    break;
            }
        }

        if (uIstate == UIState.CREATION_MENU){
            switch (command){
                case 1:
                    DictionaryService rusEngDictionary = new DictionaryService(new InMemoryDictionary(), new RusValidator(), new EngValidator());
                    if (!(dictionaryMap.containsKey("Rus-Eng"))){
                        dictionaryMap.put("Rus-Eng", rusEngDictionary);
                        showCreationMenu();
                    } else {
                        say("This dictionary already exists, please choose different option");
                        showCreationMenu();}

                    break;
                case 2:
                    DictionaryService rusJapDictionary = new DictionaryService(new InMemoryDictionary(), new RusValidator(), new JapValidator());

                    if (!(dictionaryMap.containsKey("Rus-Jap"))){
                        dictionaryMap.put("Rus-Jap",rusJapDictionary);
                        showCreationMenu();
                    } else {
                        say("This dictionary already exists, please choose different option");
                        showCreationMenu();
                    }

                    break;

                case 3: showMainMenu();

                default: say("Unknown command, please reenter your command");
                    showCreationMenu();
                    break;
            }
        }

        if (uIstate == UIState.DICTIONARY_MENU) {
            Set<String> set = dictionaryMap.keySet();
            List<String> list = new ArrayList<>(set);

            if (list == null || list.isEmpty()) {
                say("Your dictionary map is empty, backing to main menu");
                showMainMenu();
            } else {
                    if (command-1 == list.size())
                        showMainMenu();
                    if (command >= list.size()){
                        say("Unknown command, please reenter your command");
                        showDictionaryMenu();
                    }

                    else {currentDictionaryKey = list.get(command-1);

                showDictionaryEditMenu();
                    }
            }
        }
    }
}
