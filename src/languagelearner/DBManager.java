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
public final class DBManager {
    
    private static final String USER_NAME = "Group99"; //DB username
    private static final String PASSWORD = "Ass2Lang"; //DB password
    private static final String URL = "jdbc:derby://localhost:1527/Phrases&Facts";  //url of the DB host

    Connection conn;

    public DBManager() {
        establishConnection();
    }

    public Connection getConnection() {
        return this.conn;
    }

    //Establish connection to DataBase
    public void establishConnection() {
        if(this.conn == null){
            try{
                conn = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
                System.out.println(URL + " connected...");
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        
    }

    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public void updateDB(String sql) {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public ResultSet getFromDB(String sql){
        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return resultSet;
    }
}
