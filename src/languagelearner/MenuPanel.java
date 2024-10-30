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
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author mamar
 */
public class MenuPanel extends BaseGUI implements PanelListener {

    private JButton easyPanelButton;
    private JButton learnPanelButton;
    private JComboBox<String> comboBox;
    private JScrollPane funFact;
    private Model model;

    public MenuPanel(MainFrame mainFrame) {
        super(mainFrame);
        // add(new JTextField(20));
        // add(createNavButton("Go to Easy panel", "EasyPanel"));
        // add(createNavButton("GUI2", "MenuPanel"));
    }

    @Override
    protected JPanel createContentPanel(boolean lang) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        model = new Model();

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
        String[] languages = {"", "Afrikaans", "Tagalog"};
        comboBox = createComboBox(languages);
        panel.add(comboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(quitButton("Quit Button"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weighty = 1.0; // Increase weight to push everything above downward more effectively
        panel.add(Box.createVerticalGlue(), gbc);

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

        learnPanelButton = createNavButton("Button 3 to learn phrases", "Learn Panel");
        navPanel.add(learnPanelButton);
        navPanel.add(Box.createVerticalStrut(15));

        JLabel counterLabel = createLabel("Score: 0");
        counterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        navPanel.add(counterLabel);

        String fact = model.randomFact();
        funFact = createTextArea("Fun Fact: " + fact);
        funFact.setAlignmentX(Component.CENTER_ALIGNMENT);
        navPanel.add(funFact);

        return navPanel;
    }

    @Override
    public void onUpdated(Data data) {
        if (data.lang) {
            comboBox.setSelectedItem("Afrikaans");
            System.out.println("afirkaans update");
            super.lang = true;
        } else {
            comboBox.setSelectedItem("Tagalog");
            System.out.println("tagalog update");
            
        }

    }

    public void addActionListener(ActionListener listener) {
        easyPanelButton.addActionListener(listener);
        learnPanelButton.addActionListener(listener);
        comboBox.addActionListener(listener);
        String option = comboBox.getSelectedItem().toString();
        if(option == "Afrikaans"){
            super.lang = true;
        }else{
            super.lang = false;
        }
        

    }

}

