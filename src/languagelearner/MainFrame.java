/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package languagelearner;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author mamar
 */
//Main window of the Program/App, manages the layout and navigation between different panels using CarDlayout
public class MainFrame extends JFrame {

    //private fields
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private MenuPanel menuPanel;
    private EasyPanel easyPanel;
    private Model model;
    private LearnPanel learnPanel;
    private Controller controller;
    private AdminPanel admin;

    //Constructor
    public MainFrame() {

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        //Initializes model and panel components
        model = new Model();
        easyPanel = new EasyPanel(this);
        menuPanel = new MenuPanel(this);
        learnPanel = new LearnPanel(this);
        admin = new AdminPanel(this);
        
        //Adds apnels to the main Panel. Second field is to identify name of panel to switch to
        mainPanel.add(menuPanel, "Menu Panel");
        mainPanel.add(easyPanel, "Easy Panel");
        mainPanel.add(learnPanel, "Learn Panel");
        mainPanel.add(admin, "Admin Panel");
        
        
        controller = new Controller(menuPanel, easyPanel, learnPanel, model, admin);
        //Setting up of main properties of the frame
        setSize(1000, 400); //Size of Frame
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Exits when exited not using quit button
        setLocationRelativeTo(null); //Makes the frane the center of screeb
        setVisible(true);//Makes frame visible

        add(mainPanel, BorderLayout.CENTER); //Adds the main panel to frame
    }

    //method to switch between panels
    public void switchTo(String card) {
        //Checks if combo box is empty before switching Panel.
        if(menuPanel.comboBox.getSelectedItem().equals("") && card.equals("Admin Panel")){
            cardLayout.show(mainPanel, card);
        }else if(menuPanel.comboBox.getSelectedItem().equals("") && card.equals("Menu Panel")){
            cardLayout.show(mainPanel, card);
        }
        else if (!menuPanel.comboBox.getSelectedItem().equals("")) {
            cardLayout.show(mainPanel, card);
        }else{
            //If no language is selected
            JOptionPane.showMessageDialog(this, "You have to first select which language you would like to learn!");
        }

    }
    
    //Returns controller instance
    public Controller getController() {
        return this.controller;
    }
}
