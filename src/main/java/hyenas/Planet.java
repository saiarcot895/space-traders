import java.util.Random;

public class Planet {
    private double locX;
    private double locY;

    public Planet() {
        Random rand = new Random();
        this.locX = rand.nextDouble();
        this.locY = rand.nextDouble();
    }
}
