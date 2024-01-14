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
	public void render(PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn, T spider, float limbSwing,
					   float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		boolean flag = spider.getMainArm() == HumanoidArm.RIGHT;
		ItemStack stack = flag ? spider.getOffhandItem() : spider.getMainHandItem();
		ItemStack otherStack = flag ? spider.getMainHandItem() : spider.getOffhandItem();

		if (!stack.isEmpty() || !otherStack.isEmpty()) {
			poseStack.pushPose();

			this.renderHeldItem(spider, stack, ItemDisplayContext.THIRD_PERSON_LEFT_HAND, HumanoidArm.LEFT, poseStack, bufferIn, packedLightIn);
			this.renderHeldItem(spider, otherStack, ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, HumanoidArm.RIGHT, poseStack, bufferIn, packedLightIn);
			poseStack.popPose();
		}
	}

	private void renderHeldItem(Spider spider, ItemStack stack, ItemDisplayContext displayContext, HumanoidArm handSide,
								PoseStack poseStack, MultiBufferSource bufferSource, int packedLightIn) {
		if (!stack.isEmpty()) {
			poseStack.pushPose();
			this.getParentModel().translateToHand(handSide, poseStack);
			poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
			poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
			boolean flag = handSide == HumanoidArm.LEFT;
			poseStack.translate((double) ((float) (flag ? -1 : 1) / 16.0F), 0.125D, -0.625D);
			itemInHandRenderer.renderItem(spider, stack, displayContext, flag, poseStack, bufferSource, packedLightIn);
			poseStack.popPose();
		}
	}
}