package com.dictionary.dao.api;

public interface UnifiedRepository {
    void insertInto(String table, String columns, String[] values);
    void deleteFrom(String table, String column, String value);
    void selectColumnsWhere(String columns, String table, String column, String value);
    void selectAll(String table);
}
