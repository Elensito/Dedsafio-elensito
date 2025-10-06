package com.dedsafio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DamageConfig {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final File CONFIG_FILE = new File("config/dedsafio_elensito_multiplicador.json");

	// Map for entity damage multipliers (entity type -> multiplier)
	private static Map<String, Double> entityMultipliers = new HashMap<>();

	// Map for environmental damage multipliers (damage type -> multiplier)
	private static Map<String, Double> environmentalMultipliers = new HashMap<>();

	public static void loadConfig() {
		if (!CONFIG_FILE.getParentFile().exists()) {
			CONFIG_FILE.getParentFile().mkdirs();
		}

		if (!CONFIG_FILE.exists()) {
			createDefaultConfig();
		}

		// Clear existing configurations before reloading
		entityMultipliers.clear();
		environmentalMultipliers.clear();

		try (FileReader reader = new FileReader(CONFIG_FILE)) {
			JsonObject config = JsonParser.parseReader(reader).getAsJsonObject();

			// Load entity multipliers
			if (config.has("entity_multipliers")) {
				JsonObject entities = config.getAsJsonObject("entity_multipliers");
				entities.entrySet().forEach(entry -> {
					// Skip comments in JSON
					if (!entry.getKey().startsWith("_")) {
						entityMultipliers.put(entry.getKey(), entry.getValue().getAsDouble());
					}
				});
			}

			// Load environmental multipliers
			if (config.has("environmental_multipliers")) {
				JsonObject environmental = config.getAsJsonObject("environmental_multipliers");
				environmental.entrySet().forEach(entry -> {
					// Skip comments in JSON
					if (!entry.getKey().startsWith("_")) {
						environmentalMultipliers.put(entry.getKey(), entry.getValue().getAsDouble());
					}
				});
			}

			DedsafioElensitoMod.LOGGER.info("Configuration loaded successfully! " +
				"Entities: {}, Environmental: {}",
				entityMultipliers.size(),
				environmentalMultipliers.size());
		} catch (IOException e) {
			DedsafioElensitoMod.LOGGER.error("Failed to load configuration", e);
			throw new RuntimeException("Failed to load configuration", e);
		}
	}

	private static void createDefaultConfig() {
		JsonObject config = new JsonObject();

		// Default entity multipliers (1.0 = 100% damage, 0.5 = 50%, 2.0 = 200%)
		JsonObject entities = new JsonObject();
		
		// Hostile Mobs
		entities.addProperty("minecraft:zombie", 1.0);
		entities.addProperty("minecraft:zombie_villager", 1.0);
		entities.addProperty("minecraft:husk", 1.0);
		entities.addProperty("minecraft:drowned", 1.0);
		entities.addProperty("minecraft:skeleton", 1.0);
		entities.addProperty("minecraft:stray", 1.0);
		entities.addProperty("minecraft:wither_skeleton", 1.0);
		entities.addProperty("minecraft:creeper", 1.5);
		entities.addProperty("minecraft:spider", 1.0);
		entities.addProperty("minecraft:cave_spider", 1.0);
		entities.addProperty("minecraft:enderman", 1.0);
		entities.addProperty("minecraft:endermite", 1.0);
		entities.addProperty("minecraft:silverfish", 1.0);
		entities.addProperty("minecraft:blaze", 1.0);
		entities.addProperty("minecraft:ghast", 1.0);
		entities.addProperty("minecraft:magma_cube", 1.0);
		entities.addProperty("minecraft:slime", 1.0);
		entities.addProperty("minecraft:witch", 1.0);
		entities.addProperty("minecraft:phantom", 1.0);
		entities.addProperty("minecraft:shulker", 1.0);
		entities.addProperty("minecraft:hoglin", 1.0);
		entities.addProperty("minecraft:zoglin", 1.0);
		entities.addProperty("minecraft:piglin", 1.0);
		entities.addProperty("minecraft:piglin_brute", 1.0);
		entities.addProperty("minecraft:pillager", 1.0);
		entities.addProperty("minecraft:vindicator", 1.0);
		entities.addProperty("minecraft:evoker", 1.0);
		entities.addProperty("minecraft:vex", 1.0);
		entities.addProperty("minecraft:ravager", 1.0);
		entities.addProperty("minecraft:guardian", 1.0);
		entities.addProperty("minecraft:elder_guardian", 1.0);
		
		// Boss Mobs
		entities.addProperty("minecraft:ender_dragon", 1.0);
		entities.addProperty("minecraft:wither", 1.0);
		entities.addProperty("minecraft:warden", 1.0);
		
		// Neutral Mobs (can deal damage)
		entities.addProperty("minecraft:bee", 1.0);
		entities.addProperty("minecraft:wolf", 1.0);
		entities.addProperty("minecraft:iron_golem", 1.0);
		entities.addProperty("minecraft:polar_bear", 1.0);
		entities.addProperty("minecraft:llama", 1.0);
		entities.addProperty("minecraft:trader_llama", 1.0);
		entities.addProperty("minecraft:panda", 1.0);
		entities.addProperty("minecraft:dolphin", 1.0);
		entities.addProperty("minecraft:goat", 1.0);
		
		config.add("entity_multipliers", entities);

		// Default environmental multipliers
		JsonObject environmental = new JsonObject();
		environmental.addProperty("fall", 1.0);
		environmental.addProperty("fire", 1.0);
		environmental.addProperty("lava", 1.0);
		environmental.addProperty("drown", 1.0);
		environmental.addProperty("lightning", 1.0);
		environmental.addProperty("cactus", 1.0);
		environmental.addProperty("sweet_berry_bush", 1.0);
		environmental.addProperty("starve", 1.0);
		environmental.addProperty("freeze", 1.0);
		environmental.addProperty("magic", 1.0);
		environmental.addProperty("wither", 1.0);
		environmental.addProperty("anvil", 1.0);
		environmental.addProperty("falling_block", 1.0);
		environmental.addProperty("dragon_breath", 1.0);
		environmental.addProperty("hot_floor", 1.0);
		environmental.addProperty("cramming", 1.0);
		environmental.addProperty("fly_into_wall", 1.0);
		environmental.addProperty("in_wall", 1.0);
		environmental.addProperty("explosion", 1.0);
		config.add("environmental_multipliers", environmental);

		try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
			GSON.toJson(config, writer);
			DedsafioElensitoMod.LOGGER.info("Default configuration created!");
		} catch (IOException e) {
			DedsafioElensitoMod.LOGGER.error("Failed to create default configuration", e);
		}
	}

	public static double getEntityMultiplier(String entityType) {
		return entityMultipliers.getOrDefault(entityType, 1.0);
	}

	public static double getEnvironmentalMultiplier(String damageType) {
		return environmentalMultipliers.getOrDefault(damageType, 1.0);
	}

	public static boolean hasEntityMultiplier(String entityType) {
		return entityMultipliers.containsKey(entityType);
	}

	public static boolean hasEnvironmentalMultiplier(String damageType) {
		return environmentalMultipliers.containsKey(damageType);
	}
}
