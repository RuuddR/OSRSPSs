@echo off
title Compiling Controllers
cd Controllers
javac -cp .;./../../MoparScape.jar; *.java
pause