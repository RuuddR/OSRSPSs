@echo off
COLOR 09
title PhoneScape Source
"C:\Program Files\Java\jdk1.5.0_07\bin\javac" -cp . *.java
"C:\Program Files\Java\jdk1.5.0_07\bin\java" server
pause 
