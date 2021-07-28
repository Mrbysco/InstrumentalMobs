package com.mrbysco.instrumentalmobs.client.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;

public class RenderCustomBiped<T extends MobEntity, M extends BipedModel<T>> extends MobRenderer<T, M> {
    private static final ResourceLocation DEFAULT_RES_LOC = new ResourceLocation("textures/entity/steve.png");

    public RenderCustomBiped(EntityRendererManager renderManagerIn, M modelBipedIn, float shadowSize) {
        this(renderManagerIn, modelBipedIn, shadowSize, 1.0F, 1.0F, 1.0F);
    }

    public RenderCustomBiped(EntityRendererManager p_i232471_1_, M p_i232471_2_, float p_i232471_3_, float p_i232471_4_, float p_i232471_5_, float p_i232471_6_) {
        super(p_i232471_1_, p_i232471_2_, p_i232471_3_);
        this.addLayer(new HeadLayer<>(this, p_i232471_4_, p_i232471_5_, p_i232471_6_));
//        this.addLayer(new HeldItemLayer<>(this));
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    public ResourceLocation getTextureLocation(T entity)
    {
        return DEFAULT_RES_LOC;
    }
}