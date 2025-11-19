package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mrbysco.instrumentalmobs.client.render.model.XylophoneSkeletonModel;
import com.mrbysco.instrumentalmobs.client.render.state.XylophoneRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.HumanoidArm;
import org.joml.Vector3f;

public class HeldBoneLayer extends RenderLayer<XylophoneRenderState, XylophoneSkeletonModel> {

	public HeldBoneLayer(RenderLayerParent<XylophoneRenderState, XylophoneSkeletonModel> layerParent) {
		super(layerParent);
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, XylophoneRenderState state, float xRot, float yRot) {
		boolean flag = state.mainArm == HumanoidArm.RIGHT;
		ItemStackRenderState itemstack = flag ? state.leftHandItem : state.rightHandItem;
		ItemStackRenderState itemstack1 = flag ? state.rightHandItem : state.leftHandItem;

		if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
			poseStack.pushPose();

			if (state.isBaby) {
				float f = 0.5F;
				poseStack.translate(0.0F, 0.75F, 0.0F);
				poseStack.scale(f, f, f);
			}

			this.renderHeldItem(state, itemstack1, HumanoidArm.LEFT, poseStack, submitNodeCollector, packedLight);
			this.renderHeldItem(state, itemstack, HumanoidArm.RIGHT, poseStack, submitNodeCollector, packedLight);
			poseStack.popPose();
		}
	}

	private void renderHeldItem(XylophoneRenderState state, ItemStackRenderState stack,
	                            HumanoidArm arm, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight) {
		if (!stack.isEmpty()) {
			poseStack.pushPose();

			if (state.isCrouching) {
				poseStack.translate(0.0F, 0.2F, 0.0F);
			}

			this.getParentModel().translateToHand(state, arm, poseStack);

			poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
			poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
			boolean flag = arm == HumanoidArm.LEFT;
			boolean flag2 = state.isPlaying;
			if (flag2) {
				poseStack.scale(0.75F, 0.75F, 0.75F);
				poseStack.translate(0.0F, -0.225F, -0.5F);
				poseStack.mulPose(Axis.of(new Vector3f(1.0F, flag ? -0.2F : 0.2F, flag ? -0.2F : 0.2F)).rotationDegrees(45.0F));
			}
			poseStack.translate((double) ((float) (flag ? -1 : 1) / 16.0F), 0.125D, -0.625D);
			stack.submit(poseStack, submitNodeCollector, packedLight, OverlayTexture.NO_OVERLAY, state.outlineColor);

			poseStack.popPose();
		}
	}
}