package lifeline;

import questions.Question;

public class FiftyFifty extends Lifeline {

   @Override
   protected void useLifeline(Question currentQuestion) {
      currentQuestion = currentQuestion.removeTwoIncorrectOptions();
   }

   @Override
   public String getLifeline(){
    return "Fifty-Fifty";
   }

}
