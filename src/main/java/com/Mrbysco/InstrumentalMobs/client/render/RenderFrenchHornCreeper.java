package com.mrbysco.instrumentalmobs.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mrbysco.instrumentalmobs.client.render.layers.FrenchHornCreeperCharge;
import com.mrbysco.instrumentalmobs.client.render.layers.FrenchHornLayer;
import com.mrbysco.instrumentalmobs.client.render.model.FrenchHornCreeperModel;
import com.mrbysco.instrumentalmobs.entities.FrenchHornCreeperEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderFrenchHornCreeper  extends MobRenderer<FrenchHornCreeperEntity, FrenchHornCreeperModel<FrenchHornCreeperEntity>> {
	private static final ResourceLocation CREEPER_TEXTURES = new ResourceLocation("textures/entity/creeper/creeper.png");

	public RenderFrenchHornCreeper(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new FrenchHornCreeperModel<>(), 0.5F);
		this.addLayer(new FrenchHornCreeperCharge(this));
		this.addLayer(new FrenchHornLayer(this));
	}

	protected void preRenderCallback(FrenchHornCreeperEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
		float f = entitylivingbaseIn.getCreeperFlashIntensity(partialTickTime);
		float f1 = 1.0F + MathHelper.sin(f * 100.0F) * f * 0.01F;
		f = MathHelper.clamp(f, 0.0F, 1.0F);
		f = f * f;
		f = f * f;
		float f2 = (1.0F + f * 0.4F) * f1;
		float f3 = (1.0F + f * 0.1F) / f1;
		matrixStackIn.scale(f2, f3, f2);
	}

	protected float getOverlayProgress(FrenchHornCreeperEntity livingEntityIn, float partialTicks) {
		float f = livingEntityIn.getCreeperFlashIntensity(partialTicks);
		return (int)(f * 10.0F) % 2 == 0 ? 0.0F : MathHelper.clamp(f, 0.5F, 1.0F);
	}

	/**
	 * Returns the location of an entity's texture.
	 */
	public ResourceLocation getEntityTexture(FrenchHornCreeperEntity entity) {
		return CREEPER_TEXTURES;
	}
}