package game;
import java.util.*;

public class Player implements Comparable<Player> {
    private int score, modifiers, maxDie, playerLevel;
    private boolean isHuman;
    private String name;
    private Random random;
    private Set<Die> dice;
    private Scanner scanner;

    // Create human player by inputting a scanner
    Player(Scanner scanner) {
        this(true, null, scanner);
    }

    // Create a CPU player by inputting a number (player's name will be CPU #cpuNum).
    Player(int cpuNum) {
        this(false, "CPU #" + cpuNum, null);
    }

    // Private constructor doing all the heavy work
    private Player(boolean isHuman, String name, Scanner scanner) {
        if (name != null) {
            this.name = name;
        } else {
            this.name = queryName(scanner);
        }
        this.isHuman = isHuman;
        this.scanner = scanner;
        dice = new HashSet<>();
        random = new Random();
        score = 0;
        playerLevel = 1;
        modifiers = 0;
        maxDie = 0;
    }

    // Add a die to the set.
    void addDie(Die die) {
        if (die.getSides() > maxDie) maxDie = die.getSides();
        dice.add(die);
    }

    // Roll all dice from the set. If there are no dice in the set, return 0.
    int rollAll() {
        int result = 0;
        for (Die die : dice) result += die.rollDie();
        return result + modifiers;
    }

    private int upgradeCost() {
        return 10 + 10 * (playerLevel / 2 * (playerLevel + 1));
    }

    // Add a value to the player's score. Return the new score.
    int addScore(int newScore) {
        score += newScore;
        if (score >= upgradeCost()) {
            // The player can choose to buy an upgrade!!!
            if (isHuman) {
                // Manual choice
                upgradePicker();
                playerLevel += 1;

            } else {
                // Automated choice
                double choice = random.nextDouble();

                if (choice > 0.8) {
                    // Get a dice.
                    int newDieSides = random.nextInt((int) 2.5 * maxDie) - maxDie + 1;
                    addDie(new Die(newDieSides, random));
                    System.out.println(name + " hit another upgrade level and got themselves a new die with " + newDieSides + " sides!");
                    score -= upgradeCost();

                } else if (choice > 0.5) {
                    // Get a modifier.
                    Integer newModifier = null;
                    while (newModifier == null || newModifier == 0) {
                        newModifier = random.nextInt(5) - 2;
                    }
                    modifiers += newModifier;
                    System.out.println(name + " hit another upgrade level and got a " + newModifier + " modifier!");
                    score -= upgradeCost();

                } else {
                    // Don't get anything
                    System.out.println(name + " was eligible to pick an upgrade, but instead chose to keep their points.");
                }
                playerLevel += 1;
            }
        }
        return score;
    }

    private void upgradePicker() {
        System.out.println(name + " has reached a new level! They may now pick an upgrade!\n" +
                "Upgrade costs will increase as a result of your level, get them while they're cheap!\n\n" +
                "These are your options:\n" +
                "modifier :  Add a permanent modifier to your rolls.\n" +
                "die      :  Add a new die to your collection.\n" +
                "nothing  :  Keep your points and win the game.\n\n" +
                "At your current level an upgrade costs " + upgradeCost() + " points.\n");

        boolean hasChosen = false;
        while (!hasChosen) {
            System.out.print("> ");
            String choice = scanner.nextLine().strip().toLowerCase();
            switch (choice) {
                case "modifier":    getNewModifier(); hasChosen = true; break;
                case "die":         getNewDie(); hasChosen = true; break;
                case "nothing":     hasChosen = true; break;
            }
            if (!hasChosen) {
                System.out.println("Choice returned no matches. Try again.");
            }
        }
    }

    private void getNewDie() {
        int newDieValue = 0; // This value will be replaced, but initialising it makes Java happy.
        boolean hasChosen = false;
        score -= upgradeCost();

        System.out.println("Time to get a new die! You have two options:\n" +
                "gamble : Get a die between "  + (-1 * maxDie)  + " and " + (2 * maxDie) + ".\n" +
                "safe   : Get a die between 0 and " + maxDie + ".\n");

        while (!hasChosen) {
            System.out.print("> ");
            String choice = scanner.nextLine().strip().toLowerCase();
            switch (choice) {
                case "gamble":
                    newDieValue = random.nextInt(3 * maxDie + 1) - maxDie;
                    hasChosen = true;
                    break;

                case "safe":
                    newDieValue = random.nextInt(maxDie + 1);
                    hasChosen = true;
                    break;
            }
        }
        addDie(new Die(newDieValue, random));
        System.out.println("Congratulations! You got a new die with " + newDieValue + " sides!");
    }

    private void getNewModifier() {
        int newModifierValue = 0; // This value will be replaced, but initialising it makes Java happy.
        boolean hasChosen = false;
        int gambleMin = (int) -1.5 * playerLevel;
        score -= upgradeCost();

        System.out.println("Time to get a new modifier! You have two options:\n" +
                "gamble : Get a modifier between " + ((int) -1.5 * playerLevel) + " and " + (3 * playerLevel) + ".\n" +
                "safe   : Get a modifier of " + (playerLevel) + ".\n");

        while (!hasChosen) {
            System.out.print("> ");
            String choice = scanner.nextLine().strip().toLowerCase();
            switch (choice) {
                case "gamble":
                    newModifierValue = random.nextInt(gambleMin + 3 * playerLevel + 1) - gambleMin;
                    hasChosen = true;
                    break;

                case "safe":
                    newModifierValue = playerLevel;
                    hasChosen = true;
                    break;
            }
        }
        modifiers += newModifierValue;
        System.out.println("Congratulations! You got a new modifier of " + newModifierValue + ", bringing your total modifiers to " + modifiers + ".");
    }

    // Simple getter functions
    String getName() { return name; }
    int getNumDice() { return dice.size(); }
    int getModifiers() { return modifiers; }

    private String queryName(Scanner scanner) {
        String name = null;
        while (name == null) {
            System.out.print("Player name: ");
            try {
                String nameAttempt = scanner.nextLine().strip();
                if (nameAttempt.isBlank()) {
                    System.out.println("Your choice of name was either blank or empty. Am I a joke to you?");
                } else {
                    name = nameAttempt;
                }
            } catch (Exception e) {
                System.out.println("Something went wrong, try again.");
            }
        }
        return name;
    }

    @Override
    public String toString() { return name + " (level " + playerLevel + ", " + score + " points, "  + getNumDice() + " dice)"; }

    @Override
    public int compareTo(Player player) {
        return player.score - score;
    }
}
