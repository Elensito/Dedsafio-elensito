package com.dedsafio;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;

import java.util.List;

public class PassiveMobHandler {

	private static int tickCounter = 0;

	public static void onServerTick(ServerWorld world) {
		// Solo ejecutar cada 20 ticks (1 segundo)
		tickCounter++;
		if (tickCounter < 20) {
			return;
		}
		tickCounter = 0;

		// Si los mobs pacíficos no están agresivos, no hacer nada
		if (!ChangesConfig.areMobsPacificosAgresivos()) {
			return;
		}

		// Iterar sobre todos los jugadores en el mundo
		for (PlayerEntity player : world.getPlayers()) {
			// Buscar mobs pacíficos cerca del jugador (10 bloques)
			Box searchBox = player.getBoundingBox().expand(10.0);
			List<LivingEntity> nearbyEntities = world.getEntitiesByClass(
				LivingEntity.class,
				searchBox,
				entity -> isPassiveMob(entity) && entity.isAlive()
			);

			// Para cada mob pacífico cercano
			for (LivingEntity mob : nearbyEntities) {
				// Si está lo suficientemente cerca (3 bloques) y el mob puede ver al jugador
				if (mob.squaredDistanceTo(player) <= 9.0 && mob.canSee(player)) {
					// Aplicar daño al jugador
					float damage = ChangesConfig.getDañoMobsPacificos();
					if (damage > 0) {
						DamageSource damageSource = mob.getDamageSources().mobAttack(mob);
						player.damage(damageSource, damage);
					}
				}
			}
		}
	}

	private static boolean isPassiveMob(LivingEntity entity) {
		EntityType<?> type = entity.getType();
		Identifier id = Registries.ENTITY_TYPE.getId(type);
		String name = id.toString();

		return name.equals("minecraft:cow") ||
			   name.equals("minecraft:pig") ||
			   name.equals("minecraft:sheep") ||
			   name.equals("minecraft:chicken") ||
			   name.equals("minecraft:rabbit") ||
			   name.equals("minecraft:horse") ||
			   name.equals("minecraft:donkey") ||
			   name.equals("minecraft:mule") ||
			   name.equals("minecraft:camel") ||
			   name.equals("minecraft:cat") ||
			   name.equals("minecraft:parrot") ||
			   name.equals("minecraft:axolotl") ||
			   name.equals("minecraft:squid") ||
			   name.equals("minecraft:glow_squid") ||
			   name.equals("minecraft:turtle") ||
			   name.equals("minecraft:sniffer") ||
			   name.equals("minecraft:frog") ||
			   name.equals("minecraft:allay") ||
			   name.equals("minecraft:villager") ||
			   name.equals("minecraft:wandering_trader") ||
			   name.equals("minecraft:strider");
	}
}
