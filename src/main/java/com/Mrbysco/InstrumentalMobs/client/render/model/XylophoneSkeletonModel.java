package com.mrbysco.instrumentalmobs.client.render.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mrbysco.instrumentalmobs.entities.XylophoneSkeletonEntity;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;

public class XylophoneSkeletonModel<T extends XylophoneSkeletonEntity> extends BipedModel<T> {
    public XylophoneSkeletonModel() {
        this(0.0F, false);
    }

    public XylophoneSkeletonModel(float modelSize, boolean p_i46303_2_) {
        super(modelSize, 0.0F, 64, 32);
        if (!p_i46303_2_) {
            this.rightArm = new ModelRenderer(this, 40, 16);
            this.rightArm.addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
            this.rightArm.setPos(-5.0F, 2.0F, 0.0F);
            this.leftArm = new ModelRenderer(this, 40, 16);
            this.leftArm.mirror = true;
            this.leftArm.addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
            this.leftArm.setPos(5.0F, 2.0F, 0.0F);
            this.rightLeg = new ModelRenderer(this, 0, 16);
            this.rightLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
            this.rightLeg.setPos(-2.0F, 12.0F, 0.0F);
            this.leftLeg = new ModelRenderer(this, 0, 16);
            this.leftLeg.mirror = true;
            this.leftLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
            this.leftLeg.setPos(2.0F, 12.0F, 0.0F);
        }
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        ItemStack stack = entityIn.getMainHandItem();

        if (entityIn.isPlayingRibs() && stack.getItem() == Items.BONE) {
            float f = MathHelper.sin(this.attackTime * (float)Math.PI);
            float f1 = MathHelper.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);
            this.rightArm.zRot = 0.0F;
            this.leftArm.zRot = 0.0F;
            this.rightArm.yRot = -(0.1F - f * 0.6F);
            this.leftArm.yRot = 0.1F - f * 0.6F;

            //NewStuff
            float f3 = MathHelper.cos(ageInTicks * 0.09F) * (-(float)Math.PI / 0.4F);
            this.rightArm.zRot = -f3;
            this.leftArm.zRot = f3;
            this.leftArm.xRot = 1F;
            this.rightArm.xRot = -1F;

            this.rightArm.xRot = -((float)Math.PI / 2F);
            this.leftArm.xRot = -((float)Math.PI / 2F);
            this.rightArm.xRot -= f * 1.2F - f1 * 0.4F;
            this.leftArm.xRot -= f * 1.2F - f1 * 0.4F;
            this.rightArm.zRot += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
            this.leftArm.zRot -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
            this.rightArm.xRot += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
            this.leftArm.xRot -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        }
    }

    public void translateToHand(HandSide sideIn, MatrixStack matrixStackIn) {
        float f = sideIn == HandSide.RIGHT ? 1.0F : -1.0F;
        ModelRenderer modelrenderer = this.getArm(sideIn);
        modelrenderer.x += f;
        modelrenderer.translateAndRotate(matrixStackIn);
        modelrenderer.x -= f;
    }
}