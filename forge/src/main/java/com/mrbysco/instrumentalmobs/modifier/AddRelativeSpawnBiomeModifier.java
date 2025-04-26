package com.mrbysco.instrumentalmobs.modifier;

import com.mojang.serialization.MapCodec;
import com.mrbysco.instrumentalmobs.init.InstrumentalModifiers;
import net.minecraft.core.Holder;
import net.minecraft.util.random.Weighted;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.MobSpawnSettingsBuilder;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo.BiomeInfo.Builder;
import org.jetbrains.annotations.NotNull;

public record AddRelativeSpawnBiomeModifier(EntityType<?> originalType,
                                            EntityType<?> newType,
                                            int relativeWeight) implements BiomeModifier {
	@Override
	public void modify(Holder<Biome> biome, Phase phase, Builder builder) {
		if (phase == Phase.ADD) {
			MobSpawnSettingsBuilder spawns = builder.getMobSpawnSettings();
			for (Weighted<SpawnerData> entry : spawns.getSpawner(originalType.getCategory()).getList()) {
				EntityType<?> type = entry.value().type();
				if (type == originalType) {
					spawns.addSpawn(type.getCategory(), Math.min(1, entry.weight() / relativeWeight),
							new SpawnerData(newType, entry.value().minCount(), entry.value().maxCount()));
				}
			}
		}
	}

	@NotNull
	@Override
	public MapCodec<? extends BiomeModifier> codec() {
		return InstrumentalModifiers.ADD_RELATIVE_MOB_SPAWNS.get();
	}
}
