package com.dedsafio.mixin;

import com.dedsafio.ChangesConfig;
import com.dedsafio.DedsafioElensitoMod;
import net.minecraft.block.ButtonBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ButtonBlock.class)
public class ButtonBlockMixin {

	/**
	 * Aplica daño al jugador cuando pulsa un botón
	 */
	@Inject(
		method = "onUse",
		at = @At("HEAD")
	)
	private void onButtonPress(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
		// Solo aplicar daño en el servidor
		if (!world.isClient) {
			float damage = ChangesConfig.getButtonDamage();
			
			// Solo aplicar daño si es mayor que 0
			if (damage > 0) {
				try {
					// Crear una fuente de daño genérica
					DamageSource damageSource = world.getDamageSources().generic();
					player.damage(damageSource, damage);
					
					DedsafioElensitoMod.LOGGER.debug("Player {} received {} damage from pressing button at {}",
						player.getName().getString(), damage, pos);
				} catch (Exception e) {
					DedsafioElensitoMod.LOGGER.error("Error applying button damage to player", e);
				}
			}
		}
	}
}
