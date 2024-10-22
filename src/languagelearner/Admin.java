/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package languagelearner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author franc
 */
public class Admin extends JFrame {
/////////////////////////////////////////////////////////problems everywhere, so sab :(
    private JPanel mainPanel;
    private JComboBox<String> comboBox;
    private JButton submitButton;
    private JTextField afrPhraseSub;
    private JTextField tagPhraseSub;
    private JTextField engTransAfr;
    private JTextField engTransTag;
    private JTextField southAfricanFact;
    private JTextField philippinesFact;
    private JLabel afrLabel;
    private JLabel engLabel;
    private JLabel tagLabel;
    private JLabel afrFunFactLab;
    private JLabel philFunFactLab;

    public Admin() {
        setTitle("Admin Panel");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        FileIO file = new FileIO();

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String[] options = {"Afrikaans Phrases", "Tagalog phrases", "South-African Fun Facts", "Philippines Fun Facts"};
        comboBox = new JComboBox<>(options);
        comboBox.addActionListener(new ComboBoxListener());
        
        SubmitButtonHandlerForDev submitButtonHandler = new SubmitButtonHandlerForDev(mainPanel, afrPhraseSub, tagPhraseSub, southAfricanFact, philippinesFact, file);
        //submitButton.addActionListener(submitButtonHandler);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(comboBox, gbc);

        afrPhraseSub = new JTextField(10);
        engTransAfr = new JTextField(10);
        engTransTag = new JTextField(10);
        tagPhraseSub = new JTextField(10);
        submitButton = new JButton("Submit");
        afrLabel = new JLabel("Afrikaans phrase:");
        engLabel = new JLabel("English Translation:");
        tagLabel = new JLabel("Tagalog phrase:");
        afrFunFactLab = new JLabel("South-African Fun Fact:");
        philFunFactLab = new JLabel("Philippines Fun Fact:");

        add(mainPanel);
    }

    private class ComboBoxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedOption = (String) comboBox.getSelectedItem();
            refreshPanel(selectedOption);
        }
    }
    private void refreshPanel(String selectedOption) {
        mainPanel.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(comboBox, gbc);

        switch (selectedOption) {
            case "Afrikaans Phrases":
                gbc.gridy = 1;
                gbc.gridx = 0;
                mainPanel.add(afrLabel, gbc);
                gbc.gridx = 1;
                mainPanel.add(afrPhraseSub, gbc);

                gbc.gridy = 2;
                gbc.gridx = 0;
                mainPanel.add(engLabel, gbc);
                gbc.gridx = 1;
                mainPanel.add(engTransAfr, gbc);
                break;

            case "Tagalog Phrases":
                gbc.gridy = 1;
                gbc.gridx = 0;
                mainPanel.add(tagLabel, gbc);
                gbc.gridx = 1;
                mainPanel.add(tagPhraseSub, gbc);

                gbc.gridy = 2;
                gbc.gridx = 0;
                mainPanel.add(engLabel, gbc);
                gbc.gridx = 1;
                mainPanel.add(engTransTag, gbc);
                break;

            case "South-African Fun Facts":
                gbc.gridy = 1;
                gbc.gridx = 0;
                mainPanel.add(afrFunFactLab, gbc);
                gbc.gridx = 1;
                mainPanel.add(southAfricanFact, gbc);
                break;

            case "Philippines Fun Facts":
                gbc.gridy = 1;
                gbc.gridx = 0;
                mainPanel.add(philFunFactLab, gbc);
                gbc.gridx = 1;
                mainPanel.add(philippinesFact, gbc);
                break;

            default:
                gbc.gridy = 1;
                mainPanel.add(afrPhraseSub, gbc);
                break;
        }
        gbc.gridy = 3;
        mainPanel.add(submitButton, gbc);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
