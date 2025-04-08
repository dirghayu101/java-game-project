/* 
 * This class will be responsible for compiling all the questions from the question bank file into an array list.
 * 
 * 
*/

package questions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import game.GameConstants;


public class QuestionCompiler {
    private final String questionBankPath = GameConstants.QUESTION_BANK;
    private static QuestionCompiler compilerInstance = null;
    private static ArrayList<String> questionBank = new ArrayList<>();

    private QuestionCompiler(){
        QuestionCompiler.questionBank = this.getRandomQuestionsArray();
    }

    public static ArrayList<String> getQuestionBank(){
        if(compilerInstance == null){
            compilerInstance = new QuestionCompiler();
        }
        return questionBank;
    }

    private ArrayList<String> getRandomQuestionsArray(){
        ArrayList<String> allQuestions = new ArrayList<>();
        try {
            File questionBankFile = new File(questionBankPath);
            if(!questionBankFile.exists()){
                throw new FileNotFoundException();
            }
            Scanner sc = new Scanner(questionBankFile);
            while(sc.hasNext()){
                String questionLine = sc.nextLine();
                allQuestions.add(questionLine);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("Question bank file not found.");
        }
        return allQuestions;
    }    
}
