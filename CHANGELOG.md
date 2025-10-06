# Changelog - DamageEditor

All notable changes to this project will be documented in this file.

## [1.0.0] - 2025-10-06

### Added
- ✨ Initial release of DamageEditor mod for Minecraft 1.21
- ✅ Entity damage multiplier system
  - Modify damage from any entity type (zombies, creepers, skeletons, etc.)
  - Configurable percentage-based multipliers
- ✅ Environmental damage multiplier system
  - Support for fall, fire, lava, drowning, lightning, and more
  - Configurable percentage-based multipliers
- ✅ JSON configuration system
  - Auto-generated config file at `config/damageeditor.json`
  - Default values for common entities and damage types
- ✅ `/damageeditor reload` command
  - Hot-reload configuration without restarting server
  - Requires OP level 2
  - Color-coded feedback messages (green for success, red for errors)
- ✅ Mixin-based damage interception
  - Uses LivingEntity damage method injection
  - Efficient and compatible with other mods
- ✅ Debug logging
  - Logs damage modifications for troubleshooting
  - Shows original damage, modified damage, and multiplier applied

### Technical Details
- Minecraft version: 1.21
- Fabric Loader: 0.16.5+
- Fabric API: 0.105.0+1.21
- Java: 21
- Uses Fabric Command API v2
- Uses Mixin for damage modification

### Documentation
- Complete README.md with usage instructions
- BUILDING.md with compilation and installation guide
- config-example.json with all available options
- MIT License included

---

## Future Plans

### Planned Features (Future Versions)
- [ ] Per-player damage multipliers
- [ ] Dimension-specific multipliers (Nether, End, etc.)
- [ ] Time-based multipliers (day/night)
- [ ] Damage caps and minimums
- [ ] GUI for in-game configuration
- [ ] Statistics and damage tracking
- [ ] Integration with permission mods

### Compatibility
- Currently tested with Fabric only
- May be compatible with most Fabric mods
- Report compatibility issues if found
