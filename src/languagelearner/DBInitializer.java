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

    public void createTables() {
        String createAfrikaansTable = "CREATE TABLE AfrikaansPhrases ("
                + "ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "
                + "AfrikaansPhrase VARCHAR(255), "
                + "EnglishTranslation VARCHAR(255))";

        String createTagalogTable = "CREATE TABLE TagalogPhrases ("
                + "ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "
                + "TagalogPhrase VARCHAR(255), "
                + "EnglishTranslation VARCHAR(255))";

        String createFunFactsTable = "CREATE TABLE FunFacts ("
                + "ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "
                + "SouthAfricanFunFact VARCHAR(500), "
                + "PhilippinesFunFact VARCHAR(500))";

        try {
            if (!tableExists("AfrikaansPhrases")) {
                dbManager.updateDB(createAfrikaansTable);
            }
            if (!tableExists("TagalogPhrases")) {
                dbManager.updateDB(createTagalogTable);
            }
            if (!tableExists("FunFacts")) {
                dbManager.updateDB(createFunFactsTable);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void addAfrPhrase(String afrPhrase, String engTranslation){
        String insertQuery = "INSERT INTO AfrikaansPhrases (AfrikaansPhrase, EnglishTranslation) "
                + "VALUES ('" + afrPhrase + "', '" + engTranslation + "')";
        dbManager.updateDB(insertQuery);
    }
    public void addTagPhrase(String tagPhrase, String engTranslation){
        String insertQuery = "INSERT INTO AfrikaansPhrases (AfrikaansPhrase, EnglishTranslation) "
                + "VALUES ('" + tagPhrase + "', '" + engTranslation + "')";
        dbManager.updateDB(insertQuery);
    }
    
    private boolean phraseExistsAfr(String afrPhrase){
        boolean exists = false;
        String checkQuery = "SELECT 1 FROM AfrikaansPhrases WHERE AfrikaansPhrase = '" + afrPhrase + "'";
        try{
            ResultSet rs = dbManager.getFromDB(checkQuery);
            exists = rs.next();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return exists;
    }
    private boolean phraseExistsTag(String tagPhrase){
        boolean exists = false;
        String checkQuery = "SELECT 1 FROM TagalogPhrases WHERE TagalogPhrase = '" + tagPhrase + "'";
        try{
            ResultSet rs = dbManager.getFromDB(checkQuery);
            exists = rs.next();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return exists;
    }
    public void readAndInsertPhrases(String path1,String path2){
        try(BufferedReader reader1 = new BufferedReader(new FileReader(path1))){
            String afrPhrase;
            while((afrPhrase = reader1.readLine()) != null){
                String engTranslation = reader1.readLine();
                
                if(engTranslation != null){
                    if(!phraseExistsAfr(afrPhrase)){
                        addAfrPhrase(afrPhrase, engTranslation);
                    }
                }
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        try(BufferedReader reader2 = new BufferedReader(new FileReader(path2))){
            String tagPhrase;
            while((tagPhrase = reader2.readLine()) != null){
                String engTranslation = reader2.readLine();
                
                if(engTranslation != null){
                    if(!phraseExistsTag(tagPhrase)){
                        addAfrPhrase(tagPhrase, engTranslation);
                    }
                }
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
