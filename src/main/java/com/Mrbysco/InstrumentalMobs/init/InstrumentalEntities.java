package com.mrbysco.instrumentalmobs.init;

import com.mrbysco.instrumentalmobs.Reference;
import com.mrbysco.instrumentalmobs.entities.CymbalHuskEntity;
import com.mrbysco.instrumentalmobs.entities.MicrophoneGhastEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntitySpawnPlacementRegistry.PlacementType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.MobSpawnInfo.Spawners;
import net.minecraft.world.gen.Heightmap.Type;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class InstrumentalEntities {

	@SubscribeEvent(priority =  EventPriority.HIGH)
	public static void addSpawn(BiomeLoadingEvent event) {
		Biome biome = ForgeRegistries.BIOMES.getValue(event.getName());
		if(biome != null) {
			MobSpawnInfo info = biome.getMobSettings();
			List<MobSpawnInfo.Spawners> spawns = event.getSpawns().getSpawner(EntityClassification.MONSTER);
			for(Spawners entry : info.getMobs(EntityClassification.MONSTER)) {
				registerSpawn(spawns, entry, EntityType.HUSK, InstrumentalRegistry.CYMBAL_HUSK.get());
				registerSpawn(spawns, entry, EntityType.ZOMBIE, InstrumentalRegistry.DRUM_ZOMBIE.get());
				registerSpawn(spawns, entry, EntityType.CREEPER, InstrumentalRegistry.FRENCH_HORN_CREEPER.get());
				registerSpawn(spawns, entry, EntityType.SPIDER, InstrumentalRegistry.MARACA_SPIDER.get());
				registerSpawn(spawns, entry, EntityType.GHAST, InstrumentalRegistry.MICROPHONE_GHAST.get());
				registerSpawn(spawns, entry, EntityType.ENDERMAN, InstrumentalRegistry.TUBA_ENDERMAN.get());
				registerSpawn(spawns, entry, EntityType.SKELETON, InstrumentalRegistry.XYLOPHONE_SKELETON.get());
			}
		}
	}

	public static void initializeMobs() {
		EntitySpawnPlacementRegistry.register(InstrumentalRegistry.CYMBAL_HUSK.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, CymbalHuskEntity::canSpawnHere);
		EntitySpawnPlacementRegistry.register(InstrumentalRegistry.DRUM_ZOMBIE.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
		EntitySpawnPlacementRegistry.register(InstrumentalRegistry.FRENCH_HORN_CREEPER.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
		EntitySpawnPlacementRegistry.register(InstrumentalRegistry.MARACA_SPIDER.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
		EntitySpawnPlacementRegistry.register(InstrumentalRegistry.MICROPHONE_GHAST.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MicrophoneGhastEntity::canSpawnHere);
		EntitySpawnPlacementRegistry.register(InstrumentalRegistry.TUBA_ENDERMAN.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
		EntitySpawnPlacementRegistry.register(InstrumentalRegistry.XYLOPHONE_SKELETON.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
	}

	public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(InstrumentalRegistry.CYMBAL_HUSK.get(), ZombieEntity.createAttributes().build());
		event.put(InstrumentalRegistry.DRUM_ZOMBIE.get(), ZombieEntity.createAttributes().build());
		event.put(InstrumentalRegistry.FRENCH_HORN_CREEPER.get(), CreeperEntity.createAttributes().build());
		event.put(InstrumentalRegistry.MARACA_SPIDER.get(), SpiderEntity.createAttributes().build());
		event.put(InstrumentalRegistry.MICROPHONE_GHAST.get(), GhastEntity.createAttributes().build());
		event.put(InstrumentalRegistry.TUBA_ENDERMAN.get(), EndermanEntity.createAttributes().build());
		event.put(InstrumentalRegistry.XYLOPHONE_SKELETON.get(), AbstractSkeletonEntity.createAttributes().build());
	}

	public static void registerSpawn(List<Spawners> spawns, Spawners entry, EntityType<? extends LivingEntity> oldEntity, EntityType<? extends LivingEntity> newEntity) {
		if(entry.type == oldEntity) {
			spawns.add(new MobSpawnInfo.Spawners(newEntity, Math.min(1, entry.weight / 5), entry.minCount, entry.maxCount));
		}
	}
}
