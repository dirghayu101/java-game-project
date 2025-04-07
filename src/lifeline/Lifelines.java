package lifeline;

import java.util.ArrayList;
import questions.Question;

public class Lifelines {
    private static Lifelines lifelines = null;
    ArrayList<Lifeline> availableLifelines = new ArrayList<>();

    private Lifelines() {
        availableLifelines.add(new FiftyFifty());
        availableLifelines.add(new AudiencePoll());
        availableLifelines.add(new CallAFriend());
    }

    public static Lifelines getLifelines() {
        if (lifelines == null) {
            lifelines = new Lifelines();
        }
        return lifelines;
    }

    public ArrayList<String> getAvailableLifelines() {
        ArrayList<String> allAvailableLifelines = new ArrayList<>();
        for (Lifeline thisLifeline : availableLifelines) {
            if (thisLifeline.canUseLifeline()) {
                allAvailableLifelines.add(thisLifeline.getLifeline());
            }
        }
        return allAvailableLifelines;
    }

    public int getLifelineArraySize() {
        return availableLifelines.size();
    }
    
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
