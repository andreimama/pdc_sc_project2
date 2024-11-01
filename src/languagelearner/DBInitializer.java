/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package languagelearner;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    //Checks if phrase alreadu exists in table,
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
    //Checks if a fun fact exists in the specified column of the Fun facts table
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
    
    //Adds a phrase and its english translation to the specified table in the database
    public void addPhrase(String phrase, String engTranslation, String tableName) {
        String insertQuery = "INSERT INTO " + tableName + " (phrase, EnglishTranslation) "
                + "VALUES ('" + phrase + "', '" + engTranslation + "')";
        dbManager.updateDB(insertQuery);
    }
    //Adds a fun fact to the FUNFACTS table,
    public void addFact(String fact1, String fact2) {
    fact1 = fact1.isEmpty() ? "N/A" : fact1;
    fact2 = fact2.isEmpty() ? "N/A" : fact2;

    if (!factExists(fact1, "SOUTHAFRICANFUNFACT") && !factExists(fact2, "PHILIPPINESFUNFACT")) {
        String insertQuery = "INSERT INTO FUNFACTS (SOUTHAFRICANFUNFACT, PHILIPPINESFUNFACT) "
                + "VALUES ('" + fact1 + "', '" + fact2 + "')";
        dbManager.updateDB(insertQuery);
    }
}
    //Write phrases and translations to database
    public void writePhrasesToDB(List<String[]> phrases, String tableName) {
        for (String[] pair : phrases) {
            String phrase = pair[0];
            String engTranslation = pair[1];

            if (!phraseExists(phrase, tableName)) {
                addPhrase(phrase, engTranslation, tableName);
            }
        }
    }
}
