package player;

import java.util.ArrayList;
import levels.Level;
import questions.Question;

public class Player {
    
    Level level = null;
    ArrayList<Question> questions = new ArrayList<>();
    String name;

    public Player(String name, String levelChosen){
        this.name = name;
        this.level = new Level(levelChosen);
        this.questions = level.initQuestions();
    }

    public ArrayList<Question> getQuestions(){
        return questions;
    }
}
