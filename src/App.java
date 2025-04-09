import display.Display;
import game.Game;

public class App {

    /**
     * Main method to start the game.
     * @param args command line arguments
     * @throws Exception if an error occurs during game initialization
     */
    public static void main(String[] args) throws Exception {
        Display display = Display.getDisplay();
        boolean startGame = display.showWelcomeScreen();
        if (!startGame) {
            display.exit();
        }
        Game game = new Game();
        game.start();
    }

}
