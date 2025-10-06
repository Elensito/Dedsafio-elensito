# 🚀 Inicio Rápido - DedsafioElensito

## ⚡ Instalación en 3 pasos

1. **Instala Fabric Loader** para Minecraft 1.21
   - https://fabricmc.net/use/

2. **Descarga Fabric API** para Minecraft 1.21
   - https://modrinth.com/mod/fabric-api

3. **Copia ambos mods** a la carpeta `mods`:
   - `fabric-api-x.x.x.jar`
   - `dedsafio_elensito-1.0.0.jar`

## 🎮 Primer Uso

1. **Inicia Minecraft** con el perfil de Fabric
2. Los archivos de configuración se crearán automáticamente en `config/`
3. ¡Juega!

## 📁 Archivo de Configuración

### `config/dedsafio_elensito_multiplicador.json`
Controla los multiplicadores de daño para todas las entidades y daño ambiental.

**Ejemplo - Hacer creepers más peligrosos:**
```json
{
  "entity_multipliers": {
    "minecraft:creeper": 2.0,
    "minecraft:zombie": 1.5,
    "minecraft:skeleton": 1.5
  },
  "environmental_multipliers": {
    "fall": 1.5,
    "lava": 2.0
  }
}
```

## 🎯 Comandos Principales

### Ver información del mod
```
/dedsafio_elensito info
```

### Recargar configuración
```
/dedsafio_elensito reload
```

**Nota:** Necesitas ser OP nivel 2 (operador del servidor) para usar estos comandos.

## ⚙️ Configuración Rápida por Dificultad

### 🟢 Dificultad: Fácil
Daño ligeramente aumentado solo en algunos mobs:

```json
{
  "entity_multipliers": {
    "minecraft:creeper": 1.2,
    "minecraft:zombie": 1.1
  },
  "environmental_multipliers": {
    "fall": 1.0,
    "fire": 1.0
  }
}
```

### 🟡 Dificultad: Moderado (Por defecto)
Daño normal con algunos mobs peligrosos aumentados:

```json
{
  "entity_multipliers": {
    "minecraft:creeper": 1.5
  }
}
```

### 🔴 Dificultad: Difícil
Daño significativamente aumentado:

```json
{
  "entity_multipliers": {
    "minecraft:zombie": 1.5,
    "minecraft:skeleton": 1.5,
    "minecraft:creeper": 2.0,
    "minecraft:enderman": 1.5,
    "minecraft:wither_skeleton": 1.8,
    "minecraft:warden": 1.5
  },
  "environmental_multipliers": {
    "fall": 1.5,
    "fire": 1.5,
    "lava": 2.0,
    "explosion": 1.5
  }
}
```

## 💡 Consejos para Principiantes

1. **Empieza despacio:** No aumentes demasiado el daño en tu primer intento
2. **Prueba configuraciones:** Cambia los multiplicadores y recarga con `/dedsafio_elensito reload`
3. **Armadura es clave:** Con daño aumentado, necesitarás mejor armadura más temprano
4. **Bosses:** Ten cuidado al aumentar el daño de bosses como el Wither o Warden
5. **Balance:** Considera aumentar el daño gradualmente a medida que obtienes mejor equipo

## 🆘 Solución Rápida de Problemas

### El juego crashea al iniciar
- ✅ Verifica que tienes Fabric API instalado
- ✅ Asegúrate de usar Minecraft 1.21
- ✅ Verifica que tengas Java 21 instalado

### Los comandos no funcionan
- ✅ Debes ser OP del servidor: `/op TuNombre`
- ✅ En singleplayer, habilita cheats al crear el mundo

### El daño es demasiado alto
- ✅ Edita `dedsafio_elensito_multiplicador.json`
- ✅ Reduce los valores (ej: 2.0 → 1.5)
- ✅ Ejecuta `/dedsafio_elensito reload`

### Quiero volver al daño normal
Cambia todos los multiplicadores a `1.0` en el archivo de configuración y recarga

## 📚 Más Información

- **README.md** - Documentación completa del mod
- **multiplicador-example.json** - Ejemplo completo con todas las entidades

## 🎯 ¿Qué hace el mod más desafiante?

1. **Daño configurable** - Los enemigos pueden hacer mucho más daño
2. **Bosses más peligrosos** - Aumenta el desafío de Ender Dragon, Wither y Warden
3. **Daño ambiental** - Haz que caídas, fuego y lava sean más peligrosos
4. **Estrategia requerida** - Necesitarás mejor equipo y planificación

¡Disfruta del desafío! 🎮
