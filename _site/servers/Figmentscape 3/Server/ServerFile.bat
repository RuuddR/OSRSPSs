@echo off
title Project Czar
tskill java /a
javac *.java
java -cp .;./jython.jar server
