package com.mrbysco.instrumentalmobs.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mrbysco.instrumentalmobs.client.render.model.CymbalHuskModel;
import com.mrbysco.instrumentalmobs.entities.CymbalHuskEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

public class RenderCymbalZombie extends RenderCustomBiped<CymbalHuskEntity, CymbalHuskModel<CymbalHuskEntity>> {
    private static final ResourceLocation HUSK_ZOMBIE_TEXTURES = new ResourceLocation("textures/entity/zombie/husk.png");

	public RenderCymbalZombie(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CymbalHuskModel(), 0.5F);
        this.addLayer(new HeldItemLayer<>(this));
        this.addLayer(new BipedArmorLayer<>(this, new CymbalHuskModel<>(0.5F, true), new CymbalHuskModel<>(1.0F, true)));
	}

    protected void scale(CymbalHuskEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        float f = 1.0625F;
        matrixStackIn.scale(1.0625F, 1.0625F, 1.0625F);
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
	
	public ResourceLocation getTextureLocation(CymbalHuskEntity entity) {
        return HUSK_ZOMBIE_TEXTURES;
	}
}
