package questions;

import utility.GeneralUtilityMethods;

public class Question {
    public int questionId;
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

    public Question removeTwoIncorrectOptions(){
        GeneralUtilityMethods util = new GeneralUtilityMethods();
        int keepOption = util.getRandomOption(this.correctAnswer);
        String[] modifiedOptions = util.modifyOptions(this.correctAnswer, keepOption, options);
        return new Question(this.questionId, 1, this.questionText, modifiedOptions, this.canWalkAway, this.canUseLifeline, this.prizeAmount);
    }

    public double[] audiencePollOnOptions(){
        GeneralUtilityMethods util = new GeneralUtilityMethods();
        return util.audiencePollResult(this.options.length, this.correctAnswer);
    }

    public boolean isCorrectAnswer(int option){
        return option == this.correctAnswer;
    }
}
