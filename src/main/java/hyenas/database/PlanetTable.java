package hyenas.database;

import hyenas.Models.Galaxy;
import hyenas.Models.Planet;
import hyenas.Models.SolarSystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlanetTable implements Table {

    private final Connection conn;

    public PlanetTable(Connection connArgs) {
        this.conn = connArgs;
    }
    
    @Override
    public void loadTable() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet planets = stmt.executeQuery("SELECT Planet.Name,"
                    + " Planet.Radius, Planet.ClockwiseOrbit, Planet.Size,"
                    + " Planet.Tech, Planet.Type, SolarSystem.Name"
                    + " FROM Planet INNER JOIN SolarSystem"
                    + " ON Planet.SSID = SolarSystem.ID");
            while (planets.next()) {
                String solarSystemName = planets.getString(7);
                SolarSystem solarSystem = Galaxy.getInstance()
                        .getSolarSystemForName(solarSystemName);

                Planet planet = new Planet(planets.getString(1),
                        planets.getBoolean(3), planets.getInt(5),
                        planets.getInt(6));
                planet.setSize(planets.getDouble(4));
                planet.setOrbitRadius(planets.getInt(2));

                solarSystem.getPlanets().add(planet);
            }
        } catch (SQLException e) {
            Logger.getLogger(PlanetTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void createTable() {
        String create = "CREATE TABLE IF NOT EXISTS Planet "
                + "(ID INTEGER NOT NULL, " + "Name VARCHAR(20) NOT NULL, "
                + "Radius INTEGER NOT NULL, "
                + "ClockwiseOrbit BOOLEAN NOT NULL, " + "Size DOUBLE NOT NULL, "
                + "Tech INTEGER NOT NULL, " + "Type INTEGER NOT NULL, "
                + "SSID INTEGER, " + "PRIMARY KEY (ID), "
                + "FOREIGN KEY (SSID) REFERENCES SolarSystem (ID))";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(create);
        } catch (SQLException e) {
            Logger.getLogger(PlanetTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }
    // TODO: fix the x and y for planets!
    public void populateTable(Planet planet, SolarSystem system) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Planet "
                    + "(Name, Radius, ClockwiseOrbit, Size, "
                    + "Tech, Type, SSID) VALUES(?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, planet.getPlanetName());
            stmt.setInt(2, planet.getOrbitRadius());
            stmt.setBoolean(3, planet.isClockwiseOrbit());
            stmt.setDouble(4, planet.getSize());
            stmt.setInt(5, planet.getTechLevel().ordinal());
            stmt.setInt(6, planet.getPlanetType().ordinal());

            PreparedStatement systemStatement = conn.prepareStatement(
                    "SELECT ID FROM SolarSystem WHERE Name = ?");
            systemStatement.setString(1, system.getSystemName());
            ResultSet systemIDResultSet = systemStatement.executeQuery();
            if (!systemIDResultSet.next()) {
                throw new IllegalArgumentException();
            }

            stmt.setInt(7, systemIDResultSet.getInt(1));
            stmt.executeUpdate();
            // Id & ssid are generated based on how the System is generated.
        } catch (SQLException e) {
            Logger.getLogger(PlanetTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void dropTable() {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DROP TABLE Planet");
        } catch (SQLException e) {
            Logger.getLogger(PlanetTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }
    
    @Override
    public void clearTable() {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM Planet");
        } catch (SQLException e) {
            Logger.getLogger(PlanetTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }
}