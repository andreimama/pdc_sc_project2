/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package languagelearner;

/**
 *
 * @author franc
 */
public class LanguageLearner {

    private static final String pathAfr = "PhrasesAfr.txt";
    private static final String pathTag = "PhrasesTag.txt";
    private static final String funFactsAfr = "FunFactsAfr.txt";
    private static final String funFactsTag = "FunFactsPhil.txt";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DBManager dbManager = new DBManager();// To be used later
        DBInitializer DBInit = new DBInitializer();
        DBInit.createTable("AfrikaansPhrases");
        DBInit.createTable("TagalogPhrases");
        DBInit.createTable("FunFacts");
        DBInit.clearPhrases("AFRIKAANSPHRASES");
        DBInit.clearPhrases("TAGALOGPHRASES");
        DBInit.readAndInsertPhrases(pathAfr, "AfrikaansPhrases");
        DBInit.readAndInsertPhrases(pathTag, "TagalogPhrases");
        DBInit.readAndInsertFacts(funFactsAfr, funFactsTag);

        Panel f = new Panel();
        f.setVisible(true);
    }

}
