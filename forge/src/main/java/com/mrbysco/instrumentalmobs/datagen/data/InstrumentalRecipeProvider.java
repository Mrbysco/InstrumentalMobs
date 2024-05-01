package com.mrbysco.instrumentalmobs.datagen.data;

import com.mrbysco.instrumentalmobs.registration.InstrumentalRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;

public class InstrumentalRecipeProvider extends RecipeProvider {

	public InstrumentalRecipeProvider(PackOutput packOutput) {
		super(packOutput);
	}

	@Override
	protected void buildRecipes(RecipeOutput output) {
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
