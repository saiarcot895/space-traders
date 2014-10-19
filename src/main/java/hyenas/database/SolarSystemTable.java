package hyenas.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SolarSystemTable {
    
    private final String dbName;
    private final Connection conn;
    
    public SolarSystemTable(Connection connArgs, String dbNameArgs) {
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
        "create table SOLARSYSTEM " + "(ID integer NOT NULL, " +
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
    
}
