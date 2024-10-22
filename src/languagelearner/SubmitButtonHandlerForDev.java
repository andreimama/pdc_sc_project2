/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package languagelearner;

/**
 *
 * @author franc
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubmitButtonHandlerForDev implements ActionListener {
    private static final String pathAfr = "PhrasesAfr.txt";
    private static final String pathTag = "PhrasesTag.txt";
    private static final String funFactsAfr = "FunFactsAfr.txt";
    private static final String funFactsTag = "FunFactsPhil.txt";
    
    private JPanel mainPanel;
    private JTextField afrPhraseSub;
    private JTextField tagPhraseSub;
    private JTextField southAfricanFact;
    private JTextField philippinesFact;
    private JTextField engTransAfr;
    private FileIO file;

    public SubmitButtonHandlerForDev(JPanel mainPanel, JTextField afrPhraseSub, JTextField tagPhraseSub,
                               JTextField southAfricanFact, JTextField philippinesFact, FileIO file) {
        this.mainPanel = mainPanel;
        this.afrPhraseSub = afrPhraseSub;
        this.tagPhraseSub = tagPhraseSub;
        this.southAfricanFact = southAfricanFact;
        this.philippinesFact = philippinesFact;
        this.file = file;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedOption = (String) ((JComboBox<?>) mainPanel.getComponent(0)).getSelectedItem(); // Get selected option

        switch (selectedOption) {
            case "Afrikaans Phrases":
                handleAfrikaansPhrases();
                break;
            case "Tagalog Phrases":
                handleTagalogPhrases();
                break;
            case "South-African Fun Facts":
                handleSouthAfricanFunFacts();
                break;
            case "Philippines Fun Facts":
                handlePhilippinesFunFacts();
                break;
            default:
                System.out.println("Unknown option");
                break;
        }
    }
//////////////////////////////////////////////////////////////////////////////////problems
    private void handleAfrikaansPhrases() {
        if (file.addToFile(pathAfr, afrPhraseSub.getText())) {
            JOptionPane.showMessageDialog(mainPanel, "Phrase added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(mainPanel, "Unable to add phrase. It may already exist.", "Error", JOptionPane.ERROR_MESSAGE);
        }
         if (file.addToFile(pathAfr, engTransAfr.getText())) {
            JOptionPane.showMessageDialog(mainPanel, "Phrase added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(mainPanel, "Unable to add phrase. It may already exist.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleTagalogPhrases() {
        if (file.addToFile(pathTag, tagPhraseSub.getText())) {
            JOptionPane.showMessageDialog(mainPanel, "Phrase added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(mainPanel, "Unable to add phrase. It may already exist.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleSouthAfricanFunFacts() {
        if (file.addToFile(funFactsAfr, southAfricanFact.getText())) {
            JOptionPane.showMessageDialog(mainPanel, "Phrase added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(mainPanel, "Unable to add phrase. It may already exist.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handlePhilippinesFunFacts() {
        if (file.addToFile(funFactsTag, philippinesFact.getText())) {
            JOptionPane.showMessageDialog(mainPanel, "Phrase added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(mainPanel, "Unable to add phrase. It may already exist.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
