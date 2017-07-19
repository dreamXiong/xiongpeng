
cd /D %~dp0
cd ..
cd ..
call mvn clean install -Dmaven.test.skip=true

pause