/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package languagelearner;

import java.util.HashSet;
import java.util.Set;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author franc
 */
public class Model {

    public boolean lang; //true = afrikaans, false = tagalog
    public Data data = new Data();
    private DBManager dbManager = new DBManager();
    private Randomizer rand = new Randomizer();
    private List<PanelListener> listeners = new ArrayList<>();
    private int score = 0;

    //private PanelListener listener;

    public void addListener(PanelListener listener) {
        listeners.add(listener);
    }
    //makes a listener
    
    public int getScore() {
        return score;
    }
    
    public void incrementScore() {
        score++;
        notifyListener();
        
    }
    
    /* public void setListener(PanelListener listener) {
    this.listener = listener;
    }*/

    public void updateLanguageFlag(boolean lang) {
        this.lang = lang;
        data.lang = lang;
        System.out.println("yeah notifylistenr called");
        notifyListener();
    }

    public String getAllFacts(boolean lang) {
        if (lang) {
            String[] phrasesAfr = dbManager.getAllPhrases("AFRIKAANSPHRASES", "PHRASE");
            StringBuilder afrPhrases = new StringBuilder();
            afrPhrases.append("Afrikaans Phrases:\n");
            for (String phrase : phrasesAfr) {
                afrPhrases.append(phrase + "\n");
            }
            afrPhrases.append("\nEnglish Translations:\n");
            String[] phrasesEng = dbManager.getAllPhrases("AFRIKAANSPHRASES", "ENGLISHTRANSLATION");
            for (String phrase : phrasesEng) {
                afrPhrases.append(phrase + "\n");
            }
            return afrPhrases.toString();
        } else {
            String[] phrasesTag = dbManager.getAllPhrases("TAGALOGPHRASES", "PHRASE");
            StringBuilder tagPhrases = new StringBuilder();
            tagPhrases.append("Tagalog Phrases:\n");
            for (String phrase : phrasesTag) {
                tagPhrases.append(phrase + "\n");
            }
            tagPhrases.append("\nEnglish Translations:\n");
            String[] phrasesEng = dbManager.getAllPhrases("TAGALOGPHRASES", "ENGLISHTRANSLATION");
            for (String phrase : phrasesEng) {
                tagPhrases.append(phrase + "\n");
            }
            return tagPhrases.toString();
        }
    }

    public String[] fourPhrases(String afrikaansPhrase, boolean lang) {
        Set<String> phrases = new HashSet<>();

        // Add the English translation if available
        String englishTranslation = englishTranslation(afrikaansPhrase, lang);
        if (englishTranslation != null) {
            phrases.add(englishTranslation);
        }

        // Generate random phrases until we have 4 unique phrases
        while (phrases.size() < 4) {
            String phrase;
            if (lang) {
                phrase = rand.randomPhrase("afrTrans");
            } else {
                phrase = rand.randomPhrase("tagTrans");
            }

            // Only add unique phrases
            if (phrase != null) {
                phrases.add(phrase);
            }
        }

        // Convert Set to Array
        return phrases.toArray(new String[0]);
    }

    public String randomFact() {
        int lang = rand.randomIndexof2();
        String fact;
        if (lang == 0) {
            fact = rand.randomPhrase("afrFact");
            return fact;
        } else {
            fact = rand.randomPhrase("tagFact");
            return fact;
        }
    }

    public String getCurrentLanguage() {
        return lang ? "Afrikaans" : "Tagalog";
    }

    //notifys listener when data is updated
    private void notifyListener() {

        // for (PanelListener listener : listeners) {
        //   listener.onUpdated(this.data);
        for (PanelListener listener : listeners)
            listener.onUpdated(this.data);
        }

    

    public boolean checkAnswer(String phrase1, String phrase2, boolean lang) {
        if (lang) {
            return checkAnswer("AFRIKAANSPHRASES", "PHRASE", "ENGLISHTRANSLATION", phrase1, phrase2);
        } else {
            return checkAnswer("TAGALOGPHRASES", "PHRASE", "ENGLISHTRANSLATION", phrase1, phrase2);
        }
    }

    private boolean checkAnswer(String tableName, String columnName1, String columnName2, String phrase1, String phrase2) {
        String query1 = "SELECT ID FROM " + tableName + " WHERE " + columnName1 + " = '" + phrase1 + "'";
        String query2 = "SELECT ID FROM " + tableName + " WHERE " + columnName2 + " = '" + phrase2 + "'";
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {
            rs1 = dbManager.getFromDB(query1);
            if (!rs1.next()) {
                System.out.println("No results for query1: " + query1);
                return false;
            }

            rs2 = dbManager.getFromDB(query2);
            if (!rs2.next()) {
                System.out.println("No results for query2: " + query2);
                return false;
            }

            int id1 = rs1.getInt("ID");
            int id2 = rs2.getInt("ID");
            return id1 == id2;
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } finally {
            try {
                if (rs1 != null) {
                    rs1.close();
                }
                if (rs2 != null) {
                    rs2.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing ResultSet: " + e.getMessage());
            }
        }

        return false;
    }

    public String englishTranslation(String phrase, boolean lang) {
        if (lang) {
            return getTranslation("AFRIKAANSPHRASES", "PHRASE", "ENGLISHTRANSLATION", phrase);
        } else {
            return getTranslation("TAGALOGPHRASES", "PHRASE", "ENGLISHTRANSLATION", phrase);
        }
    }

    private String getTranslation(String tableName, String columnName, String translationColumn, String phrase) {
        String query = "SELECT " + translationColumn + " FROM " + tableName + " WHERE " + columnName + " = '" + phrase + "'";
        ResultSet rs = null;
        try {
            rs = dbManager.getFromDB(query);
            if (rs.next()) {
                return rs.getString(translationColumn);
            } else {
                System.out.println("Translation not found for: " + phrase);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing ResultSet: " + e.getMessage());
            }
        }
        return null;
    }

    //once everything works put this in.
    /*public void quitGame() {
        
    }*/
}
