package hyenas;

public class Ware {
    
    /* Name of good */
    private String name;
    /* Minimum Tech Level to Produce */
    private int minimumTechLevelToProduce;
    /* Minimum Tech Level to Use */
    private int minimumTechLevelToUse;
    /* Tech Level Production */
    private int techLevelProduction;
    /* The Cost of the good */
    private int basePrice;
    /* Price Increase Per Tech Level */
    private int priceIncreasePerLevel;
    /* Variance of good */
    private int variance;
    /* For Extra Credit TODO! */
    private String ie;
    /* For Extra Credit TODO! */
    private String cr;
    /* For Extra Credit TODO! */
    private String er;
    /* Min Price in space trade for good (Random Encounter) */
    private int mtl;
    /* Max Price in space trade for good (Random Encounter) */
    private int mth;
    
    Good good;
    
    public Ware(Good good) {
        this.good = good;
        setUp();
    }
    
    private void setUp() {
        switch(good) {
            case Water:
                name = "Water";
                minimumTechLevelToProduce = 0;
                minimumTechLevelToUse = 0;
                techLevelProduction = 2;
                basePrice = 30;
                priceIncreasePerLevel = 3;
                variance = 4;
                mtl = 30;
                mth = 50;
                break;
            case Furs:
                name = "Furs";
                minimumTechLevelToProduce = 0;
                minimumTechLevelToUse = 0;
                techLevelProduction = 0;
                basePrice = 250;
                priceIncreasePerLevel = 10;
                variance = 10;
                mtl = 230;
                mth = 280;
                break;
            case Food:
                name = "Food";
                minimumTechLevelToProduce = 1;
                minimumTechLevelToUse = 0;
                techLevelProduction = 1;
                basePrice = 100;
                priceIncreasePerLevel = 5;
                variance = 5;
                mtl = 90;
                mth = 160;
                break;
            case Ore:
                name = "Ore";
                minimumTechLevelToProduce = 2;
                minimumTechLevelToUse = 2;
                techLevelProduction = 3;
                basePrice = 350;
                priceIncreasePerLevel = 20;
                variance = 10;
                mtl = 350;
                mth = 420;
                break;
            case Games:
                name = "Games";
                minimumTechLevelToProduce = 3;
                minimumTechLevelToUse = 1;
                techLevelProduction = 6;
                basePrice = 250;
                priceIncreasePerLevel = -10;
                variance = 5;
                mtl = 160;
                mth = 270;
                break;
            case Firearms:
                name = "Firearms";
                minimumTechLevelToProduce = 3;
                minimumTechLevelToUse = 1;
                techLevelProduction = 5;
                basePrice = 1250;
                priceIncreasePerLevel = -75;
                variance = 100;
                mtl = 600;
                mth = 1100;
                break;
            case Medicine:
                name = "Medicine";
                minimumTechLevelToProduce = 4;
                minimumTechLevelToUse = 1;
                techLevelProduction = 6;
                basePrice = 650;
                priceIncreasePerLevel = -20;
                variance = 10;
                mtl = 400;
                mth = 700;
                break;
            case Machines:
                name = "Machines";
                minimumTechLevelToProduce = 4;
                minimumTechLevelToUse = 3;
                techLevelProduction = 5;
                basePrice = 900;
                priceIncreasePerLevel = -30;
                variance = 5;
                mtl = 600;
                mth = 800;
                break;
            case Narcotics:
                name = "Narcotics";
                minimumTechLevelToProduce = 5;
                minimumTechLevelToUse = 0;
                techLevelProduction = 5;
                basePrice = 3500;
                priceIncreasePerLevel = -125;
                variance = 150;
                mtl = 2000;
                mth = 3000;
                break;
            case Robots:
                name = "Robots";
                minimumTechLevelToProduce = 6;
                minimumTechLevelToUse = 4;
                techLevelProduction = 7;
                basePrice = 5000;
                priceIncreasePerLevel = -150;
                variance = 100;
                mtl = 3500;
                mth = 5000;
                break;
            default:
                // Do nothing
        }
    }
    
    public String getName()    {
        return name;
    }
    
    public int getMTLP()    {
        return minimumTechLevelToProduce;
    }
    
    public int getMTLU()    {
        return minimumTechLevelToUse;
    }
    
    public int getTTP()    {
        return techLevelProduction;
    }
    
    public int getBasePrice()   {
        return basePrice;
    }
    
    public int getIPL() {
        return priceIncreasePerLevel;
    }
    
    public int getVar() {
        return variance;
    }
    
    public String getIE()   {
        return ie;
    }
    
    public String getCR()   {
        return cr;
    }
    
    public String getER()   {
        return er;
    }
    
    public int getMTL() {
        return mtl;
    }
    
    public int getMTH() {
        return mth;
    }
}
