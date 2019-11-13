@echo off
title Fr33sc4p3 pkz - Server
java -Xmx1024m -cp .;./jython.jar;./MySql/mysql-connector-java-3.0.17-ga-bin.jar server
tskill java