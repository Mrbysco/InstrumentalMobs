package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mrbysco.instrumentalmobs.client.render.model.FrenchHornCreeperModel;
import com.mrbysco.instrumentalmobs.client.render.state.FrenchRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class FrenchHornLayer extends RenderLayer<FrenchRenderState, FrenchHornCreeperModel> {
	public FrenchHornLayer(RenderLayerParent<FrenchRenderState, FrenchHornCreeperModel> layerParent) {
		super(layerParent);
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, FrenchRenderState state, float xRot, float yRot) {
		ItemStackRenderState stack = state.heldItem;
		if (!stack.isEmpty()) {
			poseStack.pushPose();
			this.getParentModel().getHead().translateAndRotate(poseStack);

			poseStack.scale(0.75F, 0.75F, 0.75F);
			poseStack.mulPose(Axis.YP.rotationDegrees(90F));
			poseStack.translate(0.85F, 0.15F, 0.0F);
			poseStack.mulPose(Axis.ZP.rotationDegrees(-20F));

			stack.submit(poseStack, submitNodeCollector, packedLight, OverlayTexture.NO_OVERLAY, state.outlineColor);

			poseStack.popPose();
		}
	}
}
