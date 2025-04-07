package lifeline;

import display.Display;
import questions.Question;

public abstract class Lifeline {
    boolean isUsed = false;
    static Display userDisplay = Display.getDisplay();

    public void executeLifeline(Question currentQuestion) {
        if(!isUsed){
            this.useLifeline(currentQuestion);
            isUsed = true;
        } else {
            System.out.println("Lifeline already used.");
        }
    }

    public boolean canUseLifeline(){
        return !isUsed;
    }

    protected abstract void useLifeline(Question currentQuestion);
    public abstract String getLifeline();
}
