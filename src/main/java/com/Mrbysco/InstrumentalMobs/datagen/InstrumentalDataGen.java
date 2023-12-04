package com.mrbysco.instrumentalmobs.datagen;

import com.mrbysco.instrumentalmobs.Reference;
import com.mrbysco.instrumentalmobs.datagen.assets.InstrumentalLanguageProvider;
import com.mrbysco.instrumentalmobs.datagen.assets.InstrumentalSoundProvider;
import com.mrbysco.instrumentalmobs.datagen.data.InstrumentalAdvancementProvider;
import com.mrbysco.instrumentalmobs.datagen.data.InstrumentalDamageTypeProvider;
import com.mrbysco.instrumentalmobs.datagen.data.InstrumentalLoot;
import com.mrbysco.instrumentalmobs.datagen.data.InstrumentalRecipeProvider;
import com.mrbysco.instrumentalmobs.init.InstrumentalRegistry;
import com.mrbysco.instrumentalmobs.modifier.AddRelativeSpawnBiomeModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class InstrumentalDataGen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();

		if (event.includeServer()) {
			generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(
					packOutput, CompletableFuture.supplyAsync(InstrumentalDataGen::getProvider), Set.of(Reference.MOD_ID)));

			generator.addProvider(event.includeServer(), new InstrumentalAdvancementProvider(packOutput, lookupProvider));
			generator.addProvider(event.includeServer(), new InstrumentalLoot(packOutput));
			generator.addProvider(event.includeServer(), new InstrumentalRecipeProvider(packOutput));
		}
		if (event.includeClient()) {
			generator.addProvider(event.includeClient(), new InstrumentalLanguageProvider(packOutput));
			generator.addProvider(event.includeClient(), new InstrumentalSoundProvider(packOutput, helper));
		}
	}

	private static HolderLookup.Provider getProvider() {
		final RegistrySetBuilder registryBuilder = new RegistrySetBuilder();
		registryBuilder.add(Registries.DAMAGE_TYPE, InstrumentalDamageTypeProvider::bootstrap);
		registryBuilder.add(ForgeRegistries.Keys.BIOME_MODIFIERS, context -> {
			registerModifier(context, EntityType.HUSK, InstrumentalRegistry.CYMBAL_HUSK.get(), 5);
			registerModifier(context, EntityType.ZOMBIE, InstrumentalRegistry.DRUM_ZOMBIE.get(), 5);
			registerModifier(context, EntityType.CREEPER, InstrumentalRegistry.FRENCH_HORN_CREEPER.get(), 5);
			registerModifier(context, EntityType.SPIDER, InstrumentalRegistry.MARACA_SPIDER.get(), 5);
			registerModifier(context, EntityType.GHAST, InstrumentalRegistry.MICROPHONE_GHAST.get(), 5);
			registerModifier(context, EntityType.ENDERMAN, InstrumentalRegistry.TUBA_ENDERMAN.get(), 5);
			registerModifier(context, EntityType.SKELETON, InstrumentalRegistry.XYLOPHONE_SKELETON.get(), 5);
			registerModifier(context, EntityType.SKELETON, InstrumentalRegistry.TRUMPET_SKELETON.get(), 5);
		});
		// We need the BIOME registry to be present so we can use a biome tag, doesn't matter that it's empty
		registryBuilder.add(Registries.BIOME, context -> {
		});
		RegistryAccess.Frozen regAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);
		return registryBuilder.buildPatch(regAccess, VanillaRegistries.createLookup());
	}

	private static void registerModifier(BootstapContext<BiomeModifier> context, EntityType<?> originalType, EntityType<?> newType, int relativeWeight) {
		context.register(getModifierKey(newType), new AddRelativeSpawnBiomeModifier(
				originalType, newType, relativeWeight));
	}

	private static ResourceKey<BiomeModifier> getModifierKey(EntityType<?> type) {
		return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, ForgeRegistries.ENTITY_TYPES.getKey(type));
	}
}
