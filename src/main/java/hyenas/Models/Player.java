package hyenas.Models;

import hyenas.Models.Ship.ShipType;
import java.util.Random;

/**
 * Represents the player.
 * @author saikrishna
 */
public class Player {
    /**
     * The player name.
     */
    private String name;
    /**
     * The player points.
     */
    private int points;
    /**
     * The player pilot skill.
     */
    private int pilotSkill;
    /**
     * The player fighter skill.
     */
    private int fighterSkill;
    /**
     * The player trader skill.
     */
    private int traderSkill;
    /**
     * The player engineer skill.
     */
    private int engineerSkill;
    /**
     * The player investor skill.
     */
    private int investorSkill;
    /**
     * The system the player is currently on.
     */
    private SolarSystem currentSystem;
    /**
     * The planet the player is currently on.
     */
    private Planet currentPlanet;
    /**
     * The player ship.
     */
    private Ship ship;
    /**
     * The player credits.
     */
    private int credits;
    /**
     * The player state. For use with database
     */
    private boolean state;
    /**
     * The common player instance. For use with singleton.
     */
    private static Player instance;

    /**
     * Initializes an instance of Player, sets initial values.
     */
    private Player() {
        // KEEP PRIVATE - use getInstance instead
        ship = new Ship(ShipType.FLEA);
        Random rand = new Random();
        SolarSystem[] solarSystems = Galaxy.getInstance().getSolarSystems().values().toArray(new SolarSystem[0]);
        currentSystem = solarSystems[rand.nextInt(solarSystems.length)];
        currentPlanet = currentSystem.getPlanets().get(0);
        credits = 750;
        state = false;
    }

    /**
     * Getter for Player singleton.
     * @return the common player instance
     */
    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    /**
     * Get player's name.
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Set player's name.
     * @param name the String name of the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get player's total points.
     * @return the player's total points
     */
    public int getPoints() {
        return points;
    }
    
    /**
     * Set player's total points.
     * @param points the points
     */
    public void setPoints(int points) {
        this.points = points;
    }
    
    /**
     * Get player's pilot skill.
     * @return the pilot skill of the player
     */
    public int getPilotSkill() {
        return pilotSkill;
    }

    /**
     * Set player's pilot skill.
     * @param pilotSkill the pilot skill of the player
     */
    public void setPilotSkill(int pilotSkill) {
        this.pilotSkill = pilotSkill;
    }

    /**
     * Get player's fighter skill.
     * @return the fighter skill of the player
     */
    public int getFighterSkill() {
        return fighterSkill;
    }

    /**
     * Set player's fighter skill.
     * @param fighterSkill the fighter skill of the player
     */
    public void setFighterSkill(int fighterSkill) {
        this.fighterSkill = fighterSkill;
    }

    /**
     * Get player's trader skill.
     * @return the trader skill of the player
     */
    public int getTraderSkill() {
        return traderSkill;
    }

    /**
     * Set player's trader skill.
     * @param traderSkill the trader skill of the player
     */
    public void setTraderSkill(int traderSkill) {
        this.traderSkill = traderSkill;
    }

    /**
     * Get player's engineer skill.
     * @return the engineer skill of the player
     */
    public int getEngineerSkill() {
        return engineerSkill;
    }

    /**
     * Set player's engineer skill.
     * @param engineerSkill the engineer skill of the player
     */
    public void setEngineerSkill(int engineerSkill) {
        this.engineerSkill = engineerSkill;
    }

    /**
     * Get player's investor skill.
     * @return the investor skill of the player
     */
    public int getInvestorSkill() {
        return investorSkill;
    }

    /**
     * Set player's investor skill.
     * @param investorSkill the investor skill of the player
     */
    public void setInvestorSkill(int investorSkill) {
        this.investorSkill = investorSkill;
    }

    /**
     * Get player's current system.
     * @return the current SolarSystem of the player
     */
    public SolarSystem getCurrentSystem() {
        return currentSystem;
    }

    /**
     * Set player's current system.
     * @param currentSystem the player's current SolarSystem
     */
    public void setCurrentSystem(SolarSystem currentSystem) {
        this.currentSystem = currentSystem;
    }

    /**
     * Get player's current planet.
     * @return the current Planet of the player
     */
    public Planet getCurrentPlanet() {
        return currentPlanet;
    }

    /**
     * Set player's current planet.
     * @param currentPlanet the current planet of the player
     */
    public void setCurrentPlanet(Planet currentPlanet) {
        this.currentPlanet = currentPlanet;
    }

    /**
     * Get player's ship.
     * @return the player's ship
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Set player's ship.
     * @param ship the Ship of the player
     */
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    /**
     * Get player's credits.
     * @return the amount of credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Set player's credits.
     * @param credits the amount of credits
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }
    
    /**
     * Get player's game state.
     * @return the player's game state
     */
    public boolean getState() {
        return state;
    }
    
    /**
     * Set player's state.
     * @param state the game state
     */
    public void setState(boolean state) {
        this.state = state;
    }
}
