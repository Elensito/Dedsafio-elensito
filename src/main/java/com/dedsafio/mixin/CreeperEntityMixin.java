package com.dedsafio.mixin;

import com.dedsafio.ChangesConfig;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin {

	@Shadow
	private int fuseTime;

	@Shadow
	private int currentFuseTime;

	/**
	 * Establece el fuse time configurado cuando el creeper es creado
	 */
	@Inject(
		method = "<init>",
		at = @At("RETURN")
	)
	private void onConstruct(EntityType<? extends CreeperEntity> entityType, World world, CallbackInfo ci) {
		if (!world.isClient) {
			int configuredFuseTime = ChangesConfig.getCreeperFuseTime();
			if (configuredFuseTime != 30) {
				this.fuseTime = configuredFuseTime;
			}
		}
	}

	/**
	 * Modifica el fuse time del creeper cuando empieza a explotar
	 */
	@Inject(
		method = "tick",
		at = @At("HEAD")
	)
	private void onTick(CallbackInfo ci) {
		int configuredFuseTime = ChangesConfig.getCreeperFuseTime();
		
		// Si el fuse time configurado es diferente del vanilla y el creeper está cargándose
		if (configuredFuseTime != 30 && this.currentFuseTime > 0 && this.fuseTime != configuredFuseTime) {
			this.fuseTime = configuredFuseTime;
		}
	}

	/**
	 * Establece el fuse time correcto cuando el creeper es cargado desde NBT
	 */
	@Inject(
		method = "readCustomDataFromNbt",
		at = @At("RETURN")
	)
	private void onReadNbt(NbtCompound nbt, CallbackInfo ci) {
		int configuredFuseTime = ChangesConfig.getCreeperFuseTime();
		if (configuredFuseTime != 30) {
			this.fuseTime = configuredFuseTime;
		}
	}
}
