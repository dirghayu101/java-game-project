package display;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import lifeline.Lifelines;
import questions.Question;

public class Display {

    private static Display display_instance = null;
    static Lifelines userLifelines = Lifelines.getLifelines();
    private static final Scanner userInputScanner = new Scanner(System.in);

    private Display() {
    }

    // Singleton getter.
    public static Display getDisplay() {
        if (display_instance == null) {
            display_instance = new Display();
        }
        return display_instance;
    }

    // Method to display welcome screen to user.
    public boolean showWelcomeScreen() {
        this.showDisplayScreenFile(DisplayConstants.WELCOME_SCREEN, 300);
        boolean startGame = false;
        int userChoice = this.getUserChoice(1, 3);
        switch (userChoice) {
            case 1 -> {
                startGame = true;
            }
            case 2 -> {
                int ruleChoice = showDisplayPromptUserInput(DisplayConstants.GAME_RULES, false);
                if (ruleChoice == 1) {
                    return showWelcomeScreen();
                } else {
                    startGame = false;
                }
            }
            default -> {
                startGame = false;
            }
        }
        return startGame;
    }

    // Shows starting welcome screen text to user when the user starts game.
    public void startGame() {
        this.putSpaceOnDisplay(12);
        this.showDisplayScreenFile(DisplayConstants.GAME_START, 300);
    }

    // Display the exit message to user.
    public void exit() {
        this.putSpaceOnDisplay(10);
        System.out.println("Thanks for trying out our application! :)");
        System.out.println("Exiting game.");
        this.putSpaceOnDisplay(10);
        System.exit(0);
    }

    public void printPollResult(String[] options, double[] pollResult){
        if(options.length != pollResult.length){
            System.out.println("An issue occurred in the system.");
            return;
        }
        System.out.println("\n\nHere are the much awaited results!\n\n");
        for(int i = 0; i < options.length; i++){
            double truncatePool = Math.floor(pollResult[i] * 100) / 100;
            System.out.println("Option " + (i+1) + ": " + options[i] + "   --->   " + truncatePool + "%.");
        }
        this.putSpaceOnDisplay(3);
    }

    // Helper method to print question, options and available lifeline.
    private void printQuestionLifeline(Question question) {
        System.out.println(question.questionId + ". " + question.questionText + "\n");
        int totalOptions = question.options.length;
        for (int i = 1; i <= totalOptions; i++) {
            System.out.println("Option " + i + ": " + question.options[i - 1]);
        }
        if (question.canUseLifeline) {
            lifelinePrinter(totalOptions + 1);
        }
        System.out.println("\n\nPrize Amount: $" + question.prizeAmount + "\n");
    }

    private void lifelinePrinter(int lifelineStartOption) {
        ArrayList<String> availableLifelines = userLifelines.getAvailableLifelines();
        for (String lifeline : availableLifelines) {
            System.out.println("Lifeline option " + lifelineStartOption + ": " + lifeline + ".");
            lifelineStartOption++;
        }
    }

    private void handleLifeline(int chosenOption, Question question) {
        ArrayList<String> availableLifelines = userLifelines.getAvailableLifelines();
        String chosenLifeline = availableLifelines.get(chosenOption - 1);
        userLifelines.useLifeline(chosenLifeline, question);
    }

    // Overloaded method for Question object.
    public int showDisplayPromptUserInput(Question question) {
        boolean confirmUserChoice = true;
        boolean useLifeline;

        int userChoice;
        do {
            int maxOption = question.options.length;
            int lifelineStartOption = maxOption;
            if (question.canUseLifeline) {
                maxOption += userLifelines.getLifelineArraySize();
            }
    
            int minOption = 1;
            useLifeline = false;
            this.printQuestionLifeline(question);
            userChoice = this.getUserChoice(minOption, maxOption);
            confirmUserChoice = userConfirmsChoice(userChoice);
            if (question.canUseLifeline && userChoice > lifelineStartOption) {
                useLifeline = true;
                handleLifeline(userChoice - lifelineStartOption, question);
            }
        } while (useLifeline || confirmUserChoice);
        return userChoice;
    }

