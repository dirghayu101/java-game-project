package levels;


import Game.GameConstants;
import java.util.ArrayList;
import questions.*;

public class Level {
    String difficulty;
    ArrayList<Question> questions; 

    public Level(String chosenDifficulty){
        this.difficulty = chosenDifficulty;
        this.initQuestions();
  }

    private void initQuestions(){
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
    }

    public void printQuestions(){
        System.out.println("Printing all questions in questions ArrayList.");
        for(Question currentQuestion: this.questions){
            currentQuestion.printQuestion();
        }
        System.out.println("Printed all questions in question ArrayList.");
    }
}
