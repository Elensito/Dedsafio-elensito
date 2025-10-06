@echo off
echo ========================================
echo   DamageEditor - Compilacion Automatica
echo ========================================
echo.

echo Verificando Java...
java -version
if %errorlevel% neq 0 (
    echo ERROR: Java no esta instalado o no esta en PATH
    echo Descarga JDK 21 desde: https://adoptium.net/
    pause
    exit /b 1
)
echo.

echo Limpiando compilaciones anteriores...
call .\gradlew.bat clean
echo.

echo Compilando el mod...
call .\gradlew.bat build
if %errorlevel% neq 0 (
    echo.
    echo ERROR: La compilacion fallo
    echo Revisa los errores arriba
    pause
    exit /b 1
)
echo.

echo ========================================
echo   COMPILACION EXITOSA!
echo ========================================
echo.
echo El archivo JAR esta en:
echo build\libs\damageeditor-1.0.0.jar
echo.
echo Para instalar:
echo 1. Copia el JAR a la carpeta mods de Minecraft
echo 2. Asegurate de tener Fabric Loader y Fabric API
echo 3. Inicia Minecraft 1.21 con Fabric
echo.

pause
