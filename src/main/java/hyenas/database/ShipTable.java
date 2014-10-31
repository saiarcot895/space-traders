package hyenas.database;

import hyenas.Models.Player;
import hyenas.Models.Ship;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShipTable implements Table<Ship, Player> {

    private final Connection conn;
    
    public ShipTable(Connection connArgs) {
        this.conn = connArgs;
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
                + "Player INTEGER NOT NULL, " + "PRIMARY KEY (ID), "
                + "FOREIGN KEY (Player) REFERENCES Players (ID))";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(create);
        } catch (SQLException e) {
            Logger.getLogger(ShipTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void addRow(Ship ship, Player player) {
        try {
            String query = "INSERT INTO Ship "
                    + "(Type, Fuel, Health, Player) VALUES(?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, ship.getName());
            stmt.setDouble(2, ship.getFuel());
            stmt.setDouble(3, ship.getHealth());
            
            PreparedStatement sysStmt = conn.prepareStatement(
                    "SELECT ID FROM Players WHERE Name = ?");
            sysStmt.setString(1, player.getName());
            ResultSet shipIDResultSet = sysStmt.executeQuery();
            if (!shipIDResultSet.next()) {
                throw new IllegalArgumentException();
            }
            
            stmt.setInt(4, shipIDResultSet.getInt(1));
            stmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ShipTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void update(Ship ship, Player player) {
        try {
            String query = "UPDATE Ship "
                    + "SET Type = ?, Fuel = ?, Health = ? WHERE Player = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, ship.getName());
            stmt.setDouble(2, ship.getFuel());
            stmt.setDouble(3, ship.getHealth());
            
            PreparedStatement sysStmt = conn.prepareStatement(
                    "SELECT ID FROM Players WHERE Name = ?");
            sysStmt.setString(1, player.getName());
            ResultSet shipIDResultSet = sysStmt.executeQuery();
            if (!shipIDResultSet.next()) {
                throw new IllegalArgumentException();
            }
            
            stmt.setInt(4, shipIDResultSet.getInt(1));
            stmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ShipTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void remove(Ship ship, Player player) {
        try {
            String query = "DELETE FROM Ship "
                    + "WHERE Type = ? AND Player = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, ship.getName());
            
            PreparedStatement sysStmt = conn.prepareStatement(
                    "SELECT ID FROM Players WHERE Name = ?");
            sysStmt.setString(1, player.getName());
            ResultSet shipIDResultSet = sysStmt.executeQuery();
            if (!shipIDResultSet.next()) {
                throw new IllegalArgumentException();
            }
            
            stmt.setInt(2, shipIDResultSet.getInt(1));
            stmt.executeUpdate();
        } catch (SQLException e) {
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
            + "Ship.Health, Players.Name FROM "
            + "Ship INNER JOIN Players "
            + "ON Ship.Player = Players.ID";
            ResultSet shipInfo = stmt.executeQuery(query);
            shipInfo.next();
            
            Player.getInstance().setShip(new Ship(Ship.ShipType
                    .valueOf(shipInfo.getString(1).toUpperCase())));
            Player.getInstance().getShip().setFuel(shipInfo.getDouble(2));
            Player.getInstance().getShip().setHealth(shipInfo.getDouble(3));
        } catch (SQLException e) {
            Logger.getLogger(ShipTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }
    
    @Override
    public void dropTable() {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DROP TABLE Ship");
        } catch (SQLException e) {
            Logger.getLogger(ShipTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }
    
    @Override
    public void clearTable() {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM Ship");
        } catch (SQLException e) {
            Logger.getLogger(ShipTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }
    
}
