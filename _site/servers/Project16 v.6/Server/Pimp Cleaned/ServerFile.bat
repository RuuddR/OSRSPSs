@echo off
title pimpScape Server compile+restaRTer
tskill java /a
javac *.java
java -cp .;./jython.jar server
