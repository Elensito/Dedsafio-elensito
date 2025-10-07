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
	private static float velocidadHambre = 100.0f; // Porcentaje (100 = normal, 200 = doble velocidad, 50 = mitad)
	private static int creeperFuseTime = 30; // Tiempo en ticks antes de explotar (30 = 1.5 segundos, valor vanilla)

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

			// Load velocidad hambre setting
			if (config.has("velocidad_hambre_porcentaje")) {
				velocidadHambre = config.get("velocidad_hambre_porcentaje").getAsFloat();
			}

			// Load creeper fuse time setting
			if (config.has("creeper_fuse_time")) {
				creeperFuseTime = config.get("creeper_fuse_time").getAsInt();
			}

			DedsafioElensitoMod.LOGGER.info("Changes configuration loaded successfully! " +
				"Button Damage: {}, Radiacion: {}, Mobs Pacificos Agresivos: {}, Daño Mobs Pacificos: {}, Velocidad Hambre: {}%, " +
				"Creeper Fuse Time: {} ticks",
				buttonDamage, radiacion, mobsPacificosAgresivos, dañoMobsPacificos, velocidadHambre, creeperFuseTime);
		} catch (IOException e) {
			DedsafioElensitoMod.LOGGER.error("Failed to load changes configuration", e);
			throw new RuntimeException("Failed to load changes configuration", e);
		}
	}

	private static void createDefaultConfig() {
		JsonObject config = new JsonObject();
		
		config.addProperty("button_damage", 2.0f);
		config.addProperty("radiacion", false);
		config.addProperty("mobs_pacificos_agresivos", false);
		config.addProperty("daño_mobs_pacificos", 2.0f);
		config.addProperty("velocidad_hambre_porcentaje", 100.0f);
		config.addProperty("creeper_fuse_time", 30);

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

	public static float getVelocidadHambre() {
		return velocidadHambre;
	}

	public static int getCreeperFuseTime() {
		return creeperFuseTime;
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
		
		config.addProperty("button_damage", buttonDamage);
		config.addProperty("radiacion", radiacion);
		config.addProperty("mobs_pacificos_agresivos", mobsPacificosAgresivos);
		config.addProperty("daño_mobs_pacificos", dañoMobsPacificos);
		config.addProperty("velocidad_hambre_porcentaje", velocidadHambre);
		config.addProperty("creeper_fuse_time", creeperFuseTime);

		try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
			GSON.toJson(config, writer);
			DedsafioElensitoMod.LOGGER.info("Changes configuration saved!");
		} catch (IOException e) {
			DedsafioElensitoMod.LOGGER.error("Failed to save changes configuration", e);
		}
	}
}
