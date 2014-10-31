package hyenas.database;

import hyenas.Models.Player;
import hyenas.Models.Ship;
import hyenas.Models.Weapon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WeaponsTable implements Table<Weapon, Ship> {

    private final Connection conn;

    public WeaponsTable(Connection connArgs) {
        this.conn = connArgs;
    }
    
    @Override
    public void createTable() {
        String create = "CREATE TABLE IF NOT EXISTS Weapons "
                + "(ID INTEGER NOT NULL, "
                + "Ship INTEGER NOT NULL, "
                + "PRIMARY KEY (ID), FOREIGN KEY (Ship) "
                + "REFERENCES Ship (ID))";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(create);
        } catch (SQLException e) {
            Logger.getLogger(WeaponsTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void addRow(Weapon weapon, Ship ship) {
        try {
            String info = "INSERT INTO Weapons "
                    + "VALUES(?, ?)";
            PreparedStatement stmt = conn.prepareStatement(info);
            
            PreparedStatement sysStmt = conn.prepareStatement(
                    "SELECT ID FROM Ship WHERE Name = ?");
            sysStmt.setString(1, ship.getName());
            ResultSet shipIDResultSet = sysStmt.executeQuery();
            if (!shipIDResultSet.next()) {
                throw new IllegalArgumentException();
            }

            stmt.setInt(1, weapon.getType().ordinal());
            stmt.setInt(2, shipIDResultSet.getInt(1));
            stmt.execute();
        } catch (SQLException e) {
            Logger.getLogger(WeaponsTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void update(Weapon weapon, Ship ship) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void remove(Weapon weapon, Ship ship) {
        try {
            String info = "DELETE FROM Weapons "
                    + "WHERE ID = ? AND Ship = ?)";
            PreparedStatement stmt = conn.prepareStatement(info);
            
            PreparedStatement sysStmt = conn.prepareStatement(
                    "SELECT ID FROM Ship WHERE Name = ?");
            sysStmt.setString(1, ship.getName());
            ResultSet shipIDResultSet = sysStmt.executeQuery();
            if (!shipIDResultSet.next()) {
                throw new IllegalArgumentException();
            }

            stmt.setInt(1, weapon.getType().ordinal());
            stmt.setInt(2, shipIDResultSet.getInt(1));
            stmt.execute();
        } catch (SQLException e) {
            Logger.getLogger(WeaponsTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }
    
    @Override
    public void loadTable() {
        try {
            Statement stmt = conn.createStatement();
            String query = "SELECT ID, Ship FROM Weapons";
            ResultSet shipInfo = stmt.executeQuery(query);
            while (shipInfo.next()) {
                Weapon.WeaponType weaponType = Weapon.WeaponType.values()[
                        shipInfo.getInt(1)];
                Weapon weapon = new Weapon(weaponType);
                Player.getInstance().getShip().getWeapons().add(weapon);
            }
        } catch (SQLException e) {
            Logger.getLogger(ShipTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void dropTable() {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DROP TABLE Weapons");
        } catch (SQLException e) {
            Logger.getLogger(WeaponsTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }
    
    @Override
    public void clearTable() {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM Weapons");
        } catch (SQLException e) {
            Logger.getLogger(WeaponsTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }
    
}
