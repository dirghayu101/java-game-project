package utility;

import java.util.Random;


public class GeneralUtilityMethods {

    public GeneralUtilityMethods() {
    }

    public int getRandomOption(int correctOption){
        Random optionSelector = new Random();
        int chosenOption = correctOption;
        do { 
            chosenOption = optionSelector.nextInt(1, 5);
        } while (chosenOption == correctOption);
        return chosenOption;
    }

    public String[] modifyOptions(int option1, int option2, String[] options){
        int option1Index = option1 - 1;
        int option2Index = option2 - 1;
        String[] modifiedOptions = {options[option1Index], options[option2Index]};
        return modifiedOptions;   
    }

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
