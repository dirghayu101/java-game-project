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

    public String getUserName() {
        System.out.print("\n\n\nPlease enter your name here: ");
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

    // Overloaded method with similar logic for Question object.
    public int showDisplayPromptUserInput(Question question) {
        boolean confirmChoice = true;
        int userChoice;
        do {
            question.printQuestion();
            userChoice = this.getUserChoice();
            confirmChoice = userConfirmsChoice(userChoice);
        } while (confirmChoice);
        return userChoice;
    }

    public int showDisplayPromptUserInput(String filePath, boolean confirmChoice) {
        int userChoice;
        do {
            this.showDisplayScreenFile(filePath);
            userChoice = this.getUserChoice();
            if (confirmChoice)
                confirmChoice = this.userConfirmsChoice(userChoice);
        } while (confirmChoice);
        return userChoice;
    }

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
            this.showDisplayScreenFile(DisplayConstants.ELIMINATE);
            return true;
        }
    }

    private void putSpaceOnDisplay(int newLines) {
        for (int i = 1; i <= newLines; i++) {
            System.out.println();
        }
    }

    private boolean userConfirmsChoice(int userChoice){
        System.out.println("You have chosen the option " + userChoice);
        System.out.println("This is the final confirmation, you can still change your choice now.");
        System.out.println("Enter 1 to confirm your choice, any other character to choose again.");
        int confirm = this.getUserChoice();
        return confirm != 1;    // returns false if user enters 1.
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
        this.putSpaceOnDisplay(10);
        return choice;
    }

    private int getUserChoice(int minOption, int maxOption) {
        int choice;
        do {
            System.out.print("\n\n\nPlease enter your choice here: ");
            choice = userInputScanner.nextInt();
        } while (choice < minOption && choice > maxOption);
        this.putSpaceOnDisplay(10);
        return choice;
    }



}
