import Display.Display;
import Display.DisplayConstants;
import java.util.ArrayList;
import player.Player;
import questions.Question;

public class App {
    static Display userDisplay = Display.getDisplay();
    public static void main(String[] args) throws Exception {
        // Uncomment the next line to test Question and QuestionBuilder classes.
        // App.questionTester();
        


    }

    public static void runApplication(){
        boolean startGame =  userDisplay.showWelcomeScreen();
        if(!startGame){
            userDisplay.exitGame();
        }
        Player player = initializePlayer();
        ArrayList<Question> questions = player.getQuestions();
        displayQuestions(questions);
    }

    public static Player initializePlayer() {
        String username = userDisplay.getUserName();
        int levelChoice = userDisplay.showDisplayPromptUserInput(DisplayConstants.CHOOSE_LEVEL, true);
        handleInvalidOptions(1, 2, levelChoice);
        String levelChosen = levelChoice == 1 ? "easy" : "hard"; 
        return new Player(username, levelChosen);
    }

    public static void displayQuestions(ArrayList<Question> questions){
        for(Question currentQuestion: questions){
            int chosenOption = userDisplay.showDisplayPromptUserInput(currentQuestion);
            handleInvalidOptions(1, 4, chosenOption);
            if(!currentQuestion.correctAnswer(chosenOption)){
                System.out.println("Unfortunately, the chosen option is incorrect.");
                userDisplay.showDisplayScreenFile(DisplayConstants.ELIMINATE);
            } else if(currentQuestion.canWalkAway && userDisplay.playerWalkaway(currentQuestion)){
                    userDisplay.exitGame();
            }else{
            }
        }
    }

    public static void handleInvalidOptions(int minOption, int maxOption, int chosenOption){
        if(chosenOption > maxOption || chosenOption < minOption){
            userDisplay.exitGame();
        }
    }



}
