package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mrbysco.instrumentalmobs.client.render.model.TubaEndermanModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.EndermanRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class TubaEndermanHeldItemLayer extends RenderLayer<EndermanRenderState, TubaEndermanModel<EndermanRenderState>> {

	public TubaEndermanHeldItemLayer(RenderLayerParent<EndermanRenderState, TubaEndermanModel<EndermanRenderState>> layerParent) {
		super(layerParent);
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, EndermanRenderState state, float xRot, float yRot) {
		ItemStackRenderState stack = state.getMainHandItemState();
		if (!stack.isEmpty()) {
			poseStack.pushPose();
			this.getParentModel().getHead().translateAndRotate(poseStack);

			poseStack.translate(0.0F, -0.73F, -0.775F);
			poseStack.mulPose(Axis.ZP.rotationDegrees(-90F));
			poseStack.mulPose(Axis.XP.rotationDegrees(90F));
			poseStack.mulPose(Axis.ZP.rotationDegrees(-10F));

			poseStack.translate(-0.8F, 0.0F, 0.0F);

			stack.submit(poseStack, submitNodeCollector, packedLight, OverlayTexture.NO_OVERLAY, state.outlineColor);
			poseStack.popPose();
		}
	}
}