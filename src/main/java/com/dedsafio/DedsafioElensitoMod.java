package com.dedsafio;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DedsafioElensitoMod implements ModInitializer {
	public static final String MOD_ID = "dedsafio_elensito";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("DedsafioElensito mod initializing...");

		// Load configuration
		DamageConfig.loadConfig();

		// Register damage event handler
		DamageHandler.register();

		// Register commands
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			DedsafioElensitoCommand.register(dispatcher);
		});

		LOGGER.info("DedsafioElensito mod initialized successfully!");
	}
}
