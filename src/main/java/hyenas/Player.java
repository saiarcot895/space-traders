package hyenas;

/**
 *
 * @author saikrishna
 */
public class Player {
    private String name;
    private double locX;
    private double locY;
    private int pilotSkill;
    private int fighterSkill;
    private int traderSkill;
    private int engineerSkill;
    private int investorSkill;
    private SolarSystem currentSystem;
    private Planet tradingPlanet;
    private int credits;
    private static Player instance = null;

    protected Player() {
        // Exists only to defeat instantiation.
    }
    
    public static Player getInstance() {
        if(instance == null) {
            instance = new Player();
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPilotSkill() {
        return pilotSkill;
    }

    public void setPilotSkill(int pilotSkill) {
        this.pilotSkill = pilotSkill;
    }

    public int getFighterSkill() {
        return fighterSkill;
    }

    public void setFighterSkill(int fighterSkill) {
        this.fighterSkill = fighterSkill;
    }

    public int getTraderSkill() {
        return traderSkill;
    }

    public void setTraderSkill(int traderSkill) {
        this.traderSkill = traderSkill;
    }

    public int getEngineerSkill() {
        return engineerSkill;
    }

    public void setEngineerSkill(int engineerSkill) {
        this.engineerSkill = engineerSkill;
    }

    public int getInvestorSkill() {
        return investorSkill;
    }

    public void setInvestorSkill(int investorSkill) {
        this.investorSkill = investorSkill;
    }
    
    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
