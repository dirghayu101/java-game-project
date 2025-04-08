package lifeline;

import questions.CallAFriendHelper;
import questions.Question;

public class CallAFriend extends Lifeline {

    String callAFriendAnswer;
    int responseStatus = 2;
    
    
    /* 
     * responseStatus possible values:
     * 1 - Success, found 1 probable answers. 
     * 2 - Partial, found multiple matching answers.
     * 3 - Failure, no answers found.
    */
    @Override
   protected void useLifeline(Question currentQuestion) {
        String questionText = userDisplay.callAFriendPrompt();
        CallAFriendHelper helper = new CallAFriendHelper();
        responseStatus = helper.getMatchingAnswerStatus(questionText);
        if(questionText == null || responseStatus == 3){
            callAFriendAnswer = "";
        } else{
            callAFriendAnswer = helper.getAllPossibleAnswers();       
        }
        userDisplay.callAFriendResponse(responseStatus, callAFriendAnswer);
   }

   @Override
   public String getLifeline(){
    return "Call a Friend";
   } 
}
