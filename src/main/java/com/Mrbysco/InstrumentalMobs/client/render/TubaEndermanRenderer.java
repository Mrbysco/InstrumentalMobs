package com.mrbysco.instrumentalmobs.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mrbysco.instrumentalmobs.client.render.layers.TubaEndermanEyesLayer;
import com.mrbysco.instrumentalmobs.client.render.layers.TubaEndermanHeldItemLayer;
import com.mrbysco.instrumentalmobs.client.render.model.TubaEndermanModel;
import com.mrbysco.instrumentalmobs.entities.TubaEnderman;
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

public class TubaEndermanRenderer extends MobRenderer<TubaEnderman, TubaEndermanModel<TubaEnderman>> {
	private static final ResourceLocation ENDERMAN_TEXTURES = new ResourceLocation("textures/entity/enderman/enderman.png");
	private final Random rnd = new Random();

	public TubaEndermanRenderer(EntityRendererProvider.Context context) {
		super(context, new TubaEndermanModel<>(context.bakeLayer(ModelLayers.ENDERMAN)), 0.5F);
		this.addLayer(new TubaEndermanEyesLayer<>(this));
		this.addLayer(new TubaEndermanHeldItemLayer<>(this, context.getItemInHandRenderer()));
	}

	public void render(TubaEnderman tubaEnderman, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLightIn) {
		BlockState blockstate = tubaEnderman.getCarriedBlock();
		EndermanModel<EnderMan> endermanmodel = (EndermanModel) this.getModel();
		endermanmodel.carrying = blockstate != null;
		endermanmodel.creepy = tubaEnderman.isCreepy();
		super.render(tubaEnderman, entityYaw, partialTicks, poseStack, bufferSource, packedLightIn);
	}

	public Vec3 getRenderOffset(TubaEnderman tubaEnderman, float partialTicks) {
		if (tubaEnderman.isCreepy()) {
			double d0 = 0.02D;
			return new Vec3(this.rnd.nextGaussian() * 0.02D, 0.0D, this.rnd.nextGaussian() * 0.02D);
		} else {
			return super.getRenderOffset(tubaEnderman, partialTicks);
		}
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	public ResourceLocation getTextureLocation(TubaEnderman tubaEnderman) {
		return ENDERMAN_TEXTURES;
	}
}
