package hyenas;

import java.util.Random;

public class Planet {
    private double x;
    private double y;
    private double size;

    public Planet() {
        Random rand = new Random();
        this.x = rand.nextDouble();
        this.y = rand.nextDouble();
        this.size = rand.nextDouble();
    }
}
