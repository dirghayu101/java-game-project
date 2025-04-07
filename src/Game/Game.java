package game;

import display.Display;
import display.DisplayConstants;
import java.util.ArrayList;
import player.Player;
import questions.Question;

public class Game {

    static Display userDisplay = Display.getDisplay();

    public Game() {
    }

    public void start(){
        userDisplay.startGame();
        Player player = this.initializePlayer();
        ArrayList<Question> questions = player.getQuestions();
        displayQuestions(questions);
        userDisplay.showDisplayScreenFile(DisplayConstants.GAME_WIN, 700);
        userDisplay.exit();
    }

    private Player initializePlayer() {
        String username = userDisplay.getUserName("Please enter your name.");
        int levelChoice = userDisplay.showDisplayPromptUserInput(DisplayConstants.CHOOSE_LEVEL, true, 1, 2);
        String levelChosen = levelChoice == 1 ? "easy" : "hard";
        return new Player(username, levelChosen);
    }

    private void displayQuestions(ArrayList<Question> questions) {
        for (Question currentQuestion : questions) {
            int chosenOption = userDisplay.showDisplayPromptUserInput(currentQuestion);
            if (!currentQuestion.isCorrectAnswer(chosenOption)) {
                System.out.println("Unfortunately, the chosen option is incorrect.");
                userDisplay.showDisplayScreenFile(DisplayConstants.ELIMINATE, 300);
                userDisplay.exit();
            } else if (currentQuestion.canWalkAway && userDisplay.playerWalkaway(currentQuestion)) {
                userDisplay.exit();
            } else {
                userDisplay.showDisplayScreenFile(DisplayConstants.CORRECT_ANSWER, 350);
                userDisplay.putSpaceOnDisplay(7, "");
            }
        }
    }
}
