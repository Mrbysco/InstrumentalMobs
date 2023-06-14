package com.mrbysco.instrumentalmobs;

import com.mojang.logging.LogUtils;
import com.mrbysco.instrumentalmobs.client.ClientHandler;
import com.mrbysco.instrumentalmobs.config.InstrumentalConfig;
import com.mrbysco.instrumentalmobs.init.InstrumentalEntities;
import com.mrbysco.instrumentalmobs.init.InstrumentalModifiers;
import com.mrbysco.instrumentalmobs.init.InstrumentalRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> eventBus.addListener(ClientHandler::registerEntityRenders));
	}
}