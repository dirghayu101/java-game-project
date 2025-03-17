### Layers of Program:
1. Data Layer
2. Logic Layer
3. Presentation Layer
4. Control Layer

### Data Layer:
1. Init Game (properties: player: Player, display: Display)
    
    1. (.5) Init Display(properties: sc: Scanner)

    2. Init Player (properties: name: String, level: Level, display: Display)
        
        3. Init Level (properties: questions: Question[], prizeRoundQuestionMap: (int, int)[], totalQuestion: int, totalRound: int = 3) (methods: askQuestions(display: Display): void)
            
            4. Init QuestionBuilder (properties: fileInput: File,  setQuestions: Question[], totalQuestions: int, totalRound: int = 3, prizeQuestionMap: (int, int)[]) (methods)
            - Question Text from fileInput: (properties: questionDescription: String, questionId: int, correctAnswer: int, options: (int, String)[])
                
                5. Init Question(s) (properties: questionDescription: String, questionId: int, correctAnswer: int, options: (int, String)[], round, totalQuestion, questionNum, prize, canWalkawayBool, canUseLifelineBool) 
                - Return: Question Object

            - Return: Question Object Array

            6. Init Lifeline (properties: fiftyFiftyUsed: boolean, callAFriendUsed: boolean, audiencePoll: boolean)
            7. Init showQuestions (properties: questions: Question, )

### Logic Layer:
Flow: 
    Welcome Screen  
        ||
        \/
    Initial Screen (Start, Read Rules, Exit)
        ||                                  /\
        \/                                  ||
      (Branch)     => End Screen    ||  Rule Screen
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




# Display Screens:
- Welcome Screen 
- Menu Screen (Start game, Read rules, exit)
- Rule Screen 
- Player Name input Screen
- Level Selection Screen
- Question Screen
- Confirmation Screen
- Lifeline Screen (Different implementation for different lifeline)
- Eliminated/ Lost game screen
- Walkaway Screen
- Game Won Screen
- Application Exit Screen