package questions;

import java.util.ArrayList;

public class CallAFriendHelper {

    ArrayList<String> allQuestions = QuestionCompiler.getQuestionBank();
    ArrayList<String> possibleQuestions = new ArrayList<>();

    public CallAFriendHelper() {
        
    }
    
    public int getMatchingAnswerStatus(String questionText) {
        if(questionText == null){
            return 3;
        }
        questionText = questionText.toLowerCase().strip();
        for(String questionString: allQuestions){
            questionString = questionString.toLowerCase().strip();
            if(questionString.contains(questionText)){
                possibleQuestions.add(questionString);
            }
        }
        int possibilities = possibleQuestions.size();
        if(possibilities == 1){
            return 1;
        } else if(possibilities == 0){
            return 3;
        }
        return 2;
    }

    public String getAllPossibleAnswers() {
        String possibleAnswers = "";
        for(String question: possibleQuestions){
            String[] questionComponents = question.split("\\|");
            // Index of the answer.
            int answer = Integer.parseInt(questionComponents[5]);
            // Actual answer.
            String answerOption = questionComponents[answer] + " ";
            possibleAnswers = possibleAnswers.concat(answerOption);
        }
        return possibleAnswers;
    }

}
