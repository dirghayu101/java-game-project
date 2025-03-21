package display;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import game.GameConstants;
import questions.Question;

// Display will be a singleton class. Since display can have different states like taking in input, or confirming user options etc at any point, making it singleton will be easy to keep track of what's being displayed.
public class Display {

    private static Display display_instance = null;
    // Using multiple scanner was invoking input mismatch error. So for the user input, same instance of scanner is going to be shared across application.
    private static Scanner userInputScanner = new Scanner(System.in);

    private Display() {
    }

    public static Display getDisplay() {
        if (display_instance == null) {
            display_instance = new Display();
        }
        return display_instance;
    }

    public boolean showWelcomeScreen() {
        int userChoice = this.showDisplayPromptUserInput(DisplayConstants.WELCOME_SCREEN, false);
        boolean startGame = false;
        switch (userChoice) {
            case 1 -> {
                startGame = true;
            }
            case 2 -> {
                int ruleChoice = showDisplayPromptUserInput(DisplayConstants.GAME_RULES, false);
                if (ruleChoice == 1) {
                    showWelcomeScreen();
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

    public String getUserName() {
        System.out.print("\n\nPlease enter your name here: ");
        String username = userInputScanner.next();
        this.showDisplayScreenFile(DisplayConstants.SPACER);
        return username;
    }

    public void exit() {
        this.showDisplayScreenFile(DisplayConstants.SPACER);
        System.out.println("Exiting game.");
        System.exit(0);
    }

    // Overloaded method with similar logic for Question object.
    public int showDisplayPromptUserInput(Question question) {
        boolean confirmChoice = true;
        int userChoice;
        do {
            question.printQuestion();
            userChoice = this.getUserChoice();
            confirmChoice = this.confirmUserChoice(userChoice);
        } while (confirmChoice);
        return userChoice;
    }

    public int showDisplayPromptUserInput(String filePath, boolean confirmChoice) {
        int userChoice;
        do {
            this.showDisplayScreenFile(filePath);
            userChoice = this.getUserChoice();
            if(confirmChoice)
                confirmChoice = this.confirmUserChoice(userChoice);
        } while(confirmChoice);
        return userChoice;
    }

    public boolean playerWalkaway(Question currentQuestion) {
        System.out.println("Congratulations on reaching the end of the current round! You have now won $" + currentQuestion.prizeAmount + ".");
        int userChoice = this.showDisplayPromptUserInput(DisplayConstants.WALKAWAY, true);
        if (userChoice == 1) {
            return false;
        } else {
            this.showDisplayScreenFile(DisplayConstants.ELIMINATE);
            return true;
        }
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

    private int getUserChoice() {
        int choice;
        System.out.print("\n\nPlease enter your choice here: ");
        choice = userInputScanner.nextInt();
        return choice;
    }

    private boolean confirmUserChoice(int choice) {
        System.out.println("You have chosen the option " + choice + ". This is the final confirmation, you cannot change your choice after this stage.");
        System.out.print("Please enter 1 to confirm your choice: ");
        int confirmChoice = userInputScanner.nextInt();
        // Print some space if the user doesn't want to confirm the choice to print the previous description again.
        if (confirmChoice != 1) {
            this.showDisplayScreenFile(DisplayConstants.SPACER);
            return false;
        }
        return true;
    }

}
