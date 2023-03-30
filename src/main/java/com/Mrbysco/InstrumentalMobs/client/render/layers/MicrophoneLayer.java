package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mrbysco.instrumentalmobs.entities.MicrophoneGhastEntity;
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
	public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, T ghast, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		ItemStack stack = ghast.getItemBySlot(EquipmentSlot.HEAD);
		if (!stack.isEmpty() && ghast instanceof MicrophoneGhastEntity microphoneGhast && !microphoneGhast.isSinging()) {
			matrixStackIn.pushPose();

			matrixStackIn.scale(0.25F, 0.25F, 0.25F);
			matrixStackIn.mulPose(Axis.XP.rotationDegrees(-180F));
			matrixStackIn.translate(-0.5F, -6F, 2F);
			itemInHandRenderer.renderItem(ghast, stack, ItemDisplayContext.NONE, false, matrixStackIn, bufferIn, packedLightIn);
			matrixStackIn.popPose();
		}
	}
}
