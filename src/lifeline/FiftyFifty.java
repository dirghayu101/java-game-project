package lifeline;

import questions.Question;

public class FiftyFifty extends Lifeline {

   // useLifeline method removes two incorrect options from the question
   // and leaves one correct option and one incorrect option
   @Override
   protected void useLifeline(Question currentQuestion) {
      currentQuestion.removeTwoIncorrectOptions();
   }
   // getLifeline method returns the name of the lifeline
   @Override
   public String getLifeline(){
    return "Fifty-Fifty";
   }

}
