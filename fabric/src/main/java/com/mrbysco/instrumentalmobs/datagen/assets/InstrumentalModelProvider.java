package com.mrbysco.instrumentalmobs.datagen.assets;

import com.mrbysco.instrumentalmobs.registration.InstrumentalRegistry;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class InstrumentalModelProvider extends FabricModelProvider {
	public InstrumentalModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators blockModels) {
		Identifier resourcelocation = ModelLocationUtils.getModelLocation(InstrumentalRegistry.DRUM_BLOCK.get());
		blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(InstrumentalRegistry.DRUM_BLOCK.get(),
				BlockModelGenerators.plainVariant(resourcelocation)));
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

		registerSpawnEgg(itemModels, InstrumentalRegistry.CYMBAL_HUSK_SPAWN_EGG.get(), Items.HUSK_SPAWN_EGG);
		registerSpawnEgg(itemModels, InstrumentalRegistry.DRUM_ZOMBIE_SPAWN_EGG.get(), Items.ZOMBIE_SPAWN_EGG);
		registerSpawnEgg(itemModels, InstrumentalRegistry.FRENCH_HORN_CREEPER_SPAWN_EGG.get(), Items.CREEPER_SPAWN_EGG);
		registerSpawnEgg(itemModels, InstrumentalRegistry.MARACA_SPIDER_SPAWN_EGG.get(), Items.SPIDER_SPAWN_EGG);
		registerSpawnEgg(itemModels, InstrumentalRegistry.MICROPHONE_GHAST_SPAWN_EGG.get(), Items.GHAST_SPAWN_EGG);
		registerSpawnEgg(itemModels, InstrumentalRegistry.TUBA_ENDERMAN_SPAWN_EGG.get(), Items.ENDERMAN_SPAWN_EGG);
		registerSpawnEgg(itemModels, InstrumentalRegistry.XYLOPHONE_SKELETON_SPAWN_EGG.get(), Items.SKELETON_SPAWN_EGG);
		registerSpawnEgg(itemModels, InstrumentalRegistry.TRUMPET_SKELETON_SPAWN_EGG.get(), Items.SKELETON_SPAWN_EGG);
	}

	private void registerSpawnEgg(ItemModelGenerators itemModels, Item spawnEgg, Item originalEgg) {
		itemModels.itemModelOutput.accept(spawnEgg, ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(originalEgg)));
	}
}
