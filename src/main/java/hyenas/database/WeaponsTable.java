package hyenas.database;

import hyenas.Models.Ship;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class WeaponsTable implements Table {

    private final Connection conn;

    public WeaponsTable(Connection connArgs) {
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
    
    @Override
    public void createTable() {
        String create = 
        "CREATE TABLE IF NOT EXISTS Weapons "
        + "(ID INTEGER NOT NULL, Type VARCHAR(20) NOT NULL, "
        + "Damage INTEGER NOT NULL, WID INTEGER NOT NULL, "
        + "PRIMARY KEY (ID), FOREIGN KEY (WID) "
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
            "INSERT INTO Weapons (Type, Damage, WID) "
            + "VALUES(?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(info);
            // TODO: Get Weapon Type (initially the default)
            // TODO: Get Damage (Default)
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
