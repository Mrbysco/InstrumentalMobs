package com.mrbysco.instrumentalmobs.init;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.SpawnEggItem;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SupplierSpawnEggItem<T extends Mob> extends SpawnEggItem {
	private static final List<SupplierSpawnEggItem> MOD_EGGS = new ArrayList<>();
	private final Supplier<? extends EntityType<? extends Mob>> typeSupplier;

	public SupplierSpawnEggItem(Supplier<EntityType<T>> entityTypeSupplier, int i, int j, Properties properties) {
		super(null, i, j, properties);
		this.typeSupplier = entityTypeSupplier;

		MOD_EGGS.add(this);
	}

	public static List<SupplierSpawnEggItem> getModEggs() {
		return MOD_EGGS;
	}

	@Override
	public EntityType<?> getType(@Nullable CompoundTag compoundTag) {
		return typeSupplier.get();
	}

	@Override
	public FeatureFlagSet requiredFeatures() {
		return FeatureFlagSet.of();
	}
}
