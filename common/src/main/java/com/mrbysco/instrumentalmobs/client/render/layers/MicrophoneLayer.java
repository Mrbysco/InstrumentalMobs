package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mrbysco.instrumentalmobs.client.render.state.MicrophoneRenderState;
import net.minecraft.client.model.GhastModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class MicrophoneLayer<S extends MicrophoneRenderState, M extends GhastModel> extends RenderLayer<S, M> {

	public MicrophoneLayer(RenderLayerParent<S, M> layerParent) {
		super(layerParent);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, S state, float yRot, float xRot) {
		ItemStackRenderState stack = state.headItem;
		if (!stack.isEmpty() && !state.isSinging) {
			poseStack.pushPose();

			poseStack.scale(0.25F, 0.25F, 0.25F);
			poseStack.mulPose(Axis.XP.rotationDegrees(-180F));
			poseStack.translate(-0.5F, -6F, 2F);
			stack.render(poseStack, bufferSource, packedLight, OverlayTexture.NO_OVERLAY);
			poseStack.popPose();
		}
	}
}
