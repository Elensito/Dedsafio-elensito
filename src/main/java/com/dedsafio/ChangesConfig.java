package com.dedsafio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ChangesConfig {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final File CONFIG_FILE = new File("config/dedsafio_elensito_changes.json");

	// Configuration values
	private static float buttonDamage = 2.0f;

	public static void loadConfig() {
		if (!CONFIG_FILE.getParentFile().exists()) {
			CONFIG_FILE.getParentFile().mkdirs();
		}

		if (!CONFIG_FILE.exists()) {
			createDefaultConfig();
		}

		try (FileReader reader = new FileReader(CONFIG_FILE)) {
			JsonObject config = JsonParser.parseReader(reader).getAsJsonObject();

			// Load button damage setting
			if (config.has("button_damage")) {
				buttonDamage = config.get("button_damage").getAsFloat();
			}

			DedsafioElensitoMod.LOGGER.info("Changes configuration loaded successfully! " +
				"Button Damage: {}",
				buttonDamage);
		} catch (IOException e) {
			DedsafioElensitoMod.LOGGER.error("Failed to load changes configuration", e);
			throw new RuntimeException("Failed to load changes configuration", e);
		}
	}

	private static void createDefaultConfig() {
		JsonObject config = new JsonObject();
		
		// Default values with descriptions
		config.addProperty("_comment_button_damage", "Daño que recibe el jugador al pulsar un botón");
		config.addProperty("button_damage", 2.0f);

		try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
			GSON.toJson(config, writer);
			DedsafioElensitoMod.LOGGER.info("Default changes configuration created!");
		} catch (IOException e) {
			DedsafioElensitoMod.LOGGER.error("Failed to create default changes configuration", e);
		}
	}

	public static float getButtonDamage() {
		return buttonDamage;
	}

	public static void setButtonDamage(float damage) {
		buttonDamage = damage;
		saveConfig();
	}

	private static void saveConfig() {
		JsonObject config = new JsonObject();
		
		config.addProperty("_comment_button_damage", "Daño que recibe el jugador al pulsar un botón");
		config.addProperty("button_damage", buttonDamage);

		try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
			GSON.toJson(config, writer);
			DedsafioElensitoMod.LOGGER.info("Changes configuration saved!");
		} catch (IOException e) {
			DedsafioElensitoMod.LOGGER.error("Failed to save changes configuration", e);
		}
	}
}
