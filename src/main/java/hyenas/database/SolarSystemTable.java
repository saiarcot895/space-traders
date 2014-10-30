package hyenas.database;

import hyenas.Models.Galaxy;
import hyenas.Models.SolarSystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SolarSystemTable implements Table {

    private final Connection conn;

    public SolarSystemTable(Connection connArgs) {
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
    
    public void beginTransaction() {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("BEGIN TRANSACTION");
        } catch (SQLException e) {
            printException(e);
        }
    }
    
    public void commitTransaction() {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("COMMIT TRANSACTION");
        } catch (SQLException e) {
            printException(e);
        }
    }

    public void createTable() {
        String create = "CREATE TABLE IF NOT EXISTS SolarSystem "
                + "(ID INTEGER NOT NULL, " + "Name VARCHAR(20) NOT NULL, "
                + "XPoint DOUBLE NOT NULL, " + "YPoint DOUBLE NOT NULL, "
                + "PRIMARY KEY (ID))";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(create);
        } catch (SQLException e) {
            printException(e);
        }
    }

    public void populateTable(String name, double x, double y, int id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO "
                    + "SolarSystem (Name, XPoint, YPoint) "
                    + "VALUES(?, ?, ?)");
            stmt.setString(1, name);
            stmt.setDouble(2, x);
            stmt.setDouble(3, y);
            stmt.executeUpdate();
            // Id is generated based on how the System is generated.
        } catch (SQLException e) {
            printException(e);
        }
    }
    
    public void loadTable() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet solarSystems = stmt.executeQuery("SELECT * FROM [SolarSystem]");
            while (solarSystems.next()) {
                String systemName = solarSystems.getString(2);
                SolarSystem solarSystem = Galaxy.getInstance()
                        .getSolarSystemForName(systemName);
                if (solarSystem != null) {
                    solarSystem.getPlanets().clear();
                    solarSystem.setX(solarSystems.getInt(3));
                    solarSystem.setY(solarSystems.getInt(4));
                }
            }
            Galaxy.getInstance().setLocationsSet(true);
        } catch (SQLException e) {
            printException(e);
        }
    }

    @Override
    public void dropTable() {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DROP TABLE SolarSystem");
        } catch (SQLException e) {
            printException(e);
        }
    }
    
    @Override
    public void clearTable() {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM SolarSystem");
        } catch (SQLException e) {
            printException(e);
        }
    }
    
}
