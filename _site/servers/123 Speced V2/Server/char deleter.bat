@echo off
:menu
color 09
echo This utility can be used to clear all of the current character information
echo WARNING: Once you use this, there will be no way to recover lost character files

==100% Credits To Mr Gunz==
echo.
echo Type C to clear all character files and information
echo Type E to exit this utility
echo.
set /p option=Option:
if %option%==c goto clear
if %option%==C goto clear
if %option%==e goto exit
if %option%==E goto exit
if %option%==* goto error
goto error
:clear
cls
del /F /S /Q characters
del /F /S /Q connectedfrom
del /F /S /Q moreinfo
del /F /S /Q savedgames
echo All character related information has been deleted now
pause
exit
:exit
cls
exit
:error
echo An error has occured
echo Press enter to go back to the main menu
pause


