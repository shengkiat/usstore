call setenv.bat

dir /s /B src\*.java > sources.txt

javac -d classes @sources.txt