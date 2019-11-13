@echo off
title Compiling Scripts
cd Scripts
javac -cp .;./../../MoparScape.jar; *.java
pause