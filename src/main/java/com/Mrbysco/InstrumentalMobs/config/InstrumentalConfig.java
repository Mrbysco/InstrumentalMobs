package com.mrbysco.instrumentalmobs.config;

import com.mrbysco.instrumentalmobs.InstrumentalMobs;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class InstrumentalConfig {

	public static class Common {
		public final BooleanValue mobsReact;
		public final DoubleValue instrumentRange;

		Common(ForgeConfigSpec.Builder builder) {
			builder.comment("Server settings")
					.push("Server");

			mobsReact = builder
					.comment("Mobs react upon usage of the instruments [default: true]")
					.define("mobsReact", true);

			instrumentRange = builder
					.comment("The area in which the instruments react with mobs. [default: 10.0D]")
					.defineInRange("instrumentRange", 10.0D, 0.01D, 128.0D);

			builder.pop();
		}
	}

	public static final ForgeConfigSpec commonSpec;
	public static final InstrumentalConfig.Common COMMON;

	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(InstrumentalConfig.Common::new);
		commonSpec = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	@SubscribeEvent
	public static void onLoad(final ModConfig.Loading configEvent) {
		InstrumentalMobs.LOGGER.debug("Loaded Instrumental Mobs' config file {}", configEvent.getConfig().getFileName());
	}

	@SubscribeEvent
	public static void onFileChange(final ModConfig.Reloading configEvent) {
		InstrumentalMobs.LOGGER.debug("Instrumental Mobs' config just got changed on the file system!");
	}
}