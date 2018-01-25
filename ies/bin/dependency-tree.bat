@echo off

echo [INFO] ²é¿´ÒÀÀµÊ÷.


cd %~dp0

cd ..

call mvn dependency:tree
cd bin
pause
cd bin
pasuse