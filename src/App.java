import questions.Question;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Test test = new Test();
        test.printHelloWorld();
        Question question = new Question(1);
        question.printQuestion();
    }
}
