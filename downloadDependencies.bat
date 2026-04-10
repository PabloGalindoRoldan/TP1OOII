@echo off
REM Script para descargar las dependencias de JUnit 5

setlocal enabledelayedexpansion

REM Crear carpeta lib si no existe
if not exist lib mkdir lib

echo Descargando JUnit 5 dependencies...

REM Descargar JUnit Jupiter API
if not exist lib\junit-jupiter-api-5.9.2.jar (
    echo Descargando junit-jupiter-api-5.9.2.jar...
    powershell -Command "(New-Object Net.WebClient).DownloadFile('https://repo1.maven.org/maven2/org/junit/jupiter/junit-jupiter-api/5.9.2/junit-jupiter-api-5.9.2.jar', 'lib\junit-jupiter-api-5.9.2.jar')"
)

REM Descargar JUnit Jupiter Engine
if not exist lib\junit-jupiter-engine-5.9.2.jar (
    echo Descargando junit-jupiter-engine-5.9.2.jar...
    powershell -Command "(New-Object Net.WebClient).DownloadFile('https://repo1.maven.org/maven2/org/junit/jupiter/junit-jupiter-engine/5.9.2/junit-jupiter-engine-5.9.2.jar', 'lib\junit-jupiter-engine-5.9.2.jar')"
)

REM Descargar JUnit Platform Console Standalone
if not exist lib\junit-platform-console-standalone-1.9.2.jar (
    echo Descargando junit-platform-console-standalone-1.9.2.jar...
    powershell -Command "(New-Object Net.WebClient).DownloadFile('https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.9.2/junit-platform-console-standalone-1.9.2.jar', 'lib\junit-platform-console-standalone-1.9.2.jar')"
)

REM Descargar OpenTest4J
if not exist lib\opentest4j-1.2.0.jar (
    echo Descargando opentest4j-1.2.0.jar...
    powershell -Command "(New-Object Net.WebClient).DownloadFile('https://repo1.maven.org/maven2/org/opentest4j/opentest4j/1.2.0/opentest4j-1.2.0.jar', 'lib\opentest4j-1.2.0.jar')"
)

REM Descargar JUnit Platform Commons
if not exist lib\junit-platform-commons-1.9.2.jar (
    echo Descargando junit-platform-commons-1.9.2.jar...
    powershell -Command "(New-Object Net.WebClient).DownloadFile('https://repo1.maven.org/maven2/org/junit/platform/junit-platform-commons/1.9.2/junit-platform-commons-1.9.2.jar', 'lib\junit-platform-commons-1.9.2.jar')"
)

echo.
echo Descarga completada!
pause

