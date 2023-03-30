package com.mrbysco.instrumentalmobs.client.render.layers;

import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class TubaEndermanEyesLayer<T extends LivingEntity> extends EyesLayer<T, EndermanModel<T>> {
	private static final RenderType RENDER_TYPE = RenderType.eyes(new ResourceLocation("textures/entity/enderman/enderman_eyes.png"));

	public TubaEndermanEyesLayer(RenderLayerParent<T, EndermanModel<T>> layerParent) {
		super(layerParent);
	}

	public RenderType renderType() {
		return RENDER_TYPE;
	}
}