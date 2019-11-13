@echo off
color 77
title Ags Pking Compile
echo ========================
echo Type C For Compile
echo ========================
echo Type any other to close
:def
set /p compile=Option:
if %compile%==c goto compile
if %compile%==C goto compile
:compile
"C:\program files\Java\jdk1.6.0_12\bin\javac.exe" *.java
pause
goto def
:run
JAVA -Xmx500m EGUI
pause