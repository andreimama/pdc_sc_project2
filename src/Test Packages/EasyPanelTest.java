package languagelearner;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class EasyPanelTest {
    //Instances for  testing
    private static MainFrame mainFrame;
    private static EasyPanel easyPanel;

    public EasyPanelTest() {}

    @BeforeClass
    public static void setUpClass() {
        //Initalizes MainFrame & EasyPanel before the tests are run
        mainFrame = new MainFrame();
        easyPanel = new EasyPanel(mainFrame);
    }

    @AfterClass
    public static void tearDownClass() {
        // Add any cleanup code if necessary
    }

    @Before
    public void setUp() {
        // Add any per-test setup code if necessary
    }

    @After
    public void tearDown() {
        // Add any per-test teardown code if necessary
    }
    
    
    /**
     * Test create ContentPanel(), its depended on what the lang is
     */
    @Test
    public void testCreateContentPanel() {
        System.out.println("Testing createContentPanel");
        boolean lang = false;
        JPanel result = easyPanel.createContentPanel(lang);
        assertNotNull(result); //Checks result is not null
    }
    
    /**
     * Test of createNavPanel method.
     */
    @Test
    public void testCreateNavPanel() {
        System.out.println("Testing createNavPanel");
        JPanel result = easyPanel.createNavPanel();
        assertNotNull(result); //Checks result is not null, and navPanel is created
    }
    
    /**
     * Tests if refreshPanel() method changes the content
     */ 
    @Test
    public void testRefreshPanel() {
        System.out.println("Testing refreshPanel");

        //Sets initial conditions for the test
        String initialPhrase = "Test Phrase";
        EasyPanelTestUtils.setPhrase(initialPhrase);
        EasyPanelTestUtils.setLang(true);
        EasyPanel.model.updateLanguageFlag(true);
        
        //Simulates refreshing the panel
        EasyPanel.refreshPanel();

        //Verify that the labels and buttons are updated
        assertNotNull(EasyPanelTestUtils.getNorthLabel());
        assertEquals("Afrikaans", EasyPanelTestUtils.getNorthLabel().getText());
        assertNotNull(EasyPanelTestUtils.getButton1());
        assertNotEquals(initialPhrase, EasyPanelTestUtils.getButton1().getText()); //checks if button1 becomes initialPhrase
        assertNotNull(EasyPanelTestUtils.getCounterLabel());
        assertEquals("Score: " + EasyPanel.model.getScore(), EasyPanelTestUtils.getCounterLabel().getText());
    }

    /**
     * Test of onUpdated method
     */
    @Test
    public void testOnUpdated() {
        System.out.println("Testing onUpdate");
        Data data = new Data();
        easyPanel.onUpdated(data);
       
        //Verifys if the counter label updates correctly
        JLabel counterLabel = EasyPanelTestUtils.getCounterLabel();
        assertNotNull(counterLabel);
        assertEquals("Score: " + EasyPanel.model.getScore(), EasyPanelTestUtils.getCounterLabel().getText());
    }
    
}
