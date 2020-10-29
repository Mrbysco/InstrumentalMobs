package com.mrbysco.instrumentalmobs.init;

import com.mrbysco.instrumentalmobs.InstrumentalMobs;
import com.mrbysco.instrumentalmobs.Reference;
import com.mrbysco.instrumentalmobs.entities.EntityCymbalHusk;
import com.mrbysco.instrumentalmobs.entities.EntityDrumZombie;
import com.mrbysco.instrumentalmobs.entities.EntityFrenchHornCreeper;
import com.mrbysco.instrumentalmobs.entities.EntityMaracaSpider;
import com.mrbysco.instrumentalmobs.entities.EntityMicrophoneGhast;
import com.mrbysco.instrumentalmobs.entities.EntityTubaEnderman;
import com.mrbysco.instrumentalmobs.entities.EntityXylophoneSkeleton;
import com.mrbysco.instrumentalmobs.entities.projectiles.EntityMicrophoneWave;
import com.mrbysco.instrumentalmobs.entities.projectiles.EntitySoundWaves;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.util.ArrayList;

public class InstrumentalMobRegistry {
	private static int ID = 0;
	
	public static void register() {
		registerEntity("french_horn_creeper", EntityFrenchHornCreeper.class, "FrenchHornCreeper", 80, 3, true, 894731, 0);
		registerEntity("tuba_enderman", EntityTubaEnderman.class, "TubaEnderman", 80, 3, true, 1447446, 0);
		registerEntity("drum_zombie", EntityDrumZombie.class, "DrumZombie", 80, 3, true, 44975, 7969893);
		registerEntity("cymbal_husk", EntityCymbalHusk.class, "CymbalHusk", 80, 3, true, 7958625, 15125652);
		registerEntity("xylophone_skeleton", EntityXylophoneSkeleton.class, "XylophoneSkeleton", 80, 3, true, 12698049, 4802889);
		registerEntity("maraca_spider", EntityMaracaSpider.class, "MaracaSpider", 80, 3, true, 803406, 11013646);
		registerEntity("microphone_ghast", EntityMicrophoneGhast.class, "MicrophoneGhast", 80, 3, true, 16382457, 12369084);
		registerEntity("sound_waves", EntitySoundWaves.class, "SoundWaves", 80, 3, true);
		registerEntity("microphone_sound", EntityMicrophoneWave.class, "MicrophoneWave", 80, 3, true);
	}
	
	public static void registerEntity(String registryName, Class<? extends Entity> entityClass, String entityName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int eggPrimary, int eggSecondary) {
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, registryName), entityClass, Reference.MOD_PREFIX + entityName, ID, InstrumentalMobs.instance, trackingRange, updateFrequency, sendsVelocityUpdates, eggPrimary, eggSecondary);
		ID++;
	}
	
	public static void registerEntity(String registryName, Class<? extends Entity> entityClass, String entityName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, registryName), entityClass, Reference.MOD_PREFIX + entityName, ID, InstrumentalMobs.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
		ID++;
	}

	public static void registerBiomes() {
		for (Biome biome : Biome.REGISTRY) {
			for (Biome.SpawnListEntry entry : new ArrayList<>(biome.getSpawnableList(EnumCreatureType.MONSTER))) {
				registerSpawn(entry, biome, EntitySkeleton.class, EntityXylophoneSkeleton.class);
				registerSpawn(entry, biome, EntityCreeper.class, EntityFrenchHornCreeper.class);
				registerSpawn(entry, biome, EntityEnderman.class, EntityTubaEnderman.class);
				registerSpawn(entry, biome, EntityZombie.class, EntityDrumZombie.class);
				registerSpawn(entry, biome, EntityHusk.class, EntityCymbalHusk.class);
				registerSpawn(entry, biome, EntitySpider.class, EntityMaracaSpider.class);
				registerSpawn(entry, biome, EntityGhast.class, EntityMicrophoneGhast.class);
            }
		}
	}
	
	public static void registerSpawn(Biome.SpawnListEntry entry, Biome biome, Class<? extends Entity> oldEntity, Class<? extends EntityLiving> newEntity) {
		if(entry.entityClass == oldEntity) {
			EntityRegistry.addSpawn(newEntity, entry.itemWeight / 5, entry.minGroupCount, entry.maxGroupCount, EnumCreatureType.MONSTER, biome);
		}
	}
}
