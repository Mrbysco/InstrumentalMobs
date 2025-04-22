package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mrbysco.instrumentalmobs.client.render.state.XylophoneRenderState;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.HumanoidArm;
import org.joml.Vector3f;

public class HeldBoneLayer<S extends XylophoneRenderState, M extends EntityModel<S> & ArmedModel> extends RenderLayer<S, M> {

	public HeldBoneLayer(RenderLayerParent<S, M> layerParent) {
		super(layerParent);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, S state, float yRot, float xRot) {
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

			this.renderHeldItem(state, itemstack1, HumanoidArm.LEFT, poseStack, bufferSource, packedLight);
			this.renderHeldItem(state, itemstack, HumanoidArm.RIGHT, poseStack, bufferSource, packedLight);
			poseStack.popPose();
		}
	}

	private void renderHeldItem(S state, ItemStackRenderState stack,
	                            HumanoidArm arm, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
		if (!stack.isEmpty()) {
			poseStack.pushPose();

			if (state.isCrouching) {
				poseStack.translate(0.0F, 0.2F, 0.0F);
			}

			this.getParentModel().translateToHand(arm, poseStack);

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
			stack.render(poseStack, bufferSource, packedLight, OverlayTexture.NO_OVERLAY);
			poseStack.popPose();
		}
	}
}