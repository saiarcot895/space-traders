package hyenas.database;

import hyenas.Models.Good;
import hyenas.Models.Player;
import hyenas.Models.Ship;
import hyenas.Models.Ware;
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
                                "PRIMARY KEY (ID), " + "FOREIGN KEY (IID) REFERENCES PLAYERS (ID))";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(create);
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
                               "values(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1)");
        } catch (SQLException e) {
            printException(e);
        }
    }
    
    public void loadTable() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet itemsInfo = stmt.executeQuery("SELECT Items.Water,"
                    + " Items.Furs, Items.Food, Items.Ore, Items.Games,"
                    + " Items.Firearms, Items.Medicine,  Items.Machines,"
                    + " Items.Narcotics, Items.Robots FROM Items");
            
            if (!itemsInfo.next()) {
                return;
            }
            Ship ship = Player.getInstance().getShip();
            for (int i = 0; i < itemsInfo.getInt(1); i++) {
                ship.getCargo().add(new Ware(Good.Water));
            }
            for (int i = 0; i < itemsInfo.getInt(2); i++) {
                ship.getCargo().add(new Ware(Good.Furs));
            }
            for (int i = 0; i < itemsInfo.getInt(3); i++) {
                ship.getCargo().add(new Ware(Good.Food));
            }
            for (int i = 0; i < itemsInfo.getInt(4); i++) {
                ship.getCargo().add(new Ware(Good.Ore));
            }
            for (int i = 0; i < itemsInfo.getInt(5); i++) {
                ship.getCargo().add(new Ware(Good.Games));
            }
            for (int i = 0; i < itemsInfo.getInt(6); i++) {
                ship.getCargo().add(new Ware(Good.Firearms));
            }
            for (int i = 0; i < itemsInfo.getInt(7); i++) {
                ship.getCargo().add(new Ware(Good.Medicine));
            }
            for (int i = 0; i < itemsInfo.getInt(8); i++) {
                ship.getCargo().add(new Ware(Good.Machines));
            }
            for (int i = 0; i < itemsInfo.getInt(9); i++) {
                ship.getCargo().add(new Ware(Good.Narcotics));
            }
            for (int i = 0; i < itemsInfo.getInt(10); i++) {
                ship.getCargo().add(new Ware(Good.Robots));
            }
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
        Statement stmt = conn.createStatement();
        String query = "UPDATE ITEMS SET WATER = " + units;
        stmt.executeQuery(query);
    }
    
    /**
     * Update Fur units
     * @param units
     * @throws SQLException 
     */
    public void updateFurs(int units) throws SQLException {
        Statement stmt = conn.createStatement();
        String query = "UPDATE ITEMS SET FURS = " + units;
        stmt.executeQuery(query);
    }
    
    /**
     * Update Food units
     * @param units
     * @throws SQLException 
     */
    public void updateFood(int units) throws SQLException {
        Statement stmt = conn.createStatement();
        String query = "UPDATE ITEMS SET FOOD = " + units;
        stmt.executeQuery(query);
    }
    
    /**
     * Update Ore units
     * @param units
     * @throws SQLException 
     */
    public void updateOre(int units) throws SQLException {
        Statement stmt = conn.createStatement();
        String query = "UPDATE ITEMS SET ORE = " + units;
        stmt.executeQuery(query);
    }
    
    /**
     * Update Game units
     * @param units
     * @throws SQLException 
     */
    public void updateGames(int units) throws SQLException {
        Statement stmt = conn.createStatement();
        String query = "UPDATE ITEMS SET GAMES = " + units;
        stmt.executeQuery(query);
    }
    
    /**
     * Update Firearm units
     * @param units
     * @throws SQLException 
     */
    public void updateFirearms(int units) throws SQLException {
        Statement stmt = conn.createStatement();
        String query = "UPDATE ITEMS SET FIREARMS = " + units;
        stmt.executeQuery(query);
    }
    
    /**
     * Update Medicine units
     * @param units
     * @throws SQLException 
     */
    public void updateMedicine(int units) throws SQLException {
        Statement stmt = conn.createStatement();
        String query = "UPDATE ITEMS SET MEDICINE = " + units;
        stmt.executeQuery(query);
    }
    
    /**
     * Update Machine units
     * @param units
     * @throws SQLException 
     */
    public void updateMachines(int units) throws SQLException {
        Statement stmt = conn.createStatement();
        String query = "UPDATE ITEMS SET MACHINES = " + units;
        stmt.executeQuery(query);
    }
    
    /**
     * Update Narcotic units
     * @param units
     * @throws SQLException 
     */
    public void updateNarcotics(int units) throws SQLException {
        Statement stmt = conn.createStatement();
        String query = "UPDATE ITEMS SET NARCOTICS = " + units;
        stmt.executeQuery(query);
    }
    
    /**
     * Update Robot units
     * @param units
     * @throws SQLException 
     */
    public void updateRobots(int units) throws SQLException {
        Statement stmt = conn.createStatement();
        String query = "UPDATE ITEMS SET ROBOTS = " + units;
        stmt.executeQuery(query);
    }
    
    public void dropTable() {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DROP TABLE ITEMS");
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
