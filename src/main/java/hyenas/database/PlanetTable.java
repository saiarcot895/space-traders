package hyenas.database;

import hyenas.Models.Galaxy;
import hyenas.Models.Planet;
import hyenas.Models.SolarSystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlanetTable {

    private final Connection conn;

    public PlanetTable(Connection connArgs) {
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
    
    public void loadTable() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet planets = stmt.executeQuery("SELECT Planet.Name,"
                    + " Planet.XPoint, Planet.YPoint, Planet.Radius,"
                    + " Planet.ClockwiseOrbit, Planet.Size, Planet.Tech,"
                    + " Planet.Type, SolarSystem.Name"
                    + " FROM Planet INNER JOIN SolarSystem"
                    + " ON Planet.SSID = SolarSystem.ID");
            while (planets.next()) {
                String solarSystemName = planets.getString(9);
                SolarSystem solarSystem = Galaxy.getInstance()
                        .getSolarSystemForName(solarSystemName);

                Planet planet = new Planet(planets.getString(1),
                        planets.getBoolean(5), planets.getInt(7),
                        planets.getInt(8));
                planet.setX(planets.getInt(2));
                planet.setY(planets.getInt(3));
                planet.setSize(planets.getDouble(6));
                planet.setOrbitRadius(planets.getInt(4));

                solarSystem.getPlanets().add(planet);
            }
        } catch (SQLException e) {
            printException(e);
        }
    }

    public void createTable() {
        String create = "CREATE TABLE IF NOT EXISTS Planet "
                + "(ID INTEGER NOT NULL, " + "Name VARCHAR(20) NOT NULL, "
                + "XPoint INTEGER NOT NULL, " + "YPoint INTEGER NOT NULL, "
                + "Radius INTEGER NOT NULL, "
                + "ClockwiseOrbit BOOLEAN NOT NULL, " + "Size DOUBLE NOT NULL, "
                + "Tech INTEGER NOT NULL, " + "Type INTEGER NOT NULL, "
                + "SSID INTEGER NOT NULL, " + "PRIMARY KEY (ID), "
                + "FOREIGN KEY (SSID) REFERENCES SolarSystem (ID))";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(create);
        } catch (SQLException e) {
            printException(e);
        }
    }
    // TODO: fix the x and y for planets!
    public void populateTable(Planet planet, SolarSystem system) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Planet "
                    + "(Name, XPoint, YPoint, Radius, ClockwiseOrbit, Size, "
                    + "Tech, Type, SSID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, planet.getPlanetName());
            stmt.setInt(2, planet.getX());
            stmt.setInt(3, planet.getY());
            stmt.setInt(4, planet.getOrbitRadius());
            stmt.setBoolean(5, planet.isClockwiseOrbit());
            stmt.setDouble(6, planet.getSize());
            stmt.setInt(7, planet.getTechLevel());
            stmt.setInt(8, planet.getPlanetType());

            PreparedStatement systemStatement = conn.prepareStatement(
                    "SELECT ID FROM SolarSystem WHERE Name = ?");
            systemStatement.setString(1, system.getSystemName());
            ResultSet systemIDResultSet = systemStatement.executeQuery();
            if (!systemIDResultSet.next()) {
                throw new IllegalArgumentException();
            }

            stmt.setInt(9, systemIDResultSet.getInt(1));
            stmt.executeUpdate();
            // Id & ssid are generated based on how the System is generated.
        } catch (SQLException e) {
            printException(e);
        }
    }
}