package hyenas.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlayerTable {
    
    private final String dbName;
    private final Connection conn;
    
    public PlayerTable(Connection connArgs, String dbNameArgs) {
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
        "create table PLAYERS " + "(NAME varchar(20) NOT NULL, " + 
        "POINTS integer NOT NULL, " + "ENGINEER integer NOT NULL, " + 
        "PILOT integer NOT NULL, " + "INVESTOR integer NOT NULL, " + 
        "FIGHTER integer NOT NULL, " + "TRADER integer NOT NULL, " + "CREDITS integer, " + 
        "ID integer NOT NULL" + "SSID integer NOT NULL, "  + "PRIMARY KEY (ID), " + 
        "FOREIGN KEY (SSID) REFERENCES SOLARSYSTEM (ID))";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(create);
        } catch (SQLException e) {
            printException(e);
        }
    }
    
    /**
     * Just a tester method
     * @param conn
     * @throws SQLException 
     */
    public void viewTable(Connection conn) throws SQLException {
        Statement stmt = null;
        String query = "SELECT * FROM Hyenas.PLAYERS";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                String name = rs.getString("NAME");
                int points = rs.getInt("POINTS");
                int engineer = rs.getInt("ENGINEER");
                int pilot = rs.getInt("PILOT");
                int investor = rs.getInt("INVESTOR");
                int fighter = rs.getInt("FIGHTER");
                int trader = rs.getInt("TRADER");
                System.out.println(name + ", " + points + ", " +
                                   engineer + ", " + pilot + ", " + 
                                   pilot + ", " + investor + ", " + 
                                   fighter + ", " + trader + ", ");
            }
        } catch (SQLException e) {
            printException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    
    // Initial population
    public void populateTable(String name, int points, int ePoints,
            int pPoints, int iPoints, int fPoints, int tPoints, int credits) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            // insert into PLAYERS values('Name', points, ePoints, pPoints,
            //                    iPoints, fPoints, tPoints, credits)
            stmt.executeUpdate("insert into PLAYERS " + 
                               "values('" + name + "', " + 
                               points + ", " + ePoints + ", " + 
                               pPoints + ", " + iPoints + ", " + 
                               fPoints + ", " + tPoints + ", " + 
                               credits + ", 1)"); // <- ID
        } catch (SQLException e) {
            printException(e);
        }
    }
    
    // Important: For now, only allow one character at a time.
    // Therefore there will be only one row per table at a time.
    
    /**
     * Update total points
     * @param points
     * @throws SQLException 
     */
    public void updatePoints(int points) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT POINTS FROM Hyenas.PLAYERS";
        ResultSet update = stmt.executeQuery(query);
        // Move cursor to first row
        update.first();
        update.updateInt("POINTS", points);
        update.updateRow();
    }
    
    /**
     * Update Engineer Points
     * @param points
     * @throws SQLException 
     */
    public void updateEPoints(int points) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT ENGINEER FROM Hyenas.PLAYERS";
        ResultSet update = stmt.executeQuery(query);
        // Move cursor to first row
        update.first();
        update.updateInt("ENGINEER", points);
        update.updateRow();
    }
    
    /**
     * Update Pilot Points
     * @param points
     * @throws SQLException 
     */
    public void updatePPoints(int points) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT PILOT FROM Hyenas.PLAYERS";
        ResultSet update = stmt.executeQuery(query);
        // Move cursor to first row
        update.first();
        update.updateInt("PILOT", points);
        update.updateRow();
    }
    
    /**
     * Update Investor Points
     * @param points
     * @throws SQLException 
     */
    public void updateIPoints(int points) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT INVESTOR FROM Hyenas.PLAYERS";
        ResultSet update = stmt.executeQuery(query);
        // Move cursor to first row
        update.first();
        update.updateInt("INVESTOR", points);
        update.updateRow();
    }
    
    /**
     * Update Fighter Points
     * @param points
     * @throws SQLException 
     */
    public void updateFPoints(int points) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT FIGHTER FROM Hyenas.PLAYERS";
        ResultSet update = stmt.executeQuery(query);
        // Move cursor to first row
        update.first();
        update.updateInt("FIGHTER", points);
        update.updateRow();
    }
    
    /**
     * Update Trader Points
     * @param points
     * @throws SQLException 
     */
    public void updateTPoints(int points) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT TRADER FROM Hyenas.PLAYERS";
        ResultSet update = stmt.executeQuery(query);
        // Move cursor to first row
        update.first();
        update.updateInt("TRADER", points);
        update.updateRow();
    }
    
}
