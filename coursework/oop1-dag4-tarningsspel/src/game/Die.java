package game;
import java.util.Random;

public class Die {
    private int sides;
    private Random random;

    Die(int sides, Random random) {
        this.sides = sides; // Any number of sides.
        this.random = random;
    }

    int getSides() { return sides; }

    int rollDie() {
        if (sides == 0) {
            return 0;
        } else if (sides < 0) {
            return -1 * random.nextInt(sides * -1) - 1;
        } else {
            return random.nextInt(sides) + 1;
        }
    }

    @Override
    public String toString() {
        return "Die(" + sides + ")";
    }
}
