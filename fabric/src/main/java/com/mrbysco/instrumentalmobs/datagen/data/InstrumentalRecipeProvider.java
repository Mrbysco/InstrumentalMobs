package com.mrbysco.instrumentalmobs.datagen.data;

import com.mrbysco.instrumentalmobs.registration.InstrumentalRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;

import java.util.function.Consumer;

public class InstrumentalRecipeProvider extends FabricRecipeProvider {

	public InstrumentalRecipeProvider(FabricDataOutput dataOutput) {
		super(dataOutput);
	}

	@Override
	public void buildRecipes(Consumer<FinishedRecipe> consumer) {
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
