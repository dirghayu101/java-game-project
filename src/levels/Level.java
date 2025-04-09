package levels;

import game.GameConstants;
import java.util.ArrayList;
import questions.*;

/**
 * The Level class is responsible for initializing the game level based on the chosen difficulty.
 * It creates a set of questions based on the difficulty level and returns them as an ArrayList.
 */
public class Level {
    String difficulty;

    public Level(String chosenDifficulty) {
        this.difficulty = chosenDifficulty;
    }

    public ArrayList<Question> initQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        switch (this.difficulty) {
            case "easy" -> {
                QuestionBuilder qb = new QuestionBuilder(GameConstants.EASY_PRIZE_MAP, 9);
                questions = qb.getQuestions();
            }
            case "hard" -> {
                QuestionBuilder qb = new QuestionBuilder(GameConstants.HARD_PRIZE_MAP, 15);
                questions = qb.getQuestions();
            }
            default -> throw new AssertionError("Difficulty level passed as an argument does not exist.");
        }
        return questions;
    }
}
