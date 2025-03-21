package game;

import questions.Question;
import display.Display;
import display.DisplayConstants;
import player.Player;
import java.util.ArrayList;

public class Game {

    static Display userDisplay = Display.getDisplay();

    public Game() {
    }

    public void start(){
        Player player = this.initializePlayer();
        ArrayList<Question> questions = player.getQuestions();
        displayQuestions(questions);
    }

    private Player initializePlayer() {
        String username = userDisplay.getUserName();
        int levelChoice = userDisplay.showDisplayPromptUserInput(DisplayConstants.CHOOSE_LEVEL, true);
        handleInvalidOptions(1, 2, levelChoice);
        String levelChosen = levelChoice == 1 ? "easy" : "hard";
        return new Player(username, levelChosen);
    }

    private void displayQuestions(ArrayList<Question> questions) {
        for (Question currentQuestion : questions) {
            int chosenOption = userDisplay.showDisplayPromptUserInput(currentQuestion);
            handleInvalidOptions(1, 4, chosenOption);
            if (!currentQuestion.correctAnswer(chosenOption)) {
                System.out.println("Unfortunately, the chosen option is incorrect.");
                userDisplay.showDisplayScreenFile(DisplayConstants.ELIMINATE);
            } else if (currentQuestion.canWalkAway && userDisplay.playerWalkaway(currentQuestion)) {
                userDisplay.exit();
            } else {
            }
        }
    }

    private void handleInvalidOptions(int minOption, int maxOption, int chosenOption) {
        if (chosenOption > maxOption || chosenOption < minOption) {
            userDisplay.exit();
        }
    }
}
