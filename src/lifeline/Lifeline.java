package lifeline;

import display.Display;
import questions.Question;

/**
 * Abstract class representing a lifeline in a quiz game.
 * This class provides the structure for different types of lifelines.
 */
// It contains methods to execute the lifeline, check if it can be used,
// and an abstract method to be implemented by subclasses for specific lifeline behavior.
public abstract class Lifeline {
    boolean isUsed = false;
    static Display userDisplay = Display.getDisplay();

    // This is the main method to execute the lifeline.
    // It checks if the lifeline has already been used and calls the useLifeline method. 
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

    // useLifeline is an abstract method and must be implemented by subclasses.
    // It contains the logic for how the lifeline will be used.
    protected abstract void useLifeline(Question currentQuestion);
    // getLifeline is an abstract method and must be implemented by subclasses.
    // It returns the name of the lifeline.
    public abstract String getLifeline();
}
