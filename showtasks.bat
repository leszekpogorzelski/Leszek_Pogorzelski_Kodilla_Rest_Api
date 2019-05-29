call runcrud.bat
if "%ERRORLEVEL%" == "0" goto runchrome
echo.
echo runcrud.bat has errors - breaking work
goto fail

:runchrome
call "C:\Program Files (x86)\Google\Chrome\Application\chrome.exe" http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo google chrome has errors
goto fail

:fail
echo.
echo There were errors!
goto stoptomcat

:stoptomcat
call %CATALINA_HOME%\bin\shutdown.bat

:end
echo.
echo work done!