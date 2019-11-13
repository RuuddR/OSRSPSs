@echo off
title Figmentscape compile/run
"C:/Program Files/Java/jdk1.6.0_04/bin/javac.exe" *.java
"C:/Program Files/Java/jdk1.6.0_04/bin/java.exe" -Xmx512m server
pause
