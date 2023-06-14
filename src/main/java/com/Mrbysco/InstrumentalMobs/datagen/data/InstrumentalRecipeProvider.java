package com.mrbysco.instrumentalmobs.datagen.data;

import com.mrbysco.instrumentalmobs.init.InstrumentalRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;

import java.util.function.Consumer;

public class InstrumentalRecipeProvider extends RecipeProvider {

	public InstrumentalRecipeProvider(PackOutput packOutput) {
		super(packOutput);
	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, InstrumentalRegistry.CYMBALS.get())
				.requires(InstrumentalRegistry.CYMBAL.get())
				.requires(InstrumentalRegistry.CYMBAL.get())
				.unlockedBy("has_cymbal", has(InstrumentalRegistry.CYMBAL.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, InstrumentalRegistry.MARACAS.get())
				.requires(InstrumentalRegistry.MARACA.get())
				.requires(InstrumentalRegistry.MARACA.get())
				.unlockedBy("has_maraca", has(InstrumentalRegistry.MARACA.get()))
				.save(consumer);
	}
}
