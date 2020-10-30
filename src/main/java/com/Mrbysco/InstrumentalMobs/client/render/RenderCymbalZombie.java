package com.mrbysco.instrumentalmobs.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mrbysco.instrumentalmobs.client.render.model.ModelCymbalHusk;
import com.mrbysco.instrumentalmobs.entities.CymbalHuskEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

public class RenderCymbalZombie extends RenderCustomBiped<CymbalHuskEntity, ModelCymbalHusk<CymbalHuskEntity>> {
    private static final ResourceLocation HUSK_ZOMBIE_TEXTURES = new ResourceLocation("textures/entity/zombie/husk.png");

	public RenderCymbalZombie(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelCymbalHusk(), 0.5F);
        this.addLayer(new HeldItemLayer<>(this));
        this.addLayer(new BipedArmorLayer<>(this, new ModelCymbalHusk<>(0.5F, true), new ModelCymbalHusk<>(1.0F, true)));
	}

    protected void preRenderCallback(CymbalHuskEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        float f = 1.0625F;
        matrixStackIn.scale(1.0625F, 1.0625F, 1.0625F);
        super.preRenderCallback(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
	
	public ResourceLocation getEntityTexture(CymbalHuskEntity entity) {
        return HUSK_ZOMBIE_TEXTURES;
	}
}
