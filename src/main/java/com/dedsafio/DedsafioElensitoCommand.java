package com.dedsafio;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class DedsafioElensitoCommand {

	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		dispatcher.register(
			CommandManager.literal("dedsafio_elensito")
				.requires(source -> source.hasPermissionLevel(2)) // Requires OP level 2
				.then(CommandManager.literal("reload")
					.executes(DedsafioElensitoCommand::reload)
				)
				.then(CommandManager.literal("info")
					.executes(DedsafioElensitoCommand::info)
				)
		);
	}

	private static int reload(CommandContext<ServerCommandSource> context) {
		ServerCommandSource source = context.getSource();

		try {
			// Reload damage configuration
			DamageConfig.loadConfig();

			// Send success message
			source.sendFeedback(
				() -> Text.literal("§a[DedsafioElensito] Damage multiplier config reloaded successfully!"),
				true
			);

			DedsafioElensitoMod.LOGGER.info("Configuration reloaded by {}", source.getName());

			return 1; // Success
		} catch (Exception e) {
			// Send error message
			source.sendFeedback(
				() -> Text.literal("§c[DedsafioElensito] Failed to reload configuration: " + e.getMessage()),
				false
			);

			DedsafioElensitoMod.LOGGER.error("Failed to reload configuration", e);

			return 0; // Failure
		}
	}

	private static int info(CommandContext<ServerCommandSource> context) {
		ServerCommandSource source = context.getSource();

		try {
			source.sendFeedback(
				() -> Text.literal("§6━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"),
				false
			);
			source.sendFeedback(
				() -> Text.literal("§6[DedsafioElensito] §fMod Info"),
				false
			);
			source.sendFeedback(
				() -> Text.literal("§6━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"),
				false
			);
			source.sendFeedback(
				() -> Text.literal("§e§lDamage Multiplier System:"),
				false
			);
			source.sendFeedback(
				() -> Text.literal("§7  ✓ Entity & environmental damage multipliers"),
				false
			);
			source.sendFeedback(
				() -> Text.literal("§7  ✓ Config: §fconfig/dedsafio_elensito_multiplicador.json"),
				false
			);
			source.sendFeedback(
				() -> Text.literal("§7  ✓ Supports all Minecraft 1.21 hostile mobs"),
				false
			);
			source.sendFeedback(
				() -> Text.literal("§7  ✓ Supports bosses (Ender Dragon, Wither, Warden)"),
				false
			);
			source.sendFeedback(
				() -> Text.literal("§7  ✓ Supports environmental damage sources"),
				false
			);
			source.sendFeedback(
				() -> Text.literal(""),
				false
			);
			source.sendFeedback(
				() -> Text.literal("§7Use §f/dedsafio_elensito reload §7to reload config"),
				false
			);
			source.sendFeedback(
				() -> Text.literal("§6━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"),
				false
			);

			return 1; // Success
		} catch (Exception e) {
			source.sendFeedback(
				() -> Text.literal("§c[DedsafioElensito] Failed to get info: " + e.getMessage()),
				false
			);

			return 0; // Failure
		}
	}
}
