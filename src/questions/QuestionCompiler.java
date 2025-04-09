/* 
 * This class will be responsible for compiling all the questions from the question bank file into an array list.
 * 
 * 
*/

package questions;

import game.GameConstants;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class QuestionCompiler {
    private final String questionBankPath = GameConstants.QUESTION_BANK;
    private static QuestionCompiler compilerInstance = null;
    private static ArrayList<String> questionBank = new ArrayList<>();

    // This constructor is private to prevent instantiation from outside the class.
    private QuestionCompiler(){
        QuestionCompiler.questionBank = this.getRandomQuestionsArray();
    }

    // This method will return the main purpose of this class, which is to compile the question bank and return an array list of questions.
    public static ArrayList<String> getQuestionBank(){
        if(compilerInstance == null){
            compilerInstance = new QuestionCompiler();
        }
        return questionBank;
    }

    // This method will read the question bank file and return an array list of questions.
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
