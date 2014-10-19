package hyenas.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PlanetTable {
    
    private final String dbName;
    private final Connection conn;
    
    public PlanetTable(Connection connArgs, String dbNameArgs) {
        super();
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
    
    public void createTable() {
        String create = 
        "create table PLANET " + "(ID integer NOT NULL, " +
        "Name varchar(20) NOT NULL, " + "XPOINT double NOT NULL, " +
        "YPOINT double NOT NULL, " + "TECH varchar NOT NULL, " +
        "RESOURCE varchar NOT NULL, " + "SSID integer NOT NULL, " +
        "PRIMARY KEY (ID), " + "FOREIGN KEY (SSID) REFERENCES SOLARSYSTEM (ID))";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(create);
        } catch (SQLException e) {
            printException(e);
        }
    }
    // TODO: fix the x and y for planets!
    public void populateTable(String name, double x, 
            double y, int id, String tech, String rsrc, int ssid) {
        try {
            Statement stmt = conn.createStatement();
            final String query = "insert into PLANET " + 
                    "values(" + id + ", '" + name + "', " +
                    x + ", " + y + ", '" + tech + "', '" +
                    rsrc + "', " + ssid + ")";
            stmt.executeUpdate(query);
            // Id & ssid are generated based on how the System is generated.
        } catch (SQLException e) {
            printException(e);
        }
    }
}