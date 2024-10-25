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

public class EasyPanel extends BaseGUI {

    private JButton easyPanelButton;
    private JButton menuPanelButton;
    private static JLabel centerLabel;
    private static Model model;
    private static Randomizer rand;
    private Data data;
    private JScrollPane funFact;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private static Integer score = 0;
    private static String phrase;
    private static boolean lang;
    private static JLabel counterLabel;

    public EasyPanel(MainFrame mainFrame) {
        super(mainFrame);

    }

    @Override
    protected JPanel createContentPanel(boolean lang) {
        this.lang = lang;
        JPanel panel = new JPanel(new BorderLayout());
        model = new Model();
        rand = new Randomizer();
        data = new Data();

        JPanel northPanel = new JPanel();
        JLabel northLabel = new JLabel((lang ? "Afrikaans" : "Tagalog"));
        northPanel.add(northLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        phrase = (lang ? rand.randomPhrase("afrPhrase") : rand.randomPhrase("tagPhrase"));
        centerLabel = new JLabel(phrase);
        centerPanel.add(centerLabel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));

        //4 button panel that will be in south panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        String[] phrases = model.fourPhrases(data.lang);
        button1 = createButton(phrases[0]);
        button2 = createButton(phrases[1]);
        button3 = createButton(phrases[2]);
        button4 = createButton(phrases[3]);

        // Add buttons to the panel
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);

        applyButtonAction(button1, phrases[0]);
        applyButtonAction(button2, phrases[1]);
        applyButtonAction(button3, phrases[2]);
        applyButtonAction(button4, phrases[3]);

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
        model = new Model();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.add(Box.createVerticalStrut(30));

        easyPanelButton = createNavButton("Button 2 to Easy Test", "Easy Panel");

        navPanel.add(easyPanelButton);
        navPanel.add(Box.createVerticalStrut(15));

        menuPanelButton = createNavButton("Button 1 to Go back to Main Menu", "Menu Panel");

        navPanel.add(menuPanelButton);
        navPanel.add(Box.createVerticalStrut(15));

        counterLabel = createLabel("Score: " + score);
        counterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        navPanel.add(counterLabel);

        funFact = createTextArea("Fun Fact: " + model.randomFact());
        funFact.setAlignmentX(Component.CENTER_ALIGNMENT);
        navPanel.add(funFact);

        return navPanel;
    }

    protected static void buttonPressed(String buttonName) {
        // Logic for what happens when a button is pressed
        // Update the centerLabel or any other components as needed
        //centerLabel.setText("You pressed " + buttonName);
//        if (buttonName != null && buttonName.equals(phrase)) {
//            score++;
//        }
        if (model.checkAnswer(buttonName, phrase, lang)) {///////////////////////////////////needs work
            String newPhrase = (lang ? rand.randomPhrase("afrPhrase") : rand.randomPhrase("tagPhrase"));
            centerLabel.setText(newPhrase);
            counterLabel.setText("Score: " + score++);
            refreshPanel();
        }

         // Call refresh method
    }

    private static void refreshPanel() {
        System.out.println("Panel refreshed with new data.");
        // Example: Update other labels or UI elements here as necessary
    }

    public void addActionListener(ActionListener listener) {
        easyPanelButton.addActionListener(listener);
        menuPanelButton.addActionListener(listener);

    }

    protected void applyButtonAction(JButton button, String buttonName) {
        button.addActionListener(e -> EasyPanel.buttonPressed(buttonName));
    }
}
