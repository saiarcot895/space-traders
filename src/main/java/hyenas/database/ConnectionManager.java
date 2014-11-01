package hyenas.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages connection to database.
 * @author saikrishna
 */
public class ConnectionManager {
    private Connection conn;

    private final String host;

    private PlayerTable playerTable;
    private PlanetTable planetTable;
    private ItemsTable itemTable;
    private SolarSystemTable solarSystemTable;
    private ShipTable shipTable;
    private GadgetsTable gadgetsTable;
    private WeaponsTable weaponsTable;
    
    /**
     * Initializes a connection manager.
     * @param host the host
     */
    public ConnectionManager(String host) {
        this.host = host;
    }
    
    /**
     * Opens connection to the database.
     */
    public void openConnection() {
        try {
            conn = DriverManager.getConnection(host);
            conn.createStatement().execute("PRAGMA foreign_keys = ON");
            
            playerTable = new PlayerTable(conn);
            planetTable = new PlanetTable(conn);
            itemTable = new ItemsTable(conn);
            solarSystemTable = new SolarSystemTable(conn);
            shipTable = new ShipTable(conn);
            gadgetsTable = new GadgetsTable(conn);
            weaponsTable = new WeaponsTable(conn);
        } catch (SQLException e) {
            Logger.getLogger(ConnectionManager.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }

    /**
     * Closes connection to the database.
     */
    @SuppressWarnings("UnusedAssignment")
    public void closeConnection() {
        try {
            conn.close();
            
            playerTable = null;
            planetTable = null;
            itemTable = null;
            solarSystemTable = null;
            shipTable = null;
            gadgetsTable = null;
            weaponsTable = null;
        } catch (SQLException e) {
            Logger.getLogger(ConnectionManager.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }
    
    /**
     * Begins a transaction with the database.
     */
    public void beginTransaction() {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("BEGIN TRANSACTION");
        } catch (SQLException e) {
            Logger.getLogger(ConnectionManager.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }
    
    /**
     * Commits a transaction to the database.
     */
    public void commitTransaction() {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("COMMIT TRANSACTION");
        } catch (SQLException e) {
            Logger.getLogger(ConnectionManager.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }
    
    /**
     * Get the solar system table.
     * @return the solar system table
     */
    public SolarSystemTable getSolarSystemTable() {
        return solarSystemTable;
    }

    /**
     * Get the planet table.
     * @return the planet table
     */
    public PlanetTable getPlanetTable() {
        return planetTable;
    }

    /**
     * Get the player table.
     * @return the player table
     */
    public PlayerTable getPlayerTable() {
        return playerTable;
    }

    /**
     * Get the item table.
     * @return the item table
     */
    public ItemsTable getItemTable() {
        return itemTable;
    }

    /**
     * Get the ship table.
     * @return the ship table
     */
    public ShipTable getShipTable() {
        return shipTable;
    }

    /**
     * Get the gadget table.
     * @return the gadget table
     */
    public GadgetsTable getGadgetsTable() {
        return gadgetsTable;
    }

    /**
     * Get the weapon table.
     * @return the weapons table
     */
    public WeaponsTable getWeaponsTable() {
        return weaponsTable;
    }
}
