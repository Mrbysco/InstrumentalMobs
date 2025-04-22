package com.mrbysco.instrumentalmobs.client.render.model;

import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.ModelPart;

public class FrenchHornCreeperModel extends CreeperModel implements HeadedModel {
	public FrenchHornCreeperModel(ModelPart part) {
		super(part);
	}

	@Override
	public ModelPart getHead() {
		return this.head;
	}
}
