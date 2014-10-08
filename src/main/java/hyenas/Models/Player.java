package hyenas.Models;

/**
 * Represents the player
 * @author saikrishna
 */
public class Player {
    private String name;
    private int pilotSkill;
    private int fighterSkill;
    private int traderSkill;
    private int engineerSkill;
    private int investorSkill;
    private SolarSystem currentSystem;
    private Planet tradingPlanet;
    private Ship ship;
    private int credits;
    private static Player instance = null;

    /**
     * Initializes an instance of Player
     * Sets initial values
     */
    private Player() {
        // KEEP PRIVATE - use getInstance instead
        ship = new Ship(ShipType.FLEA);
        currentSystem = Galaxy.getInstance().getSolarSystems().values().iterator().next();
        tradingPlanet = currentSystem.getPlanets()[0];
        credits = 250;
    }
    
    /**
     * Getter for Player singleton
     * @return Player the common player instance
     */
    public static Player getInstance() {
        if(instance == null) {
            instance = new Player();
        }
        return instance;
    }

    /**
     * Get player's name
     * @return String the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Set player's name
     * @param name the String of the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get player's pilot skill
     * @return int the pilot skill of the player
     */
    public int getPilotSkill() {
        return pilotSkill;
    }

    /**
     * Set player's pilot skill
     * @param pilotSkill the pilot skill of the player
     */
    public void setPilotSkill(int pilotSkill) {
        this.pilotSkill = pilotSkill;
    }

    /**
     * Get player's fighter skill
     * @return int the fighter skill of the player
     */
    public int getFighterSkill() {
        return fighterSkill;
    }

    /**
     * Set player's fighter skill
     * @param fighterSkill the fighter skill of the player
     */
    public void setFighterSkill(int fighterSkill) {
        this.fighterSkill = fighterSkill;
    }

    /**
     * Get player's trader skill
     * @return int the trader skill of the player
     */
    public int getTraderSkill() {
        return traderSkill;
    }

    /**
     * Set player's trader skill
     * @param traderSkill the trader skill of the player
     */
    public void setTraderSkill(int traderSkill) {
        this.traderSkill = traderSkill;
    }

    /**
     * Get player's engineer skill
     * @return int the engineer skill of the player
     */
    public int getEngineerSkill() {
        return engineerSkill;
    }

    /**
     * Set player's engineer skill
     * @param engineerSkill the engineer skill of the player
     */
    public void setEngineerSkill(int engineerSkill) {
        this.engineerSkill = engineerSkill;
    }

    /**
     * Get player's investor skill
     * @return int the investor skill of the player
     */
    public int getInvestorSkill() {
        return investorSkill;
    }

    /**
     * Set player's investor skill
     * @param investorSkill the investor skill of the player
     */
    public void setInvestorSkill(int investorSkill) {
        this.investorSkill = investorSkill;
    }

    /**
     * Get player's current system
     * @return SolarSystem the current SolarSystem of the player
     */
    public SolarSystem getCurrentSystem() {
        return currentSystem;
    }

    /**
     * Set player's current system
     * @param currentSystem the player's current SolarSystem
     */
    public void setCurrentSystem(SolarSystem currentSystem) {
        this.currentSystem = currentSystem;
    }

    /**
     * Get player's trading planet
     * @return Planet the trading Planet of the player
     */
    public Planet getTradingPlanet() {
        return tradingPlanet;
    }

    /**
     * Set player's trading planet
     * @param tradingPlanet the trading Planet of the player
     */
    public void setTradingPlanet(Planet tradingPlanet) {
        this.tradingPlanet = tradingPlanet;
    }

    /**
     * Get player's ship
     * @return Ship the player's ship
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Set player's ship
     * @param ship the Ship of the player
     */
    public void setShip(Ship ship) {
        this.ship = ship;
    }
    
    /**
     * Get player's credits
     * @return int the amount of credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Set player's credits
     * @param credits the int amount of credits
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }
}
