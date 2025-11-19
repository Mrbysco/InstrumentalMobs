package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mrbysco.instrumentalmobs.client.render.model.MaracaSpiderModel;
import com.mrbysco.instrumentalmobs.client.render.state.MaracaRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.HumanoidArm;

public class MaracasLayer extends RenderLayer<MaracaRenderState, MaracaSpiderModel> {

	public MaracasLayer(RenderLayerParent<MaracaRenderState, MaracaSpiderModel> layerParent) {
		super(layerParent);
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, MaracaRenderState state, float xRot, float yRot) {
		ItemStackRenderState stack = state.mainItem;
		ItemStackRenderState otherStack = state.offItem;


		if (!stack.isEmpty() || !otherStack.isEmpty()) {
			poseStack.pushPose();

			this.renderHeldItem(state, stack, HumanoidArm.LEFT, poseStack, submitNodeCollector, packedLight);
			this.renderHeldItem(state, otherStack, HumanoidArm.RIGHT, poseStack, submitNodeCollector, packedLight);
			poseStack.popPose();
		}
	}

	private void renderHeldItem(MaracaRenderState state, ItemStackRenderState stack, HumanoidArm handSide,
	                            PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight) {
		if (!stack.isEmpty()) {
			poseStack.pushPose();
			this.getParentModel().translateToHand(state, handSide, poseStack);
			poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
			poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
			boolean flag = handSide == HumanoidArm.LEFT;
			poseStack.translate((double) ((float) (flag ? -1 : 1) / 16.0F), 0.125D, -0.625D);

			stack.submit(poseStack, submitNodeCollector, packedLight, OverlayTexture.NO_OVERLAY, state.outlineColor);

			poseStack.popPose();
		}
	}
}