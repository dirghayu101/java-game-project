## Why is userInput Scanner a static method?
- Using multiple scanner was invoking input mismatch error. So for the user input, same instance of scanner is going to be shared across application.

## Why is display a Singleton Class?
- I wanted to try singleton design pattern, first of all.
- Second reason is that display can have different states like taking in input, or confirming user options etc at any point, making it singleton will be easy to keep track of what's being displayed.