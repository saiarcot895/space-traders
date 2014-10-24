package hyenas.database;

import hyenas.Models.Galaxy;
import hyenas.Models.Planet;
import hyenas.Models.SolarSystem;
import java.sql.Connection;
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
                    + " Planet.XPoint, Planet.YPoint, Planet.Tech,"
                    + " Planet.Resource, SolarSystem.Name"
                    + " FROM Planet INNER JOIN SolarSystem"
                    + " ON Planet.SSID = SolarSystem.ID");
            while (planets.next()) {
                String solarSystemName = planets.getString(6);
                SolarSystem solarSystem = Galaxy.getInstance().getSolarSystemForName(solarSystemName);

                Planet planet = new Planet(planets.getString(1), planets.getInt(4), planets.getInt(5));
                planet.setX(planets.getInt(2));
                planet.setY(planets.getInt(3));

                solarSystem.getPlanets().add(planet);
            }
        } catch (SQLException e) {
            printException(e);
        }
    }

    public void createTable() {
        String create = "CREATE TABLE IF NOT EXISTS Planet "
                + "(ID INTEGER NOT NULL, " + "Name VARCHAR(20) NOT NULL, "
                + "XPoint DOUBLE NOT NULL, " + "YPoint DOUBLE NOT NULL, "
                + "Tech VARCHAR NOT NULL, " + "Resource VARCHAR NOT NULL, "
                + "SSID INTEGER NOT NULL, " + "PRIMARY KEY (ID), "
                + "FOREIGN KEY (SSID) REFERENCES SolarSystem (ID))";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(create);
        } catch (SQLException e) {
            printException(e);
        }
    }
    // TODO: fix the x and y for planets!
    public void populateTable(String name, double x, 
            double y, int id, String tech, String rsrc, int ssid) {
        try {
            Statement stmt = conn.createStatement();
            final String query = "INSERT INTO Planet " + 
                    "VALUES(" + id + ", '" + name + "', " +
                    x + ", " + y + ", '" + tech + "', '" +
                    rsrc + "', " + ssid + ")";
            stmt.executeUpdate(query);
            // Id & ssid are generated based on how the System is generated.
        } catch (SQLException e) {
            printException(e);
        }
    }
}