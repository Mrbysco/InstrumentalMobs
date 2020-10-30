package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.vector.Vector3f;

public class MaracasLayer<T extends SpiderEntity, M extends EntityModel<T> & IHasArm> extends LayerRenderer<T, M> {
    public MaracasLayer(IEntityRenderer<T, M> p_i50934_1_) {
        super(p_i50934_1_);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean flag = entitylivingbaseIn.getPrimaryHand() == HandSide.RIGHT;
        ItemStack itemstack = flag ? entitylivingbaseIn.getHeldItemOffhand() : entitylivingbaseIn.getHeldItemMainhand();
        ItemStack itemstack1 = flag ? entitylivingbaseIn.getHeldItemMainhand() : entitylivingbaseIn.getHeldItemOffhand();

        if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
            matrixStackIn.push();

            this.renderHeldItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, HandSide.LEFT, matrixStackIn, bufferIn, packedLightIn);
            this.renderHeldItem(entitylivingbaseIn, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, HandSide.RIGHT, matrixStackIn, bufferIn, packedLightIn);
            matrixStackIn.pop();
        }
    }

    private void renderHeldItem(SpiderEntity spiderEntity, ItemStack stack, ItemCameraTransforms.TransformType transformType, HandSide handSide, MatrixStack matrixStack, IRenderTypeBuffer typeBuffer, int packedLightIn) {
        if (!stack.isEmpty()) {
            matrixStack.push();
            this.getEntityModel().translateHand(handSide, matrixStack);
            matrixStack.rotate(Vector3f.XP.rotationDegrees(-90.0F));
            matrixStack.rotate(Vector3f.YP.rotationDegrees(180.0F));
            boolean flag = handSide == HandSide.LEFT;
            matrixStack.translate((double)((float)(flag ? -1 : 1) / 16.0F), 0.125D, -0.625D);
            Minecraft.getInstance().getFirstPersonRenderer().renderItemSide(spiderEntity, stack, transformType, flag, matrixStack, typeBuffer, packedLightIn);
            matrixStack.pop();
        }
    }
}