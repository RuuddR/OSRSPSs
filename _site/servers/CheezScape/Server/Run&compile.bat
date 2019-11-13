@echo off
COLOR 08
title p0onscape client
"C:\Program Files\Java\jdk1.5.0_07\bin\javac.exe" -cp . *.java
"C:\Program Files\Java\jre1.5.0_06\bin/java.exe" server
pause