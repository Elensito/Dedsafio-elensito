# Guía de Compilación y Uso - DamageEditor

## Requisitos Previos

1. **Java Development Kit (JDK) 21**
   - Descarga: https://adoptium.net/
   - Verifica la instalación: `java -version`

2. **Git** (opcional, para clonar el repositorio)
   - Descarga: https://git-scm.com/

## Compilación del Mod

### Paso 1: Preparar el entorno

```powershell
# Navega al directorio del proyecto
cd c:\Users\alana\Desktop\Test\DamageEditor
```

### Paso 2: Compilar el mod

```powershell
# En Windows
.\gradlew.bat build

# Si tienes problemas con permisos, ejecuta:
.\gradlew.bat clean build
```

El proceso de compilación puede tomar varios minutos la primera vez ya que Gradle descargará todas las dependencias necesarias.

### Paso 3: Localizar el archivo JAR compilado

El archivo compilado estará en:
```
build\libs\damageeditor-1.0.0.jar
```

## Instalación del Mod en Minecraft

### Para Cliente (Singleplayer)

1. Instala **Fabric Loader** para Minecraft 1.21:
   - Ve a https://fabricmc.net/use/
   - Descarga e instala el instalador de Fabric
   - Selecciona la versión 1.21 de Minecraft

2. Descarga **Fabric API**:
   - https://www.curseforge.com/minecraft/mc-mods/fabric-api
   - Descarga la versión para Minecraft 1.21

3. Copia los archivos JAR:
   - `fabric-api-x.x.x.jar` (Fabric API)
   - `damageeditor-1.0.0.jar` (este mod)
   
   A la carpeta de mods:
   - Windows: `%appdata%\.minecraft\mods`
   - Linux: `~/.minecraft/mods`
   - Mac: `~/Library/Application Support/minecraft/mods`

4. Inicia Minecraft con el perfil de Fabric

### Para Servidor

1. Instala Fabric Server:
   - Descarga el instalador de Fabric Server
   - Ejecuta: `java -jar fabric-installer.jar server`

2. Copia los archivos JAR a la carpeta `mods` del servidor:
   - `fabric-api-x.x.x.jar`
   - `damageeditor-1.0.0.jar`

3. Inicia el servidor normalmente

## Configuración

### Ubicación del archivo de configuración

Después del primer inicio, el archivo de configuración se creará automáticamente en:
- **Cliente**: `.minecraft\config\damageeditor.json`
- **Servidor**: `config\damageeditor.json`

### Editar la configuración

#### Método 1: Recarga en caliente (Recomendado)

1. Abre `config\damageeditor.json` con un editor de texto
2. Modifica los valores según tus preferencias
3. Guarda el archivo
4. En el juego, ejecuta: `/damageeditor reload`
5. Los cambios se aplicarán inmediatamente

**Nota:** Requieres permisos de operador (OP level 2) para ejecutar el comando.

#### Método 2: Reinicio completo

1. Detén el juego/servidor
2. Abre `config\damageeditor.json` con un editor de texto
3. Modifica los valores según tus preferencias
4. Guarda el archivo
5. Reinicia el juego/servidor

### Ejemplo de configuración personalizada

```json
{
  "entity_multipliers": {
    "minecraft:zombie": 0.5,       // Zombies hacen 50% de daño
    "minecraft:creeper": 2.0,      // Creepers hacen 200% de daño (el doble)
    "minecraft:skeleton": 0.75,    // Esqueletos hacen 75% de daño
    "minecraft:warden": 0.1        // Warden hace 10% de daño (muy reducido)
  },
  "environmental_multipliers": {
    "fall": 0.5,      // Daño de caída reducido al 50%
    "fire": 0.0,      // Inmunidad al fuego
    "lava": 1.5,      // Lava hace 150% de daño
    "drown": 1.0      // Ahogamiento normal
  }
}
```

## Solución de Problemas

### El mod no aparece en el juego

- Verifica que tengas instalado Fabric Loader y Fabric API
- Asegúrate de que el archivo JAR esté en la carpeta `mods` correcta
- Revisa los logs en `.minecraft\logs\latest.log` para errores

### El mod no funciona correctamente

- Verifica que la configuración en `config\damageeditor.json` sea válida JSON
- Revisa los logs del juego para mensajes de error
- Asegúrate de estar usando Minecraft 1.21

### Errores de compilación

```powershell
# Limpia y recompila
.\gradlew.bat clean build --refresh-dependencies
```

### El gradle wrapper no funciona

Si `gradlew.bat` no funciona, descarga Gradle manualmente:
```powershell
# Instala Gradle con Chocolatey (si lo tienes)
choco install gradle

# O descarga desde https://gradle.org/releases/
```

## Testing en Desarrollo

Para probar el mod sin compilar cada vez:

```powershell
# Inicia el cliente de prueba
.\gradlew.bat runClient

# Inicia el servidor de prueba
.\gradlew.bat runServer
```

Esto iniciará una instancia de Minecraft con tu mod ya instalado para pruebas.

## Contribuir

Si deseas contribuir al proyecto:

1. Haz un fork del repositorio
2. Crea una rama para tu característica
3. Realiza tus cambios
4. Envía un pull request

## Soporte

Si encuentras problemas:
- Revisa el archivo README.md
- Busca en los logs del juego
- Reporta bugs con información detallada del error
