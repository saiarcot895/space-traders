package hyenas.database;

import hyenas.Models.Galaxy;
import hyenas.Models.Player;
import hyenas.Models.SolarSystem;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlayerTable {

    private final Connection conn;

    public PlayerTable(Connection connArgs) {
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
                SQLException exception = (SQLException) e;
                if (!ignoreSQLException(exception.getSQLState())) {
                    e.printStackTrace(System.err);
                    System.err.println("State: " + exception.getSQLState());
                    System.err.println("Error Code: " + exception.getErrorCode());
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

    /**
     * Create the player table.
     */
    public void createTable() {
        String create = "CREATE TABLE IF NOT EXISTS Players " + "(ID INTEGER NOT NULL, "
                + "Name VARCHAR(20) NOT NULL, "
                + "Points INTEGER NOT NULL, " + "Engineer INTEGER NOT NULL, "
                + "Pilot INTEGER NOT NULL, " + "Investor INTEGER NOT NULL, "
                + "Fighter INTEGER NOT NULL, " + "Trader INTEGER NOT NULL, "
                + "Credits INTEGER, " 
                + "Fuel INTEGER NOT NULL, " + "Health INTEGER NOT NULL, "
                + "SSID INTEGER, "  + "PRIMARY KEY (ID), "
                + "FOREIGN KEY (SSID) REFERENCES SolarSystem (ID))";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(create);
        } catch (SQLException e) {
            printException(e);
        }
    }

    /**
     * Add a player entry into the table.
     * @param name Name of the player
     * @param points Free (unused) skill points the player has
     * @param ePoints engineer skill points
     * @param pPoints pilot skill points
     * @param iPoints investor skill points
     * @param fPoints fighter skill points
     * @param tPoints trader skill points
     * @param credits credits the player has
     * @throws SQLException if a database error occurred
     */
    public void populateTable(String name, int points, int ePoints,
            int pPoints, int iPoints, int fPoints, int tPoints, int credits) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            final String query = "INSERT INTO PLAYERS " +
                    "VALUES(1, '" + name + "', " +
                    points + ", " + ePoints + ", " +
                    pPoints + ", " + iPoints + ", " +
                    fPoints + ", " + tPoints + ", " +
                    credits + ", 700, 250, null)";
            // insert into PLAYERS values('Name', points, ePoints, pPoints,
            //                    iPoints, fPoints, tPoints, credits)
            stmt.executeUpdate(query); // <- ID
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load the player data from the database. This is used when continuing
     * the game.
     */
    public void loadTable() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet playerInfo = stmt.executeQuery("SELECT Players.Name,"
                    + " Players.Points, Players.Engineer, Players.Pilot,"
                    + " Players.Investor, Players.Fighter, Players.Trader,"
                    + " Players.Fuel, Players.Health, Players.Credits,"
                    + " SolarSystem.Name FROM Players"
                    + " INNER JOIN SolarSystem"
                    + " ON Players.SSID = SolarSystem.ID");

            playerInfo.next();
            Player player = Player.getInstance();
            player.setName(playerInfo.getString(1));
            player.setPoints(playerInfo.getInt(2));
            player.setEngineerSkill(playerInfo.getInt(3));
            player.setPilotSkill(playerInfo.getInt(4));
            player.setInvestorSkill(playerInfo.getInt(5));
            player.setFighterSkill(playerInfo.getInt(6));
            player.setTraderSkill(playerInfo.getInt(7));
            player.setCredits(playerInfo.getInt(10));

            player.getShip().setFuel(playerInfo.getInt(8));
            player.getShip().setHealth(playerInfo.getInt(9));

            SolarSystem system = Galaxy.getInstance().getSolarSystemForName(
                    playerInfo.getString(11));
            player.setCurrentSystem(system);
            player.setTradingPlanet(system.getPlanets().get(0));
        } catch (SQLException e) {
            printException(e);
        }
    }

    // Important: For now, only allow one character at a time.
    // Therefore there will be only one row per table at a time.

    /**
     * Update remaining (unused) points of the player.
     * @param points remaining points the player has
     * @throws SQLException if a database error occurred
     */
    public void updatePoints(int points) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        String query = "UPDATE PLAYERS SET POINTS = " + points;
        stmt.executeQuery(query);
    }

    /**
     * Update Engineer skill points.
     * @param points engineer skill points
     * @throws SQLException if a database error occurred
     */
    public void updateEngineerPoints(int points) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        String query = "UPDATE PLAYERS SET ENGINEER = " + points;
        stmt.executeQuery(query);
    }

    /**
     * Update Pilot Points.
     * @param points pilot skill points
     * @throws SQLException if a database error occurred
     */
    public void updatePilotPoints(int points) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        String query = "UPDATE PLAYERS SET PILOT = " + points;
        stmt.executeQuery(query);
    }

    /**
     * Update Investor Points.
     * @param points investor skill points
     * @throws SQLException if a database error occurred
     */
    public void updateInvestorPoints(int points) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        String query = "UPDATE PLAYERS SET INVESTOR = " + points;
        stmt.executeQuery(query);
    }

    /**
     * Update Fighter Points.
     * @param points fighter skill points
     * @throws SQLException if a database error occurred
     */
    public void updateFighterPoints(int points) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        String query = "UPDATE PLAYERS SET FIGHTER = " + points;
        stmt.executeQuery(query);
    }

    /**
     * Update Trader Points.
     * @param points trader skill points
     * @throws SQLException if a database error occurred
     */
    public void updateTraderPoints(int points) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        String query = "UPDATE PLAYERS SET TRADER = " + points;
        stmt.execute(query);
    }

    /**
     * Update the location of the player.
     * @param solarSystem new solar system the player is in
     * @throws SQLException if a database error occurred
     */
    public void updateLocation(SolarSystem solarSystem)
            throws SQLException {
        Statement stmt = conn.createStatement();
        String query = "SELECT ID FROM SOLARSYSTEM WHERE " +
                "NAME = '" + solarSystem.getSystemName() + "'";
        ResultSet update = stmt.executeQuery(query);
        update.next();
        int variable = update.getInt(1);
        Player player = Player.getInstance();
        query = "UPDATE PLAYERS SET SSID = " + variable
                + ", Health = " + player.getShip().getHealth()
                + ", Fuel = " + player.getShip().getFuel()
                + ", Credits = " + player.getCredits();
        stmt.execute(query);
    }

}
