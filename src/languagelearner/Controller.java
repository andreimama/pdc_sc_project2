package languagelearner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

/**
 *
 * @author mamar
 * 
 * **/
//Controller class managues user interactions and updates view and model in MVC
public class Controller implements ActionListener {

    //Fields to store references to view and model
    private MenuPanel menuPanel;
    private EasyPanel easyPanel;
    private LearnPanel learnPanel;
    private Model model;
    private AdminPanel admin;

    //Constructor
    public Controller(MenuPanel menuPanel, EasyPanel easyPanel, LearnPanel learnPanel, Model model, AdminPanel admin) {
        this.menuPanel = menuPanel;
        this.easyPanel = easyPanel;
        this.learnPanel = learnPanel;
        this.model = model;
        this.admin = admin;
        
        //Adds ActionListeners to the view components
        this.menuPanel.addActionListener(this);
        this.easyPanel.addActionListener(this);
        this.learnPanel.addActionListener(this);
        this.admin.addActionListener(this);
        this.model.addListener(learnPanel);
        this.model.addListener(easyPanel);
        this.model.addListener(admin);
    }
    
    //Handles events generated from the user interactions
    @Override
    public void actionPerformed(ActionEvent e) {
        //If event source is Combobox then it sets language flag to either false(Tagalog) or true(Afrikaans)
        if (e.getSource() instanceof JComboBox) {
            String command = (String) ((JComboBox<?>) e.getSource()).getSelectedItem();
            if ("Tagalog".equals(command)) {
                setLanguageFlag(false); //Sets to Tagalog
                
            } else if ("Afrikaans".equals(command)) {
                setLanguageFlag(true); //Sets to Afrikaans
                
            }
        } else {
            //If event source is button clicks, and switch views based on Action command
            String command = e.getActionCommand();
            
            switch (command) {
                case "Easy Panel":
                    menuPanel.getMainFrame().switchTo("Easy Panel");
                    break;
                case "Menu Panel":
                    easyPanel.getMainFrame().switchTo("Menu Panel");

                    break;
                case "Learn Panel":
                    learnPanel.getMainFrame().switchTo("Learn Panel");
                    break;
                case "Admin Panel":
                    admin.getMainFrame().switchTo("Admin Panel");
                default:

                    break;
            }
        }
    }
    //Sets language flag in model and updates it,
    public void setLanguageFlag(boolean lang) {
        model.updateLanguageFlag(lang);
    }
}
