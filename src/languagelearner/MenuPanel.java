/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package languagelearner;

/**
 *
 * @author mamar
 */
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author mamar
 */
public class MenuPanel extends BaseGUI {

    private JComboBox<String> comboBox;
    private LearnPanel learn;

    public MenuPanel(MainFrame mainFrame, LearnPanel learnPanel) {
        super(mainFrame);
        this.learn = learnPanel;
        // add(new JTextField(20));
        // add(createNavButton("Go to Easy panel", "EasyPanel"));
        // add(createNavButton("GUI2", "MenuPanel"));
    }

    @Override
    protected JPanel createContentPanel(boolean lang) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //chatgpt was used here, just to position the comboxbox in the right place.
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0); // Adds space between components

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = -4; // Push everything down
        panel.add(Box.createVerticalGlue(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0; // Reset weighty
        panel.add(createLabel("Welcome 2 Lang"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        String[] languages = {"Afrikaans", "Tagalog"};
        comboBox = createComboBox(languages);
        panel.add(comboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(createButton("Apply"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weighty = 1.0; // Increase weight to push everything above downward more effectively
        panel.add(Box.createVerticalGlue(), gbc);

        return panel;
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
        JButton source = (JButton) e.getSource();
        if (source.getText().equals("Apply")) {
            String selectedLanguage = (String) comboBox.getSelectedItem();
            if ("Afrikaans".equals(selectedLanguage)) {
                changeSuperLang(true);
                learn.refresh();
                System.out.println("Afrikaans is selected");
            } else if ("Tagalog".equals(selectedLanguage)) {
                changeSuperLang(false);//////////////////////////////////////////////This doesn't work
                super.lang = false;/////////////////////////////////////////////////This doesn't work
                //I've tried just making a boolean lang specifically just for learnpanel and menupanel, those didn't work
                learn.refresh();
                System.out.println("Tagalog is selected");
            }
        } else {
            System.out.println("Button clicked: " + source.getText());
        }
    }
}