    // Overload method when there is a single option and the chosen option doesn't
    // have to be confirmed.
    public int showDisplayPromptUserInput(String filePath, boolean confirmUserChoice) {
        int userChoice;
        do {
            this.showDisplayScreenFile(filePath, 200);
            userChoice = this.getUserChoice();
            if (confirmUserChoice)
                confirmUserChoice = this.userConfirmsChoice(userChoice);
        } while (confirmUserChoice);
        return userChoice;
    }

    // Overload method when user choice need to be verified.
    public int showDisplayPromptUserInput(String filePath, boolean confirmUserChoice, int minOption, int maxOption) {
        int userChoice;
        do {
            this.showDisplayScreenFile(filePath, 300);
            userChoice = this.getUserChoice(minOption, maxOption);
            if (confirmUserChoice) {
                confirmUserChoice = userConfirmsChoice(userChoice);
            }
        } while (confirmUserChoice);
        return userChoice;
    }

    // Method responsible for displaying the message if the user chose to walkaway
    // at the end of a round.
    public boolean playerWalkaway(Question currentQuestion) {
        System.out.println("Congratulations on reaching the end of the current round! You have now won $"
                + currentQuestion.prizeAmount + ".");
        int userChoice = this.showDisplayPromptUserInput(DisplayConstants.WALKAWAY, true);
        if (userChoice == 1) {
            return false;
        } else {
            System.out.println("You have played well and have won $" + currentQuestion.prizeAmount + ".\n");
            this.showDisplayScreenFile(DisplayConstants.ELIMINATE, 200);
            return true;
        }
    }

    // Method to put display based on screen. The newLines parameter will determine
    // the number of newlines to be placed on the screen.
    private void putSpaceOnDisplay(int newLines) {
        for (int i = 1; i <= newLines; i++) {
            System.out.println();
        }
    }

    // Overload method, put space on display also, put some filler characters on
    // each newLines number of new lines passed as lineFillers parameter
    public void putSpaceOnDisplay(int newLines, String lineFillers) {
        for (int i = 1; i <= newLines; i++) {
            System.out.println(lineFillers);
        }
    }
    
    // This method puts space on display with a timer and also uses lineFillers.
    public void putSpaceOnDisplay(int newLines, String lineFillers, int timer) {
        for (int i = 1; i <= newLines; i++) {
            System.out.println(lineFillers);
            try {
                Thread.sleep(timer); // takes input in millisecond.
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restores the interrupted state.
            }
        }
    }

    // Prompt to confirm user choice.
    private boolean userConfirmsChoice(int userChoice) {
        System.out.println("You have chosen the option " + userChoice);
        System.out.println("This is the final confirmation, you can still change your choice now.");
        System.out.println("Enter 1 to confirm your choice, any other character to choose again.");
        int confirm = this.getUserChoice();
        return confirm != 1; // returns false if user enters 1.
    }

