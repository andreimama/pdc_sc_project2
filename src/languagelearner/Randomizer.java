/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package languagelearner;

import java.util.Random;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author franc
 */
public class Randomizer {

    Random rand = new Random();
    DBManager dbManager = new DBManager();

    public int[] randomPlacement() {
        int[] range = {1, 2, 3, 4};

        for (int i = range.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = range[i];
            range[i] = range[j];
            range[j] = temp;
        }
        return range;
    }

    private int randomPhraseIndex(String tableName) {
        int count = 0;
        String countQuery = "SELECT COUNT(*) FROM " + tableName;

        try ( ResultSet rs = dbManager.getFromDB(countQuery)) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (count == 0) {
            throw new IllegalStateException("Error: There are no phrases in the database.");
        }
        return rand.nextInt(count);
    }

    private String randomPhrase(String tableName, String columnName) {
        String phrase = null;
        int index = randomPhraseIndex(tableName);
        String query = "SELECT " + columnName + " FROM " + tableName + " ORDER BY ID OFFSET " + index + " ROWS FETCH NEXT 1 ROWS ONLY";

        try ( ResultSet rs = dbManager.getFromDB(query)) {
            if (rs.next()) {
                phrase = rs.getString(columnName);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return phrase;
    }

    public String randomPhrase(String options) {
        switch (options) {
            case "afrPhrase":
                return randomPhrase("AFRIKAANSPHRASES", "PHRASE");
            case "afrTrans":
                return randomPhrase("AFRIKAANSPHRASES", "ENGLISHTRANSLATION");
            case "tagPhrase":
                return randomPhrase("TAGALOGPHRASES", "PHRASE");
            case "tagTrans":
                return randomPhrase("TAGALOGPHRASES", "ENGLISHTRANSLATION");
            case "afrFact":
                return randomPhrase("FUNFACTS","SOUTHAFRICANFUNFACT");
            case "tagFact":
                return randomPhrase("FUNFACTS","PHILIPPINESFUNFACT");
            default :
                return "Error finding a phrase";
        }
    }
}