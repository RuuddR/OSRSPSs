@echo off
title Server
java -cp .;./bin;-Xmx512M server.server -server
pause