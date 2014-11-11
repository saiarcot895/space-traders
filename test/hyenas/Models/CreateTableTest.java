package hyenas.Models;

import hyenas.database.ConnectionManager;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test to see if the tables are created on start.
 * @author Abhishek Deo
 */
public class CreateTableTest {
    
    /**
     * The Connection to the database.
     */
    ConnectionManager conn;
    
    /**
     * Database server host.
     */
    String host = "jdbc:sqlite:database.db";
    
    /**
     * Check to see if the SolarSystemTable is created
     * based on the SQL query and database server.
     */
    @Test
    public void testCreateSolarSystemTable() {
        conn = new ConnectionManager(host);
        /* Create the table */
        /* Assert to check if they were made */
        if (conn != null) {
            boolean created;
            conn.openSolarSystemConnection();
            created = conn.getSolarSystemTable().createTableTest();
            Assert.assertTrue(created);
            if (!created) {
                Assert.fail("SolarSystem was not created.");
            } else {
                System.out.println("SolarSystem created.");
            }
        } else {
            Assert.fail("Connection failed.");
        }
    }
    
    /**
     * Check to see if the GadgetsTable is created
     * based on the SQL query and database server.
     */
    @Test
    public void testCreateGadgetsTable() {
        conn = new ConnectionManager(host);
        /* Create the table */
        /* Assert to check if they were made */
        if (conn != null) {
            boolean created;
            conn.openGadgetsConnection();
            created = conn.getGadgetsTable().createTableTest();
            Assert.assertTrue(created);
            if (!created) {
                Assert.fail("GadgetsTable was not created.");
            } else {
                System.out.println("GadgetsTable created.");
            }
        } else {
            Assert.fail("Connection failed.");
        }
    }
    
    /**
     * Check to see if the ItemsTable is created
     * based on the SQL query and database server.
     */
    @Test
    public void testCreateItemsTable() {
        conn = new ConnectionManager(host);
        /* Create the table */
        /* Assert to check if they were made */
        if (conn != null) {
            boolean created;
            conn.openItemConnection();
            created = conn.getItemsTable().createTableTest();
            Assert.assertTrue(created);
            if (!created) {
                Assert.fail("ItemsTable was not created.");
            } else {
                System.out.println("ItemsTable created.");
            }
        } else {
            Assert.fail("Connection failed.");
        }
    }
    
    /**
     * Check to see if the PlanetTable is created
     * based on the SQL query and database server.
     */
    @Test
    public void testCreatePlanetTable() {
        conn = new ConnectionManager(host);
        /* Create the table */
        /* Assert to check if they were made */
        if (conn != null) {
            boolean created;
            conn.openPlanetConnection();
            created = conn.getPlanetTable().createTableTest();
            Assert.assertTrue(created);
            if (!created) {
                Assert.fail("PlanetTable was not created.");
            } else {
                System.out.println("PlanetTable created.");
            }
        } else {
            Assert.fail("Connection failed.");
        }
    }
    
    /**
     * Check to see if the PlayerTable is created
     * based on the SQL query and database server.
     */
    @Test
    public void testCreatePlayerTable() {
        conn = new ConnectionManager(host);
        /* Create the table */
        /* Assert to check if they were made */
        if (conn != null) {
            boolean created;
            conn.openPlayerConnection();
            created = conn.getPlayerTable().createTableTest();
            Assert.assertTrue(created);
            if (!created) {
                Assert.fail("PlayerTable was not created.");
            } else {
                System.out.println("PlayerTable created.");
            }
        } else {
            Assert.fail("Connection failed.");
        }
    }
    
    /**
     * Check to see if the ShipTable is created
     * based on the SQL query and database server.
     */
    @Test
    public void testCreateShipTable() {
        conn = new ConnectionManager(host);
        /* Create the table */
        /* Assert to check if they were made */
        if (conn != null) {
            boolean created;
            conn.openShipConnection();
            created = conn.getShipTable().createTableTest();
            Assert.assertTrue(created);
            if (!created) {
                Assert.fail("ShipTable was not created.");
            } else {
                System.out.println("ShipTable created.");
            }
        } else {
            Assert.fail("Connection failed.");
        }
    }
    
    /**
     * Check to see if the WeaponsTable is created
     * based on the SQL query and database server.
     */
    @Test
    public void testCreateWeaponsTable() {
        conn = new ConnectionManager(host);
        /* Create the table */
        /* Assert to check if they were made */
        if (conn != null) {
            boolean created;
            conn.openWeaponsConnection();
            created = conn.getWeaponsTable().createTableTest();
            Assert.assertTrue(created);
            if (!created) {
                Assert.fail("WeaponsTable was not created.");
            } else {
                System.out.println("WeaponsTable created.");
            }
        } else {
            Assert.fail("Connection failed.");
        }
    }
}
