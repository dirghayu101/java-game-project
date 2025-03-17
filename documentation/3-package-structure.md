## Package 1: questions
- questions: question implementation functionality.
    - Responsible for:
        - Returning an array of random questions based on chosen level.

- The question bank for the application will be located under the static directory in the file questions.txt.
    - Question format will be as follows and the next question will start in the next line. 
    ID|QUESTION|OPTION_A|OPTION_B|OPTION_C|OPTION_D|CORRECT_ANSWER

- Classes, their attribute and methods in questions package:
    - Question: questionId, correctAnswer, option[], round, questionNum, prize, canWalkaway, canUseLifeline, checkAnswer(): boolean  
    - QuestionBuilder:  
