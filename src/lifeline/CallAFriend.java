package lifeline;

import questions.Question;

public class CallAFriend extends Lifeline {

    @Override
   protected void useLifeline(Question currentQuestion) {
        String callAFriendAnswer;
        String questionText = userDisplay.callAFriendPrompt();
        if(questionText == null){
            callAFriendAnswer = "";
        } else{
            callAFriendAnswer = currentQuestion.getAnswer();        
        }
   }

   @Override
   public String getLifeline(){
    return "Call a Friend";
   } 
}
