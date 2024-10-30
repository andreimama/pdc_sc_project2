/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package languagelearner;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
//
///**
// *
// * @author franc

public class EasyPanel extends BaseGUI implements PanelListener {

    private JButton easyPanelButton;
    private JButton menuPanelButton;
    private static JLabel centerLabel;
    public static Model model;
    private static Randomizer rand;
    private JScrollPane funFact;
    private static JButton button1;
    private static JButton button2;
    private static JButton button3;
    private static JButton button4;
    private static int score = 0;
    private static String phrase;
    private static boolean lang;////////////////////////////////////////////////////
    private static JLabel northLabel;
    public static JLabel counterLabel;
    private String[] phrases;
    
    public EasyPanel(MainFrame mainFrame) {
        super(mainFrame);
        
    }

    @Override
    protected JPanel createContentPanel(boolean lang) {
        model = new Model();
        JPanel panel = new JPanel(new BorderLayout());
        rand = new Randomizer();
        model.updateLanguageFlag(lang);

        JPanel northPanel = initializeNorthPanel(lang);
        JPanel centerPanel = initializeCenterPanel(lang);
        JPanel southPanel = initializeSouthPanel(lang);

        panel.add(northPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(southPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel initializeNorthPanel(boolean lang) {
        JPanel northPanel = new JPanel();
        northLabel = new JLabel(lang ? "Afrikaans" : "Tagalog");
        northPanel.add(northLabel, BorderLayout.NORTH);
        return northPanel;
    }

    private JPanel initializeCenterPanel(boolean lang) {
        JPanel centerPanel = new JPanel();
        phrase = lang ? rand.randomPhrase("afrPhrase") : rand.randomPhrase("tagPhrase");
        centerLabel = new JLabel(phrase);
        centerPanel.add(centerLabel, BorderLayout.CENTER);
        return centerPanel;
    }

    private JPanel initializeSouthPanel(boolean lang) {
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        phrases = model.fourPhrases(phrase, lang);
        rand.shuffleArray(phrases);

        button1 = createButton(phrases[0]);
        button1.addActionListener(mainFrame.getController());
        button2 = createButton(phrases[1]);
        button3 = createButton(phrases[2]);
        button4 = createButton(phrases[3]);
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        applyButtonAction(button1, phrases[0]);
        applyButtonAction(button2, phrases[1]);
        applyButtonAction(button3, phrases[2]);
        applyButtonAction(button4, phrases[3]);
        southPanel.add(buttonPanel);
        return southPanel;
    }

    @Override
    protected JPanel createNavPanel() {
        JPanel navPanel = new JPanel();
        model = new Model();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.add(Box.createVerticalStrut(30));

        easyPanelButton = createNavButton("Test", "Easy Panel");

        navPanel.add(easyPanelButton);
        navPanel.add(Box.createVerticalStrut(15));

        menuPanelButton = createNavButton("Back to Main Menu", "Menu Panel");

        navPanel.add(menuPanelButton);
        navPanel.add(Box.createVerticalStrut(15));

        counterLabel = createLabel("Score: "+ model.getScore());
        counterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        navPanel.add(counterLabel);

        funFact = createTextArea("Fun Fact: " + model.randomFact());
        funFact.setAlignmentX(Component.CENTER_ALIGNMENT);
        navPanel.add(funFact);

        return navPanel;
    }

    protected static void buttonPressed(String buttonName) {
        if (model.checkAnswer(phrase, buttonName, lang)) {
            EasyPanel.model.incrementScore();
            phrase = (lang ? rand.randomPhrase("afrPhrase") : rand.randomPhrase("tagPhrase"));
            centerLabel.setText(phrase);
        } else {
            phrase = (lang ? rand.randomPhrase("afrPhrase") : rand.randomPhrase("tagPhrase"));
            centerLabel.setText(phrase);
            System.out.println("no point");
        }
       
        refreshPanel();
    }

    public static void refreshPanel() {
        String[] newPhrases = model.fourPhrases(phrase, lang);
        
        northLabel.setText(lang ? "Afrikaans" : "Tagalog");
        System.out.println("set new notrh label as " + lang );
        // Update button text
        
        counterLabel.setText("Score: " + EasyPanel.model.getScore());
        
        button1.setText(newPhrases[0]);
        button2.setText(newPhrases[1]);
        button3.setText(newPhrases[2]);
        button4.setText(newPhrases[3]);

        applyButtonAction(button1, newPhrases[0]);
        applyButtonAction(button2, newPhrases[1]);
        applyButtonAction(button3, newPhrases[2]);
        applyButtonAction(button4, newPhrases[3]);
    }

    

    public void addActionListener(ActionListener listener) {
        easyPanelButton.addActionListener(listener);
        menuPanelButton.addActionListener(listener);

    }
    
    
    
    private void updateComponents() {
        updateCentralLabel();
        refreshPanel();
        
    }
    private void updateCentralLabel() {
        phrase = lang ? rand.randomPhrase("afrPhrase") : rand.randomPhrase("tagPhrase");
        if (centerLabel != null) {
            centerLabel.setText(phrase);
        }
    
    }

    protected static void applyButtonAction(JButton button, String buttonName) {
        for (ActionListener al : button.getActionListeners()) {
            button.removeActionListener(al);
        }
        button.addActionListener(e -> EasyPanel.buttonPressed(buttonName));
    }

    @Override
    public void onUpdated(Data data) {
        lang = data.lang;//////////////////////////////////////////////////////////////////
        System.out.println("It work easyPanel");
        updateComponents();
    }


        
        
    
    

    }
