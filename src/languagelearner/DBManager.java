/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package languagelearner;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author franc
 */
//Class for managing database and executes SQL Queries
public final class DBManager {
    //Database credentials
    private static final String USER_NAME = "Group99"; //DB username
    private static final String PASSWORD = "Ass2Lang"; //DB password
    private static final String URL = "jdbc:derby://localhost:1527/Phrases&Facts";  //url of the DB host
    
    //Connection object to interact with database
    Connection conn;
    public DBManager() {
        establishConnection();
    }
    
    public Connection getConnection() {
        return this.conn;
    }
    //Establish connection to DataBase if one does not already exist
    public void establishConnection() {
        if (this.conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                System.out.println(URL + " connected...");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    //Closes database connection
    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    //Executes an Update query on the database 
    public void updateDB(String sql) {
        Connection connection = this.conn;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //Queries the database and returns The queryt result
    public ResultSet getFromDB(String sql) {
        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultSet;
    }
    //Retrievse a specific phrase through using an inedx.
    private String getPhrase(String tableName, String columnName, int index) {
        String phrase = null;
        String query = "SELECT " + columnName + " FROM " + tableName + " ORDER BY ID OFFSET " + index + " ROWS FETCH NEXT 1 ROWS ONLY";

        try ( ResultSet rs = getFromDB(query)) {
            if (rs.next()) {
                phrase = rs.getString(columnName);
                if (phrase.equals("N/A")) {
                    return "";
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return phrase;
    }
    //Get all Phrases from a specific table & column
    public String[] getAllPhrases(String tableName, String columnName) {
        int count = 0;
        String countQuery = "SELECT COUNT(*) FROM " + tableName;

        try ( ResultSet rs = getFromDB(countQuery)) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String[] phrases = new String[count];
        for (int i = 0; i < count; i++) {
            phrases[i] = getPhrase(tableName, columnName, i);
        }
        if (phrases == null) {
            String[] test = new String[1];
            test[0] = "didn't work";
            return test;
        }
        return phrases;
    }
}
