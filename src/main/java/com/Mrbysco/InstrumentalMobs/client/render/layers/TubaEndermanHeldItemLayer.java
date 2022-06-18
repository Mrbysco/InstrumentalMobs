package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.item.ItemStack;

public class TubaEndermanHeldItemLayer<T extends EnderMan, M extends EntityModel<T> & HeadedModel> extends RenderLayer<T, M> {
	private final ItemInHandRenderer itemInHandRenderer;

	public TubaEndermanHeldItemLayer(RenderLayerParent<T, M> layerParent, ItemInHandRenderer itemInHandRenderer) {
		super(layerParent);
		this.itemInHandRenderer = itemInHandRenderer;
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		ItemStack stack = entitylivingbaseIn.getItemBySlot(EquipmentSlot.MAINHAND);
		if (!stack.isEmpty()) {
			poseStack.pushPose();
			this.getParentModel().getHead().translateAndRotate(poseStack);

			poseStack.translate(0.0F, -0.73F, -0.775F);
			poseStack.mulPose(Vector3f.ZP.rotationDegrees(-90F));
			poseStack.mulPose(Vector3f.XP.rotationDegrees(90F));
			poseStack.mulPose(Vector3f.ZP.rotationDegrees(-10F));

			poseStack.translate(-0.8F, 0.0F, 0.0F);

			itemInHandRenderer.renderItem(entitylivingbaseIn, stack, ItemTransforms.TransformType.NONE, false, poseStack, bufferSource, packedLightIn);
			poseStack.popPose();
		}
	}
}