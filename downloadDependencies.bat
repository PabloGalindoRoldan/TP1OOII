@echo off
REM Script para descargar las dependencias de JUnit 5 y JavaMail

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
    powershell -Command "(New-Object Net.WebClient).DownloadFile('https://repo1.maven.org/maven2/org/junit/platform/junit-platform-commons-1.9.2/junit-platform-commons-1.9.2.jar', 'lib\junit-platform-commons-1.9.2.jar')"
)

REM Descargar JavaMail
if not exist lib\javax.mail-1.6.2.jar (
    echo Descargando javax.mail-1.6.2.jar...
    powershell -Command "(New-Object Net.WebClient).DownloadFile('https://repo1.maven.org/maven2/com/sun/mail/javax.mail/1.6.2/javax.mail-1.6.2.jar', 'lib\javax.mail-1.6.2.jar')"
)

REM Descargar Java Activation
if not exist lib\javax.activation-api-1.2.0.jar (
    echo Descargando javax.activation-api-1.2.0.jar...
    powershell -Command "(New-Object Net.WebClient).DownloadFile('https://repo1.maven.org/maven2/javax/activation/javax.activation-api/1.2.0/javax.activation-api-1.2.0.jar', 'lib\javax.activation-api-1.2.0.jar')"
)

REM Descargar Java Activation Implementation
if not exist lib\activation-1.1.1.jar (
    echo Descargando activation-1.1.1.jar...
    powershell -Command "(New-Object Net.WebClient).DownloadFile('https://repo1.maven.org/maven2/javax/activation/activation/1.1.1/activation-1.1.1.jar', 'lib\activation-1.1.1.jar')"
)

echo.
echo Descarga completada!
pause
