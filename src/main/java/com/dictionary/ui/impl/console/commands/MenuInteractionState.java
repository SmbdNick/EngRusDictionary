package com.dictionary.ui.impl.console.commands;

public enum MenuInteractionState {
    FIRST_OPTION("first", "1"),
    SECOND_OPTION("second", "2"),
    THIRD_OPTION("third", "3"),
    FOURTH_OPTION("fourth", "4"),
    FIFTH_OPTION("fifth", "5"),
    ;

    private String first;
    private String second;

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }

    MenuInteractionState(String first, String second) {
        this.first = first;
        this.second = second;
    }
}
