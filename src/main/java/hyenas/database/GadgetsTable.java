package hyenas.database;

import hyenas.Models.Ship;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class GadgetsTable implements Table {

    private final Connection conn;

    public GadgetsTable(Connection connArgs) {
        this.conn = connArgs;
    }

    private boolean ignoreSQLException(String state) {
        if (state == null) {
            System.out.println("State not defined");
            return false;
        }
        return state.equalsIgnoreCase("X0Y32")
                || state.equalsIgnoreCase("42Y55");
    }

    private void printException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                if (ignoreSQLException(((SQLException)e).getSQLState()) == false) {
                    e.printStackTrace(System.err);
                    System.err.println("State: " + ((SQLException)e).getSQLState());
                    System.err.println("Error Code: " + ((SQLException)e).getErrorCode());
                    System.err.println("Message: " + e.getMessage());
                    Throwable t = ex.getCause();
                    while (t != null) {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                    }
                }
            }
        }
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
        
        String create = 
        "CREATE TABLE IF NOT EXISTS Gadgets "
        + "(ID INTEGER NOT NULL, Name VARCHAR(20), "
        + "Property INTEGER, "
        + "GID INTEGER, "
        + "PRIMARY KEY (ID), FOREIGN KEY (GID) "
        + "REFERENCES Ship (ID))";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(create);
        } catch (SQLException e) {
            printException(e);
        }
    }

    public void populateTable(Ship ship) {
        try {
            String info = 
            "INSERT INTO Gadgets (Name, Property, GID) "
            + "VALUES(NULL, NULL, NULL)";
            PreparedStatement stmt = conn.prepareStatement(info);
            // TODO: Get Gadget Type (initially NULL)
            // TODO: Get Property (initially NULL)
            // TODO: Match the SID to ship ID
        } catch (SQLException e) {
            printException(e);
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
            printException(e);
        }
    }
    
}
