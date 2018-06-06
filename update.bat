@echo off

:loop

ping -n 60 127.1>NUL
::打印时间
echo %date:~0,4%-%date:~5,2%-%date:~8,2% %time:~0,2%:%time:~3,2%:%time:~6,2%
::执行更新
git pull

goto loop

pause

@echo on

:loop

::打印时间
ECHO %date:~0,4%-%date:~5,2%-%date:~8,2% %time:~0,2%:%time:~3,2%:%time:~6,2%  >>  AutogitPull.log
call echo %%i %%date%% %%time%%	>>	AutogitPull.log
::执行更新
cd C:\dev\IdeaProjects\mid

ECHO C:\dev\IdeaProjects\gomro.mid >>  AutogitPull.log
git pull  >>  AutogitPull.log

cd C:\dev\IdeaProjects\web

ECHO C:\dev\IdeaProjects\gomro.web >>  AutogitPull.log
git pull  >>  AutogitPull.log

ping -n 100 127.1>NUL

goto loop

pause
