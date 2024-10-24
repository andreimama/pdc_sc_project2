/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package languagelearner;

//import java.awt.BorderLayout;
//import java.awt.Component;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import javax.swing.Box;
//import javax.swing.BoxLayout;
//import javax.swing.JButton;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextField;
//
///**
// *
// * @author franc
// */
//public class EasyPanel extends BaseGUI {
//
//    private String testPhrase;
//    private Integer score = 0;
//    JLabel counterLabel;
//    private JPanel centerPanel;
//    private JPanel buttonPanel;
//    private Randomizer rand;
//
//    public EasyPanel(MainFrame mainFrame) {
//        super(mainFrame);
//        rand = new Randomizer();
//        counterLabel = createLabel("Score: " + score);
//        testPhrase = rand.randomPhrase(lang ? "afrPhrase" : "tagPhrase");
//        initializeGame();
//
//    }
//
//    private void initializeGame() {
//        testPhrase = rand.randomPhrase(lang ? "afrPhrase" : "tagPhrase");
//        score = 0;
//        counterLabel.setText("Score: " + score);
//    }
//
//    @Override
//    public JPanel createNavPanel() {
//        JPanel navPanel = new JPanel();
//        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
//        navPanel.add(Box.createVerticalStrut(30));
//        navPanel.add(Box.createVerticalStrut(15));
//        navPanel.add(createNavButton("Button 2 to Easy Test", "Easy Panel"));
//        navPanel.add(Box.createVerticalStrut(15));
//        navPanel.add(createNavButton("Button 3 to learn phrases", "Learn Panel"));
//        navPanel.add(Box.createVerticalStrut(15));
//
//        navPanel.add(quitButton("Quit Button"));
//        navPanel.add(Box.createVerticalStrut(15));
//
//        counterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        navPanel.add(counterLabel);
//        JScrollPane funFact = createTextArea("Fun Fact: " + rand.rabndomFact());
//        funFact.setAlignmentX(Component.CENTER_ALIGNMENT);
//        navPanel.add(funFact);
//
//        return navPanel;
//    }
//
//    @Override
//    protected JPanel createContentPanel() {
//        JPanel panel = new JPanel(new BorderLayout());
//        JPanel northPanel = new JPanel();
//        Randomizer rand = new Randomizer();
//        String title = lang ? "Afrikaans phrase:" : "Tagalog Phrase";
//        JLabel northLabel = new JLabel(title);
//        northPanel.add(northLabel, BorderLayout.NORTH);
//
//        centerPanel = new JPanel();
//        updatePanelCenter();
//
//        JPanel southPanel = new JPanel();
//        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
//        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
//        updateButtonOptions();
//
//        southPanel.add(buttonPanel);
//
//        panel.add(northPanel, BorderLayout.NORTH);
//        panel.add(centerPanel, BorderLayout.CENTER);
//        panel.add(southPanel, BorderLayout.SOUTH);
//        return panel;
//    }
//
//    private void updatePanelCenter() {
//        testPhrase = lang ? rand.randomPhrase("afrPhrase") : rand.randomPhrase("tagPhrase");
//        centerPanel.removeAll();
//        JLabel centerLabel = new JLabel(testPhrase);
//        centerPanel.add(centerLabel, BorderLayout.CENTER);
//        centerPanel.revalidate();
//        centerPanel.repaint();
//    }
//
//    private void updateButtonOptions() {
//        buttonPanel.removeAll(); // Clear existing buttons
//
//        String[] phrases = rand.fourPhrases(super.lang);
//        int[] placement = rand.randomPlacement();
//        buttonPanel.add(createButton(phrases[placement[0]]));
//        buttonPanel.add(createButton(phrases[placement[1]]));
//        buttonPanel.add(createButton(phrases[placement[2]]));
//        buttonPanel.add(createButton(phrases[placement[3]]));
//
//        buttonPanel.revalidate();
//        buttonPanel.repaint();
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        String buttonText = ((JButton) e.getSource()).getText();
//        if (buttonText.equals(testPhrase)) {
//            score++;
//        }
//        counterLabel.setText("Score: " + score);
//
//        updatePanelCenter();
//        updateButtonOptions();
//    }
//}
// Code left behind because it did work, we need to figure out how to update the components
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author mamar
 */

public class EasyPanel extends BaseGUI {
    
    public EasyPanel(MainFrame mainFrame) {
        super(mainFrame);
 
    }
    
    @Override 
    protected JPanel createContentPanel(boolean lang) {
        JPanel panel = new JPanel(new BorderLayout());
        
        
        
        JPanel northPanel = new JPanel();
        JLabel northLabel = new JLabel("will say if afrikaans or tagalog");
        northPanel.add(northLabel, BorderLayout.NORTH);
         
        JPanel centerPanel = new JPanel();
        JLabel centerLabel = new JLabel("phrase");
        centerPanel.add(centerLabel, BorderLayout.CENTER);
                
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        
            //4 button panel that will be in south panel
            JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
            buttonPanel.add(createButton("a1"));
            buttonPanel.add(createButton("a2"));
            buttonPanel.add(createButton("a3"));
            buttonPanel.add(createButton("a4"));
            
        southPanel.add(buttonPanel);
        
       // JLabel.setPreferredSize(new Dimension(200,30));
       
        panel.add(northPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(southPanel, BorderLayout.SOUTH);
        return panel;
    }
    @Override
    protected JPanel createNavPanel() {
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.add(Box.createVerticalStrut(30));
        
        navPanel.add(createNavButton("Button 2 to Easy Test", "Easy Panel"));
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
        System.out.println("Button clicked, "+ ((JButton)e.getSource()).getText());
    }
}