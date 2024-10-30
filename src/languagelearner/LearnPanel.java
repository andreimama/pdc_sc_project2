/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package languagelearner;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author mamar
 */
public class LearnPanel extends BaseGUI implements PanelListener {

    private JButton easyPanelButton;
    private JButton menuPanelButton;
    private JLabel northLabel;
    private JScrollPane textAreaPane;
    JScrollPane funFact;
    private Randomizer rand;
    private DBManager dbManager;
    private Model model;
    public static JLabel counterLabel;

    public LearnPanel(MainFrame mainFrame) {
        super(mainFrame);
        

    }

    @Override
    protected JPanel createContentPanel(boolean lang) {
        JPanel panel = new JPanel(new BorderLayout());
        rand = new Randomizer();
        dbManager = new DBManager();
        model = new Model();
        
        
        JPanel northPanel = new JPanel();
        northLabel = createLabel("Afrikaans");
        northPanel.add(northLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        textAreaPane = createTextArea("texttexttext\ntextxtextxteteexttexxtexte\ntexxtetextxetxetxetxtxetxetetxtetete");
        centerPanel.add(textAreaPane, BorderLayout.CENTER);

        panel.add(northPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);

        return panel;

    }

    @Override
    protected JPanel createNavPanel() {
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.add(Box.createVerticalStrut(30));

        easyPanelButton = createNavButton("Button 2 to Easy Test", "Easy Panel");
        //easyPanelButton.setActionCommand("Easy Panel");
        navPanel.add(easyPanelButton);
        navPanel.add(Box.createVerticalStrut(15));

        menuPanelButton = createNavButton("Button 1 to Go to Menu", "Menu Panel");
        //menuPanelButton.setActionCommand("Menu Panel");
        navPanel.add(menuPanelButton);
        navPanel.add(Box.createVerticalStrut(15));
        
        
        counterLabel = createLabel("Score: " + EasyPanel.model.getScore());
        
        counterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        navPanel.add(counterLabel);
        //counter for score

        funFact = createTextArea("Fun Fact: ");
        funFact.setAlignmentX(Component.CENTER_ALIGNMENT);
        navPanel.add(funFact);
        //fun fact will be here, a bit unsure to connect datababse and randomier for this.
        return navPanel;

    }

    public void addActionListener(ActionListener listener) {
        easyPanelButton.addActionListener(listener);
        menuPanelButton.addActionListener(listener);
    }
    
    public void updateCounterLabel(int currentScore) {
        if (counterLabel != null) {
            counterLabel.setText("Score: " + currentScore);
        }
    }
    @Override
    public void onUpdated(Data data) {
        if (northLabel != null) {
            northLabel.setText(data.lang ? "Afrikaans" : "Tagalog");
        }
        if(textAreaPane != null){
            JTextArea textArea = (JTextArea) textAreaPane.getViewport().getView();
            textArea.setText(model.getAllFacts(data.lang));
        }
        if(funFact != null){
            JTextArea funfact = (JTextArea) funFact.getViewport().getView();
            funfact.setText("Fun fact: " + model.randomFact());
        }
        
        updateCounterLabel(EasyPanel.model.getScore());
        System.out.println("it reaches the end of learn panel on upDted");
        System.out.println("score: " + EasyPanel.model.getScore());
    }

}

