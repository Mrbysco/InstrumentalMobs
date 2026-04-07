package com.mrbysco.instrumentalmobs.client.render;

import com.mrbysco.instrumentalmobs.entities.TrumpetSkeleton;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

public class TrumpetSkeletonRenderer extends CustomBipedRenderer<TrumpetSkeleton, SkeletonRenderState, SkeletonModel<SkeletonRenderState>> {
	private static final Identifier SKELETON_TEXTURES = Identifier.withDefaultNamespace("textures/entity/skeleton/skeleton.png");

	public TrumpetSkeletonRenderer(EntityRendererProvider.Context context) {
		super(context, new SkeletonModel<>(context.bakeLayer(ModelLayers.SKELETON)), 0.5F);
		this.addLayer(new ItemInHandLayer<>(this));
	}

	@NotNull
	@Override
	public SkeletonRenderState createRenderState() {
		return new SkeletonRenderState();
	}

	@Override
	public void extractRenderState(@NotNull TrumpetSkeleton skeleton, @NotNull SkeletonRenderState state, float partialTick) {
		super.extractRenderState(skeleton, state, partialTick);
		state.isAggressive = skeleton.isAggressive();
		state.isShaking = skeleton.isShaking();
		state.isHoldingBow = skeleton.getMainHandItem().is(Items.BOW);
	}

	@Override
	protected boolean isShaking(SkeletonRenderState state) {
		return state.isShaking;
	}

	@NotNull
	@Override
	public Identifier getTextureLocation(@NotNull SkeletonRenderState state) {
		return SKELETON_TEXTURES;
	}
}
