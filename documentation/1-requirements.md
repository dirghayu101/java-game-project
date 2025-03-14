# Requirements gathered from the project document:
- All screens must have descriptive messages and/or prompts.
- Initial Screen should include:
    1. Start the game
    2. View the rules of the game -  Note, when a user views the rules of the game, there must be an option that allows them to go back to the main menu.
    3. Exit the game
- Once the user start the game. 
    1. They should be asked for their name.
    2. Following that they will pick a difficulty level.
        - Questions based on difficulty level chosen:
            - Easy: 9 Questions
            - Hard: 15 Questions
        - Question format should be Multiple Choice with 4 options provided.
- A game will be played in 3 rounds:
    - Round 1: 
        - 3 questions of varying difficulty levels in easy option.
            - Question 1: $100, Question 2: $500 and Question 3: $1000
        - 5 questions of varying difficulty level in hard option.
            - Question 1: $100, Question 2: $200, Question 3: $300, Question 4: $500 and Question 5: $1000
        - Once a player answers all questions of round 1 in any level, they will get to choose the option to walkaway with $1000 (prize of round 1).
        - Failure to not answer all the questions will result in elimination.
    - Round 2:
        - If the player successfully completes round 1 and choose to not walkaway they will be in round 2.
        - 3 questions of varying difficulty in easy option.
            - Question 4: $8000, Question 5: $16000 and Question 6: $32000
        - 5 questions of varying difficulty in hard option.
            - Question 6: $2000, Question 7: $4000, Question 8: $8000, Question 9: $16000 and Question 10: $32000
        - Once a player answers all questions of round 2 in any level, they will get to choose the option to walkaway with $32000 (prize of round 2).
        - Failure to not answer all the questions will result in elimination.
    - Round 3:
        - If the player successfully completes round 2 and choose to not walkaway they will be in round 3.
        - 3 questions of varying difficulty in easy option.
            - Question 7: $125,000, Question 8: $500,000 and Question 9: $1,000,000
        - 5 questions of varying difficulty in hard option.
            - Question 11: $64,000, Question 12: $125,000, Question 13: $250,000, Question 14: $500,000 and Question 15: $1,000,000.
        - Once a player answers all questions of round 3 in any level, they will walkaway with $1,000,000 (prize of round 3). They have won the game in that level.
        - Failure to not answer all the questions will result in elimination.
- Other notes about the game:
    - The questions should be selected randomly from a bank of question.
    - No question should be repeated for a game.
    - For both difficulty option, a player will have 3 lifelines. In easy level players will have access to these lifelines from round 1 but for hard level, these lifeline will only be available in round 2.
    - A lifeline can only be used once in a game. These lifelines are:
        - 50/50 
        - Ask the audience
        - Phone a friend
    - There should be confirmation before a player finally submit an option as an answer. The user can choose to choose again if they choose not to submit.
    - Proper error handling should be there in the software.