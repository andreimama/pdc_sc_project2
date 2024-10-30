/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package languagelearner;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author mamar
 */
public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private MenuPanel menuPanel;
    private EasyPanel easyPanel;
    private Model model;
    private LearnPanel learnPanel;
    private Controller controller;
    
    
    public MainFrame() {
        
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        
        model = new Model();
        easyPanel = new EasyPanel(this);
        menuPanel = new MenuPanel(this);
        learnPanel = new LearnPanel(this);
        
        
        mainPanel.add(menuPanel, "Menu Panel");
        mainPanel.add(easyPanel, "Easy Panel");
        mainPanel.add(learnPanel, "Learn Panel");
        
        
         controller = new Controller(menuPanel, easyPanel, learnPanel, model);
       setSize(1000,400);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setLocationRelativeTo(null);
       setVisible(true);
        
        
        add(mainPanel,BorderLayout.CENTER);
        
        
        
    }
    
    public void switchTo(String card) {
        cardLayout.show(mainPanel, card);
    }
    
}

