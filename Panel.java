/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pdc_sc_project2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.JTextField;


/**
 *
 * @author mamar
 */

    
public class Panel extends JFrame implements ActionListener {
    public JButton button, button2, button3, button4, quitButton;
   // public JTextField text;
    public JLabel staticTextLabel;

    public Panel() {
        initComponents();
        initPanels();
        initActionListener();
    }
    
    public void initComponents() {
        this.button = createButton("button");
        this.button2 = createButton("button2");
        this.button3 = createButton("button3");
        this.button4 = createButton("button4");
        this.quitButton = createButton("Quit");
        this.staticTextLabel = new JLabel("Fun Facts!");
        
        
    }
    
    public void initPanels() {
        
        JPanel northPanel = new JPanel();
        this.add(northPanel, BorderLayout.NORTH);
        northPanel.add(this.quitButton);
        
        JPanel centerPanel = new JPanel();
        //centerPanel.add(this.button2);
        this.add(centerPanel, BorderLayout.CENTER);
        
        JPanel eastPanel = new JPanel();
        this.add(eastPanel, BorderLayout.EAST);
        eastPanel.add(this.staticTextLabel);
        
        
        JPanel southPanel = new JPanel(new GridLayout(2,2));
        southPanel.add(this.button3);
        southPanel.add(this.button4);
        southPanel.add(this.button2);
        southPanel.add(this.button);
        
        this.add(southPanel, BorderLayout.SOUTH);
        
        
        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
    
    public void initActionListener() {
        this.quitButton.addActionListener(this);
    }
    
    
    private JButton createButton(String name) {
        JButton button = new JButton(name);
        button.addActionListener(this);
        return button;
    }
    //add this if initializng so many label components.
   /* private JLabel createJLabel(String name) {
        JLabel jlabel = new JLabel(name);
        return jlabel;
    }*/
    
     public void fetchButtonNamesFromDatabase() {
        String url = "jdbc:your_database_url";
        String user = "your_database_user";
        String password = "your_database_password";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             java.sql.Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name FROM button_names")) {

            if (rs.next()) this.button.setText(rs.getString("name"));
            if (rs.next()) this.button2.setText(rs.getString("name"));
            if (rs.next()) this.button3.setText(rs.getString("name"));
            if (rs.next()) this.button4.setText(rs.getString("name"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quitButton) {
            System.exit(0);
    } else if (e.getSource() == button) {
        System.out.println("Button 1 clicked");
    } else if (e.getSource() == button2) {
        System.out.println("Button 2 clicked");
    } else if (e.getSource() == button3) {
        System.out.println("Button 3 clicked");
    } else if (e.getSource() == button4) {
        System.out.println("Button 4 clicked");
    } else {
        throw new UnsupportedOperationException("Not supported yet."); // Handle unexpected actions
    }
    }
    
    public static void main(String[] args) {
        Panel f = new Panel();
        f.setVisible(true);
    }
}
