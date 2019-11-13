@echo off
:index
bold

set voi=commands.txt
set dir=.\
if exist .\sources set dir=.\sources\
if exist .\Sources set dir=.\Sources\
set code=
set code1=
set code2=
set code3=
set code4=
set code5=
set code6=
set code7=
set code8=
set code9=
set code10=
cls
title Command maker - Home
echo :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
echo :: How to make a command:                                        ::
echo :: 1. Choose command name                                        ::
echo :: 2. Choose selection                                           ::
echo :: 3. Follow onscreen instructions                               ::
echo :: You can make fully functional commands in under 10 seconds!   ::
echo :: This program is (c)opyrighted 2007 Runescape3 Ltd.            ::
echo :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: -----------------------------------------------------         ::
::  |----\                                             |         ::
::  | [] |                                             |         ::
::  |    /                                             |         ::
::  |    \ une                                         |         ::
::  |_/\__\ Scape3 SoftWare                            |         ::
:: -----------------------------------------------------         ::
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
echo start - Start making the command.
echo exit - Leave the Code Generator.
echo.
echo.
set /p opt=Option: 
if %opt%==start goto step1a
if %opt%==Start goto step1a
if %opt%==START goto step1a
if %opt%==exit goto end
if %opt%==Exit goto end
if %opt%==EXIT goto end
if %opt%==* goto er
goto er
:er
title Command maker - Error!
cls
echo Unrecognized command. Make sure you are spelling
echo the commands correctly.
pause
goto index
:er2
title Command maker - Error!
cls
echo You do not have commands.txt. Would you like to
echo create it now?
echo.
echo.
set /p yn=(Y/N)? 
if %yn%==y goto y
if %yn%==Y goto y
if %yn%==yes goto y
if %yn%==Yes goto y
if %yn%==YES goto y
if %yn%==n goto n
if %yn%==N goto n
if %yn%==no goto n
if %yn%==No goto n
if %yn%==NO goto n
if %yn%==* goto er
goto er
:y
echo //made by command maker  >> %dir%%voi%
echo. >> %dir%%voi%
echo import java.io.*; >> %dir%%voi%
echo import java.util.StringTokenizer; >> %dir%%voi%
echo import java.util.Calendar; >> %dir%commands.txt
echo import java.util.GregorianCalendar; >> %dir%%voi%
echo. >> %dir%%voi%
goto step1
:n
cls
echo You can not continue until you have commands.txt.
pause
goto intro
:end
cls
exit
:step1
cls
title Command maker - Step 1
if not exist %dir%%voi% (goto er2) else (goto step1a)
if not exist %dir%%voi% (goto er2) else (goto step1a)
if not exist %dir%%voi% (goto er2) else (goto step1a)
:step1a
cls
title Command maker - Step 2
set /p name=What is the name of the command?
:step1b
echo Now select which features you would like.
echo all - teleport, additem and sendmessage
echo sel1 - teleport and additem
echo sel2 - additem and sendmessage
echo sel3 - teleport and sendmessage
set /p opt3=Option: 
if %opt3%==all goto step1g
if %opt3%==All goto step1g
if %opt3%==ALL goto step1g
if %opt3%==sel1 goto stepsel1g
if %opt3%==Sel1 goto stepsel1g
if %opt3%==SEL1 goto stepsel1g
if %opt3%==sel2 goto stepsel2x
if %opt3%==Sel2 goto stepsel2x
if %opt3%==SEL2 goto stepsel2x
if %opt3%==sel3 goto stepsel3g
if %opt3%==Sel3 goto stepsel3g
if %opt3%==SEL3 goto stepsel3g
if %opt3%==* goto er
:stepsel1
cls
title Command maker - Step 3
echo Now install the features one by one starting with tele
echo item - (second)Adds additem to your command
echo tele -  (first)Adds teleto to your command
echo finish - Finalizes the command.
echo.
echo.
set /p opt3=Option: 
if %opt3%==finish goto step1e
if %opt3%==Finish goto step1e
if %opt3%==FINISH goto step1e
if %opt3%==item goto stepsel1x
if %opt3%==Item goto stepsel1x
if %opt3%==ITEM goto stepsel1x
if %opt3%==tele goto stepsel1g
if %opt3%==Tele goto stepsel1g
if %opt3%==TELE goto stepsel1g
if %opt3%==* goto er
goto er
:stepsel2
cls
title Command maker - Step 3
echo Now install the features one by one starting with additem
echo message - (second)Adds a sendMessage to your command.
echo item - (first)Adds additem to your command
echo finish - Finalizes the command.
echo.
echo.
set /p opt3=Option: 
if %opt3%==message goto stepsel2d
if %opt3%==Message goto stepsel2d
if %opt3%==MESSAGE goto stepsel2d
if %opt3%==finish goto step1e
if %opt3%==Finish goto step1e
if %opt3%==FINISH goto step1e
if %opt3%==item goto stepsel2x
if %opt3%==Item goto stepsel2x
if %opt3%==ITEM goto stepsel2x
if %opt3%==* goto er
goto er
:stepall
cls
title Command maker - Step 3
echo Now install the features one by one starting with tele
echo message - (third)Adds a sendMessage to your command.
echo item - (second)Adds additem to your command
echo tele -  (first)Adds teleto to your command
echo finish - Finalizes the command.
echo.
echo.
set /p opt3=Option: 
if %opt3%==message goto step1d
if %opt3%==Message goto step1d
if %opt3%==MESSAGE goto step1d
if %opt3%==finish goto step1e
if %opt3%==Finish goto step1e
if %opt3%==FINISH goto step1e
if %opt3%==item goto step1x
if %opt3%==Item goto step1x
if %opt3%==ITEM goto step1x
if %opt3%==tele goto step1g
if %opt3%==Tele goto step1g
if %opt3%==TELE goto step1g
if %opt3%==* goto er
goto er
:stepsel3
cls
title Command maker - Step 3
echo Now install the features one by one starting with tele
echo message - (second)Adds a sendMessage to your command.
echo tele -  (first)Adds teleto to your command
echo finish - Finalizes the command.
echo.
echo.
set /p opt3=Option: 
if %opt3%==message goto stepsel3d
if %opt3%==Message goto stepsel3d
if %opt3%==MESSAGE goto stepsel3d
if %opt3%==finish goto step1e
if %opt3%==Finish goto step1e
if %opt3%==FINISH goto step1e
if %opt3%==tele goto stepsel3g
if %opt3%==Tele goto stepsel3g
if %opt3%==TELE goto stepsel3g
if %opt3%==* goto er
goto er
:stepsel1x
cls
title Command maker - Step 5
set /p mess=What item number/how much do you want? eg: 995, 2000000000 for 2billion gp: 
set item=addItem(%mess%);
goto stepsel1f
:step1x
cls
title Command maker - Step 5
set /p mess=What item number/how much do you want? eg: 995, 2000000000 for 2billion gp: 
set item=addItem(%mess%);
goto step1d

