/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package languagelearner;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Iterator;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
//import javax.swing.JTextField;


/**
 *
 * @author mamar
 */

    
public class EasyMode extends JFrame implements ActionListener {
    public JButton button, button2, button3, button4, quitButton;
   // public JTextField text;
    //Dynamic Text label will be label for fun facts that changes from db
    public JLabel staticTextLabel, dynamicTextLabel;

    public EasyMode() {
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
        this.dynamicTextLabel = new JLabel("this is a test fact! philippines is where i am from! :3");
        
        
    }
    
    public void initPanels() {
        
        //1 2 being i row 2 columns
        //JPanel mainPanel = new JPanel(new GridLayout(1,2));
        
        JPanel leftPanel = new JPanel(new BorderLayout());
        
        
        JPanel northPanel = new JPanel();
        northPanel.add(this.quitButton);
        leftPanel.add(northPanel, BorderLayout.NORTH);
        
        
        JPanel centerPanel = new JPanel();
        //centerPanel.add(this.button2);
        leftPanel.add(centerPanel, BorderLayout.CENTER);
        
        //JPanel eastPanel = new JPanel();
        
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(this.staticTextLabel);
        rightPanel.add(this.dynamicTextLabel);
        

//this.add(eastPanel, BorderLayout.EAST);
        //eastPanel.add(this.staticTextLabel);
        //eastPanel.add(this.dynamicTextLabel);
        
        
        /*JPanel southPanel = new JPanel(new GridLayout(2,2));
        southPanel.add(this.button3);
        southPanel.add(this.button4);
        southPanel.add(this.button2);
        southPanel.add(this.button);
        
        this.add(southPanel, BorderLayout.SOUTH);
        */
   JPanel southPanel = new JPanel();
    southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
    southPanel.add(Box.createVerticalStrut(10)); // Top margin

    JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10)); // 2x2 grid with 10px gaps
    buttonPanel.add(this.button3);
    buttonPanel.add(this.button4);
    buttonPanel.add(this.button2);
    buttonPanel.add(this.button);
    southPanel.add(buttonPanel);
    southPanel.add(Box.createVerticalStrut(10)); // Bottom margin

    leftPanel.add(southPanel, BorderLayout.SOUTH);
    
    
    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
    splitPane.setResizeWeight(0.9); // 75% to the left, 25% to the right
    splitPane.setEnabled(false);//will disable moving margin
    
    this.add(splitPane);
    
        
        this.setSize(800,400);
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
}
