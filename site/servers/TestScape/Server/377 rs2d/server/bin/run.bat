@echo off

if  "%1"==""  goto  noparameter
	ant -f ..\custom-build.xml -Dmainclass=%1 run

goto  exit

:noparameter
	echo "Usage: run.bat <mainclass[required]>"
	echo "	- where <mainclass> is the fully qualified class name of a main class on the classpath."
	pause
:exit
