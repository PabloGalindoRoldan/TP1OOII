#!/bin/bash
# Script para compilar y ejecutar el proyecto

# Crear directorios si no existen
mkdir -p out/production/TPOOIIV2
mkdir -p out/test/TPOOIIV2

# Compilar archivos fuente
echo "[Compilando archivos fuente...]"
javac -d out/production/TPOOIIV2 src/*.java
if [ $? -ne 0 ]; then
    echo "Error en compilación de fuentes"
    exit 1
fi

echo "[Ejecutando Main...]"
cd out/production/TPOOIIV2
java Main
cd ../../..

# Mantener la ventana abierta (solo en Windows)
if [[ "$OSTYPE" == "msys" || "$OSTYPE" == "cygwin" ]]; then
    read -p "Presiona Enter para salir..."
fi

