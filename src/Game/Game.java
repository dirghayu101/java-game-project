package game;

import display.Display;
import display.DisplayConstants;
import java.util.ArrayList;
import player.Player;
import questions.Question;

public class Game {

    static Display userDisplay = Display.getDisplay();
    int walkableFactor = 5;

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
        // Modifies game class variables based on chosen level.
        if(levelChosen.equalsIgnoreCase("easy")){
            this.walkableFactor = 3;
        }
        return new Player(username, levelChosen);
    }

    private void displayQuestions(ArrayList<Question> questions) {
        int roundQuestionCount = 1;
        int currentRound = 1;
        for (Question currentQuestion : questions) {
            int chosenOption = userDisplay.showDisplayPromptUserInput(currentQuestion);
            if (!currentQuestion.isCorrectAnswer(chosenOption)) {
                System.out.println("Unfortunately, the chosen option is incorrect.");
                userDisplay.showDisplayScreenFile(DisplayConstants.ELIMINATE, 300);
                userDisplay.exit();
            } 
            userDisplay.showDisplayScreenFile(DisplayConstants.CORRECT_ANSWER, 350);
            userDisplay.putSpaceOnDisplay(7, "");
            // This if condition will trigger at the end of a round.
            if(roundQuestionCount == walkableFactor){
                
                userDisplay.roundCleared(currentRound);
                // This if condition will prompt user to choose if they wanna walkaway. The player would not be prompted to walkaway if it is the final question as they have already won the game at that point.
                if(currentQuestion.prizeAmount != 1000000 && userDisplay.playerWalkaway(currentQuestion)){
                    userDisplay.exit();
                }
                roundQuestionCount = 0;
                currentRound++;
            }
            roundQuestionCount++;
        }
    }
}
