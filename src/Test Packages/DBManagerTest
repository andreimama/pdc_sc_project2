package languagelearner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DBManagerTest {
    private DBManager dbManager;

    @Before
    public void setUp() {
        dbManager = new DBManager();
        dbManager.establishConnection();
        
        //Makes sure a clean state for tests, drops Test table if it exists 
        if (tableExists("TestTable")) {
            dbManager.updateDB("DROP TABLE TestTable");
        }
        //Creates a new TestTable
        dbManager.updateDB("CREATE TABLE TestTable (ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, phrase VARCHAR(255))");
    }

    @After
    public void tearDown() throws SQLException {
        //checkls connection is open before checking table existence. runs after each test to clean up test environment
        if (dbManager.getConnection() != null && !dbManager.getConnection().isClosed()) {
            if (tableExists("TestTable")) {
                dbManager.updateDB("DROP TABLE TestTable");
            }
            dbManager.closeConnections();
        }
    }
    
    /**
     * Test to verify connection method
     */
    @Test
    public void testGetConnection() {
        System.out.println("Testing getConnection");
        Connection result = dbManager.getConnection();
        assertNotNull("Connection should be established", result);
    }
    
    /**
     * Test to verify establishConenection method
     */
    @Test
    public void testEstablishConnection() {
        System.out.println("Testing establishConnection");
        dbManager.establishConnection();
        Connection result = dbManager.getConnection();
        assertNotNull("Connection should be established", result);
    }
    
    
    /**
     * Test to verify closeConnections method
     */
   
    @Test
    public void testCloseConnections() throws SQLException {
        System.out.println("Testing closeConnections");
        dbManager.closeConnections();
        Connection result = dbManager.getConnection();
        assertTrue("Connection should be closed", result == null || result.isClosed());
    }

    /**
     * Test to verify updateDB method
     */
    @Test
    public void testUpdateDB() {
        System.out.println("Testing updateDB");
        String insertSQL = "INSERT INTO TestTable (phrase) VALUES ('TestPhrase')";
        dbManager.updateDB(insertSQL);

        ResultSet rs = dbManager.getFromDB("SELECT phrase FROM TestTable WHERE phrase = 'TestPhrase'");
        try {
            assertTrue("ResultSet should contain a result", rs.next());
            assertEquals("TestPhrase", rs.getString("phrase"));
        } catch (SQLException e) {
            fail("SQLException was thrown: " + e.getMessage());
        }
    }
    
    /**
     * Test to verify getFromDB method
     */
    
    @Test
    public void testGetFromDB() {
        System.out.println("Testing getFromDB");
        String insertSQL = "INSERT INTO TestTable (phrase) VALUES ('TestPhrase')";
        dbManager.updateDB(insertSQL);

        ResultSet rs = dbManager.getFromDB("SELECT phrase FROM TestTable WHERE phrase = 'TestPhrase'");
        try {
            assertTrue("ResultSet should contain a result", rs.next());
            assertEquals("TestPhrase", rs.getString("phrase"));
        } catch (SQLException e) {
            fail("SQLException was thrown: " + e.getMessage());
        }
    }
    
    /**
     * Test to verify getAllPhrase() method.
     */
    @Test
    public void testGetAllPhrases() {
        System.out.println("Testing getAllPhrases");
        dbManager.updateDB("INSERT INTO TestTable (phrase) VALUES ('Phrase1')");
        dbManager.updateDB("INSERT INTO TestTable (phrase) VALUES ('Phrase2')");

        String[] result = dbManager.getAllPhrases("TestTable", "phrase");
        String[] expResult = {"Phrase1", "Phrase2"};
        assertArrayEquals("Phrases should match", expResult, result);
    }

    /**
     * Helper method to see if table exists
     */
    private boolean tableExists(String tableName) {
        try (ResultSet rs = dbManager.getFromDB("SELECT 1 FROM SYS.SYSTABLES WHERE TABLENAME = '" + tableName.toUpperCase() + "'")) {
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }
}
