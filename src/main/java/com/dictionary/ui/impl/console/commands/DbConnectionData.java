package com.dictionary.ui.impl.console.commands;

public enum DbConnectionData {
    DB_URL("jdbc:postgresql://localhost:1808/Dictionary_DB"),
    USERNAME("postgres"),
    PASSWORD("123456")
    ;

    private final String data;

    public String getData(){
        return data;
    }

    DbConnectionData(String data){
        this.data = data;
    }
}
