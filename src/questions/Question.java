package questions;

public class Question {
    int questionId;
    private final int correctAnswer;
    public String questionText;
    public String[] options;
    public boolean canWalkAway = false;
    public boolean canUseLifeline = false;
    public int prizeAmount = 0;


    public Question(int questionId, int correctAnswer, String questionText, String[] options, boolean canWalkAway, boolean canUseLifeline, int prizeAmount) {
        this.questionId = questionId;
        this.correctAnswer = correctAnswer;
        this.questionText = questionText;
        this.options = options;
        this.canUseLifeline = canUseLifeline;
        this.canWalkAway = canWalkAway;
        this.prizeAmount = prizeAmount;
    }

    public boolean isCorrectAnswer(int option){
        return option == this.correctAnswer;
    }

    public void printQuestion(){
        System.out.println(questionId + ". " + questionText + "\n");
        for(int i = 1; i <= 4; i++){
            System.out.println("Option " + i + ": " + options[i-1]);
        }
        System.out.println("\nPrize Amount: $"+ prizeAmount + "\n");
    }
}
