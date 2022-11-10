package com.mrbysco.instrumentalmobs.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mrbysco.instrumentalmobs.client.render.layers.FrenchHornCreeperCharge;
import com.mrbysco.instrumentalmobs.client.render.layers.FrenchHornLayer;
import com.mrbysco.instrumentalmobs.client.render.model.FrenchHornCreeperModel;
import com.mrbysco.instrumentalmobs.entities.FrenchHornCreeperEntity;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class FrenchHornCreeperRenderer extends MobRenderer<FrenchHornCreeperEntity, FrenchHornCreeperModel<FrenchHornCreeperEntity>> {
	private static final ResourceLocation CREEPER_TEXTURES = new ResourceLocation("textures/entity/creeper/creeper.png");

	public FrenchHornCreeperRenderer(EntityRendererProvider.Context context) {
		super(context, new FrenchHornCreeperModel(context.bakeLayer(ModelLayers.CREEPER)), 0.5F);
		this.addLayer(new FrenchHornCreeperCharge(this, context.getModelSet()));
		this.addLayer(new FrenchHornLayer(this));
	}

	protected void scale(FrenchHornCreeperEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
		float f = entitylivingbaseIn.getSwelling(partialTickTime);
		float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
		f = Mth.clamp(f, 0.0F, 1.0F);
		f = f * f;
		f = f * f;
		float f2 = (1.0F + f * 0.4F) * f1;
		float f3 = (1.0F + f * 0.1F) / f1;
		matrixStackIn.scale(f2, f3, f2);
	}

	protected float getWhiteOverlayProgress(FrenchHornCreeperEntity livingEntityIn, float partialTicks) {
		float f = livingEntityIn.getSwelling(partialTicks);
		return (int)(f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
	}

	/**
	 * Returns the location of an entity's texture.
	 */
	public ResourceLocation getTextureLocation(FrenchHornCreeperEntity entity) {
		return CREEPER_TEXTURES;
	}
}