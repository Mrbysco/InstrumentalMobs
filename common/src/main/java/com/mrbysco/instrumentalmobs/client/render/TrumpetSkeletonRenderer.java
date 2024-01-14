package com.mrbysco.instrumentalmobs.client.render;

import com.mrbysco.instrumentalmobs.entities.TrumpetSkeleton;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class TrumpetSkeletonRenderer extends CustomBipedRenderer<TrumpetSkeleton, SkeletonModel<TrumpetSkeleton>> {
	private static final ResourceLocation SKELETON_TEXTURES = new ResourceLocation("textures/entity/skeleton/skeleton.png");

	public TrumpetSkeletonRenderer(EntityRendererProvider.Context context) {
		super(context, new SkeletonModel<>(context.bakeLayer(ModelLayers.SKELETON)), 0.5F);
		this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
	}

	public ResourceLocation getTextureLocation(TrumpetSkeleton trumpetSkeleton) {
		return SKELETON_TEXTURES;
	}
}
