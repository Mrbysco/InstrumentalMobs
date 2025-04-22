package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mrbysco.instrumentalmobs.client.render.model.MaracaSpiderModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

public class MaracaSpiderEyesLayer<M extends MaracaSpiderModel> extends EyesLayer<LivingEntityRenderState, M> {
	private static final RenderType RENDER_TYPE = RenderType.eyes(ResourceLocation.withDefaultNamespace("textures/entity/spider_eyes.png"));

	public MaracaSpiderEyesLayer(RenderLayerParent<LivingEntityRenderState, M> layerParent) {
		super(layerParent);
	}

	public RenderType renderType() {
		return RENDER_TYPE;
	}
}