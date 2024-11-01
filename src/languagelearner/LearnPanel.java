/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package languagelearner;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author mamar
 */
//Class that also extends BaseGUI. Learn Panel that shows the phrases to learn
public class LearnPanel extends BaseGUI implements PanelListener {

    //Fields for GUI COMPONENTS
    private JButton easyPanelButton;
    private JButton menuPanelButton;
    private JLabel northLabel;
    private JScrollPane textAreaPane;
    JScrollPane funFact;
    private Randomizer rand;
    private DBManager dbManager;
    private Model model;

    //Constructor that iinitialises LearnPanel with MainFrame
    public LearnPanel(MainFrame mainFrame) {
        super(mainFrame);
    }
    
    //Creates ContentPanel with Text area for the content from Database
    @Override
    protected JPanel createContentPanel(boolean lang) {
        //This is default, will show if no combobox is selected, and code in mainFrame else switch.To is not working 
        JPanel panel = new JPanel(new BorderLayout());
        //Initiases the randomizer, DBManager and Model
         rand = new Randomizer(); 
        dbManager = new DBManager();
        model = new Model();
        
        //NorthPanel with Label of the Language
         JPanel northPanel = new JPanel();
        northLabel = createLabel("Label");
        northPanel.add(northLabel, BorderLayout.NORTH);
        
        //Center Panel with text area
        JPanel centerPanel = new JPanel();
        textAreaPane = createTextArea("text text");
        centerPanel.add(textAreaPane, BorderLayout.CENTER);
        
        //Adding panels to main content panel
        panel.add(northPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        return panel;
    
    }
    
    //Nav Panel
    @Override
    protected JPanel createNavPanel() {
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.add(Box.createVerticalStrut(30));

        //Nav Button to 
        easyPanelButton = createNavButton("Easy Test", "Easy Panel"); //Nav Button to Easy Panel
        navPanel.add(easyPanelButton); //Add button to navPanel
        navPanel.add(Box.createVerticalStrut(15));

        menuPanelButton = createNavButton("Back to Main Menu", "Menu Panel"); //Nav Button to Menu Panel
        //menuPanelButton.setActionCommand("Menu Panel");
        navPanel.add(menuPanelButton);//Add button to navPanel
        navPanel.add(Box.createVerticalStrut(15));
        
 
        //Adds Fun fact, gets random fact from database
        funFact = createTextArea("Fun Fact: ");
        funFact.setAlignmentX(Component.CENTER_ALIGNMENT);
        navPanel.add(funFact);
        return navPanel;

    }
    //Adds Actions listeners to navigation buttons
    public void addActionListener(ActionListener listener) {
        easyPanelButton.addActionListener(listener);
        menuPanelButton.addActionListener(listener);
    }
    
    // When new data is recieved (Combobox is selected which means lang is updated) this updates the panel
    @Override
    public void onUpdated(Data data) {
        //Updates Language albel based on the data changed
        if (northLabel != null) {
            northLabel.setText(data.lang ? "Afrikaans" : "Tagalog");
        }
        //Updates text area with phrases from the model
        if(textAreaPane != null){
            JTextArea textArea = (JTextArea) textAreaPane.getViewport().getView();
            textArea.setText(model.getAllPhrases(data.lang));
        }
        //Up[dates fun fact Area 
        if(funFact != null){
            JTextArea funfact = (JTextArea) funFact.getViewport().getView();
            funfact.setText("Fun fact: " + model.randomFact());
        }
    }
}
