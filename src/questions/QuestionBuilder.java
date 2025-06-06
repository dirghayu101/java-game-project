package questions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class QuestionBuilder {
    private final int totalQuestions;
    private HashMap<Integer, Integer> prizeMap = new HashMap<>(); 
    private final String prizeMapPath;


    public QuestionBuilder(String prizeMap, int totalQuestions) {
        this.totalQuestions = totalQuestions;
        this.prizeMapPath = prizeMap;
        this.initPrizeMap();

    }

    // This method will initialize the Map prizeMap. It takes values for the prizeMap from the static file which will be passed as a string based on chosen difficulty.
    private void initPrizeMap() {
        try {
            File prizeMapFile = new File(this.prizeMapPath);
            if(!prizeMapFile.exists()){
                throw new FileNotFoundException();
            }

            Scanner sc = new Scanner(prizeMapFile);
            while(sc.hasNextInt()){
                int key = sc.nextInt();
                int value = sc.nextInt();
                this.prizeMap.put(key, value);
            }
            sc.close();  
        } catch (FileNotFoundException e) {
            System.err.println("Question Prize Map file not found.");
        }
    }

    // getQuestion method will initialize the question objects and return an array of questions to be asked to the player.
    public ArrayList<Question> getQuestions(){
        ArrayList<String> questionSetString = this.getRandomQuestionsArray();
        ArrayList<Question> questionSet = new ArrayList<>();
        int key = 1;
        for(String question: questionSetString){
            boolean canUseLifeline = true;
            int questionId = key;
            if(this.prizeMap.get(key) == null){
                System.out.println("error about to occur");
            }
            int questionPrize = this.prizeMap.get(key);
            // Determines if the player can use lifeline for the question.
            if(this.totalQuestions == 15 && questionId <= 5){
                canUseLifeline = false;
            }
            String[] questionComponents = question.split("\\|");
            String questionStatement = questionComponents[0];
            String[] options = new String[4];
            for(int i = 0; i < 4; i++){
                options[i] = questionComponents[i+1];
            }
            int answer = Integer.parseInt(questionComponents[5]);
            Question thisQuestion = new Question(questionId, answer, questionStatement, options, canUseLifeline, questionPrize);
            questionSet.add(thisQuestion);
            key++;
        }
        return questionSet;
    }



    // Helper method for the getQuestions(), this returns totalQuestions number of questions as an array (list) of string after randomly shuffling them.
    public ArrayList<String> getRandomQuestionsArray(){
        ArrayList<String> allQuestions = QuestionCompiler.getQuestionBank();
        Collections.shuffle(allQuestions);
        ArrayList<String> randomQuestions = new ArrayList<>();
        for(int i = 0; i < totalQuestions; i++){
            randomQuestions.add(allQuestions.get(i));
        }
        return randomQuestions;
    }
}
