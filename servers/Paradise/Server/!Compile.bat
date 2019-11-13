@echo off

SET javac=javac

:: Clean up the "bin" folder
IF EXIST "bin" RD /S /Q "bin"

:: Make a new "bin" directory if need be.
IF NOT EXIST "bin" MD "bin"

%javac% -d bin -cp src src/net/paradise/Server.java

pause