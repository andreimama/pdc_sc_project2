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

/**
 *
 * @author franc
 */

//Class that also extends BaseGUI. Game panel that tests language knowledge.
public class EasyPanel extends BaseGUI implements PanelListener {
    //GUI Components
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
    private static JLabel counterLabel;
    private String[] phrases;
    private MenuPanel menu = new MenuPanel(mainFrame);
    
    //Constructor that initalizes easy panel with main frame
    public EasyPanel(MainFrame mainFrame) {
        super(mainFrame);
    }
    
    // Creates main content for Easy Panel
    @Override
    protected JPanel createContentPanel(boolean lang) {
        model = new Model();
        JPanel panel = new JPanel(new BorderLayout());
        rand = new Randomizer();
        model.updateLanguageFlag(lang);

        //Creates North,Center, and South poanels using methods below.
        JPanel northPanel = initializeNorthPanel(lang);
        JPanel centerPanel = initializeCenterPanel(lang);
        JPanel southPanel = initializeSouthPanel(lang);
        
        //Adding panels to content Panel.
        panel.add(northPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(southPanel, BorderLayout.SOUTH);

        return panel;
    }
    //Initalises the North panel with specified language.
    private JPanel initializeNorthPanel(boolean lang) {
        JPanel northPanel = new JPanel();
        northLabel = new JLabel(lang ? "Afrikaans" : "Tagalog"); //Sets label based on language
        northPanel.add(northLabel, BorderLayout.NORTH);
        return northPanel;
    }
    //Initalises the Center panel with specified language.
    private JPanel initializeCenterPanel(boolean lang) {
        JPanel centerPanel = new JPanel();
        phrase = lang ? rand.randomPhrase("afrPhrase") : rand.randomPhrase("tagPhrase"); //Gets a random phrase from database
        centerLabel = new JLabel(phrase); //Sets Phrase
        centerPanel.add(centerLabel, BorderLayout.CENTER);
        return centerPanel;
    }
    //Initalises the South panel with specified language.
    private JPanel initializeSouthPanel(boolean lang) {
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        
        //Gets and shuffles phrases
        phrases = model.fourPhrases(phrase, lang);
        rand.shuffleArray(phrases);
        
        //Create buttons and add to panel
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
    
    //Creates Navigation panel for EasyPanel class
    @Override
    protected JPanel createNavPanel() {
        JPanel navPanel = new JPanel();
        model = new Model();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.add(Box.createVerticalStrut(30));

        easyPanelButton = createNavButton("Learn Phrases", "Learn Panel"); //Nav Button to Learn Panel

        navPanel.add(easyPanelButton);
        navPanel.add(Box.createVerticalStrut(15)); //Add button to navPanel

        menuPanelButton = createNavButton("Back to Main Menu", "Menu Panel"); //Nav Button to Menu Panel

        navPanel.add(menuPanelButton);
        navPanel.add(Box.createVerticalStrut(15)); //Add button to navPanel
        
        counterLabel = createLabel("Score: " + model.getScore()); //Label that records score on nav Panel
        counterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        navPanel.add(counterLabel);
        
        //Adds Fun fact, gets random fact from database
        funFact = createTextArea("Fun Fact: " + model.randomFact());
        funFact.setAlignmentX(Component.CENTER_ALIGNMENT);
        navPanel.add(funFact);

        return navPanel;
    }
    //Handles button presses and updates game state
    protected static void buttonPressed(String buttonName) {
        if (model.checkAnswer(phrase, buttonName, lang)) {
            //If answer is right, score is upgraded and phrases are set and changed
            model.incrementScore();
            
            phrase = (lang ? rand.randomPhrase("afrPhrase") : rand.randomPhrase("tagPhrase"));
            centerLabel.setText(phrase);
        } else {
            //If answer is wrong, doesnt increment score but changes phrases
            phrase = (lang ? rand.randomPhrase("afrPhrase") : rand.randomPhrase("tagPhrase"));
            centerLabel.setText(phrase);
        }
        refreshPanel(); //Refreshes UI
    }
    //Refreshes UI components to show updated game staet
    public static void refreshPanel() {
        String[] newPhrases = model.fourPhrases(phrase, lang); 

        northLabel.setText(lang ? "Afrikaans" : "Tagalog"); //Updates Language label if switched languages
        counterLabel.setText("Score: " + model.getScore()); //Updates Score Label in nav Panel

        //Updates Buttons and actions of buttons
        button1.setText(newPhrases[0]);
        button2.setText(newPhrases[1]);
        button3.setText(newPhrases[2]);
        button4.setText(newPhrases[3]);

        applyButtonAction(button1, newPhrases[0]);
        applyButtonAction(button2, newPhrases[1]);
        applyButtonAction(button3, newPhrases[2]);
        applyButtonAction(button4, newPhrases[3]);
    }
    
    //Action listeners to buttons
    public void addActionListener(ActionListener listener) {
        easyPanelButton.addActionListener(listener);
        menuPanelButton.addActionListener(listener);

    }
    //Updates UI Components
    private void updateComponents() {
        updateCentralLabel();
        refreshPanel();
    }
    //Updates Central label wioth a new phrase
    private void updateCentralLabel() {
        phrase = lang ? rand.randomPhrase("afrPhrase") : rand.randomPhrase("tagPhrase");
        if (centerLabel != null) {
            centerLabel.setText(phrase);
        }

    }
    //Puts action to button, removes it first before adding an ew one.
    protected static void applyButtonAction(JButton button, String buttonName) {
        for (ActionListener al : button.getActionListeners()) {
            button.removeActionListener(al);
        }
        button.addActionListener(e -> EasyPanel.buttonPressed(buttonName));
    }
    @Override
    public void onUpdated(Data data) {
        //updates lang based on lang from model and PanelListener
        lang = data.lang;
        updateComponents(); //calls the method from above to update the components of the UI
    }
    
   
}
