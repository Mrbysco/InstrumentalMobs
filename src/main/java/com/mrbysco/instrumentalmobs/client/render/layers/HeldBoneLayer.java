package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.mrbysco.instrumentalmobs.entities.XylophoneSkeletonEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class HeldBoneLayer<T extends LivingEntity, M extends EntityModel<T> & ArmedModel> extends RenderLayer<T, M> {
    public HeldBoneLayer(RenderLayerParent<T, M> layerParent) {
        super(layerParent);
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean flag = entitylivingbaseIn.getMainArm() == HumanoidArm.RIGHT;
        ItemStack itemstack = flag ? entitylivingbaseIn.getOffhandItem() : entitylivingbaseIn.getMainHandItem();
        ItemStack itemstack1 = flag ? entitylivingbaseIn.getMainHandItem() : entitylivingbaseIn.getOffhandItem();

        if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
            matrixStackIn.pushPose();

            if (entitylivingbaseIn.isBaby()) {
                float f = 0.5F;
                matrixStackIn.translate(0.0F, 0.75F, 0.0F);
                matrixStackIn.scale(f,f,f);
            }

            this.renderHeldItem(entitylivingbaseIn, itemstack1, ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND, HumanoidArm.LEFT, matrixStackIn, bufferIn, packedLightIn);
            this.renderHeldItem(entitylivingbaseIn, itemstack, ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, HumanoidArm.RIGHT, matrixStackIn, bufferIn, packedLightIn);
            matrixStackIn.popPose();
        }
    }

    private void renderHeldItem(LivingEntity livingBase, ItemStack stack, ItemTransforms.TransformType transformType, HumanoidArm handSide, PoseStack matrixStack, MultiBufferSource typeBuffer, int packedLightIn) {
        if (!stack.isEmpty()) {
            matrixStack.pushPose();

            if (livingBase.isShiftKeyDown()) {
                matrixStack.translate(0.0F, 0.2F, 0.0F);
            }

            this.getParentModel().translateToHand(handSide, matrixStack);

            matrixStack.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
            matrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
            boolean flag = handSide == HumanoidArm.LEFT;
            if(livingBase instanceof XylophoneSkeletonEntity) {
                XylophoneSkeletonEntity skeletal = (XylophoneSkeletonEntity)livingBase;
                boolean flag2 = skeletal.isPlayingRibs();
                if(flag2) {
                    matrixStack.scale(0.75F, 0.75F, 0.75F);
                    matrixStack.translate(0.0F, -0.225F, -0.5F);
                    matrixStack.mulPose(new Vector3f(1.0F, flag ? -0.2F : 0.2F, flag ? -0.2F : 0.2F).rotationDegrees(45.0F));
                }
            }
            matrixStack.translate((double)((float)(flag ? -1 : 1) / 16.0F), 0.125D, -0.625D);
            Minecraft.getInstance().getItemInHandRenderer().renderItem(livingBase, stack, transformType, flag, matrixStack, typeBuffer, packedLightIn);
            matrixStack.popPose();
        }
    }
}