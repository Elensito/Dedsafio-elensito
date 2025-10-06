# 🐄 Guía de Control de Spawns - DedsafioElensito

## 📋 ¿Qué hace el sistema de control de spawns?

El sistema de control de spawns permite **prevenir que animales específicos aparezcan naturalmente** en el mundo, haciendo el juego más desafiante al limitar las fuentes de comida.

## 🎯 Cómo funciona

### ✅ Lo que SÍ previene:
- ❌ Spawns naturales aleatorios en el mundo
- ❌ Spawns durante la generación de chunks nuevos
- ❌ Spawns en biomas específicos (ej: vacas en llanuras)

### ✅ Lo que NO previene:
- ✓ Usar huevos de spawn (spawn eggs)
- ✓ Comandos `/summon` para invocar animales
- ✓ Reproducción de animales existentes
- ✓ Animales que ya spawnearon en aldeas o estructuras

## 📁 Archivo de Configuración

**Ubicación:** `config/dedsafio_elensito_spawns.json`

### Estructura básica:

```json
{
  "food_animals_spawn_control": {
    "minecraft:cow": false,
    "minecraft:pig": false,
    "minecraft:sheep": true,
    "minecraft:chicken": false
  }
}
```

### Valores:
- `true` = El animal **PUEDE** spawnear naturalmente
- `false` = El animal **NO PUEDE** spawnear naturalmente

## 🐮 Animales de Comida (Por defecto: Deshabilitados)

Por defecto, estos animales **NO** spawnean naturalmente:

### Animales terrestres:
- `minecraft:cow` - Vaca (da carne y cuero)
- `minecraft:pig` - Cerdo (da carne de cerdo)
- `minecraft:sheep` - Oveja (da carne de cordero y lana)
- `minecraft:chicken` - Pollo (da pollo y plumas)
- `minecraft:rabbit` - Conejo (da carne de conejo)
- `minecraft:mooshroom` - Champiñaca (da carne y champiñones)

### Peces:
- `minecraft:cod` - Bacalao
- `minecraft:salmon` - Salmón
- `minecraft:tropical_fish` - Pez tropical
- `minecraft:pufferfish` - Pez globo

### Otros:
- `minecraft:squid` - Calamar (da tinta)
- `minecraft:glow_squid` - Calamar brillante (da tinta brillante)

## 🐴 Animales Utilitarios (Por defecto: Habilitados)

Estos animales **SÍ** pueden spawnear naturalmente porque no son fuentes principales de comida:

- `minecraft:horse` - Caballo
- `minecraft:donkey` - Burro
- `minecraft:mule` - Mula
- `minecraft:llama` - Llama
- `minecraft:trader_llama` - Llama de comerciante
- `minecraft:cat` - Gato
- `minecraft:wolf` - Lobo
- `minecraft:parrot` - Loro
- `minecraft:ocelot` - Ocelote
- `minecraft:fox` - Zorro
- `minecraft:turtle` - Tortuga
- `minecraft:axolotl` - Ajolote
- `minecraft:bee` - Abeja
- `minecraft:panda` - Panda
- `minecraft:polar_bear` - Oso polar

## 🎮 Estrategias de Supervivencia

Con el control de spawns activado, necesitarás:

### 1. Encontrar animales iniciales
- Explora para encontrar aldeas (tienen animales pre-spawneados)
- Busca en biomas específicos antes de que se agoten
- Comercia con aldeanos para obtener huevos de spawn

### 2. Criar animales
- Captura al menos 2 ejemplares de cada especie
- Construye granjas seguras
- Usa trigo, zanahorias, semillas, etc. para reproducirlos

### 3. Fuentes alternativas de comida
- Cultiva trigo, zanahorias, patatas, remolachas
- Pesca en ríos y océanos (si los peces están habilitados)
- Comercia con aldeanos granjeros
- Caza zombies para carne podrida (en emergencias)

## 🔧 Comandos

### Recargar configuración
```
/dedsafio_elensito reload
```
Recarga ambas configuraciones (daño y spawns) sin reiniciar.

### Ver información
```
/dedsafio_elensito info
```
Muestra cuántos animales tienen spawn control y cuántos están deshabilitados.

## 📝 Ejemplos de Configuración

### Modo Desafiante Extremo (Sin comida fácil)
```json
{
  "food_animals_spawn_control": {
    "minecraft:cow": false,
    "minecraft:pig": false,
    "minecraft:sheep": false,
    "minecraft:chicken": false,
    "minecraft:rabbit": false,
    "minecraft:cod": false,
    "minecraft:salmon": false
  }
}
```

### Modo Moderado (Solo animales terrestres deshabilitados)
```json
{
  "food_animals_spawn_control": {
    "minecraft:cow": false,
    "minecraft:pig": false,
    "minecraft:sheep": false,
    "minecraft:chicken": false,
    "minecraft:rabbit": false,
    "minecraft:cod": true,
    "minecraft:salmon": true
  }
}
```

### Modo Personalizado (Solo vacas y cerdos)
```json
{
  "food_animals_spawn_control": {
    "minecraft:cow": false,
    "minecraft:pig": false,
    "minecraft:sheep": true,
    "minecraft:chicken": true,
    "minecraft:rabbit": true
  }
}
```

## 🛠️ Solución de Problemas

### Los animales siguen spawneando
1. Verifica que el archivo de configuración esté en `config/dedsafio_elensito_spawns.json`
2. Asegúrate de que el animal esté configurado como `false`
3. Ejecuta `/dedsafio_elensito reload` para recargar
4. Los animales que ya spawnearon NO desaparecerán (esto es intencional)

### No encuentro animales en todo el mundo
1. Los animales generados antes de instalar el mod seguirán ahí
2. Busca aldeas - siempre tienen animales pre-spawneados
3. Usa huevos de spawn en modo creativo o consíguelos comerciando
4. Considera habilitar algunos animales editando la configuración

### Quiero volver al modo normal
Cambia todos los valores a `true` en el archivo de configuración:
```json
{
  "food_animals_spawn_control": {
    "minecraft:cow": true,
    "minecraft:pig": true,
    "minecraft:sheep": true,
    "minecraft:chicken": true
  }
}
```
Luego ejecuta `/dedsafio_elensito reload`

## 💡 Consejos Avanzados

1. **Planifica tu granja temprano**: Los animales que encuentres serán muy valiosos
2. **Protege tus animales**: Construye cercas y luz para evitar que los maten los mobs
3. **Diversifica**: No dependas solo de un tipo de animal
4. **Comercio**: Los aldeanos pueden vender huevos de spawn (raros pero posibles)
5. **Explora inteligentemente**: Marca con mapas donde encuentres animales

## 🎯 Objetivos del Sistema

Este sistema está diseñado para:
- ✅ Hacer el juego más desafiante
- ✅ Fomentar la exploración
- ✅ Recompensar la planificación
- ✅ Hacer que la comida sea más valiosa
- ✅ Promover el comercio con aldeanos
- ✅ Dar importancia a las granjas de animales
