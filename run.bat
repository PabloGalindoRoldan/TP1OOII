@echo off
REM Script para compilar y ejecutar el proyecto

setlocal enabledelayedexpansion

REM Crear directorios si no existen
if not exist "out\production\TPOOIIV2" mkdir out\production\TPOOIIV2
if not exist "out\test\TPOOIIV2" mkdir out\test\TPOOIIV2

REM Compilar archivos fuente
echo [Compilando archivos fuente...]
javac -d out/production/TPOOIIV2 src/*.java
if errorlevel 1 (
    echo Error en compilación de fuentes
    exit /b 1
)

echo [Ejecutando Main...]
cd out\production\TPOOIIV2
java Main
cd ..\..\..

pause

