package com.mrbysco.instrumentalmobs.client.render.model;

import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.EndermanRenderState;

public class TubaEndermanModel<S extends EndermanRenderState> extends EndermanModel<S> implements HeadedModel {

	public TubaEndermanModel(ModelPart part) {
		super(part);
	}

	public void setupAnim(S state) {
		super.setupAnim(state);
	}

	@Override
	public ModelPart getHead() {
		return this.head;
	}
}