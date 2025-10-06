package com.dedsafio.mixin;

import com.dedsafio.ChangesConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetherWartBlock.class)
public class NetherWartBlockMixin {
	@Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
	private void onRandomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
		if (ChangesConfig.isRadiacionEnabled()) {
			ci.cancel();
		}
	}
}
