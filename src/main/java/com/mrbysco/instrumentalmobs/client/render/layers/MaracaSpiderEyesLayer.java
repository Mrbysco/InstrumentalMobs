package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mrbysco.instrumentalmobs.client.render.model.MaracaSpiderModel;
import com.mrbysco.instrumentalmobs.entities.MaracaSpiderEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class MaracaSpiderEyesLayer<T extends MaracaSpiderEntity, M extends MaracaSpiderModel<T>> extends EyesLayer<T, M> {
	private static final RenderType RENDER_TYPE = RenderType.eyes(new ResourceLocation("textures/entity/spider_eyes.png"));

	public MaracaSpiderEyesLayer(RenderLayerParent<T, M> layerParent) {
		super(layerParent);
	}

	public RenderType renderType() {
		return RENDER_TYPE;
	}
}