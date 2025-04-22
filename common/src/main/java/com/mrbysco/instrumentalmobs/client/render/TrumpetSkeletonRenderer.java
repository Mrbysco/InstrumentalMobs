package com.mrbysco.instrumentalmobs.client.render;

import com.mrbysco.instrumentalmobs.client.render.state.XylophoneRenderState;
import com.mrbysco.instrumentalmobs.entities.TrumpetSkeleton;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.ResourceLocation;

public class TrumpetSkeletonRenderer extends CustomBipedRenderer<TrumpetSkeleton, SkeletonRenderState, SkeletonModel<SkeletonRenderState>> {
	private static final ResourceLocation SKELETON_TEXTURES = ResourceLocation.withDefaultNamespace("textures/entity/skeleton/skeleton.png");

	public TrumpetSkeletonRenderer(EntityRendererProvider.Context context) {
		super(context, new SkeletonModel(context.bakeLayer(ModelLayers.SKELETON)), 0.5F);
		this.addLayer(new ItemInHandLayer(this));
	}

	@Override
	public SkeletonRenderState createRenderState() {
		return new XylophoneRenderState();
	}

	@Override
	public void extractRenderState(TrumpetSkeleton skeleton, SkeletonRenderState state, float partialTick) {
		super.extractRenderState(skeleton, state, partialTick);
	}

	@Override
	public ResourceLocation getTextureLocation(SkeletonRenderState state) {
		return SKELETON_TEXTURES;
	}
}
