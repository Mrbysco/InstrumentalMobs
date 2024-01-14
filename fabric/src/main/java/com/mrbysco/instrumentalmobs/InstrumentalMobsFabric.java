package com.mrbysco.instrumentalmobs;

import com.mrbysco.instrumentalmobs.config.InstrumentalConfigFabric;
import com.mrbysco.instrumentalmobs.entities.CymbalHusk;
import com.mrbysco.instrumentalmobs.entities.MicrophoneGhast;
import com.mrbysco.instrumentalmobs.registration.InstrumentalEntities;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.function.Predicate;

public class InstrumentalMobsFabric implements ModInitializer {
	public static ConfigHolder<InstrumentalConfigFabric> config;

	@Override
	public void onInitialize() {
		config = AutoConfig.register(InstrumentalConfigFabric.class, Toml4jConfigSerializer::new);

		CommonClass.init();

		registerSpawnPlacements();
		registerEntityAttributes();
		addSpawns();
	}

	public void registerSpawnPlacements() {
		SpawnPlacements.register(InstrumentalEntities.CYMBAL_HUSK.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CymbalHusk::canSpawnHere);
		SpawnPlacements.register(InstrumentalEntities.DRUM_ZOMBIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(InstrumentalEntities.FRENCH_HORN_CREEPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(InstrumentalEntities.MARACA_SPIDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(InstrumentalEntities.MICROPHONE_GHAST.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MicrophoneGhast::canSpawnHere);
		SpawnPlacements.register(InstrumentalEntities.TUBA_ENDERMAN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(InstrumentalEntities.XYLOPHONE_SKELETON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(InstrumentalEntities.TRUMPET_SKELETON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
	}

	public void registerEntityAttributes() {
		FabricDefaultAttributeRegistry.register(InstrumentalEntities.CYMBAL_HUSK.get(), Zombie.createAttributes().build());
		FabricDefaultAttributeRegistry.register(InstrumentalEntities.DRUM_ZOMBIE.get(), Zombie.createAttributes().build());
		FabricDefaultAttributeRegistry.register(InstrumentalEntities.FRENCH_HORN_CREEPER.get(), Creeper.createAttributes().build());
		FabricDefaultAttributeRegistry.register(InstrumentalEntities.MARACA_SPIDER.get(), Spider.createAttributes().build());
		FabricDefaultAttributeRegistry.register(InstrumentalEntities.MICROPHONE_GHAST.get(), Ghast.createAttributes().build());
		FabricDefaultAttributeRegistry.register(InstrumentalEntities.TUBA_ENDERMAN.get(), EnderMan.createAttributes().build());
		FabricDefaultAttributeRegistry.register(InstrumentalEntities.XYLOPHONE_SKELETON.get(), AbstractSkeleton.createAttributes().build());
		FabricDefaultAttributeRegistry.register(InstrumentalEntities.TRUMPET_SKELETON.get(), AbstractSkeleton.createAttributes().build());
	}


	public void addSpawns() {
		BiomeModifications.addSpawn(getContext(EntityType.HUSK), MobCategory.MONSTER, InstrumentalEntities.CYMBAL_HUSK.get(), 16, 4, 4);
		BiomeModifications.addSpawn(getContext(EntityType.ZOMBIE), MobCategory.MONSTER, InstrumentalEntities.DRUM_ZOMBIE.get(), 16, 4, 4);
		BiomeModifications.addSpawn(getContext(EntityType.CREEPER), MobCategory.MONSTER, InstrumentalEntities.FRENCH_HORN_CREEPER.get(), 20, 4, 4);
		BiomeModifications.addSpawn(getContext(EntityType.SPIDER), MobCategory.MONSTER, InstrumentalEntities.MARACA_SPIDER.get(), 20, 4, 4);
		BiomeModifications.addSpawn(getContext(EntityType.GHAST), MobCategory.MONSTER, InstrumentalEntities.MICROPHONE_GHAST.get(), 20, 4, 4);
		BiomeModifications.addSpawn(getContext(EntityType.ENDERMAN), MobCategory.MONSTER, InstrumentalEntities.TUBA_ENDERMAN.get(), 2, 4, 4);
		BiomeModifications.addSpawn(getContext(EntityType.SKELETON), MobCategory.MONSTER, InstrumentalEntities.XYLOPHONE_SKELETON.get(), 20, 4, 4);
		BiomeModifications.addSpawn(getContext(EntityType.SKELETON), MobCategory.MONSTER, InstrumentalEntities.TRUMPET_SKELETON.get(), 20, 4, 4);
	}

	private Predicate<BiomeSelectionContext> getContext(EntityType<?> type) {
		return BiomeSelectors.spawnsOneOf(type);
	}
}
