/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package languagelearner;

import java.awt.event.ActionListener;
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
public class MenuPanelTest {
    private MainFrame mainFrame;
    private MenuPanel menuPanel;
    
    public MenuPanelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        mainFrame = new MainFrame();
        menuPanel = new MenuPanel(mainFrame);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createContentPanel method, of class MenuPanel.
     */
    @Test
    public void testCreateContentPanel() {
        System.out.println("createContentPanel");
        boolean lang = false;
        JPanel result = menuPanel.createContentPanel(lang);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of createNavPanel method, of class MenuPanel.
     */
    @Test
    public void testCreateNavPanel() {
        System.out.println("Testing createNavPanel");
        JPanel result = menuPanel.createNavPanel();
        assertNotNull(result);
 
    }
   

    /**
     * Test of addActionListener method, of class MenuPanel.
     */
    @Test
    public void testAddActionListener() {
        System.out.println("Testing addActionListener");
        ActionListener listener = null;
        MenuPanel instance = null;
        instance.addActionListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
   
}


