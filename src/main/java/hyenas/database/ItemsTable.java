package hyenas.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ItemsTable {
    
    private final String dbName;
    private final Connection conn;
    
    public ItemsTable(Connection connArgs, String dbNameArgs) {
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
        "create table ITEMS " + "(WATER integer, " + "FURS integer, " + 
                                "FOOD integer, " + "ORE integer, " + 
                                "GAMES integer, " + "FIREARMS integer, " + 
                                "MEDICINE integer, " + "MACHINES integer, " + 
                                "NARCOTICS integer, " + "ROBOTS integer, " +
                                "IID integer NOT NULL, " + "ID integer NOT NULL, " + 
                                "PRIMARY KEY (ID), " + "FOREIGN KEY (IID) REFERENCE PLAYERS (ID))";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeLargeUpdate(create);
        } catch (SQLException e) {
            printException(e);
        }
    }
    
    // Initial population
    public void populateTable(String name) {
        try (Statement stmt = conn.createStatement()) {
        // initially everything is zero    
        // insert into ITEMS values(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1)
            stmt.executeUpdate("insert into ITEMS " + 
                               "values(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1");
        } catch (SQLException e) {
            printException(e);
        }
    }
    
    /**
     * Update Water units
     * @param units
     * @throws SQLException 
     */
    public void updateWater(int units) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT WATER FROM Hyenas.ITEMS";
        ResultSet update = stmt.executeQuery(query);
        // Move cursor to first row
        update.first();
        update.updateInt("WATER", units);
        update.updateRow();
    }
    
    /**
     * Update Fur units
     * @param units
     * @throws SQLException 
     */
    public void updateFurs(int units) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT FURS FROM Hyenas.ITEMS";
        ResultSet update = stmt.executeQuery(query);
        // Move cursor to first row
        update.first();
        update.updateInt("FURS", units);
        update.updateRow();
    }
    
    /**
     * Update Food units
     * @param units
     * @throws SQLException 
     */
    public void updateFood(int units) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT FOOD FROM Hyenas.ITEMS";
        ResultSet update = stmt.executeQuery(query);
        // Move cursor to first row
        update.first();
        update.updateInt("FOOD", units);
        update.updateRow();
    }
    
    /**
     * Update Ore units
     * @param units
     * @throws SQLException 
     */
    public void updateOre(int units) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT ORE FROM Hyenas.ITEMS";
        ResultSet update = stmt.executeQuery(query);
        // Move cursor to first row
        update.first();
        update.updateInt("ORE", units);
        update.updateRow();
    }
    
    /**
     * Update Game units
     * @param units
     * @throws SQLException 
     */
    public void updateGames(int units) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT GAMES FROM Hyenas.ITEMS";
        ResultSet update = stmt.executeQuery(query);
        // Move cursor to first row
        update.first();
        update.updateInt("GAMES", units);
        update.updateRow();
    }
    
    /**
     * Update Firearm units
     * @param units
     * @throws SQLException 
     */
    public void updateFirearms(int units) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT FIREARMS FROM Hyenas.ITEMS";
        ResultSet update = stmt.executeQuery(query);
        // Move cursor to first row
        update.first();
        update.updateInt("FIREARMS", units);
        update.updateRow();
    }
    
    /**
     * Update Medicine units
     * @param units
     * @throws SQLException 
     */
    public void updateMedicine(int units) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT MEDICINE FROM Hyenas.ITEMS";
        ResultSet update = stmt.executeQuery(query);
        // Move cursor to first row
        update.first();
        update.updateInt("MEDICINE", units);
        update.updateRow();
    }
    
    /**
     * Update Machine units
     * @param units
     * @throws SQLException 
     */
    public void updateMachines(int units) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT MACHINES FROM Hyenas.ITEMS";
        ResultSet update = stmt.executeQuery(query);
        // Move cursor to first row
        update.first();
        update.updateInt("MACHINES", units);
        update.updateRow();
    }
    
    /**
     * Update Narcotic units
     * @param units
     * @throws SQLException 
     */
    public void updateNarcotics(int units) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT NARCOTICS FROM Hyenas.ITEMS";
        ResultSet update = stmt.executeQuery(query);
        // Move cursor to first row
        update.first();
        update.updateInt("NARCOTICS", units);
        update.updateRow();
    }
    
    /**
     * Update Robot units
     * @param units
     * @throws SQLException 
     */
    public void updateRobots(int units) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT ROBOTS FROM Hyenas.ITEMS";
        ResultSet update = stmt.executeQuery(query);
        // Move cursor to first row
        update.first();
        update.updateInt("ROBOTS", units);
        update.updateRow();
    }
    
    public void dropTable() {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DROP TABLE PLAYERS");
        } catch (SQLException e) {
            printException(e);
        }
    }
    
    public void deleteRow()  throws SQLException {
        
    }
    
    // TODO: When we allow multiple characters per user.
    public void insertRow() throws SQLException {
        
    }
    
}
