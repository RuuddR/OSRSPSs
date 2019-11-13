@echo off
COLOR f0
title RealisticScape Client
COLOR 08
title Compiling...
"C:\Program Files\Java\jdk1.5.0_06\bin\javac.exe" -cp . *.java
COLOR f0
title Starting RealisticScape...
"C:\Program Files\Java\jre1.5.0_06\bin\java.exe" server
COLOR f0
pause