package com.mrbysco.instrumentalmobs.modifier;

import com.mojang.serialization.Codec;
import com.mrbysco.instrumentalmobs.init.InstrumentalModifiers;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.common.world.ModifiableBiomeInfo.BiomeInfo.Builder;

import java.util.List;

public record AddRelativeSpawnBiomeModifier(EntityType<?> originalType,
											EntityType<?> newType, int relativeWeight) implements BiomeModifier {
	@Override
	public void modify(Holder<Biome> biome, Phase phase, Builder builder) {
		if (phase == Phase.ADD) {
			MobSpawnSettingsBuilder spawns = builder.getMobSpawnSettings();
			MobSpawnSettings info = biome.get().getMobSettings();
			final List<SpawnerData> spawnsList = spawns.getSpawner(MobCategory.MONSTER);
			for (SpawnerData entry : info.getMobs(MobCategory.MONSTER).unwrap()) {
				if (entry.type == originalType) {
					spawnsList.add(new SpawnerData(newType, Math.min(1, entry.getWeight().asInt() / relativeWeight), entry.minCount, entry.maxCount));
				}
			}
		}
	}

	@Override
	public Codec<? extends BiomeModifier> codec() {
		return InstrumentalModifiers.ADD_RELATIVE_MOB_SPAWNS.get();
	}
}
