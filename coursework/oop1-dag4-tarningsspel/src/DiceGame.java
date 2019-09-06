import game.Game;

public class DiceGame {
    public static void main(String[] args) {
        Game game = new Game();
        game.initializePlayers();
        game.start();
    }
}
