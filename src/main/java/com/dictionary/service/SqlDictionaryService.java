package com.dictionary.service;

import com.dictionary.dao.impl.UnifiedRepositoryImpl;
import com.dictionary.service.validator.SupportedLanguages;
import com.dictionary.service.validator.api.Validator;
import com.dictionary.service.validator.impl.EngValidator;
import com.dictionary.service.validator.impl.RusValidator;

import static com.dictionary.ui.impl.console.commands.api.Command.consoleInteractions;


public class SqlDictionaryService {
    private Validator validator;
    private final UnifiedRepositoryImpl unifiedRepositoryImpl = new UnifiedRepositoryImpl();

    public void insertIntoTable(String table, String columns, String[] values) {
        unifiedRepositoryImpl.insertInto(table, columns, values);
    }

    public void insertIntoTable(String table, String columns, String values) {
        unifiedRepositoryImpl.insertInto(table, columns, values);
    }

    public void deleteFromWhere(String table, String column, String value) {
        unifiedRepositoryImpl.deleteFrom(table, column, value);
    }

    public void selectColumnsFromWhere(String columns, String table, String column, String value) {
        unifiedRepositoryImpl.selectColumnsWhere(columns, table, column, value);
    }

    public void selectAllFrom(String table) {
        unifiedRepositoryImpl.selectAll(table);
    }

    public void insertNewTranslation(String word, String translation, String dictionary) {
        unifiedRepositoryImpl.insertNewTranslation(word, translation, dictionary);
    }

    public void deleteTranslation(String word, String translation, String dictionary) {
        unifiedRepositoryImpl.deleteTranslation(word, translation, dictionary);
    }

    public void validate(String language, String word) {
        String languageUpper = language.toUpperCase();
        SupportedLanguages supportedLanguages = SupportedLanguages.valueOf(languageUpper);

        switch (supportedLanguages) {
            case ENGLISH:
                validator = new EngValidator();
                validator.validate(word);
                break;
            case RUSSIAN:
                validator = new RusValidator();
                validator.validate(word);
                break;
            default:
                consoleInteractions.say("Not supported or not valuable language! Try again!");
        }
    }
}
