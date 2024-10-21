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
import java.util.ArrayList;

/**
 *
 * @author franc
 */
//The purpose of this class is to extract a collection of phrases/Fun-facts from text files to then insert them the database if they aren't already in the DB
//This allows for the developer to coninously add more phrases and facts while only having to add them to the txt file.
//This class is used for updates.
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

    private boolean factExists(String fact, String columnName) {
        String checkQuery = "SELECT COUNT(*) FROM FUNFACTS WHERE " + columnName + " = '" + fact + "'";
        try ( ResultSet rs = dbManager.getFromDB(checkQuery)) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private void addPhrase(String phrase, String engTranslation, String tableName) {
        String insertQuery = "INSERT INTO " + tableName + " (phrase, EnglishTranslation) "
                + "VALUES ('" + phrase + "', '" + engTranslation + "')";
        dbManager.updateDB(insertQuery);
    }

    private void addFact(String fact1, String fact2) {

        String fact1Value = (fact1 != null && !fact1.isEmpty()) ? "'" + fact1 + "'" : "NULL";
        String fact2Value = (fact2 != null && !fact2.isEmpty()) ? "'" + fact2 + "'" : "NULL";

        if (!factExists(fact1, "SOUTHAFRICANFUNFACT") && !factExists(fact2, "PHILIPPINESFUNFACT")) {
            String insertQuery = "INSERT INTO FUNFACTS (SOUTHAFRICANFUNFACT, PHILIPPINESFUNFACT) "
                    + "VALUES ('" + fact1 + "', '" + fact2 + "')";
            dbManager.updateDB(insertQuery);
        }
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

    public void clearFunFacts() {
        dbManager.updateDB("TRUNCATE TABLE FUNFACTS");
    }

    public void clearPhrases(String tableName) {
        dbManager.updateDB("TRUNCATE TABLE " + tableName);
    }

    public void readAndInsertFacts(String pathAfr, String pathPhil) {

        clearFunFacts();
        ArrayList<String> afrikaansFacts = new ArrayList<>();
        ArrayList<String> filipinoFacts = new ArrayList<>();

        try ( BufferedReader readerAfr = new BufferedReader(new FileReader(pathAfr))) {
            String fact;
            while ((fact = readerAfr.readLine()) != null) {
                afrikaansFacts.add(fact);
            }
        } catch (IOException e) {
            System.out.println("Error reading Afrikaans facts: " + e.getMessage());
        }

        try ( BufferedReader readerPhil = new BufferedReader(new FileReader(pathPhil))) {
            String fact;
            while ((fact = readerPhil.readLine()) != null) {
                filipinoFacts.add(fact);
            }
        } catch (IOException e) {
            System.out.println("Error reading Filipino facts: " + e.getMessage());
        }
        int minSize = Math.min(afrikaansFacts.size(), filipinoFacts.size());
        for (int i = 0; i < minSize; i++) {
            String afrFact = afrikaansFacts.get(i);
            String philFact = filipinoFacts.get(i);
            addFact(afrFact, philFact);
        }
    }

}
