package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mrbysco.instrumentalmobs.client.render.state.DrumRenderState;
import net.minecraft.client.model.monster.zombie.ZombieModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class DrumLayer extends RenderLayer<DrumRenderState, ZombieModel<DrumRenderState>> {

	public DrumLayer(RenderLayerParent<DrumRenderState, ZombieModel<DrumRenderState>> layerParent) {
		super(layerParent);
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, DrumRenderState state, float xRot, float yRot) {
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

			stack.submit(poseStack, submitNodeCollector, packedLight, OverlayTexture.NO_OVERLAY, state.outlineColor);

			poseStack.popPose();
		}
	}
}
