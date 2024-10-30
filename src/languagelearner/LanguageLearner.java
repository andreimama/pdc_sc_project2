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

    //constructor
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        

        
        DBManager dbManager = new DBManager();// To be used later
        DBInitializer DBInit = new DBInitializer();
        Randomizer rand = new Randomizer();
        //MenuPanel menu = new MenuPanel();
        MainFrame main = new MainFrame();

        DBInit.clearPhrases("AFRIKAANSPHRASES");
        DBInit.clearPhrases("TAGALOGPHRASES");
        DBInit.processPhrases(pathAfr, "AfrikaansPhrases");
        DBInit.processPhrases(pathTag, "TagalogPhrases");
        DBInit.processFacts(funFactsAfr, funFactsTag);

        // Testing
        //admin.setVisible(true);///////////////////////////////////////////keep/////////////////////////////////////////////////
//        System.out.println(rand.randomPhrase("afrPhrase"));
//        System.out.println(rand.randomPhrase("afrTrans"));
//        System.out.println(rand.randomPhrase("tagPhrase"));
//        System.out.println(rand.randomPhrase("tagTrans"));
//        System.out.println(rand.randomPhrase("afrFact"));
//        System.out.println(rand.randomPhrase("tagFact"));
    }
}
