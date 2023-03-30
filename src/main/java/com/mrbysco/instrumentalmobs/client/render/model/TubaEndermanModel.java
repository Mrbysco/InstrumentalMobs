package com.mrbysco.instrumentalmobs.client.render.model;

import com.mrbysco.instrumentalmobs.entities.TubaEndermanEntity;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.ModelPart;

public class TubaEndermanModel<T extends TubaEndermanEntity> extends EndermanModel<T> implements HeadedModel {

	public TubaEndermanModel(ModelPart part) {
		super(part);
	}

	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}

	@Override
	public ModelPart getHead() {
		return this.head;
	}
}