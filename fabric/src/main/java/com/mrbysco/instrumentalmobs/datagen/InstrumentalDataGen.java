package com.mrbysco.instrumentalmobs.datagen;

import com.mrbysco.instrumentalmobs.datagen.assets.InstrumentalLanguageProvider;
import com.mrbysco.instrumentalmobs.datagen.assets.InstrumentalSoundProvider;
import com.mrbysco.instrumentalmobs.datagen.data.InstrumentalAdvancementProvider;
import com.mrbysco.instrumentalmobs.datagen.data.InstrumentalBlockLootProvider;
import com.mrbysco.instrumentalmobs.datagen.data.InstrumentalDamageTypeProvider;
import com.mrbysco.instrumentalmobs.datagen.data.InstrumentalEntityLootProvider;
import com.mrbysco.instrumentalmobs.datagen.data.InstrumentalRecipeProvider;
import com.mrbysco.instrumentalmobs.datagen.data.InstrumentalRegistryDataProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class InstrumentalDataGen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		var pack = generator.createPack();

		pack.addProvider(InstrumentalRegistryDataProvider::new);
		pack.addProvider(InstrumentalAdvancementProvider::new);
		pack.addProvider(InstrumentalBlockLootProvider::new);
		pack.addProvider(InstrumentalEntityLootProvider::new);
		pack.addProvider(InstrumentalRecipeProvider::new);

		pack.addProvider(InstrumentalLanguageProvider::new);
		pack.addProvider(InstrumentalSoundProvider::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.DAMAGE_TYPE, InstrumentalDamageTypeProvider::bootstrap);
	}
}
