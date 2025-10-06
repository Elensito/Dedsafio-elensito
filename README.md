# DedsafioElensito - Fabric Mod for Minecraft 1.21

## Descripción

DedsafioElensito es un mod de Fabric para Minecraft 1.21 diseñado para hacer el juego más desafiante mediante el sistema de multiplicadores de daño.

- **Modificación de daño**: Controla el daño recibido según la fuente del daño (entidades y ambiental)
- **Soporte completo**: Todos los mobs hostiles, bosses y daño ambiental de Minecraft 1.21

## Características

- ✅ Multiplicadores de daño para todas las entidades hostiles de Minecraft 1.21
- ✅ Soporte para bosses (Ender Dragon, Wither, Warden)
- ✅ Multiplicadores de daño ambiental
- ✅ Configuración JSON fácil de editar
- ✅ Comando `/dedsafio_elensito reload` para recargar configuración sin reiniciar
- ✅ Comando `/dedsafio_elensito info` para ver información del mod
- ✅ Recarga en caliente de configuración

## Instalación

1. Asegúrate de tener instalado [Fabric Loader](https://fabricmc.net/use/) para Minecraft 1.21
2. Descarga [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api) para la versión 1.21
3. Coloca ambos mods (Fabric API y DedsafioElensito) en la carpeta `mods` de tu instalación de Minecraft
4. Inicia el juego

## Compilación

Para compilar el mod desde el código fuente:

```bash
# En Windows PowerShell
.\gradlew.bat build

# En Linux/Mac
./gradlew build
```

El archivo JAR compilado se encontrará en `build/libs/dedsafio_elensito-1.0.0.jar`

## Configuración

El mod genera automáticamente un archivo de configuración:

- `config/dedsafio_elensito_multiplicador.json` - Configuración de multiplicadores de daño

### Configuración de Multiplicadores (dedsafio_elensito_multiplicador.json)

```json
{
  "entity_multipliers": {
    "minecraft:zombie": 1.0,
    "minecraft:skeleton": 1.0,
    "minecraft:creeper": 1.5,
    "minecraft:spider": 1.0,
    "minecraft:enderman": 1.0,
    "minecraft:blaze": 1.0,
    "minecraft:wither_skeleton": 1.0,
    "minecraft:ender_dragon": 1.0,
    "minecraft:wither": 1.0,
    "minecraft:warden": 1.0
  },
  "environmental_multipliers": {
    "fall": 1.0,
    "fire": 1.0,
    "lava": 1.0,
    "drown": 1.0,
    "lightning": 1.0,
    "explosion": 1.0
  }
}
```

**Entidades Soportadas:**
- Todos los zombies (zombie, husk, drowned, zombie_villager)
- Todos los esqueletos (skeleton, stray, wither_skeleton)
- Arañas (spider, cave_spider)
- Creepers
- Enderman, endermite, silverfish
- Mobs del Nether (blaze, ghast, magma_cube, hoglin, zoglin, piglin, piglin_brute)
- Mobs acuáticos hostiles (guardian, elder_guardian, drowned)
- Illagers (pillager, vindicator, evoker, vex, ravager)
- Bosses (ender_dragon, wither, warden)
- Mobs neutrales que pueden atacar (bee, wolf, iron_golem, polar_bear, llama, panda, dolphin, goat)

### Multiplicadores de Daño

- `1.0` = 100% de daño (normal)
- `0.5` = 50% de daño (reduce a la mitad)
- `2.0` = 200% de daño (duplica el daño)
- `0.0` = 0% de daño (inmunidad)



## Comandos

### `/dedsafio_elensito reload`

Recarga el archivo de configuración de multiplicadores sin necesidad de reiniciar el servidor o cliente.

**Permisos requeridos:** Nivel de operador 2 (OP level 2)

### `/dedsafio_elensito info`

Muestra información sobre el mod y sus características.

**Permisos requeridos:** Nivel de operador 2 (OP level 2)

## Cómo hace el juego más desafiante

**Daño configurable**: Puedes aumentar el daño de cualquier mob hostil o boss para hacer el juego más difícil:
- Aumenta el daño de creepers para explosiones más peligrosas
- Haz que los esqueletos sean más letales
- Aumenta el daño de bosses como el Wither o el Ender Dragon
- Configura daño ambiental más peligroso (caídas, fuego, lava, etc.)

**Ejemplos de configuración desafiante:**
- Creepers con 2.0x daño = Explosiones devastadoras
- Warden con 1.5x daño = El mob más peligroso del juego
- Caídas con 1.5x daño = Necesitarás más cuidado al explorar
- Fuego/Lava con 2.0x daño = El Nether se vuelve mucho más peligroso

## Licencia

MIT License - Siéntete libre de usar, modificar y distribuir este mod.

## Soporte

Si encuentras algún problema o tienes sugerencias, por favor abre un issue en el repositorio del proyecto.
