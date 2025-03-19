import levels.Level;

public class App {
    public static void main(String[] args) throws Exception {
        // Uncomment the next line to test Question and QuestionBuilder classes.
        // App.questionTester();
    }

    public static void questionTester(){
        Level easyLevel = new Level("easy");
        easyLevel.printQuestions();
        Level hardLevel = new Level("hard");
        hardLevel.printQuestions();
    }
}
