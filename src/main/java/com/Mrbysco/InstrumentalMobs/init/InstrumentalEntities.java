package com.mrbysco.instrumentalmobs.init;

import com.mrbysco.instrumentalmobs.Reference;
import com.mrbysco.instrumentalmobs.entities.CymbalHuskEntity;
import com.mrbysco.instrumentalmobs.entities.MicrophoneGhastEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntitySpawnPlacementRegistry.PlacementType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
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
			MobSpawnInfo info = biome.getMobSpawnInfo();
			List<MobSpawnInfo.Spawners> spawns = event.getSpawns().getSpawner(EntityClassification.MONSTER);
			for(Spawners entry : info.getSpawners(EntityClassification.MONSTER)) {
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
		EntitySpawnPlacementRegistry.register(InstrumentalRegistry.DRUM_ZOMBIE.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawnInLight);
		EntitySpawnPlacementRegistry.register(InstrumentalRegistry.FRENCH_HORN_CREEPER.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawnInLight);
		EntitySpawnPlacementRegistry.register(InstrumentalRegistry.MARACA_SPIDER.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawnInLight);
		EntitySpawnPlacementRegistry.register(InstrumentalRegistry.MICROPHONE_GHAST.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MicrophoneGhastEntity::canSpawnHere);
		EntitySpawnPlacementRegistry.register(InstrumentalRegistry.TUBA_ENDERMAN.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawnInLight);
		EntitySpawnPlacementRegistry.register(InstrumentalRegistry.XYLOPHONE_SKELETON.get(), PlacementType.ON_GROUND, Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawnInLight);

		GlobalEntityTypeAttributes.put(InstrumentalRegistry.CYMBAL_HUSK.get(), ZombieEntity.func_234342_eQ_().create());
		GlobalEntityTypeAttributes.put(InstrumentalRegistry.DRUM_ZOMBIE.get(), ZombieEntity.func_234342_eQ_().create());
		GlobalEntityTypeAttributes.put(InstrumentalRegistry.FRENCH_HORN_CREEPER.get(), CreeperEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(InstrumentalRegistry.MARACA_SPIDER.get(), SpiderEntity.func_234305_eI_().create());
		GlobalEntityTypeAttributes.put(InstrumentalRegistry.MICROPHONE_GHAST.get(), GhastEntity.func_234290_eH_().create());
		GlobalEntityTypeAttributes.put(InstrumentalRegistry.TUBA_ENDERMAN.get(), EndermanEntity.func_234287_m_().create());
		GlobalEntityTypeAttributes.put(InstrumentalRegistry.XYLOPHONE_SKELETON.get(), AbstractSkeletonEntity.registerAttributes().create());
	}

	public static void registerSpawn(List<Spawners> spawns, Spawners entry, EntityType<? extends LivingEntity> oldEntity, EntityType<? extends LivingEntity> newEntity) {
		if(entry.type == oldEntity) {
			spawns.add(new MobSpawnInfo.Spawners(newEntity, Math.min(1, entry.itemWeight / 5), entry.minCount, entry.maxCount));
		}
	}
}
