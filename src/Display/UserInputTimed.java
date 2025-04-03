package display;

import java.util.Scanner;


public class UserInputTimed {
    // volatile deals with latches issue that
    private static volatile String userInput = null;
    
    public String getUserInputTimed(long lapseTime, Scanner scanner){
        // Thread to get user input.
        Thread inputThread = new Thread( () -> {
            userInput = scanner.nextLine();
        });

        inputThread.start();
        // Wait for the input thread to finish or timeout in the lapseTime.
        // lapseTime is in milliseconds.
        try {
            inputThread.join(lapseTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userInput = formatText(userInput);
        return userInput;
    }

    // Replaces multiple spaces & newlines with a single space. 
    // Also converts to lowercase.
    private static String formatText(String text) {
        return (text == null) ? null : text.trim().replaceAll("\\s+", " ").toLowerCase();
    }
}
