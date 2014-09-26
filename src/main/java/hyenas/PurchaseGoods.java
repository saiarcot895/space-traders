
package hyenas;

import java.util.Random;

public interface PurchaseGoods {
    
    /**
     * Function to purchase goods
     */
    public void purchase();
    
    /**
     * Function to sell goods
     */
    public void sell();
    
    /**
     * Check if good can be sold at the tech level
     */
    public void techLevelCompatibility();
    
    /**
     * 
     * @param max
     * @param min
     * @return Variance
     */
    /*
    public static int calculateVariance(int max, int min) {
        Random random = new Random();
        int variance = random.nextInt((max - min) + 1) + min;
        return variance;
    }
    
    public static int coinFlip() {
        return 0;
    }
    */
}
