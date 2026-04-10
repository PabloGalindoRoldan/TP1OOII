@echo off
REM Compilar los archivos fuente
echo [Compilando archivos fuente...]
if not exist out\production\TPOOIIV2 mkdir out\production\TPOOIIV2
javac -d out\production\TPOOIIV2 src\*.java

REM Compilar tests
echo [Compilando tests...]
if not exist out\test\TPOOIIV2 mkdir out\test\TPOOIIV2
javac -cp "out\production\TPOOIIV2;lib\*" -d out\test\TPOOIIV2 test\*.java

REM Ejecutar los tests usando JUnit Platform
echo [Ejecutando tests...]
java -cp "out\test\TPOOIIV2;out\production\TPOOIIV2;lib\*" org.junit.platform.console.ConsoleLauncher --scan-classpath out\test\TPOOIIV2

pause


