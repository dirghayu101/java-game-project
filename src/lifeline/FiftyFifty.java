package lifeline;

import questions.Question;

public class FiftyFifty extends Lifeline<Question> {

   @Override
   protected Question useLifeline(Question currentQuestion) {
      return currentQuestion.removeTwoIncorrectOptions();
   }

}
