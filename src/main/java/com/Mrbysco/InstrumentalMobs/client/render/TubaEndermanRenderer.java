package com.mrbysco.instrumentalmobs.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mrbysco.instrumentalmobs.client.render.layers.TubaEndermanEyesLayer;
import com.mrbysco.instrumentalmobs.client.render.layers.TubaEndermanHeldItemLayer;
import com.mrbysco.instrumentalmobs.client.render.model.TubaEndermanModel;
import com.mrbysco.instrumentalmobs.entities.TubaEndermanEntity;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class TubaEndermanRenderer extends MobRenderer<TubaEndermanEntity, TubaEndermanModel<TubaEndermanEntity>> {
	private static final ResourceLocation ENDERMAN_TEXTURES = new ResourceLocation("textures/entity/enderman/enderman.png");
	private final Random rnd = new Random();

	public TubaEndermanRenderer(EntityRendererProvider.Context context) {
		super(context, new TubaEndermanModel(context.bakeLayer(ModelLayers.ENDERMAN)), 0.5F);
		this.addLayer(new TubaEndermanEyesLayer(this));
		this.addLayer(new TubaEndermanHeldItemLayer(this, context.getItemInHandRenderer()));
	}

	public void render(TubaEndermanEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
		BlockState blockstate = entityIn.getCarriedBlock();
		EndermanModel<EnderMan> endermanmodel = (EndermanModel) this.getModel();
		endermanmodel.carrying = blockstate != null;
		endermanmodel.creepy = entityIn.isCreepy();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	public Vec3 getRenderOffset(TubaEndermanEntity entityIn, float partialTicks) {
		if (entityIn.isCreepy()) {
			double d0 = 0.02D;
			return new Vec3(this.rnd.nextGaussian() * 0.02D, 0.0D, this.rnd.nextGaussian() * 0.02D);
		} else {
			return super.getRenderOffset(entityIn, partialTicks);
		}
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	public ResourceLocation getTextureLocation(TubaEndermanEntity entity) {
		return ENDERMAN_TEXTURES;
	}
}
