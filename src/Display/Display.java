package display;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import questions.Question;

public class Display {

    private static Display display_instance = null;
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

    public boolean showWelcomeScreen() {
        this.showDisplayScreenFile(DisplayConstants.WELCOME_SCREEN);
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

    public void startGame() {
        this.putSpaceOnDisplay(12);
        this.showDisplayScreenFile(DisplayConstants.GAME_START, 300);
    }

    public String getUserName() {
        System.out.print("\n\nPlease enter your name here: ");
        String username = userInputScanner.next();
        this.putSpaceOnDisplay(10);
        return username;
    }

    public void exit() {
        this.putSpaceOnDisplay(10);
        System.out.println("Thanks for trying out our application! :)");
        System.out.println("Exiting game.");
        this.putSpaceOnDisplay(10);
        System.exit(0);
    }

    // Overloaded method for Question object.
    public int showDisplayPromptUserInput(Question question) {
        boolean confirmUserChoice = true;
        int userChoice;
        do {
            question.printQuestion();
            userChoice = this.getUserChoice(1, 4);
            confirmUserChoice = userConfirmsChoice(userChoice);
        } while (confirmUserChoice);
        return userChoice;
    }

    // Overload method when there is a single option and the chosen option doesn't
    // need to be checked.
    public int showDisplayPromptUserInput(String filePath, boolean confirmUserChoice) {
        int userChoice;
        do {
            this.showDisplayScreenFile(filePath);
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
            this.showDisplayScreenFile(filePath);
            userChoice = this.getUserChoice(minOption, maxOption);
            if (confirmUserChoice) {
                confirmUserChoice = userConfirmsChoice(userChoice);
            }
        } while (confirmUserChoice);
        return userChoice;
    }

    public boolean playerWalkaway(Question currentQuestion) {
        System.out.println("Congratulations on reaching the end of the current round! You have now won $"
                + currentQuestion.prizeAmount + ".");
        int userChoice = this.showDisplayPromptUserInput(DisplayConstants.WALKAWAY, true);
        if (userChoice == 1) {
            return false;
        } else {
            System.out.println("You have played well and have won $" + currentQuestion.prizeAmount + ".\n");
            this.showDisplayScreenFile(DisplayConstants.ELIMINATE);
            return true;
        }
    }

    private void putSpaceOnDisplay(int newLines) {
        for (int i = 1; i <= newLines; i++) {
            System.out.println();
        }
    }

    private boolean userConfirmsChoice(int userChoice) {
        System.out.println("You have chosen the option " + userChoice);
        System.out.println("This is the final confirmation, you can still change your choice now.");
        System.out.println("Enter 1 to confirm your choice, any other character to choose again.");
        int confirm = this.getUserChoice();
        return confirm != 1; // returns false if user enters 1.
    }

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
    // Overload method. Pauses between printing lines.
    public void showDisplayScreenFile(String filePath, int timer){
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
        int choice;
        System.out.print("\n\nPlease enter your choice here: ");
        choice = userInputScanner.nextInt();
        this.putSpaceOnDisplay(10);
        return choice;
    }

    // Overloaded method. Also checks if the option entered by user is valid.
    private int getUserChoice(int minOption, int maxOption) {
        int choice;
        do {
            System.out.print("\n\n\nPlease enter your choice here: ");
            choice = userInputScanner.nextInt();
        } while (choice < minOption || choice > maxOption);
        this.putSpaceOnDisplay(10);
        return choice;
    }

}
