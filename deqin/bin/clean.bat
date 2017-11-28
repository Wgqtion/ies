@echo off

echo [INFO] Çå³ý.


cd %~dp0

cd ..

call mvn clean
cd bin
pause
cd bin
pasuse