package com.mrbysco.instrumentalmobs.datagen.assets;

import com.mrbysco.instrumentalmobs.registration.InstrumentalRegistry;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;

public class InstrumentalModelProvider extends FabricModelProvider {
	public InstrumentalModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators blockModels) {
		ResourceLocation resourcelocation = ModelLocationUtils.getModelLocation(InstrumentalRegistry.DRUM_BLOCK.get());
		blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(InstrumentalRegistry.DRUM_BLOCK.get(), resourcelocation));
	}

	@Override
	public void generateItemModels(ItemModelGenerators itemModels) {
		itemModels.generateFlatItem(InstrumentalRegistry.DRUM_ITEM.get(), ModelTemplates.FLAT_ITEM);
		itemModels.generateFlatItem(InstrumentalRegistry.CYMBAL.get(), ModelTemplates.FLAT_ITEM);
		itemModels.generateFlatItem(InstrumentalRegistry.CYMBALS.get(), ModelTemplates.FLAT_ITEM);
		itemModels.generateFlatItem(InstrumentalRegistry.FRENCH_HORN.get(), ModelTemplates.FLAT_ITEM);
		itemModels.generateFlatItem(InstrumentalRegistry.MARACA.get(), ModelTemplates.FLAT_ITEM);
		itemModels.generateFlatItem(InstrumentalRegistry.MARACAS.get(), ModelTemplates.FLAT_ITEM);
		itemModels.generateFlatItem(InstrumentalRegistry.MICROPHONE.get(), ModelTemplates.FLAT_ITEM);
		itemModels.generateFlatItem(InstrumentalRegistry.TUBA.get(), ModelTemplates.FLAT_ITEM);
		itemModels.generateFlatItem(InstrumentalRegistry.XYLOPHONE.get(), ModelTemplates.FLAT_ITEM);
		itemModels.generateFlatItem(InstrumentalRegistry.TRUMPET.get(), ModelTemplates.FLAT_ITEM);

		itemModels.generateSpawnEgg(InstrumentalRegistry.CYMBAL_HUSK_SPAWN_EGG.get(), 7958625, 15125652);
		itemModels.generateSpawnEgg(InstrumentalRegistry.DRUM_ZOMBIE_SPAWN_EGG.get(), 44975, 7969893);
		itemModels.generateSpawnEgg(InstrumentalRegistry.FRENCH_HORN_CREEPER_SPAWN_EGG.get(), 894731, 0);
		itemModels.generateSpawnEgg(InstrumentalRegistry.MARACA_SPIDER_SPAWN_EGG.get(), 803406, 11013646);
		itemModels.generateSpawnEgg(InstrumentalRegistry.MICROPHONE_GHAST_SPAWN_EGG.get(), 16382457, 12369084);
		itemModels.generateSpawnEgg(InstrumentalRegistry.TUBA_ENDERMAN_SPAWN_EGG.get(), 1447446, 0);
		itemModels.generateSpawnEgg(InstrumentalRegistry.XYLOPHONE_SKELETON_SPAWN_EGG.get(), 12698049, 4802889);
		itemModels.generateSpawnEgg(InstrumentalRegistry.TRUMPET_SKELETON_SPAWN_EGG.get(), 12698049, 4802889);
	}
}
