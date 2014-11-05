package hyenas.database;

import hyenas.Models.Gadget;
import hyenas.Models.Ship;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GadgetsTable implements Table<Gadget, Ship> {

    private final Connection conn;

    public GadgetsTable(Connection connArgs) {
        this.conn = connArgs;
    }
    
    /* Shield is a type of Gadget!!!!!!!!! */
    /* So there will be no Shield table */
    @Override
    public void createTable() {
        // Property -> Increase Shield
        // Property -> Increase Health
        // Property -> Increase Weapon Damage (all)
        // Make the property an integer type
        // 1 -> Increase health (Random value)
        // 2 -> Increase shields (Random value)
        // 3 -> Increase Weapon Damage (all)
        
        String create = "CREATE TABLE IF NOT EXISTS Gadgets "
                + "(ID INTEGER NOT NULL, Name VARCHAR(20), "
                + "Property INTEGER, " + "Ship INTEGER, "
                + "PRIMARY KEY (ID), FOREIGN KEY (Ship) "
                + "REFERENCES Ship (ID))";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(create);
        } catch (SQLException e) {
            Logger.getLogger(GadgetsTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void addRow(Gadget item, Ship parent) {
        try {
            String info = "INSERT INTO Gadgets (Name, Property, GID) "
                    + "VALUES(NULL, NULL, NULL)";
            PreparedStatement stmt = conn.prepareStatement(info);
            // TODO: Get Gadget Type (initially NULL)
            // TODO: Get Property (initially NULL)
            // TODO: Match the SID to ship ID
        } catch (SQLException e) {
            Logger.getLogger(GadgetsTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }
    
    @Override
    public void update(Gadget item, Ship parent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Gadget item, Ship parent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void loadTable() {
        // TODO
    }

    @Override
    public void dropTable() {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DROP TABLE Gadgets");
        } catch (SQLException e) {
            Logger.getLogger(GadgetsTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }
    
    @Override
    public void clearTable() {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM Gadgets");
        } catch (SQLException e) {
            Logger.getLogger(GadgetsTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }
    
}
