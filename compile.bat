call setenv.bat

REM dir /s /B src\*.java > sources.txt

REM "javac" -d classes @sources.txt

"javac" -d classes -sourcepath src src\sg\edu\nus\iss\uss\client\Application.java