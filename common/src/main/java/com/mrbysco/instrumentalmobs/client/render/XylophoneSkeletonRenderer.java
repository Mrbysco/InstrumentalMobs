package com.mrbysco.instrumentalmobs.client.render;

import com.mrbysco.instrumentalmobs.client.render.layers.HeldBoneLayer;
import com.mrbysco.instrumentalmobs.client.render.model.XylophoneSkeletonModel;
import com.mrbysco.instrumentalmobs.client.render.state.XylophoneRenderState;
import com.mrbysco.instrumentalmobs.entities.XylophoneSkeleton;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

public class XylophoneSkeletonRenderer extends CustomBipedRenderer<XylophoneSkeleton, XylophoneRenderState, XylophoneSkeletonModel> {
	private static final Identifier SKELETON_TEXTURES = Identifier.withDefaultNamespace("textures/entity/skeleton/skeleton.png");

	public XylophoneSkeletonRenderer(EntityRendererProvider.Context context) {
		super(context, new XylophoneSkeletonModel(context.bakeLayer(ModelLayers.SKELETON)), 0.5F);
		this.addLayer(new HeldBoneLayer(this));
	}

	@NotNull
	@Override
	public XylophoneRenderState createRenderState() {
		return new XylophoneRenderState();
	}

	@Override
	public void extractRenderState(@NotNull XylophoneSkeleton skeleton, @NotNull XylophoneRenderState state, float partialTick) {
		super.extractRenderState(skeleton, state, partialTick);
		state.isPlaying = skeleton.isPlayingInstrument() && skeleton.getMainHandItem().is(Items.BONE);
		state.isAggressive = skeleton.isAggressive();
		state.isShaking = skeleton.isShaking();
		state.isHoldingBow = skeleton.getMainHandItem().is(Items.BOW);
	}

	@Override
	protected boolean isShaking(XylophoneRenderState state) {
		return state.isShaking;
	}

	@NotNull
	@Override
	public Identifier getTextureLocation(@NotNull XylophoneRenderState state) {
		return SKELETON_TEXTURES;
	}
}
