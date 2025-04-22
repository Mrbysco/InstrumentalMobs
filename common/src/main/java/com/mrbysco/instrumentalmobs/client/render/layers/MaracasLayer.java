package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mrbysco.instrumentalmobs.client.render.state.MaracaRenderState;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.HumanoidArm;

public class MaracasLayer<S extends MaracaRenderState, M extends EntityModel<S> & ArmedModel> extends RenderLayer<S, M> {

	public MaracasLayer(RenderLayerParent<S, M> layerParent) {
		super(layerParent);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, S state, float yRot, float xRot) {
		ItemStackRenderState stack = state.mainItem;
		ItemStackRenderState otherStack = state.offItem;


		if (!stack.isEmpty() || !otherStack.isEmpty()) {
			poseStack.pushPose();

			this.renderHeldItem(state, stack, HumanoidArm.LEFT, poseStack, bufferSource, packedLight);
			this.renderHeldItem(state, otherStack, HumanoidArm.RIGHT, poseStack, bufferSource, packedLight);
			poseStack.popPose();
		}
	}

	private void renderHeldItem(S state, ItemStackRenderState stack, HumanoidArm handSide,
	                            PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
		if (!stack.isEmpty()) {
			poseStack.pushPose();
			this.getParentModel().translateToHand(handSide, poseStack);
			poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
			poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
			boolean flag = handSide == HumanoidArm.LEFT;
			poseStack.translate((double) ((float) (flag ? -1 : 1) / 16.0F), 0.125D, -0.625D);

			stack.render(poseStack, bufferSource, packedLight, OverlayTexture.NO_OVERLAY);

			poseStack.popPose();
		}
	}
}