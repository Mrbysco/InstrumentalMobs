package com.mrbysco.instrumentalmobs.datagen.data;

import com.mrbysco.instrumentalmobs.init.InstrumentalRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;

import java.util.concurrent.CompletableFuture;

public class InstrumentalRecipeProvider extends RecipeProvider {

	public InstrumentalRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, lookupProvider);
	}

	@Override
	protected void buildRecipes(RecipeOutput recipeOutput) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, InstrumentalRegistry.CYMBALS.get())
				.requires(InstrumentalRegistry.CYMBAL.get())
				.requires(InstrumentalRegistry.CYMBAL.get())
				.unlockedBy("has_cymbal", has(InstrumentalRegistry.CYMBAL.get()))
				.save(recipeOutput);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, InstrumentalRegistry.MARACAS.get())
				.requires(InstrumentalRegistry.MARACA.get())
				.requires(InstrumentalRegistry.MARACA.get())
				.unlockedBy("has_maraca", has(InstrumentalRegistry.MARACA.get()))
				.save(recipeOutput);
	}
}
