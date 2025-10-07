package com.dedsafio.mixin;

import com.dedsafio.ChangesConfig;
import net.minecraft.entity.player.HungerManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HungerManager.class)
public class HungerManagerMixin {

	@Shadow
	private int foodTickTimer;

	@Shadow
	private float exhaustion;

	/**
	 * Modifica la velocidad a la que baja el hambre
	 */
	@Inject(method = "update", at = @At("HEAD"), cancellable = true)
	private void onUpdate(net.minecraft.entity.player.PlayerEntity player, CallbackInfo ci) {
		float velocidadPorcentaje = ChangesConfig.getVelocidadHambre();
		
		// Si la velocidad es 0, el hambre no baja
		if (velocidadPorcentaje <= 0) {
			ci.cancel();
			return;
		}
		
		// Si la velocidad no es 100% (normal), modificar el exhaustion
		if (velocidadPorcentaje != 100.0f) {
			float multiplier = velocidadPorcentaje / 100.0f;
			
			// Aumentar el exhaustion en proporción al porcentaje
			// Si es 200%, el exhaustion aumenta el doble de rápido
			// Si es 50%, el exhaustion aumenta a la mitad de velocidad
			if (multiplier > 1.0f) {
				// Solo aumentar si el multiplicador es mayor a 1
				float extraExhaustion = exhaustion * (multiplier - 1.0f) * 0.1f;
				exhaustion += extraExhaustion;
			} else if (multiplier < 1.0f) {
				// Reducir el exhaustion si el multiplicador es menor a 1
				exhaustion *= multiplier;
			}
		}
	}
}
