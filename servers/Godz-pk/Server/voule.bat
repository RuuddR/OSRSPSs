@echo off
title Satan's Isle Restarter
cls
echo Voule Auto restart
tskill java /a
echo Restarting server
title name Voule Auto Restarter
java -Xmx1024m -cp .;./jython.jar;./MySql/mysql-connector-java-3.0.17-ga-bin.jar server
cls
pause