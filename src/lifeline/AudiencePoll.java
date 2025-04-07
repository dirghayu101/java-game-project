package lifeline;

import questions.Question;

public class AudiencePoll extends Lifeline{
    @Override
   protected void useLifeline(Question currentQuestion) {
    double[] audiencePollResult =  currentQuestion.audiencePollOnOptions();
   }

   @Override
   public String getLifeline(){
    return "Audience Poll";
   }
}
