
package hyenas;

public class Wares {
    
    /* Name of good */
    private String name;
    /* Minimum Tech Level to Produce */
    private int mtlp;
    /* Minimum Tech Level to Use */
    private int mtlu;
    /* Tech Level Production */
    private int ttp;
    /* The Cost of the good */
    private int basePrice;
    /* Price Increase Per Tech Level */
    private int ipl;
    /* Variance of good */
    private int var;
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

    public Wares() {
        
    }
    
    public String getName()    {
        return name;
    }
    
    public int getMTLP()    {
        return mtlp;
    }
    
    public int getMTLU()    {
        return mtlu;
    }
    
    public int getTTP()    {
        return ttp;
    }
    
    public int getBasePrice()   {
        return basePrice;
    }
    
    public int getIPL() {
        return ipl;
    }
    
    public int getVar() {
        return var;
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
    
    public enum Good {
        Water,
        Furs,
        Food,
        Ore,
        Games,
        Firearms,
        Medicine,
        Machines,
        Narcotics,
        Robots
    }
    
    Good good;
    
    public Wares(Good good) {
        this.good = good;
    }
    
    public void setUp() {
        switch(good) {
            case Water:
                name = "Water";
                mtlp = 0;
                mtlu = 0;
                ttp = 2;
                basePrice = 30;
                ipl = 3;
                var = 4;
                mtl = 30;
                mth = 50;
                break;
            case Furs:
                name = "Furs";
                mtlp = 0;
                mtlu = 0;
                ttp = 0;
                basePrice = 250;
                ipl = 10;
                var = 10;
                mtl = 230;
                mth = 280;
                break;
            case Food:
                name = "Food";
                mtlp = 1;
                mtlu = 0;
                ttp = 1;
                basePrice = 100;
                ipl = 5;
                var = 5;
                mtl = 90;
                mth = 160;
                break;
            case Ore:
                name = "Ore";
                mtlp = 2;
                mtlu = 2;
                ttp = 3;
                basePrice = 350;
                ipl = 20;
                var = 10;
                mtl = 350;
                mth = 420;
                break;
            case Games:
                name = "Games";
                mtlp = 3;
                mtlu = 1;
                ttp = 6;
                basePrice = 250;
                ipl = -10;
                var = 5;
                mtl = 160;
                mth = 270;
                break;
            case Firearms:
                name = "Firearms";
                mtlp = 3;
                mtlu = 1;
                ttp = 5;
                basePrice = 1250;
                ipl = -75;
                var = 100;
                mtl = 600;
                mth = 1100;
                break;
            case Medicine:
                name = "Medicine";
                mtlp = 4;
                mtlu = 1;
                ttp = 6;
                basePrice = 650;
                ipl = -20;
                var = 10;
                mtl = 400;
                mth = 700;
                break;
            case Machines:
                name = "Machines";
                mtlp = 4;
                mtlu = 3;
                ttp = 5;
                basePrice = 900;
                ipl = -30;
                var = 5;
                mtl = 600;
                mth = 800;
                break;
            case Narcotics:
                name = "Narcotics";
                mtlp = 5;
                mtlu = 0;
                ttp = 5;
                basePrice = 3500;
                ipl = -125;
                var = 150;
                mtl = 2000;
                mth = 3000;
                break;
            case Robots:
                name = "Robots";
                mtlp = 6;
                mtlu = 4;
                ttp = 7;
                basePrice = 5000;
                ipl = -150;
                var = 100;
                mtl = 3500;
                mth = 5000;
                break;
            default:
                // Do nothing
        }
    }
    
}
