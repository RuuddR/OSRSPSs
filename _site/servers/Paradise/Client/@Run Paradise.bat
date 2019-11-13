@echo off
COLOR 09
title Paradise 317
@echo Client Is loading!
@echo -----------------------
java -Xmx1000m -cp .;Theme.jar Gui 0 0 highmem members 32
pause