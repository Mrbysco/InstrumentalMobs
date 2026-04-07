package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mrbysco.instrumentalmobs.client.render.state.MicrophoneRenderState;
import net.minecraft.client.model.monster.ghast.GhastModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class MicrophoneLayer extends RenderLayer<MicrophoneRenderState, GhastModel> {

	public MicrophoneLayer(RenderLayerParent<MicrophoneRenderState, GhastModel> layerParent) {
		super(layerParent);
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, MicrophoneRenderState state, float xRot, float yRot) {
		ItemStackRenderState stack = state.headItem;
		if (!stack.isEmpty() && !state.isSinging) {
			poseStack.pushPose();

			poseStack.mulPose(Axis.XP.rotationDegrees(-180F));
			poseStack.translate(0F, -1F, 2.25F);

			stack.submit(poseStack, submitNodeCollector, packedLight, OverlayTexture.NO_OVERLAY, state.outlineColor);
			poseStack.popPose();
		}
	}
}
