package com.mrbysco.instrumentalmobs.datagen.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

import static com.mrbysco.instrumentalmobs.registration.InstrumentalRegistry.DRUM_BLOCK;
import static com.mrbysco.instrumentalmobs.registration.InstrumentalRegistry.DRUM_ITEM;

public class InstrumentalBlockLootProvider extends FabricBlockLootTableProvider {
	public InstrumentalBlockLootProvider(FabricDataOutput dataOutput) {
		super(dataOutput);
	}

	@Override
	public void generate() {
		this.dropOther(DRUM_BLOCK.get(), DRUM_ITEM.get());
	}
}
