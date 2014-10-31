package hyenas.database;

import hyenas.Models.Galaxy;
import hyenas.Models.Player;
import hyenas.Models.SolarSystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerTable implements Table{

    private final Connection conn;

    public PlayerTable(Connection connArgs) {
        this.conn = connArgs;
    }

    /**
     * Create the player table.
     */
    @Override
    public void createTable() {
        String create = "CREATE TABLE IF NOT EXISTS Players " + "(ID INTEGER NOT NULL, "
                + "Name VARCHAR(20) NOT NULL, "
                + "Points INTEGER NOT NULL, " + "Engineer INTEGER NOT NULL, "
                + "Pilot INTEGER NOT NULL, " + "Investor INTEGER NOT NULL, "
                + "Fighter INTEGER NOT NULL, " + "Trader INTEGER NOT NULL, "
                + "Credits INTEGER, "
                + "SSID INTEGER, "  + "PRIMARY KEY (ID), "
                + "FOREIGN KEY (SSID) REFERENCES SolarSystem (ID))";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(create);
        } catch (SQLException e) {
            Logger.getLogger(PlayerTable.class.getName()).
                    log(Level.SEVERE, null, e);
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
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Players "
                    + "(Name, Points, Engineer, Pilot, Fighter, Investor, "
                    + "Trader, Credits) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, name);
            stmt.setInt(2, points);
            stmt.setInt(3, ePoints);
            stmt.setInt(4, pPoints);
            stmt.setInt(5, iPoints);
            stmt.setInt(6, fPoints);
            stmt.setInt(7, tPoints);
            stmt.setInt(8, credits);
            stmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(PlayerTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }

    /**
     * Load the player data from the database. This is used when continuing
     * the game.
     */
    @Override
    public void loadTable() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet playerInfo = stmt.executeQuery("SELECT Players.Name,"
                    + " Players.Points, Players.Engineer, Players.Pilot,"
                    + " Players.Investor, Players.Fighter, Players.Trader,"
                    + " Players.Credits,"
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
            player.setCredits(playerInfo.getInt(8));

            SolarSystem system = Galaxy.getInstance().getSolarSystemForName(
                    playerInfo.getString(9));
            player.setCurrentSystem(system);
            player.setTradingPlanet(system.getPlanets().get(0));
        } catch (SQLException e) {
            Logger.getLogger(PlayerTable.class.getName()).
                    log(Level.SEVERE, null, e);
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
        String query = "UPDATE PLAYERS SET Investor = " + points;
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
                + ", Credits = " + player.getCredits();
        stmt.execute(query);
    }

    @Override
    public void dropTable() {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DROP TABLE Players");
        } catch (SQLException e) {
            Logger.getLogger(PlayerTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }
    
    @Override
    public void clearTable() {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM Players");
        } catch (SQLException e) {
            Logger.getLogger(PlayerTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }

}
