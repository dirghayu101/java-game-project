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

    /*
     * Mapping for the lifelines based on lifelineCode value:
     * 1: Audience Poll
     * 2: Call a Friend
     * 3: Fifty Fifty
     */
    public void useLifeline(int lifelineCode, Question currentQuestion) {
        switch (lifelineCode) {
            case 1 -> useLifelineHelper("Audience Poll", currentQuestion);
            case 2 -> useLifelineHelper("Call a Friend", currentQuestion);
            case 3 -> useLifelineHelper("Fifty-Fifty", currentQuestion);
            default -> System.out.println("Invalid lifeline selection.");
        }
    }

    private void useLifelineHelper(String lifelineName, Question currentQuestion) {
        for (Lifeline lifeline : availableLifelines) {
            if (lifeline.getLifeline().equalsIgnoreCase(lifelineName)) {
                if (lifeline.canUseLifeline()) {
                    lifeline.executeLifeline(currentQuestion);
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
