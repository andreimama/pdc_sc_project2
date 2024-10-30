/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package languagelearner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

/**
 *
 * @author franc
 */
public class Controller implements ActionListener {
    private MenuPanel menuPanel;
    private EasyPanel easyPanel;
    private LearnPanel learnPanel;
    private Model model;

    public Controller(MenuPanel menuPanel, EasyPanel easyPanel, LearnPanel learnPanel, Model model) {
        this.menuPanel = menuPanel;
        this.easyPanel = easyPanel;
        this.learnPanel = learnPanel;
        this.model = model;
        
        this.menuPanel.addActionListener(this);
        this.easyPanel.addActionListener(this);
        this.learnPanel.addActionListener(this);
        this.model.addListener(learnPanel);
        this.model.addListener(easyPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Button clicked!");
        if (e.getSource() instanceof JComboBox) {
        String command = (String) ((JComboBox<?>) e.getSource()).getSelectedItem();
        if ("Tagalog".equals(command)) {
            setLanguageFlag(false);
            System.out.println("Current Language: " + model.getCurrentLanguage());
        } else if ("Afrikaans".equals(command)) {
            setLanguageFlag(true);
            System.out.println("Current Language: " + model.getCurrentLanguage());
        }
    } else {
        String command = e.getActionCommand();
        System.out.println("Action:" + command);
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
           /* case "Tagalog":
                setLanguageFlag(false);
                System.out.println(model.getCurrentLanguage());
                break;
            case "Afrikaans":
                setLanguageFlag(true);
                System.out.println(model.getCurrentLanguage());
                break; 8*/
                /* case "Quit":
                model.quitGame();
                break; */
            default:
                
                break;
        }
    }
    }

    public void setLanguageFlag(boolean lang) {
        model.updateLanguageFlag(lang);

    }
  
}


