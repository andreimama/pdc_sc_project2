/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package languagelearner;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author mamar
 */
//Menu Class extends BaseGUI. Is the Main Menu panel that users will choose what language they will want to learn/test on
public class MenuPanel extends BaseGUI implements PanelListener {
    
    //GUI COMPONENTS in private fields
    private JButton easyPanelButton;
    private JButton learnPanelButton;
    private JButton adminPanelButton;
    public JComboBox<String> comboBox;
    private JScrollPane funFact;
    private Model model;

    //Constructor
    public MenuPanel(MainFrame mainFrame) {
        super(mainFrame);
    }
    
    //Creates its own Contentpanel for menupanel.
    @Override
    protected JPanel createContentPanel(boolean lang) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //Chatgpt was used here, just to position the comboxbox in the right place.
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0); // Adds space between components

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = -4; // Push everything down
        panel.add(Box.createVerticalGlue(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0; // Reset weighty
        panel.add(createLabel("Welcome to Language Learner ! Please select a language you would like to learn"), gbc);

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
    
    //Navigation Panel for MenuPanel class
    @Override
    protected JPanel createNavPanel() {
        JPanel navPanel = new JPanel();
        model = new Model();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.add(Box.createVerticalStrut(30));

        easyPanelButton = createNavButton("Easy Test", "Easy Panel"); //Nav Button to Easy Panel
        navPanel.add(easyPanelButton);//Add button to navPanel
        navPanel.add(Box.createVerticalStrut(15));

        learnPanelButton = createNavButton("Learn phrases", "Learn Panel");//Nav Button to Learn Panel
        navPanel.add(learnPanelButton);//Add button to navPanel
        navPanel.add(Box.createVerticalStrut(15));
        
        adminPanelButton = createNavButton("Admin Panel", "Admin Panel"); //Nav Button to Admin Panel
        navPanel.add(adminPanelButton);//Add button to navPanel
        navPanel.add(Box.createVerticalStrut(15));

        //Adds Fun fact, gets random fact from database
        String fact = model.randomFact();
        funFact = createTextArea("Fun Fact: " + fact);
        funFact.setAlignmentX(Component.CENTER_ALIGNMENT);
        navPanel.add(funFact);

        return navPanel;
    }

    @Override //Method intentionally left empty/ No use for updating data on menu panel yet, could be for future purposes.
    public void onUpdated(Data data) {
    
    }
    
    public void addActionListener(ActionListener listener) {
        easyPanelButton.addActionListener(listener);
        learnPanelButton.addActionListener(listener);
        adminPanelButton.addActionListener(listener);
        comboBox.addActionListener(listener);
        
        //Updates the language flag based on the combo box selection
        String option = comboBox.getSelectedItem().toString();
        if("Afrikaans".equals(option)){
            super.lang = true;
        } else{
            super.lang = false;
        }
    }
}
