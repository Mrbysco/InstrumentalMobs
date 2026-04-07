package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mrbysco.instrumentalmobs.client.render.model.MaracaSpiderModel;
import com.mrbysco.instrumentalmobs.client.render.state.MaracaRenderState;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class MaracaSpiderEyesLayer extends EyesLayer<MaracaRenderState, MaracaSpiderModel> {
	private static final RenderType RENDER_TYPE = RenderTypes.eyes(Identifier.withDefaultNamespace("textures/entity/spider_eyes.png"));

	public MaracaSpiderEyesLayer(RenderLayerParent<MaracaRenderState, MaracaSpiderModel> layerParent) {
		super(layerParent);
	}

	@NotNull
	@Override
	public RenderType renderType() {
		return RENDER_TYPE;
	}
}