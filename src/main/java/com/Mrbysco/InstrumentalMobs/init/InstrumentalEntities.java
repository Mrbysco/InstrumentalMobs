package com.mrbysco.instrumentalmobs.init;

import com.mrbysco.instrumentalmobs.entities.CymbalHusk;
import com.mrbysco.instrumentalmobs.entities.MicrophoneGhast;
import net.minecraft.world.entity.SpawnPlacements.Type;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.SpawnPlacementRegisterEvent;

public class InstrumentalEntities {

	public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
		event.register(InstrumentalRegistry.CYMBAL_HUSK.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, CymbalHusk::canSpawnHere, SpawnPlacementRegisterEvent.Operation.AND);
		event.register(InstrumentalRegistry.DRUM_ZOMBIE.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
		event.register(InstrumentalRegistry.FRENCH_HORN_CREEPER.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
		event.register(InstrumentalRegistry.MARACA_SPIDER.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
		event.register(InstrumentalRegistry.MICROPHONE_GHAST.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, MicrophoneGhast::canSpawnHere, SpawnPlacementRegisterEvent.Operation.AND);
		event.register(InstrumentalRegistry.TUBA_ENDERMAN.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
		event.register(InstrumentalRegistry.XYLOPHONE_SKELETON.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
		event.register(InstrumentalRegistry.TRUMPET_SKELETON.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
	}

	public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(InstrumentalRegistry.CYMBAL_HUSK.get(), Zombie.createAttributes().build());
		event.put(InstrumentalRegistry.DRUM_ZOMBIE.get(), Zombie.createAttributes().build());
		event.put(InstrumentalRegistry.FRENCH_HORN_CREEPER.get(), Creeper.createAttributes().build());
		event.put(InstrumentalRegistry.MARACA_SPIDER.get(), Spider.createAttributes().build());
		event.put(InstrumentalRegistry.MICROPHONE_GHAST.get(), Ghast.createAttributes().build());
		event.put(InstrumentalRegistry.TUBA_ENDERMAN.get(), EnderMan.createAttributes().build());
		event.put(InstrumentalRegistry.XYLOPHONE_SKELETON.get(), AbstractSkeleton.createAttributes().build());
		event.put(InstrumentalRegistry.TRUMPET_SKELETON.get(), AbstractSkeleton.createAttributes().build());
	}
}
