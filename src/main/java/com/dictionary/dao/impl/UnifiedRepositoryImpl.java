package com.dictionary.dao.impl;

import com.dictionary.dao.api.UnifiedRepository;
import com.dictionary.service.exception.DbSqlException;
import com.dictionary.ui.impl.console.commands.DbConnectionData;
import org.postgresql.core.ResultHandler;

import java.sql.*;

public class UnifiedRepositoryImpl implements UnifiedRepository {
    @Override
    public void insertInto(String table, String columns, String[] values) {
        String sql = "";
        sql = sql.concat("INSERT INTO public.")
                .concat(table)
                .concat("(" + columns + ") ")
                .concat("VALUES(")
                .concat(sqlParameterBuilder(values) + ")"); //Несколько параметров, используем sqlParameterBuilder

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            for (int i = 0; i < values.length; i++) {
                statement.setString(i + 1, values[i]);
            }

            statement.executeUpdate();
        } catch (SQLException e) {
            DbSqlException dbSqlException = new DbSqlException();
            dbSqlException.printErrorMessage(e);
        }
    }

    public void insertInto(String table, String columns, String values) {
        String sql = "";
        sql = sql.concat("INSERT INTO public.")
                .concat(table)
                .concat("(" + columns + ") ")
                .concat("VALUES(?)");


        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, values);
            statement.executeUpdate();
        } catch (SQLException e) {
            DbSqlException dbSqlException = new DbSqlException();
            dbSqlException.printErrorMessage(e);
        }
    }

    @Override
    public void deleteFrom(String table, String column, String value) {
        String sql = "";
        sql = sql.concat("DELETE FROM public.")
                .concat(table)
                .concat(" WHERE ")
                .concat(column + "=?");

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, value);
            statement.executeUpdate();
        } catch (SQLException e) {
            DbSqlException dbSqlException = new DbSqlException();
            dbSqlException.printErrorMessage(e);
        }
    }

    @Override
    public void selectColumnsWhere(String columns, String table, String column, String value) {
        String sql = "";
        sql = sql.concat("SELECT ")
                .concat(columns)
                .concat(" FROM public.")
                .concat(table)
                .concat(" WHERE ")
                .concat(column + " = ?");

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, value);

            ResultSet resultSet = statement.executeQuery();
            consoleResultSetOutput(resultSet);
        } catch (SQLException e) {
            DbSqlException dbSqlException = new DbSqlException();
            dbSqlException.printErrorMessage(e);
        }
    }

    @Override
    public void selectAll(String table) {
        String sql = "SELECT * FROM " + table;
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();
            consoleResultSetOutput(resultSet);

        } catch (SQLException e) {
            DbSqlException dbSqlException = new DbSqlException();
            dbSqlException.printErrorMessage(e);
        }
    }

    public void insertNewTranslation(String word, String translation, String dictionary) {
        String sql = """
                INSERT INTO public.dictionary_values (word_id, translation_id, dictionary_id)
                SELECT (SELECT id
                FROM public.words
                WHERE word = ?),
                (SELECT id
                FROM public.words
                WHERE word = ?),
                (SELECT id
                FROM public.dictionaries
                WHERE dictionary = ?)""";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, word);
            statement.setString(2, translation);
            statement.setString(3, dictionary);
            statement.executeUpdate();
        } catch (SQLException e) {
            DbSqlException dbSqlException = new DbSqlException();
            dbSqlException.printErrorMessage(e);
        }
    }

    public void deleteTranslation(String word, String translation, String dictionary) {
        String sql = """
                DELETE FROM public.dictionary_values
                WHERE word_id=(
                SELECT id
                FROM public.words
                WHERE word = ?
                ) AND translation_id=(
                SELECT id
                FROM public.words
                WHERE word = ?
                ) AND dictionary_id=(
                SELECT id
                FROM public.dictionaries
                WHERE dictionary = ?
                )""";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, word);
            statement.setString(2, translation);
            statement.setString(3, dictionary);
            statement.executeUpdate();
        } catch (SQLException e) {
            DbSqlException dbSqlException = new DbSqlException();
            dbSqlException.printErrorMessage(e);
        }
    }

    private void consoleResultSetOutput(ResultSet resultSet) {
        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();


            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnValue = resultSet.getString(i);
                    System.out.print(resultSetMetaData.getColumnName(i) + "-" + columnValue + " , ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            DbSqlException dbSqlException = new DbSqlException();
            dbSqlException.printErrorMessage(e);
        }

    }

    private String sqlParameterBuilder(String[] input) {
        StringBuilder outputParameter = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            outputParameter.append("?");
            if (i < input.length - 1) {
                outputParameter.append(", ");
            }
        }
        return outputParameter.toString();
    }

    private Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DbConnectionData.DB_URL.getData(),
                    DbConnectionData.USERNAME.getData(),
                    DbConnectionData.PASSWORD.getData());
        } catch (SQLException e) {
            DbSqlException dbSqlException = new DbSqlException();
            dbSqlException.printErrorMessage(e);
        }
        return connection;
    }
}
