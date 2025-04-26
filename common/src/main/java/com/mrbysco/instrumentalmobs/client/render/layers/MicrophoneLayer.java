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

public class MicrophoneLayer extends RenderLayer<MicrophoneRenderState, GhastModel> {

	public MicrophoneLayer(RenderLayerParent<MicrophoneRenderState, GhastModel> layerParent) {
		super(layerParent);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, MicrophoneRenderState state, float yRot, float xRot) {
		ItemStackRenderState stack = state.headItem;
		if (!stack.isEmpty() && !state.isSinging) {
			poseStack.pushPose();

			poseStack.mulPose(Axis.XP.rotationDegrees(-180F));
			poseStack.translate(0F, -1F, 2.25F);

			stack.render(poseStack, bufferSource, packedLight, OverlayTexture.NO_OVERLAY);
			poseStack.popPose();
		}
	}
}
