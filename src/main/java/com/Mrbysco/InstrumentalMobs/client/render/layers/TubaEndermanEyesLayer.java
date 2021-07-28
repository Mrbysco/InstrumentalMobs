package com.mrbysco.instrumentalmobs.client.render.layers;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.client.renderer.entity.model.EndermanModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;

public class TubaEndermanEyesLayer<T extends LivingEntity> extends AbstractEyesLayer<T, EndermanModel<T>> {
    private static final RenderType RENDER_TYPE = RenderType.eyes(new ResourceLocation("textures/entity/enderman/enderman_eyes.png"));

    public TubaEndermanEyesLayer(IEntityRenderer<T, EndermanModel<T>> rendererIn) {
        super(rendererIn);
    }

    public RenderType renderType() {
        return RENDER_TYPE;
    }
}