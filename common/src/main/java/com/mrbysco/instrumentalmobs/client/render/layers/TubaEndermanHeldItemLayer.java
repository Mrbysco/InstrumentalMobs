package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.EndermanRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class TubaEndermanHeldItemLayer<S extends EndermanRenderState, M extends EntityModel<S> & HeadedModel> extends RenderLayer<S, M> {

	public TubaEndermanHeldItemLayer(RenderLayerParent<S, M> layerParent) {
		super(layerParent);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, S state, float yRot, float xRot) {
		ItemStackRenderState stack = state.getMainHandItem();
		if (!stack.isEmpty()) {
			poseStack.pushPose();
			this.getParentModel().getHead().translateAndRotate(poseStack);

			poseStack.translate(0.0F, -0.73F, -0.775F);
			poseStack.mulPose(Axis.ZP.rotationDegrees(-90F));
			poseStack.mulPose(Axis.XP.rotationDegrees(90F));
			poseStack.mulPose(Axis.ZP.rotationDegrees(-10F));

			poseStack.translate(-0.8F, 0.0F, 0.0F);

			stack.render(poseStack, bufferSource, packedLight, OverlayTexture.NO_OVERLAY);
			poseStack.popPose();
		}
	}
}