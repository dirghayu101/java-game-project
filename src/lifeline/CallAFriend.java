package lifeline;

import questions.Question;

public class CallAFriend extends Lifeline {

    String callAFriendAnswer;
    int responseStatus = 2;

    @Override
   protected void useLifeline(Question currentQuestion) {
        String questionText = userDisplay.callAFriendPrompt();
        if(questionText == null){
            callAFriendAnswer = "";
            responseStatus = 3;
        } else{
            callAFriendAnswer = currentQuestion.getAnswer(); 
            responseStatus = 1;       
        }
        userDisplay.callAFriendResponse(responseStatus, callAFriendAnswer);
   }

   @Override
   public String getLifeline(){
    return "Call a Friend";
   } 
}
