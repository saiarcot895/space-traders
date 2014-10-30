package hyenas.database;

import hyenas.Models.Ship;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WeaponsTable implements Table {

    private final Connection conn;

    public WeaponsTable(Connection connArgs) {
        this.conn = connArgs;
    }
    
    @Override
    public void createTable() {
        String create = "CREATE TABLE IF NOT EXISTS Weapons "
                + "(ID INTEGER NOT NULL, Type VARCHAR(20) NOT NULL, "
                + "Damage INTEGER NOT NULL, Ship INTEGER NOT NULL, "
                + "PRIMARY KEY (ID), FOREIGN KEY (Ship) "
                + "REFERENCES Ship (ID))";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(create);
        } catch (SQLException e) {
            Logger.getLogger(WeaponsTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }

    public void populateTable(Ship ship) {
        try {
            String info = 
            "INSERT INTO Weapons (Type, Damage, WID) "
            + "VALUES(?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(info);
            // TODO: Get Weapon Type (initially the default)
            // TODO: Get Damage (Default)
            // TODO: Match the SID to ship ID
        } catch (SQLException e) {
            Logger.getLogger(WeaponsTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }
    
    @Override
    public void loadTable() {
        // TODO
    }

    @Override
    public void dropTable() {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DROP TABLE Weapon");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void clearTable() {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM Weapon");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
