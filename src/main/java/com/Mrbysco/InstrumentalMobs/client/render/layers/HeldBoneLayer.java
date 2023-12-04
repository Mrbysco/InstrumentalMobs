package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mrbysco.instrumentalmobs.entities.XylophoneSkeleton;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3f;

public class HeldBoneLayer<T extends LivingEntity, M extends EntityModel<T> & ArmedModel> extends RenderLayer<T, M> {
	private final ItemInHandRenderer itemInHandRenderer;

	public HeldBoneLayer(RenderLayerParent<T, M> layerParent, ItemInHandRenderer itemInHandRenderer) {
		super(layerParent);
		this.itemInHandRenderer = itemInHandRenderer;
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		boolean flag = livingEntity.getMainArm() == HumanoidArm.RIGHT;
		ItemStack itemstack = flag ? livingEntity.getOffhandItem() : livingEntity.getMainHandItem();
		ItemStack itemstack1 = flag ? livingEntity.getMainHandItem() : livingEntity.getOffhandItem();

		if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
			poseStack.pushPose();

			if (livingEntity.isBaby()) {
				float f = 0.5F;
				poseStack.translate(0.0F, 0.75F, 0.0F);
				poseStack.scale(f, f, f);
			}

			this.renderHeldItem(livingEntity, itemstack1, ItemDisplayContext.THIRD_PERSON_LEFT_HAND, HumanoidArm.LEFT, poseStack, bufferIn, packedLightIn);
			this.renderHeldItem(livingEntity, itemstack, ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, HumanoidArm.RIGHT, poseStack, bufferIn, packedLightIn);
			poseStack.popPose();
		}
	}

	private void renderHeldItem(LivingEntity livingBase, ItemStack stack, ItemDisplayContext displayContext,
								HumanoidArm arm, PoseStack poseStack, MultiBufferSource bufferSource, int packedLightIn) {
		if (!stack.isEmpty()) {
			poseStack.pushPose();

			if (livingBase.isShiftKeyDown()) {
				poseStack.translate(0.0F, 0.2F, 0.0F);
			}

			this.getParentModel().translateToHand(arm, poseStack);

			poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
			poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
			boolean flag = arm == HumanoidArm.LEFT;
			if (livingBase instanceof XylophoneSkeleton skeletal) {
				boolean flag2 = skeletal.isPlayingInstrument();
				if (flag2) {
					poseStack.scale(0.75F, 0.75F, 0.75F);
					poseStack.translate(0.0F, -0.225F, -0.5F);
					poseStack.mulPose(Axis.of(new Vector3f(1.0F, flag ? -0.2F : 0.2F, flag ? -0.2F : 0.2F)).rotationDegrees(45.0F));
				}
			}
			poseStack.translate((double) ((float) (flag ? -1 : 1) / 16.0F), 0.125D, -0.625D);
			itemInHandRenderer.renderItem(livingBase, stack, displayContext, flag, poseStack, bufferSource, packedLightIn);
			poseStack.popPose();
		}
	}
}