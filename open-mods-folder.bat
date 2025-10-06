@echo off
echo ========================================
echo   Abriendo carpeta de mods de Minecraft
echo ========================================
echo.

set MODS_FOLDER=%appdata%\.minecraft\mods

if not exist "%MODS_FOLDER%" (
    echo La carpeta de mods no existe. Creandola...
    mkdir "%MODS_FOLDER%"
    echo Carpeta creada: %MODS_FOLDER%
)

echo Abriendo: %MODS_FOLDER%
echo.
explorer "%MODS_FOLDER%"

echo.
echo INSTRUCCIONES:
echo 1. Copia damageeditor-1.0.0.jar a esta carpeta
echo 2. Descarga Fabric API desde: https://modrinth.com/mod/fabric-api
echo 3. Copia fabric-api-x.x.x.jar tambien
echo 4. Inicia Minecraft con el perfil Fabric
echo.

pause
