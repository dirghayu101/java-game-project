package questions;

import java.util.ArrayList;

// This class is used to help the player with the Call A Friend lifeline.
public class CallAFriendHelper {

    ArrayList<String> allQuestions = QuestionCompiler.getQuestionBank();
    ArrayList<String> possibleQuestions = new ArrayList<>();

    public CallAFriendHelper() {
        
    }

    // This methods matches the question text with the questions in the question bank.
    // It returns 1 if there is only one possible question, 2 if there are multiple possible questions, and 3 if there are no possible questions.
    public int getMatchingAnswerStatus(String questionText) {
        if(questionText == null || "".equals(questionText)){
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

    // This method returns the possible questions that match the question text.
    // Returns a single String answer with all possible answers as friend's response.
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
