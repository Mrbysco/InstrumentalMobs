package com.mrbysco.instrumentalmobs.client.render;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class CustomBipedRenderer<T extends Mob, M extends HumanoidModel<T>> extends MobRenderer<T, M> {
	private static final ResourceLocation DEFAULT_RES_LOC = new ResourceLocation("textures/entity/steve.png");

	public CustomBipedRenderer(EntityRendererProvider.Context context, M humanoidModel, float shadowSize) {
		this(context, humanoidModel, shadowSize, 1.0F, 1.0F, 1.0F);
	}

	public CustomBipedRenderer(EntityRendererProvider.Context context, M humanoidModel, float p_i232471_3_, float p_i232471_4_, float p_i232471_5_, float p_i232471_6_) {
		super(context, humanoidModel, p_i232471_3_);
		this.addLayer(new CustomHeadLayer<>(this, context.getModelSet(), p_i232471_4_, p_i232471_5_, p_i232471_6_, context.getItemInHandRenderer()));
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	public ResourceLocation getTextureLocation(T entity) {
		return DEFAULT_RES_LOC;
	}
}