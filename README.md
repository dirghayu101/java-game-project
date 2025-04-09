# Running the compiled application directly
`java -jar final-project.jar`

# To compile the application from scratch and running it

**Working directory for both should be final-project with src.**

1. For Powershell (haven't confirmed myself):
``` powershell
# Compiles the file into a compiled directory.
javac -d compiled (Get-ChildItem -Recurse -Filter *.java -Path src | ForEach-Object { $_.FullName })
# Copy the static folder which contains text files for the game
Copy-Item -Recurse static compiled/
# Runs the game.
java -cp compiled App
```
2. For Mac or linux based system:
```bash
# Compile the java files and copy the static text files.
rm -rf compiled && mkdir compiled && javac -d compiled $(find src -name "*.java") && cp -r static compiled/
# Run the game.
java -cp compiled App
```


