package languagelearner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *
 * @author franc
 */

//Extends BaseGUI, GUI that has interface to add phrases or facts to the database
public class AdminPanel extends BaseGUI implements PanelListener {

    //GUI Componments
    JButton menuButton;
    JButton submitButton;
    JComboBox<String> comboBox;
    private JPanel contentPanel;
    private JLabel contentLabel;
    JTextField inputTextField1;
    JTextField inputTextField2;
    DBInitializer dbInit = new DBInitializer();

    private static final String pathAfr = "PhrasesAfr.txt";

    //constructor
    public AdminPanel(MainFrame mainFrame) {
        super(mainFrame); //(Inheritance) calls constructor of BaseGUI
        addActionListenerToSubmit(); //sets up action listeners
    }
    
    //Overides method to create NAV Panel (implements own nav Panel different to other panels)
    @Override
    protected JPanel createNavPanel() {
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));

        //Create ComboBox with options of what table to add to
        String[] options = {"", "Afrikaans Phrases", "Tagalog Phrases", "South-African Fact", "Philippines Fact"};
        comboBox = createComboBox(options);
        comboBox.addActionListener(e -> updateContentBasedOnSelection());
        navPanel.add(comboBox);

        //Create Buttons to go back to main menu or submit phrase or fact
        menuButton = createNavButton("Back to Main Menu", "Menu Panel");
        submitButton = createButton("Submit");

        navPanel.add(menuButton);
        navPanel.add(submitButton);

        return navPanel;
    }
    //Updates content panel based on what was selected in combobox earlier.
    private void updateContentBasedOnSelection() {
        contentPanel.removeAll();
        String selectedOption = (String) comboBox.getSelectedItem();

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        JLabel instructionLabel;
        inputTextField1 = new JTextField();
        inputTextField1.setPreferredSize(new Dimension(200, 30));
        
        //Updates conternt based on option selected.
        if ("Afrikaans Phrases".equals(selectedOption)) {
            instructionLabel = new JLabel("Please enter an Afrikaans phrase in the first text box and the English Translation in the second:");
            inputTextField2 = new JTextField();
            inputTextField2.setPreferredSize(new Dimension(200, 30));
            inputPanel.add(instructionLabel);
            inputPanel.add(Box.createVerticalStrut(10));
            inputPanel.add(inputTextField1);
            inputPanel.add(Box.createVerticalStrut(5));
            inputPanel.add(inputTextField2);
        } else if ("Tagalog Phrases".equals(selectedOption)) {
            instructionLabel = new JLabel("Please enter a Tagalog phrase in the first text box and the English Translation in the second:");
            inputTextField2 = new JTextField();
            inputTextField2.setPreferredSize(new Dimension(200, 30));
            inputPanel.add(instructionLabel);
            inputPanel.add(Box.createVerticalStrut(10));
            inputPanel.add(inputTextField1);
            inputPanel.add(Box.createVerticalStrut(5));
            inputPanel.add(inputTextField2);
        } else if ("Philippines Fact".equals(selectedOption)) {
            instructionLabel = new JLabel("Please enter a Tagalog fact in the first text box and press submit in the right-hand panel.");
            inputPanel.add(instructionLabel);
            inputPanel.add(inputTextField1);
        } else if ("South-African Fact".equals(selectedOption)) {
            instructionLabel = new JLabel("Please enter an Afrikaans fact in the first text box and press submit in the right-hand panel.");
            inputPanel.add(instructionLabel);
            inputPanel.add(inputTextField1);
        } else {
            return; //Do nothing if no option is selected
        }

        contentPanel.add(inputPanel, BorderLayout.CENTER); //Adds input panel to content Panel
        contentPanel.revalidate(); //Refreshes UI
        contentPanel.repaint(); //Repaints the panel to show cth cganges
    }
    
    //ContentPanel with inital instructions on choosing from combobox 
    @Override
    protected JPanel createContentPanel(boolean lang) {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        JLabel instructionLabel = new JLabel("Please select what table to add to database.");
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentLabel = new JLabel("Please select where you w like to add data in the combo box ->");
        contentPanel.add(instructionLabel);
        contentPanel.add(contentLabel, BorderLayout.CENTER);

        return contentPanel;
    }
    //Adds actionlistner to buttons 
    public void addActionListener(ActionListener listener) {
        menuButton.addActionListener(listener);
        submitButton.addActionListener(listener);
    }
    //adds ActionListener to submit button
    private void addActionListenerToSubmit() {
        submitButton.addActionListener(e -> {
            String selectedOption = (String) comboBox.getSelectedItem();
            if ("Afrikaans Phrases".equals(selectedOption) || "Tagalog Phrases".equals(selectedOption)) {
                String phrase = inputTextField1.getText().trim();
                String translation = inputTextField2.getText().trim();
                if (!phrase.isEmpty() && !translation.isEmpty()) {
                    String tableName = "AFRIKAANSPHRASES";
                    if ("Tagalog Phrases".equals(selectedOption)) {
                        tableName = "TAGALOGPHRASES";
                    }
                    dbInit.addPhrase(phrase, translation, tableName); //Add phrase and translation into database
                    JOptionPane.showMessageDialog(this, "Phrase and translation submitted successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter both the phrase and translation.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } 
            if ("South-African Fact".equals(selectedOption) || "Philippines Fact".equals(selectedOption)) {
                String fact = inputTextField1.getText().trim();
                if("South-African Fact".equals(selectedOption)){
                     dbInit.addFact(fact,""); //adds Afrikaan fact
                }else{
                    dbInit.addFact("", fact); //Adds Tagalog fact
                }
            }
        });
    }
    @Override //Method intentionally left empty/ No use for updating data on admin panel yet, could be for future purposes.
    public void onUpdated(Data data) {
    
    }
}
