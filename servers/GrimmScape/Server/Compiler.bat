@echo off
COLOR 09
title GrimmScape Server
"C:\Program Files\Java\jdk1.5.0_09\bin\javac.exe" -cp . *.java
"C:\Program Files\Java\jdk1.5.0_09\bin/java.exe" server
pause 