package com.dictionary.service.exception;

import java.sql.SQLException;

public class DbSqlException extends SQLException {
    public void printErrorMessage(SQLException e) {
        System.err.println("An SQL error happened:");
        System.err.println("SQL State: " + e.getSQLState());
        System.err.println("Error Message: " + e.getMessage());
    }
}
