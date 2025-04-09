package lifeline;

import java.util.ArrayList;
import questions.Question;

// Lifelines class is a singleton that manages the available lifelines in the game.
public class Lifelines {
    private static Lifelines lifelines = null;
    ArrayList<Lifeline> availableLifelines = new ArrayList<>();

    // Private constructor to prevent instantiation
    private Lifelines() {
        availableLifelines.add(new FiftyFifty());
        availableLifelines.add(new AudiencePoll());
        availableLifelines.add(new CallAFriend());
    }

    // Static method to get the single instance of Lifelines
    public static Lifelines getLifelines() {
        if (lifelines == null) {
            lifelines = new Lifelines();
        }
        return lifelines;
    }

    // Method to get the available lifelines
    public ArrayList<String> getAvailableLifelines() {
        ArrayList<String> allAvailableLifelines = new ArrayList<>();
        for (Lifeline thisLifeline : availableLifelines) {
            if (thisLifeline.canUseLifeline()) {
                allAvailableLifelines.add(thisLifeline.getLifeline());
            }
        }
        return allAvailableLifelines;
    }

    // Method to get the number of available lifelines. Helps in setting the maxOptions in the menu.
    public int getLifelineArraySize() {
        return availableLifelines.size();
    }
    
    // Calls the respective executeLifeline method of the lifeline class.
    public void useLifeline(String lifelineLiteral, Question currentQuestion) {
        for (Lifeline lifeline : availableLifelines) {
            if (lifeline.getLifeline().equalsIgnoreCase(lifelineLiteral)) {
                if (lifeline.canUseLifeline()) {
                    lifeline.executeLifeline(currentQuestion);
                    availableLifelines.remove(lifeline);
                    return; 
                } else {
                    System.out.println("Lifeline already used.");
                    return;
                }
            }
        }
        System.out.println("Lifeline not found.");
    }
    
}
