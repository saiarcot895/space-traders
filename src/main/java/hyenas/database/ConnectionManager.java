/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hyenas.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.sqlite.JDBC;

/**
 *
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
    
    public ConnectionManager(String host) {
        this.host = host;
    }
    
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
    
    public void beginTransaction() {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("BEGIN TRANSACTION");
        } catch (SQLException e) {
            Logger.getLogger(ConnectionManager.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }
    
    public void commitTransaction() {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("COMMIT TRANSACTION");
        } catch (SQLException e) {
            Logger.getLogger(ConnectionManager.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }
    
    public SolarSystemTable getSolarSystemTable() {
        return solarSystemTable;
    }

    public PlanetTable getPlanetTable() {
        return planetTable;
    }

    public PlayerTable getPlayerTable() {
        return playerTable;
    }

    public ItemsTable getItemsTable() {
        return itemTable;
    }

    public ItemsTable getItemTable() {
        return itemTable;
    }

    public ShipTable getShipTable() {
        return shipTable;
    }

    public GadgetsTable getGadgetsTable() {
        return gadgetsTable;
    }

    public WeaponsTable getWeaponsTable() {
        return weaponsTable;
    }
}
