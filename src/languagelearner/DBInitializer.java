/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package languagelearner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author franc
 */

//The purpose of this class is to extract a collection of phrases/Fun-facts from text files to then insert them the databse if they aren't already in the DB
//This allows future expansion for other languages.
//This class will be deleted and used in future updates after database has been initialized.
public class DBInitializer {

    DBManager dbManager = new DBManager();

    private boolean tableExists(String table) {
        boolean exists = false;
        try {
            String checkQuery = "SELECT 1 FROM SYS.SYSTABLES WHERE TABLENAME = '" + table.toUpperCase() + "'";
            ResultSet rs = dbManager.getFromDB(checkQuery);
            exists = rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return exists;
    }

    public void createTable(String tableName) {
        String createTable = "CREATE TABLE " + tableName + " ("
                + "ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "
                + "phrase VARCHAR(255), "
                + "EnglishTranslation VARCHAR(255))";

        try {
            if (!tableExists(tableName)) {
                dbManager.updateDB(createTable);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private boolean phraseExists(String phrase, String tableName) {
        boolean exists = false;
        String checkQuery = "SELECT 1 FROM " + tableName + " WHERE phrase = '" + phrase + "'";
        try {
            ResultSet rs = dbManager.getFromDB(checkQuery);
            exists = rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return exists;
    }

    private void addPhrase(String phrase, String engTranslation, String tableName) {
        String insertQuery = "INSERT INTO " + tableName + " (phrase, EnglishTranslation) "
                + "VALUES ('" + phrase + "', '" + engTranslation + "')";
        dbManager.updateDB(insertQuery);
    }
    private void addFact(String fact, String columnName){
        String insertQuery = "INSERT INTO FUNFACTS (" + columnName + ") "
                + "VALUES ('" + fact + "')";
        dbManager.updateDB(insertQuery);
    }

    public void readAndInsertPhrases(String path, String tableName) {
        try ( BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String phrase;
            while ((phrase = reader.readLine()) != null) {
                String engTranslation = reader.readLine();

                if (engTranslation != null) {
                    if (!phraseExists(phrase, tableName)) {
                        addPhrase(phrase, engTranslation, tableName);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void readAndInsertFacts(String path, String column){
        try ( BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String fact;
            while ((fact = reader.readLine()) != null){
                addFact(fact, column);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
