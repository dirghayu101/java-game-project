package lifeline;

import questions.Question;

public class CallAFriend extends Lifeline<String> {

    @Override
   protected String useLifeline(Question currentQuestion) {
        String questionText = userDisplay.callAFriend();
        

   }
}
