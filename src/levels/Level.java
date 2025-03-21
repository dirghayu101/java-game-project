package levels;


import java.util.ArrayList;

import game.GameConstants;
import questions.*;

public class Level {
    String difficulty; 

    public Level(String chosenDifficulty){
        this.difficulty = chosenDifficulty;
  }

    public ArrayList<Question> initQuestions(){
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
