@echo off
echo =========================================
echo  Descargando Gradle Wrapper...
echo =========================================
echo.

echo Creando directorios...
if not exist "gradle\wrapper" mkdir "gradle\wrapper"

echo Descargando gradle-wrapper.jar...
powershell -Command "& {[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; Invoke-WebRequest -Uri 'https://github.com/gradle/gradle/raw/v8.8.0/gradle/wrapper/gradle-wrapper.jar' -OutFile 'gradle/wrapper/gradle-wrapper.jar'}"

if exist "gradle\wrapper\gradle-wrapper.jar" (
    echo.
    echo SUCCESS! Gradle wrapper descargado correctamente.
    echo.
    echo Ahora puedes compilar con: .\gradlew.bat build
    echo.
) else (
    echo.
    echo ERROR: No se pudo descargar el wrapper.
    echo.
    echo SOLUCION ALTERNATIVA:
    echo 1. Instala Gradle manualmente desde: https://gradle.org/releases/
    echo 2. O descarga un proyecto Fabric de ejemplo que incluya el wrapper
    echo.
)

pause
