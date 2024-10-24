/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package languagelearner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author franc
 */
//The purpose of this class is to extract a collection of phrases/Fun-facts from text files to then insert them the database if they aren't already in the DB
//This allows for the developer to coninously add more phrases and facts while only having to add them to the txt file.
//This class is used for updates.
public class DBInitializer {

    DBManager dbManager = new DBManager();
    FileIO file = new FileIO();

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
        if (!factExists(fact1, "SOUTHAFRICANFUNFACT") && !factExists(fact2, "PHILIPPINESFUNFACT")) {
            String insertQuery = "INSERT INTO FUNFACTS (SOUTHAFRICANFUNFACT, PHILIPPINESFUNFACT) "
                    + "VALUES ('" + fact1 + "', '" + fact2 + "')";
            dbManager.updateDB(insertQuery);
        }
    }

    private void writePhrasesToDB(List<String[]> phrases, String tableName) {
        for (String[] pair : phrases) {
            String phrase = pair[0];
            String engTranslation = pair[1];

            if (!phraseExists(phrase, tableName)) {
                addPhrase(phrase, engTranslation, tableName);
            }
        }
    }

    public void processPhrases(String path, String tableName) {
        List<String[]> phrases = file.readPhrases(path);
        writePhrasesToDB(phrases, tableName);
    }

    public void clearFunFacts() {
        dbManager.updateDB("TRUNCATE TABLE FUNFACTS");
    }

    public void clearPhrases(String tableName) {
        dbManager.updateDB("TRUNCATE TABLE " + tableName);
    }

    private void writeFactsToDB(ArrayList<String> afrFacts, ArrayList<String> tagFacts) {
        clearFunFacts();

        int maxSize = Math.max(afrFacts.size(), tagFacts.size());

        for (int i = 0; i < maxSize; i++) {
            String afrFact = i < afrFacts.size() ? afrFacts.get(i) : "N/A";
            String tagFact = i < tagFacts.size() ? tagFacts.get(i) : "N/A";
            addFact(afrFact, tagFact);
        }
    }
    public void processFacts(String afrPath, String tagPath) {
        ArrayList<String> afrFacts = file.readLine(afrPath);
        ArrayList<String> tagFacts = file.readLine(tagPath);
        writeFactsToDB(afrFacts, tagFacts);
    }
}
