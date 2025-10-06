package com.dedsafio.mixin;

import com.dedsafio.ChangesConfig;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public class MobEntityMixin {
	
	@Shadow @Final protected GoalSelector goalSelector;
	@Shadow @Final protected GoalSelector targetSelector;
	
	@Unique
	private boolean dedsafio_hasAddedAggressiveGoals = false;
	
	@Inject(method = "tick", at = @At("HEAD"))
	private void onTick(CallbackInfo ci) {
		MobEntity mob = (MobEntity) (Object) this;
		
		// Si los mobs pacíficos deben ser agresivos y este es un mob pacífico
		if (ChangesConfig.areMobsPacificosAgresivos() && isPassiveMob(mob) && !dedsafio_hasAddedAggressiveGoals) {
			try {
				// Agregar goals de ataque y targeting
				targetSelector.add(1, new ActiveTargetGoal<>(mob, PlayerEntity.class, true));
				dedsafio_hasAddedAggressiveGoals = true;
			} catch (Exception e) {
				// Algunos mobs pueden no soportar estos goals
			}
		}
		
		// Si los mobs pacíficos ya no deben ser agresivos, resetear
		if (!ChangesConfig.areMobsPacificosAgresivos() && dedsafio_hasAddedAggressiveGoals) {
			dedsafio_hasAddedAggressiveGoals = false;
		}
	}
	
	@Unique
	private boolean isPassiveMob(MobEntity entity) {
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
			   name.equals("minecraft:tadpole") ||
			   name.equals("minecraft:allay") ||
			   name.equals("minecraft:villager") ||
			   name.equals("minecraft:wandering_trader") ||
			   name.equals("minecraft:strider") ||
			   name.equals("minecraft:dolphin") ||
			   name.equals("minecraft:cod") ||
			   name.equals("minecraft:salmon") ||
			   name.equals("minecraft:tropical_fish") ||
			   name.equals("minecraft:pufferfish") ||
			   name.equals("minecraft:llama") ||
			   name.equals("minecraft:trader_llama") ||
			   name.equals("minecraft:snow_golem") ||
			   name.equals("minecraft:bat") ||
			   name.equals("minecraft:ocelot") ||
			   name.equals("minecraft:armadillo");
	}
}
