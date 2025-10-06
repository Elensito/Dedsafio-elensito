# Guía Rápida - Comando /damageeditor reload

## 🎯 Propósito

El comando `/damageeditor reload` permite recargar la configuración de multiplicadores de daño **sin necesidad de reiniciar el servidor o cliente**. Esto es ideal para ajustar valores en tiempo real mientras juegas.

## 🔑 Requisitos

- **Permisos:** Nivel de operador 2 (OP level 2) o superior
- **En servidor:** Debes ser OP del servidor
- **En singleplayer:** Debes tener cheats habilitados

## 📝 Sintaxis

```
/damageeditor reload
```

## 🚀 Ejemplo de Uso Completo

### Paso 1: Modificar la configuración

Abre el archivo `config/damageeditor.json` y modifica los valores:

```json
{
  "entity_multipliers": {
    "minecraft:zombie": 0.5,    // Cambio: de 1.0 a 0.5
    "minecraft:creeper": 3.0    // Cambio: de 1.5 a 3.0
  },
  "environmental_multipliers": {
    "fall": 0.25,               // Cambio: de 1.0 a 0.25
    "fire": 0.0                 // Cambio: de 1.0 a 0.0 (inmunidad)
  }
}
```

### Paso 2: Guardar el archivo

Guarda el archivo `damageeditor.json` (Ctrl+S)

### Paso 3: Recargar en el juego

Abre el chat (T) y ejecuta:
```
/damageeditor reload
```

### Paso 4: Confirmar

Verás uno de estos mensajes:

✅ **Éxito:**
```
[DamageEditor] Configuration reloaded successfully!
```
- Color: Verde
- Significa: La configuración se aplicó correctamente

❌ **Error:**
```
[DamageEditor] Failed to reload configuration: [descripción del error]
```
- Color: Rojo
- Significa: Hay un error en el archivo JSON (revisa la sintaxis)

## 🔍 Verificar los Cambios

Después de recargar, los nuevos multiplicadores se aplican **inmediatamente**:

1. Los zombies ahora hacen 50% de daño (0.5x)
2. Los creepers hacen 300% de daño (3.0x)
3. El daño de caída se redujo al 25% (0.25x)
4. Eres inmune al fuego (0.0x)

## ⚠️ Errores Comunes

### Error: "Unknown command"
**Causa:** El mod no está instalado correctamente
**Solución:** Verifica que `damageeditor-1.0.0.jar` esté en la carpeta `mods`

### Error: "You do not have permission to use this command"
**Causa:** No tienes permisos de operador
**Solución en servidor:** Pide a un admin que ejecute `/op TuNombre`
**Solución singleplayer:** Habilita cheats al crear el mundo, o abre a LAN con cheats

### Error: JSON parsing failed
**Causa:** Sintaxis incorrecta en el archivo JSON
**Solución:** 
- Verifica que todas las comas estén bien colocadas
- Asegúrate de cerrar todas las llaves `{ }`
- Usa un validador JSON online: https://jsonlint.com/
- Restaura desde `config-example.json` si es necesario

## 💡 Tips y Trucos

### Tip 1: Pruebas rápidas
```bash
1. Edita config/damageeditor.json
2. /damageeditor reload
3. Prueba el daño
4. Si no te gusta, edita de nuevo
5. /damageeditor reload (repite hasta encontrar el balance perfecto)
```

### Tip 2: Backup de configuración
Antes de experimentar, copia tu configuración:
```powershell
# Windows PowerShell
copy config\damageeditor.json config\damageeditor-backup.json
```

### Tip 3: Restaurar valores por defecto
Si algo sale mal, elimina el archivo y reinicia:
```powershell
# Windows PowerShell
del config\damageeditor.json
# Reinicia el juego para generar valores por defecto
```

### Tip 4: Ver logs en tiempo real
Para depurar, revisa los logs:
- Cliente: `.minecraft\logs\latest.log`
- Servidor: `logs/latest.log`

Busca líneas con `[DamageEditor]` para ver qué está pasando.

## 🎮 Casos de Uso

### Caso 1: Hacer el juego más fácil
```json
{
  "entity_multipliers": {
    "minecraft:zombie": 0.5,
    "minecraft:skeleton": 0.5,
    "minecraft:creeper": 0.3
  },
  "environmental_multipliers": {
    "fall": 0.5,
    "fire": 0.5
  }
}
```
Ejecuta: `/damageeditor reload`

### Caso 2: Modo Hardcore extremo
```json
{
  "entity_multipliers": {
    "minecraft:zombie": 2.0,
    "minecraft:skeleton": 2.0,
    "minecraft:creeper": 3.0
  },
  "environmental_multipliers": {
    "fall": 2.0,
    "fire": 1.5
  }
}
```
Ejecuta: `/damageeditor reload`

### Caso 3: Modo creativo "pseudo"
```json
{
  "entity_multipliers": {
    "minecraft:zombie": 0.0,
    "minecraft:skeleton": 0.0,
    "minecraft:creeper": 0.0
  },
  "environmental_multipliers": {
    "fall": 0.0,
    "fire": 0.0,
    "lava": 0.0,
    "drown": 0.0
  }
}
```
Ejecuta: `/damageeditor reload`

## 📊 Monitoreo

El mod registra cada modificación de daño en los logs (nivel DEBUG):

```
[DamageEditor] Entity damage from minecraft:zombie: 4.0 -> 2.0 (x0.5)
[DamageEditor] Environmental damage from fall: 10.0 -> 2.5 (x0.25)
```

Esto te ayuda a confirmar que los multiplicadores funcionan correctamente.

## 🔗 Recursos Adicionales

- **README.md**: Documentación completa
- **BUILDING.md**: Guía de compilación
- **config-example.json**: Ejemplos de configuración
- **CHANGELOG.md**: Historial de cambios

---

**¿Necesitas ayuda?** Revisa los logs del juego o abre un issue en el repositorio del proyecto.
