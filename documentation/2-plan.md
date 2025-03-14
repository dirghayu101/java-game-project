### Layers of Program:
1. Data Layer
2. Logic Layer
3. Presentation Layer
4. Control Layer

### Data Layer:
1. Init Game (properties: player: Player)
    
    2. Init Player (properties: name: String, level: Level)
        
        3. Init Level (properties: canUseLifelineBool, QuestionBuilder, prizeRoundQuestionMap: (int, int)[])
            
            4. Init QuestionBuilder (properties: fileInput: File,  setQuestions: Question[], totalRound: int, totalQuestions: int, prizeQuestionMap: (int, int)[])
            - Question Text from fileInput: (properties: questionDescription: String, questionId: int, correctAnswer: int, options: (int, String)[])
                
                5. Init Question(s) (properties: questionDescription: String, questionId: int, correctAnswer: int, options: (int, String)[], round, totalQuestion, questionNum, prize, canWalkawayBool)
                    
                - Destroy Question(s): return Question
            - Destroy QuestionBuilder: return Question[]
            6. Init Lifeline (properties: fiftyFiftyUsed: boolean, callAFriendUsed: boolean, audiencePoll: boolean)
            7. Init showQuestions (properties: questions: Question, )

            Which properties should be tracked in which class? And how is it determined?

### Logic Layer:
Flow: 
    Welcome Screen  
        ||
        \/
    Initial Screen (Start, Read Rules, Exit)
        ||
        \/
      (Branch)     => End Screen    || Rule Screen
        ||
        \/
    Player Name Input Screen
        ||
        \/
    Level Selection
        ||
        \/
    Question (range from 1 to 15) 
        ||               /\
        ||               ||
        \/               ||
     Confirm Choice      ||
        ||               ||    
        \/               ||
      (Branch) =======> Lifeline 
        ||
        \/
      (Branch) => Eliminated Screen || Walkaway Screen || Won Screen 
        ||            \                                          /
        ||                                ||
        ||                                \/
        \/                            Application Exit 
    Goes to next question 




