package com.mrbysco.instrumentalmobs.datagen.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

import static com.mrbysco.instrumentalmobs.registration.InstrumentalRegistry.DRUM_BLOCK;
import static com.mrbysco.instrumentalmobs.registration.InstrumentalRegistry.DRUM_ITEM;

public class InstrumentalBlockLootProvider extends FabricBlockLootTableProvider {
	public InstrumentalBlockLootProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(dataOutput, registryLookup);
	}

	@Override
	public void generate() {
		this.dropOther(DRUM_BLOCK.get(), DRUM_ITEM.get());
	}
}
