package player;

import java.util.ArrayList;
import levels.Level;
import questions.Question;

public class Player {
    
    Level level = null;
    ArrayList<Question> questions = new ArrayList<>();
    String name;

    // Constructor to initialize the player with a name and level
    // The level is initialized based on the level chosen by the player.
    public Player(String name, String levelChosen){
        this.name = name;
        this.level = new Level(levelChosen);
        this.questions = level.initQuestions();
    }

    // Method to get the questions for the player
    public ArrayList<Question> getQuestions(){
        return questions;
    }
}
