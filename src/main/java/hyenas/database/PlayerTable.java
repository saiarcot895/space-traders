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

public class PlayerTable implements Table<Player, Void> {

    private final Connection conn;

    public PlayerTable(Connection connArgs) {
        this.conn = connArgs;
    }

    /**
     * Create the player table.
     */
    @Override
    public void createTable() {
        String create = "CREATE TABLE IF NOT EXISTS Players "
                + "(ID INTEGER NOT NULL, " + "Name VARCHAR(20) NOT NULL, "
                + "Points INTEGER NOT NULL, " + "Engineer INTEGER NOT NULL, "
                + "Pilot INTEGER NOT NULL, " + "Investor INTEGER NOT NULL, "
                + "Fighter INTEGER NOT NULL, " + "Trader INTEGER NOT NULL, "
                + "Credits INTEGER, " + "SSID INTEGER, "
                + "PRIMARY KEY (ID), "
                + "FOREIGN KEY (SSID) REFERENCES SolarSystem (ID))";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(create);
        } catch (SQLException e) {
            Logger.getLogger(PlayerTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void addRow(Player player, Void unused) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Players "
                    + "(Name, Points, Engineer, Pilot, Fighter, Investor, "
                    + "Trader, Credits) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, player.getName());
            stmt.setInt(2, player.getPoints());
            stmt.setInt(3, player.getEngineerSkill());
            stmt.setInt(4, player.getPilotSkill());
            stmt.setInt(5, player.getFighterSkill());
            stmt.setInt(6, player.getInvestorSkill());
            stmt.setInt(7, player.getTraderSkill());
            stmt.setInt(8, player.getCredits());
            stmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(PlayerTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }
    
    @Override
    public void update(Player player, Void parent) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE Players "
                    + "SET Name = ?, Points = ?, Engineer = ?, Pilot = ?, "
                    + "Fighter = ?, Investor = ?, Trader = ?, Credits = ? ");
            stmt.setString(1, player.getName());
            stmt.setInt(2, player.getPoints());
            stmt.setInt(3, player.getEngineerSkill());
            stmt.setInt(4, player.getPilotSkill());
            stmt.setInt(5, player.getFighterSkill());
            stmt.setInt(6, player.getInvestorSkill());
            stmt.setInt(7, player.getTraderSkill());
            stmt.setInt(8, player.getCredits());
            stmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(PlayerTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }
    
    @Override
    public void remove(Player player, Void unused) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM "
                    + "Players WHERE Name = ?");
            stmt.setString(1, player.getName());
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
            player.setCurrentPlanet(system.getPlanets().get(0));
        } catch (SQLException e) {
            Logger.getLogger(PlayerTable.class.getName()).
                    log(Level.SEVERE, null, e);
        }
    }

    // Important: For now, only allow one character at a time.
    // Therefore there will be only one row per table at a time.

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