:stepsel2x
cls
title Command maker - Step 5
set /p mess=What item number/how much do you want? eg: 995, 2000000000 for 2billion gp: 
set item=addItem(%mess%);
goto stepsel2d

:stepsel2d
cls
title Command maker - Step 5
set /p mess=What do you want the message to say? 
set code=sendMessage("%mess%");
goto stepsel2f
:step1d
cls
title Command maker - Step 5
set /p mess=What do you want the message to say? 
set code=sendMessage("%mess%");
goto step1f
:step1g
cls
title Command maker - Step 5
set /p mess=teleport x coord: 
set telex=teleportToX = %mess%;
goto step1z
:step1z
cls
title Command maker - Step 5
set /p mess=teleport y coord: 
set teley=teleportToY = %mess%; 
goto step1x
:stepsel3d
cls
title Command maker - Step 5
set /p mess=What do you want the message to say? 
set code=sendMessage("%mess%");
goto stepsel3f
:stepsel3g
cls
title Command maker - Step 5
set /p mess=teleport x coord: 
set telex=teleportToX = %mess%;
goto stepsel3z
:stepsel3z
cls
title Command maker - Step 5
set /p mess=teleport y coord: 
set teley=teleportToY = %mess%;
goto stepsel3d
:stepsel1g
cls
title Command maker - Step 5
set /p mess=teleport x coord: 
set telex=teleportToX = %mess%;
goto stepsel1z
:stepsel1z
cls
title Command maker - Step 5
set /p mess=teleport y coord: 
set teley=teleportToY = %mess%;
goto stepsel1x
:step1f
cls
title Command maker - Generating command
echo Generating the command and adding it to commands.txt...
pause
cls
if not defined code goto step1fa
echo if (command.startsWith("%name%")) >> %dir%%voi%
echo { >> %dir%%voi%
echo 	%code% >> %dir%%voi%
echo 	%item% >> %dir%%voi% >> %dir%%voi%
echo 	%telex% >> %dir%%voi% >> %dir%%voi%
echo 	%teley% >> %dir%%voi% >> %dir%%voi%
echo } >> %dir%%voi%
echo. >> %dir%%voi%
goto step1g
:stepsel3f
cls
title Command maker - Generating command
echo Generating the command and adding it to commands.txt...
pause
cls
if not defined code goto step1fa
echo if (command.startsWith("%name%")) >> %dir%%voi%
echo { >> %dir%%voi%
echo 	%code% >> %dir%%voi%
echo 	%telex% >> %dir%%voi% >> %dir%%voi%
echo 	%teley% >> %dir%%voi% >> %dir%%voi%
echo } >> %dir%%voi%
echo. >> %dir%%voi%
goto step1g
:stepsel2f
cls
title Command maker - Generating command
echo Generating the command and adding it to commands.txt...
pause
cls
if not defined code goto step1fa
echo if (command.startsWith("%name%")) >> %dir%%voi%
echo { >> %dir%%voi%
echo 	%code% >> %dir%%voi%
echo 	%item% >> %dir%%voi% >> %dir%%voi%
echo } >> %dir%%voi%
echo. >> %dir%%voi%
goto step1g
:stepsel1f
cls
title Command maker - Generating command
echo Generating the command and adding it to commands.txt...
pause
cls
echo if (command.startsWith("%name%")) >> %dir%%voi%
echo { >> %dir%%voi%
echo 	%item% >> %dir%%voi% >> %dir%%voi%
echo 	%telex% >> %dir%%voi% >> %dir%%voi%
echo 	%teley% >> %dir%%voi% >> %dir%%voi%
echo } >> %dir%%voi%
echo. >> %dir%%voi%
goto step1g
:step1fa
if %nummess% GEQ 2 goto step1fb
echo if (command.startsWith("%name%")) >> %dir%%voi%
echo { >> %dir%%voi%
echo } >> %dir%%voi%
echo. >> %dir%%voi%
goto step1g

:step1fc
echo } >> %dir%%voi%
echo. >> %dir%%voi%
:step1g
cls
title Command maker - Command Created Successfully!
echo The command was created and added to commands.txt
echo succesffully! Where would you like to go now?
echo.
echo start - Starts another command.
echo exit - Exits out of the Generator.
echo.
echo.
set /p opt5=Option: 
if %opt5%==start goto step1a
if %opt5%==Start goto step1a
if %opt5%==START goto step1a
if %opt5%==exit goto end
if %opt5%==Exit goto end
if %opt5%==EXIT goto end
if %opt5%==* goto er

goto er