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
	private static boolean radiacion = false;
	private static boolean mobsPacificosAgresivos = false;
	private static float dañoMobsPacificos = 2.0f;

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

			// Load radiacion setting
			if (config.has("radiacion")) {
				radiacion = config.get("radiacion").getAsBoolean();
			}

			// Load mobs pacificos agresivos setting
			if (config.has("mobs_pacificos_agresivos")) {
				mobsPacificosAgresivos = config.get("mobs_pacificos_agresivos").getAsBoolean();
			}

			// Load daño mobs pacificos setting
			if (config.has("daño_mobs_pacificos")) {
				dañoMobsPacificos = config.get("daño_mobs_pacificos").getAsFloat();
			}

			DedsafioElensitoMod.LOGGER.info("Changes configuration loaded successfully! " +
				"Button Damage: {}, Radiacion: {}, Mobs Pacificos Agresivos: {}, Daño Mobs Pacificos: {}",
				buttonDamage, radiacion, mobsPacificosAgresivos, dañoMobsPacificos);
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
		
		config.addProperty("_comment_radiacion", "Si es true, los cultivos no crecerán por paso de ticks");
		config.addProperty("radiacion", false);
		
		config.addProperty("_comment_mobs_pacificos", "Si es true, los mobs pacíficos atacarán a los jugadores");
		config.addProperty("mobs_pacificos_agresivos", false);
		
		config.addProperty("_comment_daño_mobs_pacificos", "Daño que hacen todos los mobs pacíficos cuando están agresivos");
		config.addProperty("daño_mobs_pacificos", 2.0f);

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

	public static boolean isRadiacionEnabled() {
		return radiacion;
	}

	public static boolean areMobsPacificosAgresivos() {
		return mobsPacificosAgresivos;
	}

	public static float getDañoMobsPacificos() {
		return dañoMobsPacificos;
	}

	public static void setButtonDamage(float damage) {
		buttonDamage = damage;
		saveConfig();
	}

	public static void setRadiacion(boolean enabled) {
		radiacion = enabled;
		saveConfig();
	}

	public static void setMobsPacificosAgresivos(boolean enabled) {
		mobsPacificosAgresivos = enabled;
		saveConfig();
	}

	public static void setDañoMobsPacificos(float damage) {
		dañoMobsPacificos = damage;
		saveConfig();
	}

	private static void saveConfig() {
		JsonObject config = new JsonObject();
		
		config.addProperty("_comment_button_damage", "Daño que recibe el jugador al pulsar un botón");
		config.addProperty("button_damage", buttonDamage);
		
		config.addProperty("_comment_radiacion", "Si es true, los cultivos no crecerán por paso de ticks");
		config.addProperty("radiacion", radiacion);
		
		config.addProperty("_comment_mobs_pacificos", "Si es true, los mobs pacíficos atacarán a los jugadores");
		config.addProperty("mobs_pacificos_agresivos", mobsPacificosAgresivos);
		
		config.addProperty("_comment_daño_mobs_pacificos", "Daño que hacen todos los mobs pacíficos cuando están agresivos");
		config.addProperty("daño_mobs_pacificos", dañoMobsPacificos);

		try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
			GSON.toJson(config, writer);
			DedsafioElensitoMod.LOGGER.info("Changes configuration saved!");
		} catch (IOException e) {
			DedsafioElensitoMod.LOGGER.error("Failed to save changes configuration", e);
		}
	}
}
