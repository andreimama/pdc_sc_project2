/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package languagelearner;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author mamar
 */
public class AdminPanelTest {
    //Instances of mainFrame and adminPanel
    private static MainFrame mainFrame;
    private static AdminPanel adminPanel;
    public AdminPanelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        mainFrame = new MainFrame();
        adminPanel = new AdminPanel(mainFrame);
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createNavPanel method, of class AdminPanel.
     */
    @Test
    public void testCreateNavPanel() {
        System.out.println("Testing createNavPanel");
        JPanel result = adminPanel.createNavPanel(); //creates nav panel
        assertNotNull(result); //If its not null nav panel has been created
 
    
    }

    /**
     * Test of createContentPanel method, of class AdminPanel.
     */
   @Test
    public void testCreateContentPanel() {
        System.out.println("Testing createContentPanel");
        boolean lang = false; //Set the language flag for the test
        JPanel result = adminPanel.createContentPanel(lang); //Create content panel
        assertNotNull(result); 
    }
    
    /**
     * Test of addActionListener method, of class AdminPanel. and tests if button pressed
     */
    @Test
    public void testButtonAddActionListener() {
        System.out.println("Testing addActionListener");
        
    //flag to see if listener was triggered
        final boolean[] wasClicked = {false};
        
        ActionListener listener = e -> {
            System.out.println("Button clicked"); //Create a test ActionListener
            wasClicked[0] = true; //GPT was used here to verify addactionlistener
        };
        adminPanel.addActionListener(listener); //Add ActionListener to AdminPanel

        //Simulate button click and verify the output or state change
        JButton menuButton = adminPanel.menuButton;
        assertNotNull(menuButton); //Ensure the button is not null
        
        menuButton.setActionCommand("Menu Panel");
        menuButton.doClick();
        
        //Checks if the flag was set true meaning listener was added and triggered prints error if was not clicked
         if (!wasClicked[0]) {
           throw new AssertionError("The ActionListener should be triggered when the button is clicked.");
     
        }
        
    }
        

        


}
