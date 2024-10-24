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
    private LearnPanel learnPanel;
    
    
    public MainFrame() {
        setSize(600,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        learnPanel = new LearnPanel(this);
        mainPanel.add(new MenuPanel(this, learnPanel), "Menu Panel");
        mainPanel.add(new EasyPanel(this), "Easy Panel");
        mainPanel.add(new LearnPanel(this), "Learn Panel");
        
        add(mainPanel,BorderLayout.CENTER);
        setVisible(true);
        
        
    }
    protected void refreshPanel() {
        revalidate(); 
        repaint();
    }
    
    public void switchTo(String card) {
        cardLayout.show(mainPanel, card);
        refreshPanel();
    }
    
   
    
}
