package lifeline;

import display.DisplayConstants;
import questions.Question;

public class AudiencePoll extends Lifeline{
    @Override
   protected void useLifeline(Question currentQuestion) {
    userDisplay.showDisplayScreenFile(DisplayConstants.AUDIENCE_POLL, 450);
    double[] audiencePollResult =  currentQuestion.audiencePollOnOptions();
    userDisplay.printPollResult(currentQuestion.options, audiencePollResult);
   }

   @Override
   public String getLifeline(){
    return "Audience Poll";
   }
}
