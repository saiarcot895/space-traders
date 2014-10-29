package hyenas.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShipTable implements Table {

    private final Connection conn;
    
    public ShipTable(Connection connArgs) {
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
        try {
            Statement stmt = conn.createStatement();
            ResultSet ships = stmt.executeQuery("");
            while (ships.next()) {
                String shipName = ships.getString(1);
                
            }
        } catch (SQLException e) {
            printException(e);
        }
    }

    @Override
    public void loadTable() {
        
    }

    @Override
    public void dropTable() {
        
    }
    
}
