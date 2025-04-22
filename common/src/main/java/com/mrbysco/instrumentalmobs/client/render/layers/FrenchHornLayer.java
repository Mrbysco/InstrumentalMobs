package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mrbysco.instrumentalmobs.client.render.state.FrenchRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class FrenchHornLayer<S extends FrenchRenderState, M extends EntityModel<S> & HeadedModel> extends RenderLayer<S, M> {
	public FrenchHornLayer(RenderLayerParent<S, M> layerParent) {
		super(layerParent);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, S state, float yRot, float xRot) {
		ItemStackRenderState stack = state.heldItem;
		if (!stack.isEmpty()) {
			poseStack.pushPose();
			this.getParentModel().getHead().translateAndRotate(poseStack);

			poseStack.scale(0.75F, 0.75F, 0.75F);
			poseStack.mulPose(Axis.YP.rotationDegrees(90F));
			poseStack.translate(0.85F, 0.15F, 0.0F);
			poseStack.mulPose(Axis.ZP.rotationDegrees(-20F));

			stack.render(poseStack, bufferSource, packedLight, OverlayTexture.NO_OVERLAY);

			poseStack.popPose();
		}
	}
}
