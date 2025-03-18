## Package 1: questions
- questions: question implementation functionality.
    - Responsible for:
        - Returning an array of random questions based on chosen level.

- The question bank for the application will be located under the static directory in the file questions.txt.
    - Question format will be as follows and the next question will start in the next line. 
    QUESTION|OPTION_A|OPTION_B|OPTION_C|OPTION_D|CORRECT_ANSWER

- Classes, their attribute and methods in questions package:
    - Question: questionId, correctAnswer, option[], round, questionNum, prize, canWalkaway, canUseLifeline, checkAnswer(): boolean  
    - QuestionBuilder: inputQuestionBank, setQuestions: Question[], totalQuestions: int, totalRound: int = 3, prizeQuestionMap: (int, int)[]



## Package 2: levels
- Abstract class Level: questionBank, 
    - Why abstract? 
        - Provides feasibility for future enhancement.
        - Some functionality implementation based on level chosen by player could potentially lead to a lot of branching. So introducing abstract class at this level helps us in mitigation if this happens.


## Package 3: player
- Class Player