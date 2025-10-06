package com.dedsafio;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class DamageHandler {

	public static void register() {
		DedsafioElensitoMod.LOGGER.info("Damage handler initialized - using Mixin injection!");
	}

	/**
	 * Calculate modified damage based on damage source
	 */
	public static float calculateModifiedDamage(LivingEntity entity, DamageSource source, float originalAmount) {
		double multiplier = 1.0;

		// Check if damage is from an entity
		Entity attacker = source.getAttacker();
		if (attacker != null) {
			EntityType<?> entityType = attacker.getType();
			Identifier entityId = Registries.ENTITY_TYPE.getId(entityType);
			String entityName = entityId.toString();

			if (DamageConfig.hasEntityMultiplier(entityName)) {
				multiplier = DamageConfig.getEntityMultiplier(entityName);
				DedsafioElensitoMod.LOGGER.debug("Entity damage from {}: {} -> {} (x{})",
					entityName, originalAmount, originalAmount * multiplier, multiplier);
			}
		} else {
			// Check for environmental damage
			String damageTypeName = getDamageTypeName(source);

			if (DamageConfig.hasEnvironmentalMultiplier(damageTypeName)) {
				multiplier = DamageConfig.getEnvironmentalMultiplier(damageTypeName);
				DedsafioElensitoMod.LOGGER.debug("Environmental damage from {}: {} -> {} (x{})",
					damageTypeName, originalAmount, originalAmount * multiplier, multiplier);
			}
		}

		return (float) (originalAmount * multiplier);
	}

	/**
	 * Get the damage type name for environmental damage
	 */
	private static String getDamageTypeName(DamageSource source) {
		// Extract damage type name from the damage source
		String typeName = source.getName();

		// Map common damage types
		if (source.isOf(net.minecraft.entity.damage.DamageTypes.FALL)) {
			return "fall";
		} else if (source.isOf(net.minecraft.entity.damage.DamageTypes.IN_FIRE) ||
				   source.isOf(net.minecraft.entity.damage.DamageTypes.ON_FIRE)) {
			return "fire";
		} else if (source.isOf(net.minecraft.entity.damage.DamageTypes.LAVA)) {
			return "lava";
		} else if (source.isOf(net.minecraft.entity.damage.DamageTypes.DROWN)) {
			return "drown";
		} else if (source.isOf(net.minecraft.entity.damage.DamageTypes.LIGHTNING_BOLT)) {
			return "lightning";
		} else if (source.isOf(net.minecraft.entity.damage.DamageTypes.CACTUS)) {
			return "cactus";
		} else if (source.isOf(net.minecraft.entity.damage.DamageTypes.SWEET_BERRY_BUSH)) {
			return "sweet_berry_bush";
		} else if (source.isOf(net.minecraft.entity.damage.DamageTypes.STARVE)) {
			return "starve";
		} else if (source.isOf(net.minecraft.entity.damage.DamageTypes.FREEZE)) {
			return "freeze";
		} else if (source.isOf(net.minecraft.entity.damage.DamageTypes.MAGIC)) {
			return "magic";
		} else if (source.isOf(net.minecraft.entity.damage.DamageTypes.WITHER)) {
			return "wither";
		} else if (source.isOf(net.minecraft.entity.damage.DamageTypes.FALLING_ANVIL)) {
			return "anvil";
		} else if (source.isOf(net.minecraft.entity.damage.DamageTypes.FALLING_BLOCK)) {
			return "falling_block";
		} else if (source.isOf(net.minecraft.entity.damage.DamageTypes.DRAGON_BREATH)) {
			return "dragon_breath";
		} else if (source.isOf(net.minecraft.entity.damage.DamageTypes.HOT_FLOOR)) {
			return "hot_floor";
		} else if (source.isOf(net.minecraft.entity.damage.DamageTypes.CRAMMING)) {
			return "cramming";
		}

		return typeName.toLowerCase();
	}
}
