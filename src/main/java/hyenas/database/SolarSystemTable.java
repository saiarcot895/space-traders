package hyenas.database;

import hyenas.Models.Galaxy;
import hyenas.Models.SolarSystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SolarSystemTable implements Table<SolarSystem, Void> {

    private final Connection conn;

    public SolarSystemTable(Connection connArgs) {
        this.conn = connArgs;
    }

    @Override
    public void createTable() {
        String create = "CREATE TABLE IF NOT EXISTS SolarSystem "
                + "(ID INTEGER NOT NULL, " + "Name VARCHAR(20) NOT NULL, "
                + "XPoint INTEGER NOT NULL, " + "YPoint INTEGER NOT NULL, "
                + "PRIMARY KEY (ID))";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(create);
        } catch (SQLException e) {
            Logger.getLogger(SolarSystemTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void addRow(SolarSystem solarSystem, Void unused) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO "
                    + "SolarSystem (Name, XPoint, YPoint) "
                    + "VALUES(?, ?, ?)");
            stmt.setString(1, solarSystem.getSystemName());
            stmt.setInt(2, solarSystem.getX());
            stmt.setInt(3, solarSystem.getY());
            stmt.executeUpdate();
            // Id is generated based on how the System is generated.
        } catch (SQLException e) {
            Logger.getLogger(SolarSystemTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void update(SolarSystem solarSystem, Void unused) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE "
                    + "SolarSystem SET XPoint = ?, YPoint = ? "
                    + "WHERE Name = ?");
            stmt.setInt(1, solarSystem.getX());
            stmt.setInt(2, solarSystem.getY());
            stmt.setString(3, solarSystem.getSystemName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(SolarSystemTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void remove(SolarSystem solarSystem, Void unused) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM "
                    + "SolarSystem WHERE Name = ?");
            stmt.setString(1, solarSystem.getSystemName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(SolarSystemTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }
    
    @Override
    public void loadTable() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet solarSystems = stmt.executeQuery("SELECT * FROM [SolarSystem]");
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
            Logger.getLogger(SolarSystemTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void dropTable() {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DROP TABLE SolarSystem");
        } catch (SQLException e) {
            Logger.getLogger(SolarSystemTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void clearTable() {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM SolarSystem");
        } catch (SQLException e) {
            Logger.getLogger(SolarSystemTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }
    
}
