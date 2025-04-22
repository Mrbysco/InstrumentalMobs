package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mrbysco.instrumentalmobs.client.render.state.DrumRenderState;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class DrumLayer<S extends DrumRenderState, M extends ZombieModel<S> & ArmedModel> extends RenderLayer<S, M> {

	public DrumLayer(RenderLayerParent<S, M> layerParent) {
		super(layerParent);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, S state, float yRot, float xRot) {
		ItemStackRenderState stack = state.chestEquipment;
		if (!stack.isEmpty()) {
			poseStack.pushPose();

			poseStack.translate(0.0F, 0.6F, -0.55F);
			poseStack.scale(0.75F, 0.75F, 0.75F);
			if (state.isBaby) {
				float f = 0.5F;
				poseStack.translate(0.0D, 0.75D, 0.0D);
				poseStack.scale(f, f, f);
			}
			poseStack.mulPose(Axis.XP.rotationDegrees(-10F));

			stack.render(poseStack, bufferSource, packedLight, OverlayTexture.NO_OVERLAY);

			poseStack.popPose();
		}
	}
}
