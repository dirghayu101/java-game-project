package questions;

public class Question {
    int questionId;
    public int questionNum;
    private final int correctAnswer;
    public String questionText;
    public String[] options;
    public boolean canWalkAway = false;
    public boolean canUseLifeline = false;
    public int prizeAmount = 0;


    public Question(int questionId, int questionNum, int correctAnswer, String questionText, String[] options, boolean canWalkAway, boolean canUseLifeline, int prizeAmount) {
        this.questionId = questionId;
        this.questionNum = questionNum;
        this.correctAnswer = correctAnswer;
        this.questionText = questionText;
        this.options = options;
        this.canUseLifeline = canUseLifeline;
        this.canWalkAway = canWalkAway;
        this.prizeAmount = prizeAmount;
    }

    public boolean checkAnswer(int option){
        return option == this.correctAnswer;
    }
}
