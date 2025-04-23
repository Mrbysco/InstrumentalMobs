package com.mrbysco.instrumentalmobs.client.render.model;

import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.EndermanRenderState;
import org.jetbrains.annotations.NotNull;

public class TubaEndermanModel<S extends EndermanRenderState> extends EndermanModel<S> implements HeadedModel {

	public TubaEndermanModel(ModelPart part) {
		super(part);
	}

	public void setupAnim(@NotNull S state) {
		super.setupAnim(state);
	}

	@NotNull
	@Override
	public ModelPart getHead() {
		return this.head;
	}
}