package display;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class CallAFriendDisplayHelper {
    
    public String getUserInputTimed(long lapseTime, Scanner scanner){
        StringBuilder input = new StringBuilder();
        long startTime = System.currentTimeMillis();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (System.currentTimeMillis() - startTime < lapseTime) {
                if (reader.ready()) {  // Check if input is available
                    char c = (char) reader.read();
                    if (c == '\n') {   // Enter key pressed
                        return formatText(input.toString());
                    }
                    input.append(c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return formatText(input.toString());  // Return what we have on error
        }
        return formatText(input.toString());
    }

    // Replaces multiple spaces & newlines with a single space. 
    // Also converts to lowercase.
    private static String formatText(String text) {
        return (text == null) ? null : text.trim().replaceAll("\\s+", " ").toLowerCase();
    }
}
