package com.dictionary.service;

import com.dictionary.dao.impl.DbSqlDictionary;
import com.dictionary.service.validator.SupportedLanguages;
import com.dictionary.service.validator.api.Validator;
import com.dictionary.service.validator.impl.EngValidator;
import com.dictionary.service.validator.impl.RusValidator;

import static com.dictionary.ui.impl.console.commands.api.Command.consoleInteractions;


public class SqlDictionaryService {
    private Validator validator;
    private final DbSqlDictionary dbSqlDictionary = new DbSqlDictionary();

    public void insertIntoTable(String table, String columns, String[] values) {
        dbSqlDictionary.insertInto(table, columns, values);
    }

    public void insertIntoTable(String table, String columns, String values) {
        dbSqlDictionary.insertInto(table, columns, values);
    }

    public void deleteFromWhere(String table, String column, String value) {
        dbSqlDictionary.deleteFrom(table, column, value);
    }

    public void selectColumnsFromWhere(String columns, String table, String column, String value) {
        dbSqlDictionary.selectColumnsWhere(columns, table, column, value);
    }

    public void selectAllFrom(String table) {
        dbSqlDictionary.selectAll(table);
    }

    public void insertNewTranslation(String word, String translation, String dictionary) {
        dbSqlDictionary.insertNewTranslation(word, translation, dictionary);
    }

    public void deleteTranslation(String word, String translation, String dictionary) {
        dbSqlDictionary.deleteTranslation(word, translation, dictionary);
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
