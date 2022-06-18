package com.mrbysco.instrumentalmobs.init;

import com.mrbysco.instrumentalmobs.Reference;
import com.mrbysco.instrumentalmobs.entities.CymbalHuskEntity;
import com.mrbysco.instrumentalmobs.entities.MicrophoneGhastEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.SpawnPlacements.Type;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class InstrumentalEntities {

//	@SubscribeEvent(priority = EventPriority.HIGH)
//	public static void addSpawn(BiomeLoadingEvent event) {
//		Biome biome = ForgeRegistries.BIOMES.getValue(event.getName());
//		if (biome != null) {
//			MobSpawnSettings info = biome.getMobSettings();
//			List<MobSpawnSettings.SpawnerData> spawns = event.getSpawns().getSpawner(MobCategory.MONSTER);
//			for (MobSpawnSettings.SpawnerData entry : info.getMobs(MobCategory.MONSTER).unwrap()) {
//				registerSpawn(spawns, entry, EntityType.HUSK, InstrumentalRegistry.CYMBAL_HUSK.get());
//				registerSpawn(spawns, entry, EntityType.ZOMBIE, InstrumentalRegistry.DRUM_ZOMBIE.get());
//				registerSpawn(spawns, entry, EntityType.CREEPER, InstrumentalRegistry.FRENCH_HORN_CREEPER.get());
//				registerSpawn(spawns, entry, EntityType.SPIDER, InstrumentalRegistry.MARACA_SPIDER.get());
//				registerSpawn(spawns, entry, EntityType.GHAST, InstrumentalRegistry.MICROPHONE_GHAST.get());
//				registerSpawn(spawns, entry, EntityType.ENDERMAN, InstrumentalRegistry.TUBA_ENDERMAN.get());
//				registerSpawn(spawns, entry, EntityType.SKELETON, InstrumentalRegistry.XYLOPHONE_SKELETON.get());
//			}
//		}
//	}

	public static void initializeMobs() {
		SpawnPlacements.register(InstrumentalRegistry.CYMBAL_HUSK.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, CymbalHuskEntity::canSpawnHere);
		SpawnPlacements.register(InstrumentalRegistry.DRUM_ZOMBIE.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(InstrumentalRegistry.FRENCH_HORN_CREEPER.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(InstrumentalRegistry.MARACA_SPIDER.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(InstrumentalRegistry.MICROPHONE_GHAST.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, MicrophoneGhastEntity::canSpawnHere);
		SpawnPlacements.register(InstrumentalRegistry.TUBA_ENDERMAN.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(InstrumentalRegistry.XYLOPHONE_SKELETON.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
	}

	public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(InstrumentalRegistry.CYMBAL_HUSK.get(), Zombie.createAttributes().build());
		event.put(InstrumentalRegistry.DRUM_ZOMBIE.get(), Zombie.createAttributes().build());
		event.put(InstrumentalRegistry.FRENCH_HORN_CREEPER.get(), Creeper.createAttributes().build());
		event.put(InstrumentalRegistry.MARACA_SPIDER.get(), Spider.createAttributes().build());
		event.put(InstrumentalRegistry.MICROPHONE_GHAST.get(), Ghast.createAttributes().build());
		event.put(InstrumentalRegistry.TUBA_ENDERMAN.get(), EnderMan.createAttributes().build());
		event.put(InstrumentalRegistry.XYLOPHONE_SKELETON.get(), AbstractSkeleton.createAttributes().build());
	}

	public static void registerSpawn(List<SpawnerData> spawns, SpawnerData entry, EntityType<? extends LivingEntity> oldEntity, EntityType<? extends LivingEntity> newEntity) {
		if (entry.type == oldEntity) {
			spawns.add(new MobSpawnSettings.SpawnerData(newEntity, Math.min(1, entry.getWeight().asInt() / 5), entry.minCount, entry.maxCount));
		}
	}
}
