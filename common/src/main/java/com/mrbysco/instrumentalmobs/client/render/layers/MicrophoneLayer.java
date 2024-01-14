package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mrbysco.instrumentalmobs.entities.MicrophoneGhast;
import net.minecraft.client.model.GhastModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class MicrophoneLayer<T extends Ghast, M extends GhastModel<T>> extends RenderLayer<T, M> {
	private final ItemInHandRenderer itemInHandRenderer;

	public MicrophoneLayer(RenderLayerParent<T, M> layerParent, ItemInHandRenderer itemInHandRenderer) {
		super(layerParent);
		this.itemInHandRenderer = itemInHandRenderer;
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLightIn, T ghast,
					   float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		ItemStack stack = ghast.getItemBySlot(EquipmentSlot.HEAD);
		if (!stack.isEmpty() && ghast instanceof MicrophoneGhast microphoneGhast && !microphoneGhast.isSinging()) {
			poseStack.pushPose();

			poseStack.scale(0.25F, 0.25F, 0.25F);
			poseStack.mulPose(Axis.XP.rotationDegrees(-180F));
			poseStack.translate(-0.5F, -6F, 2F);
			itemInHandRenderer.renderItem(ghast, stack, ItemDisplayContext.NONE, false, poseStack, bufferSource, packedLightIn);
			poseStack.popPose();
		}
	}
}
