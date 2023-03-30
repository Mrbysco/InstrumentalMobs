package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class MaracasLayer<T extends Spider, M extends EntityModel<T> & ArmedModel> extends RenderLayer<T, M> {
	private final ItemInHandRenderer itemInHandRenderer;

	public MaracasLayer(RenderLayerParent<T, M> layerParent, ItemInHandRenderer itemInHandRenderer) {
		super(layerParent);
		this.itemInHandRenderer = itemInHandRenderer;
	}

	@Override
	public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		boolean flag = entitylivingbaseIn.getMainArm() == HumanoidArm.RIGHT;
		ItemStack itemstack = flag ? entitylivingbaseIn.getOffhandItem() : entitylivingbaseIn.getMainHandItem();
		ItemStack itemstack1 = flag ? entitylivingbaseIn.getMainHandItem() : entitylivingbaseIn.getOffhandItem();

		if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
			matrixStackIn.pushPose();

			this.renderHeldItem(entitylivingbaseIn, itemstack, ItemDisplayContext.THIRD_PERSON_LEFT_HAND, HumanoidArm.LEFT, matrixStackIn, bufferIn, packedLightIn);
			this.renderHeldItem(entitylivingbaseIn, itemstack1, ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, HumanoidArm.RIGHT, matrixStackIn, bufferIn, packedLightIn);
			matrixStackIn.popPose();
		}
	}

	private void renderHeldItem(Spider spiderEntity, ItemStack stack, ItemDisplayContext transformType, HumanoidArm handSide, PoseStack matrixStack, MultiBufferSource typeBuffer, int packedLightIn) {
		if (!stack.isEmpty()) {
			matrixStack.pushPose();
			this.getParentModel().translateToHand(handSide, matrixStack);
			matrixStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
			matrixStack.mulPose(Axis.YP.rotationDegrees(180.0F));
			boolean flag = handSide == HumanoidArm.LEFT;
			matrixStack.translate((double) ((float) (flag ? -1 : 1) / 16.0F), 0.125D, -0.625D);
			itemInHandRenderer.renderItem(spiderEntity, stack, transformType, flag, matrixStack, typeBuffer, packedLightIn);
			matrixStack.popPose();
		}
	}
}