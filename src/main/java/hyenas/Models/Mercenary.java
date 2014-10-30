package hyenas.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A Mercenary that can be hired and added to a ship
 * @author Alex
 */
class Mercenary {
    private int pilotSkill;
    private int fighterSkill;
    private int traderSkill;
    private int engineerSkill;
    private int investorSkill;
    private int price;
    private String name;
    
    /**
     * constructor for mercenary
     * randomly assigns mercenary's skills and name then generates cost accordingly
     */
    public Mercenary()  {
        Random rand = new Random();
        String[] names = new String[] {
            "Hoban Washburne", "Inara Serra", "Malcolm Reynolds", "Jayne Cobb",
            "Zoe Washburne", "Kaylee Frye", "Simon Tam", "River Tam", "Derrial Book"
        };
        name = names[rand.nextInt(names.length)];
        pilotSkill = rand.nextInt(30);
        fighterSkill = rand.nextInt(30);
        traderSkill = rand.nextInt(30);
        engineerSkill = rand.nextInt(30);
        investorSkill = rand.nextInt(30);
        price = (pilotSkill + fighterSkill + traderSkill + engineerSkill + investorSkill)*50;
    }
    
    /**
     * constructor for mercenary
     * takes in generated mercenary's skills and name
     * @param name, new mercenary's name
     * @param pilot, new mercenary's pilot skill
     * @param fighter, new mercenary's fighter skill
     * @param trader, new mercenary's trader skill
     * @param engineer, new mercenary's engineer skill
     * @param investor , new mercenary's investor skill
     */
    public Mercenary(String name, int pilot, int fighter, int trader, int engineer, int investor)    {
        this.name = name;
        pilotSkill = pilot;
        fighterSkill = fighter;
        traderSkill = trader;
        engineerSkill = engineer;
        investorSkill = investor;
        price = (pilotSkill + fighterSkill + traderSkill + engineerSkill + investorSkill)*50;
    }
    
    /**
     * return the mercenary's pilot skill
     * @return the mercenary's pilot skill
     */
    public int getPilotSkill()  {
        return pilotSkill;
    }
    
    /**
     * return the mercenary's fighter skill
     * @return the mercenary's fighter skill
     */
    public int getFighterSkill()  {
        return fighterSkill;
    }
    
    /**
     * return the mercenary's trader skill
     * @return the mercenary's trader skill
     */
    public int getTraderSkill()  {
        return traderSkill;
    }
    
    /**
     * return the mercenary's engineer skill
     * @return the mercenary's engineer skill
     */
    public int getEngineerSkill()  {
        return engineerSkill;
    }
    
    /**
     * return the mercenary's investor skill
     * @return the mercenary's investor skill
     */
    public int getInvestorSkill()  {
        return investorSkill;
    }
    
    /**
     * return the mercenary's price
     * @return the mercenary's price
     */
    public int getPrice()   {
        return price;
    }
    
    /**
     * return the mercenary's name
     * @return the mercenary's name
     */
    public String getName() {
        return name;
    }
}
