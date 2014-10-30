package hyenas.database;

import hyenas.Models.Player;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        // name (type) string, int upkeep (UK), <- Wat?
        // double fuel, <- (Can be NULL because India)
        // double health, int SID <- Ship Id to reference
        // double shield
        // the other tables related to ship
        String create = "CREATE TABLE IF NOT EXISTS Ship "
                + "(ID INTEGER NOT NULL, " + "Type VARCHAR(20) NOT NULL, "
                + "Fuel DOUBLE NOT NULL, " + "Health DOUBLE NOT NULL, "
                + "Shield DOUBLE NOT NULL "
                + "Player INTEGER NOT NULL, " + "PRIMARY KEY (ID), "
                + "FOREIGN KEY (Player) REFERENCES Players (ID))";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(create);
        } catch (SQLException e) {
            printException(e);
        }
    }

    public void populateTable(Player player){
        try {
            player = Player.getInstance();
            String query = 
            "INSERT INTO Ship "
            + "(Type, Fuel, Health, Shield, SID) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, player.getShip().getName());
            stmt.setDouble(2, player.getShip().getFuel());
            stmt.setDouble(3, player.getShip().getHealth());
            // TODO: Get shield value (indexed at 4)
            PreparedStatement sysStmt = conn.prepareStatement(
                    "SELECT ID FROM Players WHERE Name = ?");
            sysStmt.setString(1, player.getName());
            ResultSet shipIDResultSet = sysStmt.executeQuery();
            if (!shipIDResultSet.next()) {
                throw new IllegalArgumentException();
            }
            stmt.setInt(5, shipIDResultSet.getInt(1));
            stmt.executeUpdate();
        } catch (SQLException e) {
            printException(e);
            Logger.getLogger(ShipTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }
    
    @Override
    public void loadTable() {
        try {
            Statement stmt = conn.createStatement();
            String query = 
            "SELECT Ship.Type, " + "Ship.Fuel, "
            + "Ship.Health, Ship.Shield, Players.Name FROM "
            + "Ship INNER JOIN Players "
            + "ON Ship.SID = Players.ID";
            ResultSet shipInfo = stmt.executeQuery(query);
            shipInfo.next();
            // TODO!!!!!!!!
        } catch (SQLException ex) {
            printException(ex);
            Logger.getLogger(ShipTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Update ship type
     * @param type
     * @throws SQLException 
     */
    public void updateShipType(String type) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        String query = "UPDATE SHIP SET TYPE = " + type;
        stmt.executeQuery(query);
    }
    
    /**
     * Update ship health
     * @param health
     * @throws SQLException 
     */
    public void updateShipHealth(int health) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        String query = "UPDATE SHIP SET HEALTH = " + health;
        stmt.executeQuery(query);
    }
    
    /**
     * Update ship fuel
     * @param fuel
     * @throws SQLException 
     */
    public void updateShipFuel(int fuel) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        String query = "UPDATE SHIP SET FUEL = " + fuel;
        stmt.executeQuery(query);
    }
    
    /**
     * Update ship shields
     * @param shield
     * @throws SQLException 
     */
    public void updateShield(int shield) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        String query = "UPDATE SHIP SET SHIELD = " + shield;
        stmt.executeQuery(query);
    }
    
    @Override
    public void dropTable() {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DROP TABLE Ship");
        } catch (SQLException e) {
            printException(e);
        }
    }
    
    @Override
    public void clearTable() {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM Ship");
        } catch (SQLException e) {
            printException(e);
        }
    }
    
}
