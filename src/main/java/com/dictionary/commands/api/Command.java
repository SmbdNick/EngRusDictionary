package com.dictionary.commands.api;

import com.dictionary.service.DictionaryService;
import com.dictionary.ui.api.UIState;

import java.io.Console;
import java.util.Map;
import java.util.Scanner;

public interface Command {
    public void doCommand(UIState uiState, Console console, Scanner scanner);
}
