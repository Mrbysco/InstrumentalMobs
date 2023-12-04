package com.mrbysco.instrumentalmobs;

import com.mojang.logging.LogUtils;
import com.mrbysco.instrumentalmobs.client.ClientHandler;
import com.mrbysco.instrumentalmobs.config.InstrumentalConfig;
import com.mrbysco.instrumentalmobs.init.InstrumentalEntities;
import com.mrbysco.instrumentalmobs.init.InstrumentalModifiers;
import com.mrbysco.instrumentalmobs.init.InstrumentalRegistry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.fml.loading.FMLEnvironment;
import org.slf4j.Logger;

@Mod(Reference.MOD_ID)
public class InstrumentalMobs {
	public static final Logger LOGGER = LogUtils.getLogger();

	public InstrumentalMobs() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, InstrumentalConfig.commonSpec);
		eventBus.register(InstrumentalConfig.class);

		InstrumentalRegistry.BLOCKS.register(eventBus);
		InstrumentalRegistry.ITEMS.register(eventBus);
		InstrumentalRegistry.ENTITIES.register(eventBus);
		InstrumentalRegistry.CREATIVE_MODE_TABS.register(eventBus);
		InstrumentalRegistry.SOUND_EVENTS.register(eventBus);
		InstrumentalModifiers.BIOME_MODIFIER_SERIALIZERS.register(eventBus);

		eventBus.addListener(InstrumentalEntities::registerEntityAttributes);
		eventBus.addListener(InstrumentalEntities::registerSpawnPlacements);

		if (FMLEnvironment.dist.isClient()) {
			eventBus.addListener(ClientHandler::registerEntityRenders);
		}
	}
}