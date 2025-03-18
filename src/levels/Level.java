package levels;

import java.util.ArrayList;
import questions.*;

public abstract class Level {
    String difficulty;
    ArrayList<Question> questions; 

    public Level(String chosenDifficulty){
        this.difficulty = chosenDifficulty;
        this.initQuestions();
    }

    private void initQuestions(){
        switch (this.difficulty) {
            case "easy" -> {
                QuestionBuilder qb = new QuestionBuilder(System.getenv("easyPrizeMap"), 15);
                questions = qb.getQuestions();
            }
            case "hard" -> {
                QuestionBuilder qb = new QuestionBuilder(System.getenv("hardPrizeMap"), 9);
                questions = qb.getQuestions();
            }
            default -> throw new AssertionError("Difficulty level passed as an argument does not exist.");
        }
    }
}
