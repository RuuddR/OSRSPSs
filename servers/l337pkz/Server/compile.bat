@echo off
COLOR 08
title 1337pkz Client
"C:\Program Files\Java\jdk1.5.0_06\bin\javac.exe" -cp . *.java
"C:\Program Files\Java\jdk1.5.0_06\bin\java.exe" server
pause