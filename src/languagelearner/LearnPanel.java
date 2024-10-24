/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package languagelearner;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author mamar
 */
public class LearnPanel extends BaseGUI {
    
    public LearnPanel(MainFrame mainFrame) {
        super(mainFrame);
 
    }

    @Override
    protected JPanel createContentPanel(boolean lang) {// This didn't work
         JPanel panel = new JPanel(new BorderLayout());
        
        JPanel northPanel = new JPanel();
        JLabel northLabel = new JLabel((lang ? "Afrikaans" : "Tagalog"));// Didnt change
        northPanel.add(northLabel, BorderLayout.NORTH);
         
        JPanel centerPanel = new JPanel();
        JScrollPane textAreaPane = createTextArea("texttexttext\ntextxtextxteteexttexxtexte\ntexxtetextxetxetxetxtxetxetetxtetete");
        centerPanel.add(textAreaPane, BorderLayout.CENTER);
        
        panel.add(northPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
       
        
        return panel;
        
        
    }

    public void refresh(){// this didn't work even when either the menupanel had a lang or when this class had a lang
        revalidate();
        repaint();
        System.out.println("Super lang is: " + super.lang);
        
    }
    
    @Override
    protected JPanel createNavPanel() {
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.add(Box.createVerticalStrut(30));
        
        navPanel.add(createNavButton("Button 2 to Easy Test", "Easy Panel"));
        navPanel.add(Box.createVerticalStrut(15));
        navPanel.add(createNavButton("Button 3 to learn phrases", "Learn Panel"));
        navPanel.add(Box.createVerticalStrut(15));

        navPanel.add(createNavButton("Button 1 to Go back to Main Menu", "Menu Panel"));
        
        navPanel.add(Box.createVerticalStrut(15));
        
        JLabel counterLabel = createLabel("Score: 0");
        counterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        navPanel.add(counterLabel);
        //counter for score
        
        
        JScrollPane funFact = createTextArea("Fun Fact: ");
        funFact.setAlignmentX(Component.CENTER_ALIGNMENT);
        navPanel.add(funFact);
        //fun fact will be here, a bit unsure to connect datababse and randomier for this.
        return navPanel;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
