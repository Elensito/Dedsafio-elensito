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

public class SpawnConfig {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final File CONFIG_FILE = new File("config/dedsafio_elensito_spawns.json");

	// Map for food-giving animal spawn control (entity type -> can spawn naturally)
	private static Map<String, Boolean> foodAnimalSpawnControl = new HashMap<>();

	public static void loadConfig() {
		if (!CONFIG_FILE.getParentFile().exists()) {
			CONFIG_FILE.getParentFile().mkdirs();
		}

		if (!CONFIG_FILE.exists()) {
			createDefaultConfig();
		}

		// Clear existing configurations before reloading
		foodAnimalSpawnControl.clear();

		try (FileReader reader = new FileReader(CONFIG_FILE)) {
			JsonObject config = JsonParser.parseReader(reader).getAsJsonObject();

			// Load food animal spawn control
			if (config.has("food_animals_spawn_control")) {
				JsonObject foodAnimals = config.getAsJsonObject("food_animals_spawn_control");
				foodAnimals.entrySet().forEach(entry -> {
					// Skip comments in JSON
					if (!entry.getKey().startsWith("_")) {
						foodAnimalSpawnControl.put(entry.getKey(), entry.getValue().getAsBoolean());
					}
				});
			}

			DedsafioElensitoMod.LOGGER.info("Spawn configuration loaded successfully! " +
				"Food animals: {}", foodAnimalSpawnControl.size());
		} catch (IOException e) {
			DedsafioElensitoMod.LOGGER.error("Failed to load spawn configuration", e);
			throw new RuntimeException("Failed to load spawn configuration", e);
		}
	}

	private static void createDefaultConfig() {
		JsonObject config = new JsonObject();

		// Default food-giving animal spawn control (true = can spawn naturally, false = cannot spawn naturally)
		JsonObject foodAnimals = new JsonObject();

		// Passive animals that provide food
		foodAnimals.addProperty("minecraft:cow", false);      // Gives beef, leather
		foodAnimals.addProperty("minecraft:pig", false);      // Gives porkchop
		foodAnimals.addProperty("minecraft:sheep", false);    // Gives mutton, wool
		foodAnimals.addProperty("minecraft:chicken", false);  // Gives chicken, feathers
		foodAnimals.addProperty("minecraft:rabbit", false);   // Gives rabbit meat
		foodAnimals.addProperty("minecraft:horse", true);     // Can spawn (not primarily for food)
		foodAnimals.addProperty("minecraft:donkey", true);    // Can spawn (not primarily for food)
		foodAnimals.addProperty("minecraft:mule", true);      // Can spawn (not primarily for food)
		foodAnimals.addProperty("minecraft:llama", true);     // Can spawn (not primarily for food)
		foodAnimals.addProperty("minecraft:trader_llama", true); // Can spawn (not primarily for food)

		// Fish that provide food
		foodAnimals.addProperty("minecraft:cod", false);      // Gives cod
		foodAnimals.addProperty("minecraft:salmon", false);   // Gives salmon
		foodAnimals.addProperty("minecraft:tropical_fish", false); // Gives tropical fish
		foodAnimals.addProperty("minecraft:pufferfish", false); // Gives pufferfish

		// Other food sources
		foodAnimals.addProperty("minecraft:mooshroom", false); // Gives beef, leather (special cow)
		foodAnimals.addProperty("minecraft:squid", false);    // Gives ink sac (not direct food but useful)
		foodAnimals.addProperty("minecraft:glow_squid", false); // Gives glow ink sac

		config.add("food_animals_spawn_control", foodAnimals);

		try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
			GSON.toJson(config, writer);
			DedsafioElensitoMod.LOGGER.info("Default spawn configuration created!");
		} catch (IOException e) {
			DedsafioElensitoMod.LOGGER.error("Failed to create default spawn configuration", e);
		}
	}

	public static boolean canFoodAnimalSpawnNaturally(String entityType) {
		return foodAnimalSpawnControl.getOrDefault(entityType, true);
	}

	public static boolean hasFoodAnimalSpawnControl(String entityType) {
		return foodAnimalSpawnControl.containsKey(entityType);
	}

	public static Map<String, Boolean> getAllFoodAnimalSpawnControls() {
		return new HashMap<>(foodAnimalSpawnControl);
	}
}