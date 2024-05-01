package com.mrbysco.instrumentalmobs.datagen.data;

import com.mrbysco.instrumentalmobs.registration.InstrumentalRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;

import java.util.concurrent.CompletableFuture;

public class InstrumentalRecipeProvider extends FabricRecipeProvider {

	public InstrumentalRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public void buildRecipes(RecipeOutput output) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, InstrumentalRegistry.CYMBALS.get())
				.requires(InstrumentalRegistry.CYMBAL.get())
				.requires(InstrumentalRegistry.CYMBAL.get())
				.unlockedBy("has_cymbal", has(InstrumentalRegistry.CYMBAL.get()))
				.save(output);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, InstrumentalRegistry.MARACAS.get())
				.requires(InstrumentalRegistry.MARACA.get())
				.requires(InstrumentalRegistry.MARACA.get())
				.unlockedBy("has_maraca", has(InstrumentalRegistry.MARACA.get()))
				.save(output);
	}
}
