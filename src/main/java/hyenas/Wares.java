
package hyenas;

public class Wares {
    
    /* Name of good */
    private String name;
    /* Minimum Tech Level to Produce */
    private int MTLP;
    /* Minimum Tech Level to Use */
    private int MTLU;
    /* Tech Level Production */
    private int TTP;
    /* The Cost of the good */
    private int BasePrice;
    /* Price Increase Per Tech Level */
    private int IPL;
    /* Variance of good */
    private int VAR;
    /* For Extra Credit TODO! */
    private String IE;
    /* For Extra Credit TODO! */
    private String CR;
    /* For Extra Credit TODO! */
    private String ER;
    /* Min Price in space trade for good (Random Encounter) */
    private int MTL;
    /* Max Price in space trade for good (Random Encounter) */
    private int MTH;
    
    
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
                MTLP = 0;
                MTLU = 0;
                TTP = 2;
                BasePrice = 30;
                IPL = 3;
                VAR = 4;
                MTL = 30;
                MTH = 50;
                break;
            case Furs:
                name = "Furs";
                MTLP = 0;
                MTLU = 0;
                TTP = 0;
                BasePrice = 250;
                IPL = 10;
                VAR = 10;
                MTL = 230;
                MTH = 280;
                break;
            case Food:
                name = "Food";
                MTLP = 1;
                MTLU = 0;
                TTP = 1;
                BasePrice = 100;
                IPL = 5;
                VAR = 5;
                MTL = 90;
                MTH = 160;
                break;
            case Ore:
                name = "Ore";
                MTLP = 2;
                MTLU = 2;
                TTP = 3;
                BasePrice = 350;
                IPL = 20;
                VAR = 10;
                MTL = 350;
                MTH = 420;
                break;
            case Games:
                name = "Games";
                MTLP = 3;
                MTLU = 1;
                TTP = 6;
                BasePrice = 250;
                IPL = -10;
                VAR = 5;
                MTL = 160;
                MTH = 270;
                break;
            case Firearms:
                name = "Firearms";
                MTLP = 3;
                MTLU = 1;
                TTP = 5;
                BasePrice = 1250;
                IPL = -75;
                VAR = 100;
                MTL = 600;
                MTH = 1100;
                break;
            case Medicine:
                name = "Medicine";
                MTLP = 4;
                MTLU = 1;
                TTP = 6;
                BasePrice = 650;
                IPL = -20;
                VAR = 10;
                MTL = 400;
                MTH = 700;
                break;
            case Machines:
                name = "Machines";
                MTLP = 4;
                MTLU = 3;
                TTP = 5;
                BasePrice = 900;
                IPL = -30;
                VAR = 5;
                MTL = 600;
                MTH = 800;
                break;
            case Narcotics:
                name = "Narcotics";
                MTLP = 5;
                MTLU = 0;
                TTP = 5;
                BasePrice = 3500;
                IPL = -125;
                VAR = 150;
                MTL = 2000;
                MTH = 3000;
                break;
            case Robots:
                name = "Robots";
                MTLP = 6;
                MTLU = 4;
                TTP = 7;
                BasePrice = 5000;
                IPL = -150;
                VAR = 100;
                MTL = 3500;
                MTH = 5000;
                break;
            default:
                // Do nothing
        }
    }
    
}
