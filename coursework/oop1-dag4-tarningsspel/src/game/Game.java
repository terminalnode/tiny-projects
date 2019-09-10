package game;
/* The game module handles all the interaction with the game.*/
import java.util.*;

public class Game {
    private Scanner scanner;
    private Random random;
    private List<Player> players;

    public Game() {
        players = new ArrayList<>();
        scanner = new Scanner(System.in);
        random = new Random();
    }

    public void initializePlayers(){
        int humanPlayers = 0;
        int cpuPlayers = 0;
        int minimumPlayers = 1;
        while (humanPlayers + cpuPlayers < minimumPlayers) {
            // Ask how many human and cpu players that are going to participate in the game.
            System.out.println("How many human players will you have?");
            humanPlayers = getInteger(true, true);
            System.out.println("How many CPU players will you have?");
            cpuPlayers = getInteger(true);

            // Check if the total number of players is enough.
            int totalPlayers = humanPlayers + cpuPlayers;
            if (totalPlayers < minimumPlayers) {
                System.out.println("Your game would have a total of " + totalPlayers + ".\n" +
                        "That's not enough, you need at least " + minimumPlayers + " player(s).\n");
            }
        }

        // Check how many dice each player should start with.
        int[] diceSelection = null;
        System.out.println("\nPick you starting dice set by typing a string of numbers.\n" +
                "For example, typing \"6 6 6\" will start the game with three six-sided dice.\n" +
                "Leave empty for default selection.");
        while (diceSelection == null) {
            System.out.print("Selection: ");
            diceSelection = getDiceSelection(scanner.nextLine().strip());
        }

        // Create the players
        if (humanPlayers > 0) System.out.println("\nCreating " + humanPlayers + " human players.");
        for (int i = 0; i < humanPlayers; i++) {
            players.add(new Player(scanner));
        }

        if (cpuPlayers > 0) System.out.println("\nCreating " + cpuPlayers + " CPU players.");
        for (int i = 0; i < cpuPlayers; i++) {
            players.add(new Player(i + 1));
        }

        // Give all the players some dice
        for (Player player : players) {
            for (int numSides : diceSelection) {
                player.addDie(new Die(numSides, random));
            }
        }
        System.out.println(); // Empty line to separate the initialization from the rest.
    }

    public void start() {
        System.out.println("Welcome to\n" +
                " ____  _           __        __         _     _    ___   ___   ___   ___  \n" +
                "|  _ \\(_) ___ ___  \\ \\      / /__  _ __| | __| |  / _ \\ / _ \\ / _ \\ / _ \\ \n" +
                "| | | | |/ __/ _ \\  \\ \\ /\\ / / _ \\| '__| |/ _` | | (_) | | | | | | | | | |\n" +
                "| |_| | | (_|  __/   \\ V  V / (_) | |  | | (_| |  \\__, | |_| | |_| | |_| |\n" +
                "|____/|_|\\___\\___|    \\_/\\_/ \\___/|_|  |_|\\__,_|    /_/ \\___/ \\___/ \\___/ \n");

        boolean gameOver = false;
        System.out.println("Type \"roll\" to roll all dice or \"end\" to tally up the scores and determine a winner.");
        while (!gameOver) {
            System.out.print("> ");
            String command = scanner.nextLine().strip().toLowerCase();
            if (command.isBlank()) continue;

            // Interpret command
            switch (command) {
                case "roll": rollAllDice(); break;
                case "end":  tallyScore(); gameOver = true; break;
            }

            // Wanna play again?
            if (gameOver) {
                System.out.println("Would you like to restart the game? [y/n]");
                do {
                    System.out.print("> ");
                    command = scanner.nextLine().strip().toLowerCase();
                } while(command.length() < 1);
                if ('y' == command.charAt(0)) {
                    players = new ArrayList<>();
                    System.out.println("\n");
                    initializePlayers();
                    gameOver = false;
                }
            }
        }
    }

    private void rollAllDice() {
        for (Player player : players) {
            System.out.print(player.getName() + " is preparing to roll their " + player.getNumDice() + " dice. ");
            if (player.getModifiers() != 0) {
                System.out.println("They have a " + player.getModifiers() + " modifier.");
            } else {
                System.out.println();
            }

            int rollResult = player.rollAll();
            int newScore = player.addScore(rollResult);
            System.out.println(player.getName() + " rolled " + rollResult + ". New total score: " + newScore + "\n");
        }
        System.out.println();
    }

    private void tallyScore() {
        Collections.sort(players);
        for (int i = 0; i < players.size(); i++) {
            System.out.println("#" + (i + 1) + ": " + players.get(i));
        }
    }

    // Helper functions
    private int[] getDiceSelection(String initialSelection) {
        String[] selection = initialSelection.split("\\s+");
        int[] result = new int[selection.length];

        for (int i = 0; i < selection.length; i++) {
            if (selection[i].isBlank()) {
                System.out.println("Starting with the default value of three six-sided dice.");
                return new int[] {6,6,6};
            }
            try {
                result[i] = Integer.parseInt(selection[i]);
            } catch (Exception e) {
                System.out.println("Sorry, but " + selection[i] + " doesn't seem like an integer to me. Please try again.");
                System.out.println("All integers are allowed. Even negative-sided and zero-sided dice are available!\n");
                return null;
            }
        }
        return result;
    }

    private int getInteger(boolean allowZero) {
        return getInteger(allowZero, false);
    }

    private int getInteger(boolean allowZero, boolean allowNegative) {
        // Returns an integer from user input.
        Integer result = null;
        System.out.print("Number: ");
        while (result == null) {
            try {
                int numberInput = Integer.parseInt(scanner.nextLine());
                if (!allowZero && numberInput == 0) {
                    System.out.println("Zero is not allowed!");
                    throw new IllegalArgumentException("Zero is not allowed.");
                } else if (!allowNegative && numberInput < 0) {
                    System.out.println("Negative numbers are not allowed!");
                    throw new IllegalArgumentException("Negative numbers are not allowed!");
                } else {
                    result = numberInput;
                }
            } catch (Exception e) {
                System.out.print("\nBetter number: ");
            }
        }
        return result;
    }
}
