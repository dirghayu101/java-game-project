package game;

import display.Display;
import display.DisplayConstants;
import java.util.ArrayList;
import player.Player;
import questions.Question;

/**
 * The Game class is responsible for managing the game flow, including initializing the player,
 * displaying questions, and handling user interactions.
 */
public class Game {

    static Display userDisplay = Display.getDisplay();
    // The walkable factor determines how many questions are asked before a player can walk away.
    int walkableFactor = 5;

    public Game() {
    }

    /**
     * Starts the game by displaying the welcome screen, initializing the player, and managing the game flow.
     */
    public void start(){
        userDisplay.startGame();
        Player player = this.initializePlayer();
        ArrayList<Question> questions = player.getQuestions();
        displayQuestions(questions);
        userDisplay.showDisplayScreenFile(DisplayConstants.GAME_WIN, 700);
        userDisplay.exit();
    }

    /**
     * Initializes the player by prompting for their name and level choice.
     * @return a Player object with the chosen name and level
     */
    private Player initializePlayer() {
        String username = userDisplay.getUserName("Please enter your name.");
        int levelChoice = userDisplay.showDisplayPromptUserInput(DisplayConstants.CHOOSE_LEVEL, true, 1, 2);
        String levelChosen = levelChoice == 1 ? "easy" : "hard";
        // Modifies game class variables based on chosen level.
        // Walkable factor is the number of questions before a player can walk away. 
        // Based on the level chosen, the number of questions before a player can walk away is set.
        if(levelChosen.equalsIgnoreCase("easy")){
            this.walkableFactor = 3;
        }
        return new Player(username, levelChosen);
    }

    /**
     * Displays the questions to the user and handles their responses.
     * @param questions an ArrayList of Question objects to be displayed
     */
    private void displayQuestions(ArrayList<Question> questions) {
        int roundQuestionCount = 1;
        int currentRound = 1;
        userDisplay.roundStart(currentRound);
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
                
                // Prompt user if they wanna walkaway. This prompt should not be asked when the question is the last one.
                if(currentQuestion.prizeAmount != 1000000  ){
                    if(userDisplay.playerWalkaway(currentQuestion)){
                        userDisplay.exit();
                    }
                    roundQuestionCount = 0;
                    currentRound++;
                    userDisplay.roundStart(currentRound);
                }

            }
            roundQuestionCount++;
        }
    }


}
