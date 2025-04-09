package lifeline;

import display.DisplayConstants;
import questions.Question;

/**
 * The AudiencePoll class extends the Lifeline class and represents the audience poll lifeline in the game.
 * It provides a method to use the lifeline and display the results of the audience poll.
 */
public class AudiencePoll extends Lifeline{
    // useLifeline method is overridden to get and display the audience poll results.
    @Override
   protected void useLifeline(Question currentQuestion) {
    userDisplay.showDisplayScreenFile(DisplayConstants.AUDIENCE_POLL, 450);
    double[] audiencePollResult =  currentQuestion.audiencePollOnOptions();
    userDisplay.printPollResult(currentQuestion.options, audiencePollResult);
   }

    // getLifeline method is overridden to return the name of the lifeline.
   @Override
   public String getLifeline(){
    return "Audience Poll";
   }
}
