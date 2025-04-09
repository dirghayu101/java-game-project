package questions;

import utility.GeneralUtilityMethods;

public class Question {
    public int questionId;
    private int correctAnswer;
    public String questionText;
    public String[] options;
    public boolean canUseLifeline = false;
    public int prizeAmount = 0;


    public Question(int questionId, int correctAnswer, String questionText, String[] options, boolean canUseLifeline, int prizeAmount) {
        this.questionId = questionId;
        this.correctAnswer = correctAnswer;
        this.questionText = questionText;
        this.options = options;
        this.canUseLifeline = canUseLifeline;
        this.prizeAmount = prizeAmount;
    }

    // Gets the question's answer option value.
    public String getAnswer(){
        return this.options[this.correctAnswer - 1];
    }

    // Method to remove two incorrect options from the options array.
    // This method is used for the lifeline "50-50".
    public void removeTwoIncorrectOptions(){
        GeneralUtilityMethods util = new GeneralUtilityMethods();
        int keepOption = util.getRandomOption(this.correctAnswer);
        String[] modifiedOptions = util.modifyOptions(this.correctAnswer, keepOption, options);
        // This will help in validating the choice user enters.
        this.correctAnswer = 1;
        this.options = modifiedOptions;
    }

    // Method to perform an audience poll on the options.
    public double[] audiencePollOnOptions(){
        GeneralUtilityMethods util = new GeneralUtilityMethods();
        return util.audiencePollResult(this.options.length, this.correctAnswer);
    }

    // Method to verify if the option selected by the user is correct.
    public boolean isCorrectAnswer(int option){
        return option == this.correctAnswer;
    }
}
