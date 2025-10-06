package com.dedsafio;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class SpawnHandler {

	public static void register() {
		DedsafioElensitoMod.LOGGER.info("Spawn handler initialized - controlling natural spawns!");
	}

	/**
	 * Check if an entity should be allowed to spawn naturally
	 * Returns false if the entity is a food-giving animal that should not spawn naturally
	 */
	public static boolean shouldAllowNaturalSpawn(Entity entity) {
		EntityType<?> entityType = entity.getType();
		Identifier entityId = Registries.ENTITY_TYPE.getId(entityType);
		String entityName = entityId.toString();

		// Check if this entity has spawn control configured
		if (SpawnConfig.hasFoodAnimalSpawnControl(entityName)) {
			boolean canSpawn = SpawnConfig.canFoodAnimalSpawnNaturally(entityName);

			if (!canSpawn) {
				DedsafioElensitoMod.LOGGER.debug("Preventing natural spawn of food animal: {}", entityName);
				return false;
			}
		}

		return true;
	}
}