
cd /D %~dp0
cd ..

mvn clean install tomcat7:redeploy -P web
