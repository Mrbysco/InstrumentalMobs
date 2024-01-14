package com.mrbysco.instrumentalmobs;

import com.mrbysco.instrumentalmobs.client.ClientHandler;
import com.mrbysco.instrumentalmobs.config.InstrumentalConfigForge;
import com.mrbysco.instrumentalmobs.entities.CymbalHusk;
import com.mrbysco.instrumentalmobs.entities.MicrophoneGhast;
import com.mrbysco.instrumentalmobs.init.InstrumentalModifiers;
import com.mrbysco.instrumentalmobs.registration.InstrumentalEntities;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class InstrumentalMobsForge {

	public InstrumentalMobsForge() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, InstrumentalConfigForge.commonSpec);
		eventBus.register(InstrumentalConfigForge.class);

		InstrumentalModifiers.BIOME_MODIFIER_SERIALIZERS.register(eventBus);

		CommonClass.init();

		eventBus.addListener(this::registerEntityAttributes);
		eventBus.addListener(this::registerSpawnPlacements);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			eventBus.addListener(ClientHandler::registerEntityRenders);
		});
	}

	public void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
		event.register(InstrumentalEntities.CYMBAL_HUSK.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CymbalHusk::canSpawnHere, SpawnPlacementRegisterEvent.Operation.AND);
		event.register(InstrumentalEntities.DRUM_ZOMBIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
		event.register(InstrumentalEntities.FRENCH_HORN_CREEPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
		event.register(InstrumentalEntities.MARACA_SPIDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
		event.register(InstrumentalEntities.MICROPHONE_GHAST.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MicrophoneGhast::canSpawnHere, SpawnPlacementRegisterEvent.Operation.AND);
		event.register(InstrumentalEntities.TUBA_ENDERMAN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
		event.register(InstrumentalEntities.XYLOPHONE_SKELETON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
		event.register(InstrumentalEntities.TRUMPET_SKELETON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
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