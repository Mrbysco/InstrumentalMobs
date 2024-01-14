package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mrbysco.instrumentalmobs.client.render.model.TubaEndermanModel;
import com.mrbysco.instrumentalmobs.entities.TubaEnderman;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class TubaEndermanEyesLayer<T extends TubaEnderman> extends EyesLayer<T, TubaEndermanModel<T>> {
	private static final RenderType RENDER_TYPE = RenderType.eyes(new ResourceLocation("textures/entity/enderman/enderman_eyes.png"));

	public TubaEndermanEyesLayer(RenderLayerParent<T, TubaEndermanModel<T>> layerParent) {
		super(layerParent);
	}

	public RenderType renderType() {
		return RENDER_TYPE;
	}
}