package hyenas.database;

import hyenas.Models.Galaxy;
import hyenas.Models.SolarSystem;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SolarSystemTable {
    
    private final String dbName;
    private final Connection conn;
    
    public SolarSystemTable(Connection connArgs, String dbNameArgs) {
        this.dbName = dbNameArgs;
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
        String create = 
        "create table IF NOT EXISTS SOLARSYSTEM " + "(ID integer NOT NULL, " +
        "Name varchar(20) NOT NULL, " + "XPOINT double NOT NULL, " +
        "YPOINT double NOT NULL, " + "PRIMARY KEY (ID))";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(create);
        } catch (SQLException e) {
            printException(e);
        }
    }
    
    public void populateTable(String name, double x, 
            double y, int id) {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("insert into SOLARSYSTEM " + 
                               "values(" + id + ", '" + name + "', " + 
                               x + ", " + y + ")");
            // Id is generated based on how the System is generated.
        } catch (SQLException e) {
            printException(e);
        }
    }
    
    public void loadTable() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet solarSystems = stmt.executeQuery("SELECT * FROM [SOLARSYSTEM]");
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
    
}
