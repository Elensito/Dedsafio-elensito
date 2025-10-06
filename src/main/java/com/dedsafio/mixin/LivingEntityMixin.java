package com.dedsafio.mixin;

import com.dedsafio.DamageHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

	/**
	 * Modify the damage amount before it's applied to the entity
	 * This intercepts the damage method and modifies the amount parameter
	 */
	@ModifyVariable(
		method = "damage",
		at = @At("HEAD"),
		argsOnly = true,
		ordinal = 0
	)
	private float modifyDamageAmount(float amount, DamageSource source) {
		LivingEntity entity = (LivingEntity) (Object) this;
		return DamageHandler.calculateModifiedDamage(entity, source, amount);
	}
}
