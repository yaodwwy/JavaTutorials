@echo off

:loop

ping -n 60 127.1>NUL
::打印时间
echo %date:~0,4%-%date:~5,2%-%date:~8,2% %time:~0,2%:%time:~3,2%:%time:~6,2%
::执行更新
git pull

goto loop

pause