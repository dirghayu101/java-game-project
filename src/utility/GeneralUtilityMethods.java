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
        int correctOptionIndex = correctOption - 1;
        double upperBound = 100.1;
        double[] pollResult = new double[4];
        double sum = 0;
        for(int i = 0; i < totalOptions; i++){
            if(i == correctOptionIndex){
                continue;
            }
            pollResult[i] = pollGenerator.nextDouble(0, upperBound);
            sum += pollResult[i];
            upperBound -= sum;
        }
        pollResult[correctOptionIndex] = upperBound - sum;
        return pollResult;
    }
}
