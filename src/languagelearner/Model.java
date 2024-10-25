/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package languagelearner;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.sql.ResultSet;
import javax.swing.JTextArea;

/**
 *
 * @author franc
 */
public class Model {

    public boolean lang; //true = afrikaans, false = tagalog
    public Data data = new Data();
    private DBManager dbManager = new DBManager();
    private Randomizer rand = new Randomizer();
    //LearnPanel learnPanel = new LearnPanel(BaseGUI.mainFrame);
    //  private List<PanelListener> listeners = new ArrayList<>();

    private PanelListener listener;

    //makes a listener
    public void setListener(PanelListener listener) {
        this.listener = listener;
    }

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

    public String[] fourPhrases(boolean lang) {
        Set<String> phrases = new HashSet<>();
        while (phrases.size() < 4) {
            String phrase;
            if (lang) {
                phrase = rand.randomPhrase("afrTrans");
            } else {
                phrase = rand.randomPhrase("tagTrans");
            }
            if (phrase != null) {
                phrases.add(phrase);
            }
        }
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
        if (listener != null) {
            listener.onUpdated(this.data);
        }

    }
    public boolean checkAnswer(String phrase1,String phrase2,boolean lang){
        if(lang){
            return checkAnswer("AFRIKAANSPHRASES","PHRASE","ENGLISHTRANSLATION",phrase1,phrase2);
        }else{
            return checkAnswer("TAGALOGPHRASES","PHRASE","ENGLISHTRANSLATION",phrase1,phrase2);
        }
    }
    private boolean checkAnswer(String tableName,String columnName1,String columnName2, String phrase1,String phrase2){
        String query1 = "SELECT ID FROM " + tableName + " WHERE " + columnName1 + " = '" + phrase1 + "'";
        String query2 = "SELECT ID FROM " + tableName + " WHERE " + columnName2 + " = '" + phrase2 + "'";
        ResultSet rs1 = dbManager.getFromDB(query1);
        ResultSet rs2 = dbManager.getFromDB(query2);
        if(rs1 == rs2){
            return true;
        }else{
            return false;
        }
    }

    //once everything works put this in.
    /*public void quitGame() {
        
    }*/
}
