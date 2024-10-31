package languagelearner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author franc
 */
public class AdminPanel extends BaseGUI implements PanelListener {

    JButton menuButton;
    JButton submitButton;
    JComboBox<String> comboBox;
    private JPanel contentPanel;
    private JLabel contentLabel;
    JTextField inputTextField1;
    JTextField inputTextField2;
    FileIO file = new FileIO();
    DBInitializer dbInit = new DBInitializer();

    private static final String pathAfr = "PhrasesAfr.txt";

    public AdminPanel(MainFrame mainFrame) {
        super(mainFrame);
        addActionListenerToSubmit();
    }

    @Override//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected JPanel createNavPanel() {
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));

        // Create ComboBox
        String[] options = {"", "Afrikaans Phrases", "Tagalog Phrases", "South-African Fact", "Philippines Fact"};
        comboBox = createComboBox(options);
        comboBox.addActionListener(e -> updateContentBasedOnSelection());
        navPanel.add(comboBox);

        // Create Buttons
        menuButton = createNavButton("Menu", "Menu Panel");
        submitButton = createButton("Submit");

        navPanel.add(menuButton);
        navPanel.add(submitButton);

        return navPanel;
    }

    private void updateContentBasedOnSelection() {
        contentPanel.removeAll();
        String selectedOption = (String) comboBox.getSelectedItem();

        if ("Afrikaans Phrases".equals(selectedOption)) {
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
            JLabel instructionLabel = new JLabel("Please enter a Akrikaans phrase in the first text box and the English Translation in the second: ");
            instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            inputTextField1 = new JTextField();
            inputTextField1.setPreferredSize(new Dimension(200, 30));
            inputTextField2 = new JTextField();
            inputTextField2.setPreferredSize(new Dimension(200, 30));
            inputPanel.add(instructionLabel);
            inputPanel.add(Box.createVerticalStrut(10));
            inputPanel.add(inputTextField1);
            inputPanel.add(Box.createVerticalStrut(5));
            inputPanel.add(inputTextField2);
            contentPanel.add(inputPanel, BorderLayout.CENTER);
        }
        if ("Tagalog Phrases".equals(selectedOption)) {
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
            JLabel instructionLabel = new JLabel("Please enter a Tagalog phrase in the first text box and the English Translation in the second: ");
            instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            inputTextField1 = new JTextField();
            inputTextField1.setPreferredSize(new Dimension(200, 30));
            inputTextField2 = new JTextField();
            inputTextField2.setPreferredSize(new Dimension(200, 30));
            inputPanel.add(instructionLabel);
            inputPanel.add(Box.createVerticalStrut(10));
            inputPanel.add(inputTextField1);
            inputPanel.add(Box.createVerticalStrut(5));
            inputPanel.add(inputTextField2);
            contentPanel.add(inputPanel, BorderLayout.CENTER);
        }
        if ("Philippines Fact".equals(selectedOption)) {
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
            JLabel instructionLabel = new JLabel("Please enter a Tagalog fact in the first text box and press submit in the right hand panel.");
            instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            inputTextField1 = new JTextField();
            inputTextField1.setPreferredSize(new Dimension(200, 30));
            inputPanel.add(instructionLabel);
            inputPanel.add(inputTextField1);
            contentPanel.add(inputPanel, BorderLayout.CENTER);
        }
        
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    @Override/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected JPanel createContentPanel(boolean lang) {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        JLabel instructionLabel = new JLabel("Please select what table to add to database.");
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentLabel = new JLabel(lang ? "English Content" : "Other Language Content");
        contentPanel.add(instructionLabel);
        contentPanel.add(contentLabel, BorderLayout.CENTER);

        return contentPanel;
    }

    public void addActionListener(ActionListener listener) {
        menuButton.addActionListener(listener);
        submitButton.addActionListener(listener);
    }

    private void addActionListenerToSubmit() {
        submitButton.addActionListener(e -> {
            String selectedOption = (String) comboBox.getSelectedItem();

            // Check if the selected option is either Afrikaans or Tagalog Phrases
            if ("Afrikaans Phrases".equals(selectedOption) || "Tagalog Phrases".equals(selectedOption)) {
                String phrase = inputTextField1.getText().trim();
                String translation = inputTextField2.getText().trim();

                // Check if both fields are not empty
                if (!phrase.isEmpty() && !translation.isEmpty()) {
                    String tableName = "AFRIKAANSPHRASES";
                    if ("Tagalog Phrases".equals(selectedOption)) {
                        tableName = "TAGALOGPHRASES";
                    }
                    dbInit.addPhrase(phrase,translation,"AFRIKAANSPHRASES");
                    JOptionPane.showMessageDialog(this, "Phrase and translation submitted successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter both the phrase and translation.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a valid option from the dropdown.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    @Override
    public void onUpdated(Data data) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
