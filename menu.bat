@echo off
title DamageEditor - Menu de Compilacion e Instalacion
color 0A

:menu
cls
echo ========================================
echo   DamageEditor - Menu Principal
echo ========================================
echo.
echo 1. Compilar el mod (crear JAR)
echo 2. Abrir carpeta de mods de Minecraft
echo 3. Copiar JAR a la carpeta de mods
echo 4. Verificar instalacion de Java
echo 5. Limpiar archivos de compilacion
echo 6. Salir
echo.
echo ========================================
set /p choice="Selecciona una opcion (1-6): "

if "%choice%"=="1" goto compile
if "%choice%"=="2" goto openmods
if "%choice%"=="3" goto copymods
if "%choice%"=="4" goto checkjava
if "%choice%"=="5" goto clean
if "%choice%"=="6" goto exit
goto menu

:compile
cls
echo ========================================
echo   Compilando DamageEditor...
echo ========================================
echo.
call .\gradlew.bat build
if %errorlevel% neq 0 (
    echo.
    echo ERROR: La compilacion fallo
    pause
    goto menu
)
echo.
echo EXITO! JAR creado en: build\libs\damageeditor-1.0.0.jar
echo.
pause
goto menu

:openmods
cls
echo ========================================
echo   Abriendo carpeta de mods
echo ========================================
echo.
set MODS_FOLDER=%appdata%\.minecraft\mods
if not exist "%MODS_FOLDER%" (
    mkdir "%MODS_FOLDER%"
    echo Carpeta creada: %MODS_FOLDER%
)
explorer "%MODS_FOLDER%"
echo Carpeta abierta: %MODS_FOLDER%
echo.
pause
goto menu

:copymods
cls
echo ========================================
echo   Copiando JAR a carpeta de mods
echo ========================================
echo.
set MODS_FOLDER=%appdata%\.minecraft\mods
set JAR_FILE=build\libs\damageeditor-1.0.0.jar

if not exist "%JAR_FILE%" (
    echo ERROR: El JAR no existe. Debes compilar primero (opcion 1^)
    pause
    goto menu
)

if not exist "%MODS_FOLDER%" (
    mkdir "%MODS_FOLDER%"
)

copy "%JAR_FILE%" "%MODS_FOLDER%\"
if %errorlevel% equ 0 (
    echo.
    echo EXITO! Mod copiado a: %MODS_FOLDER%
    echo.
    echo RECUERDA:
    echo - Necesitas Fabric Loader instalado
    echo - Necesitas Fabric API en la carpeta mods
    echo - Usa Minecraft 1.21
) else (
    echo ERROR: No se pudo copiar el archivo
)
echo.
pause
goto menu

:checkjava
cls
echo ========================================
echo   Verificando instalacion de Java
echo ========================================
echo.
java -version
if %errorlevel% neq 0 (
    echo.
    echo ERROR: Java no esta instalado
    echo Descarga JDK 21 desde: https://adoptium.net/
) else (
    echo.
    echo Java esta instalado correctamente!
)
echo.
pause
goto menu

:clean
cls
echo ========================================
echo   Limpiando archivos de compilacion
echo ========================================
echo.
call .\gradlew.bat clean
echo.
echo Archivos de compilacion eliminados
echo.
pause
goto menu

:exit
cls
echo.
echo Gracias por usar DamageEditor!
echo.
timeout /t 2 >nul
exit
