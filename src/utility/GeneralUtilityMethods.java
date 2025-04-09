package utility;

import java.util.Random;


public class GeneralUtilityMethods {

    public GeneralUtilityMethods() {
    }

    // This method will return a random option from 1 to 4, excluding the correct option.
    // This is used for the lifeline "50-50" to select one of the three incorrect options to include in the final options.
    public int getRandomOption(int correctOption){
        Random optionSelector = new Random();
        int chosenOption = correctOption;
        do { 
            chosenOption = optionSelector.nextInt(1, 5);
        } while (chosenOption == correctOption);
        return chosenOption;
    }

    // This method will take two options and the options array, and return an array of two options.
    // This is used for the lifeline "50-50" to select two options to display to the user. It modifies the options array to include only the two selected options.
    public String[] modifyOptions(int option1, int option2, String[] options){
        int option1Index = option1 - 1;
        int option2Index = option2 - 1;
        String[] modifiedOptions = {options[option1Index], options[option2Index]};
        return modifiedOptions;   
    }

    // This method will generate a random audience poll result for the given options.
    // The correct option will have a higher percentage of votes.
    // There can be 2 or more options. 
    public double[] audiencePollResult(int totalOptions, int correctOption){
        Random pollGenerator = new Random();
        int correctIndex = correctOption - 1;
        double[] pollResult = new double[totalOptions];
        double correctPercentage = 50 + pollGenerator.nextDouble() * 30;
        pollResult[correctIndex] = correctPercentage;
        double remaining = 100.0 - correctPercentage;
        double sum = 0.0;
        int lastIncorrect = -1;
        for (int i = 0; i < totalOptions; i++) {
            if (i == correctIndex) continue;
            lastIncorrect = i;
            double rand = pollGenerator.nextDouble() * (remaining - sum);
            pollResult[i] = rand;
            sum += rand;
        }
        pollResult[lastIncorrect] += (remaining - sum);
        return pollResult;
    }


}
