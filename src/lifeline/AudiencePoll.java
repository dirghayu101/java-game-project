package lifeline;

import questions.Question;

public class AudiencePoll extends Lifeline<double[]>{
    @Override
   protected double[] useLifeline(Question currentQuestion) {
    return currentQuestion.audiencePollOnOptions();
   }

}
