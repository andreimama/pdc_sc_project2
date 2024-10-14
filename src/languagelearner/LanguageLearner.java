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
    
    private static final String path1 = "PhrasesAfr.txt";
    private static final String path2 = "PhrasesTag.txt";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DBManager dbManager = new DBManager();// To be used later
        DBInitializer DBInit = new DBInitializer();
        DBInit.createTables();
        DBInit.readAndInsertPhrases(path1, path2);
    }

}
