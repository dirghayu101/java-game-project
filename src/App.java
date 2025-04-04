import game.Game;
import display.Display;

public class App {

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