    // Displays a static file stored in the filepath parameter.
    public void showDisplayScreenFile(String filePath) {
        try {
            File displayFile = new File(filePath);
            if (!displayFile.exists()) {
                throw new FileNotFoundException();
            }
            Scanner sc = new Scanner(displayFile);
            while (sc.hasNext()) {
                System.out.println(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("Display file not found.");
        }
    }

    // Overload method. Pauses while printing lines for the timer milliseconds.
    public void showDisplayScreenFile(String filePath, int timer) {
        try {
            File displayFile = new File(filePath);
            if (!displayFile.exists()) {
                throw new FileNotFoundException();
            }
            Scanner sc = new Scanner(displayFile);
            while (sc.hasNext()) {
                System.out.println(sc.nextLine());
                try {
                    Thread.sleep(timer); // takes input in millisecond.
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restores the interrupted state.
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("Display file not found.");
        }
    }

    // Overload method just returns the user input without checking against the
    // available option. Example use case: confirmation of user choice.
    private int getUserChoice() {
        int choice = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("\n\nPlease enter your choice here: ");
                choice = userInputScanner.nextInt();

                if (choice == 0) {
                    this.exit();
                }

                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                userInputScanner.nextLine(); // clear invalid input
            }
        }

        this.putSpaceOnDisplay(10);
        return choice;
    }

    // Overloaded method. Also checks if the option entered by user is valid.
    private int getUserChoice(int minOption, int maxOption) {
        int choice = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("\n\n\nPlease enter your choice here: ");
                choice = userInputScanner.nextInt();

                if (choice == 0) {
                    this.exit();
                }

                if (choice >= minOption && choice <= maxOption) {
                    validInput = true;
                } else {
                    System.out.println("Please enter a number between " + minOption + " and " + maxOption + ".");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                userInputScanner.nextLine();
            }
        }

        this.putSpaceOnDisplay(10);
        return choice;
    }

    // Prompt user to input username.
    public String getUserName(String promptMessage) {
        this.putSpaceOnDisplay(2);
        String username = "";

        while (true) {
            try {
                System.out.print(promptMessage + " Type here : ");
                username = userInputScanner.next();

                // Validation to avoid empty or invalid usernames
                if (username.trim().isEmpty()) {
                    System.out.println("Username cannot be empty. Please try again.");
                } else {
                    break;
                }

            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
                userInputScanner.nextLine(); 
            }
        }

        this.putSpaceOnDisplay(4);
        return username;
    }

    // Round cleared message.
    public void roundCleared(int currentRound){
        System.out.println("CONGRATULATIONS! YOU MADE IT!!!");
        this.putSpaceOnDisplay(3, ".", 950);
        System.out.println("Made it to the end of round " + currentRound +"!!!");
        this.putSpaceOnDisplay(7);
    }

    // callAFriend method for handling the call a friend lifeline.
    public String callAFriendPrompt() {
        this.putSpaceOnDisplay(2);
        System.out.println("You have chosen to call a friend. You will get a total of 30 seconds to ask question.\n");
        String friend = getUserName("Please enter the friend's name you wanna connect to.");
        System.out.println("Game will be attempting to connect to " + friend + "...");
        this.putSpaceOnDisplay(2, ".");
        this.showDisplayScreenFile(DisplayConstants.CALL_FRIEND, 800);
        this.putSpaceOnDisplay(2, ".");
        this.showDisplayScreenFile(DisplayConstants.FRIEND_RESPONSE, 1200);
        this.putSpaceOnDisplay(2, ".");
        System.out.println(friend + " has requested you to send them the question text.");
        this.putSpaceOnDisplay(2, ".");
        System.out.println("NOTE! You have 26 seconds remaining in this conversation with your friend, " + friend
                + ".\nPlease enter the question text below and press enter once done.");
        this.putSpaceOnDisplay(2, ".");
        System.out.print("Please enter the question text here: ");
        CallAFriendDisplayHelper userInputTimed = new CallAFriendDisplayHelper();
        String userInput = userInputTimed.getUserInputTimed(26000, userInputScanner);
        return userInput;
    }

    // callAFriendResponse method.
    /*
     * responseStatus == 1 -> question typed successfully.
     * responseStatus == 2 -> question typed partially.
     * responseStatus == 3 -> gibberish typed. Or nothing typed.
     */
    public void callAFriendResponse(int responseStatus, String respondedAnswer) {
        switch (responseStatus) {
            case 1 -> {
                this.showDisplayScreenFile(DisplayConstants.CALL_FRIEND_SUCCESS, 400);
                System.out.println("I am pretty sure the answer is: " + respondedAnswer);
            }
            case 2 -> {
                this.showDisplayScreenFile(DisplayConstants.CALL_FRIEND_PARTIAL, 400);
                System.out.println("I am kinda unsure, but I think the answer is: " + respondedAnswer);
            }
            default -> this.showDisplayScreenFile(DisplayConstants.CALL_FRIEND_FAILURE, 400);
        }
        System.out.println("CALL HANGING OUT!\n\nCall a friend lifeline, has been used.\n\n");
    }
}
