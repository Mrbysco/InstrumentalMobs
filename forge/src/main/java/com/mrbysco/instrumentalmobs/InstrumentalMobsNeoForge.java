package com.mrbysco.instrumentalmobs;

import com.mrbysco.instrumentalmobs.client.ClientHandler;
import com.mrbysco.instrumentalmobs.config.InstrumentalConfigNeoForge;
import com.mrbysco.instrumentalmobs.entities.CymbalHusk;
import com.mrbysco.instrumentalmobs.entities.MicrophoneGhast;
import com.mrbysco.instrumentalmobs.init.InstrumentalModifiers;
import com.mrbysco.instrumentalmobs.registration.InstrumentalEntities;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@Mod(Constants.MOD_ID)
public class InstrumentalMobsNeoForge {

	public InstrumentalMobsNeoForge(IEventBus eventBus, Dist dist, ModContainer container) {
		container.registerConfig(ModConfig.Type.COMMON, InstrumentalConfigNeoForge.commonSpec);
		eventBus.register(InstrumentalConfigNeoForge.class);

		InstrumentalModifiers.BIOME_MODIFIER_SERIALIZERS.register(eventBus);

		CommonClass.init();

		eventBus.addListener(this::registerEntityAttributes);
		eventBus.addListener(this::registerSpawnPlacements);

		if (dist.isClient()) {
			container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
			eventBus.addListener(ClientHandler::registerEntityRenders);
		}
	}

	public void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
		event.register(InstrumentalEntities.CYMBAL_HUSK.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CymbalHusk::canSpawnHere, RegisterSpawnPlacementsEvent.Operation.AND);
		event.register(InstrumentalEntities.DRUM_ZOMBIE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
		event.register(InstrumentalEntities.FRENCH_HORN_CREEPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
		event.register(InstrumentalEntities.MARACA_SPIDER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
		event.register(InstrumentalEntities.MICROPHONE_GHAST.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MicrophoneGhast::canSpawnHere, RegisterSpawnPlacementsEvent.Operation.AND);
		event.register(InstrumentalEntities.TUBA_ENDERMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
		event.register(InstrumentalEntities.XYLOPHONE_SKELETON.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
		event.register(InstrumentalEntities.TRUMPET_SKELETON.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
	}

	public void registerEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(InstrumentalEntities.CYMBAL_HUSK.get(), Zombie.createAttributes().build());
		event.put(InstrumentalEntities.DRUM_ZOMBIE.get(), Zombie.createAttributes().build());
		event.put(InstrumentalEntities.FRENCH_HORN_CREEPER.get(), Creeper.createAttributes().build());
		event.put(InstrumentalEntities.MARACA_SPIDER.get(), Spider.createAttributes().build());
		event.put(InstrumentalEntities.MICROPHONE_GHAST.get(), Ghast.createAttributes().build());
		event.put(InstrumentalEntities.TUBA_ENDERMAN.get(), EnderMan.createAttributes().build());
		event.put(InstrumentalEntities.XYLOPHONE_SKELETON.get(), AbstractSkeleton.createAttributes().build());
		event.put(InstrumentalEntities.TRUMPET_SKELETON.get(), AbstractSkeleton.createAttributes().build());
	}
}