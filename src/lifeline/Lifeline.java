package lifeline;

import questions.Question;

public abstract class Lifeline<T> {
    private boolean isUsed = false;

    public void executeLifeline(Question currentQuestion) {
        if(!isUsed){
            this.useLifeline(currentQuestion);
            isUsed = true;
        } else {
            System.out.println("Lifeline already used.");
        }
    }

    protected abstract T useLifeline(Question currentQuestion);
}
