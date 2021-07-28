package com.mrbysco.instrumentalmobs.client.render.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mrbysco.instrumentalmobs.entities.MaracaSpiderEntity;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class MaracaSpiderModel<T extends MaracaSpiderEntity> extends SegmentedModel<T> implements IHasArm {
    private final ModelRenderer spiderHead;
    private final ModelRenderer spiderNeck;
    private final ModelRenderer spiderBody;
    private final ModelRenderer spiderLeg1;
    private final ModelRenderer spiderLeg2;
    private final ModelRenderer spiderLeg3;
    private final ModelRenderer spiderLeg4;
    private final ModelRenderer spiderLeg5;
    private final ModelRenderer spiderLeg6;
    private final ModelRenderer spiderLeg7;
    private final ModelRenderer spiderLeg8;

    public MaracaSpiderModel() {
        float f = 0.0F;
        int i = 15;
        this.spiderHead = new ModelRenderer(this, 32, 4);
        this.spiderHead.addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, 0.0F);
        this.spiderHead.setPos(0.0F, 15.0F, -3.0F);
        this.spiderNeck = new ModelRenderer(this, 0, 0);
        this.spiderNeck.addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F);
        this.spiderNeck.setPos(0.0F, 15.0F, 0.0F);
        this.spiderBody = new ModelRenderer(this, 0, 12);
        this.spiderBody.addBox(-5.0F, -4.0F, -6.0F, 10.0F, 8.0F, 12.0F, 0.0F);
        this.spiderBody.setPos(0.0F, 15.0F, 9.0F);
        this.spiderLeg1 = new ModelRenderer(this, 18, 0);
        this.spiderLeg1.addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F);
        this.spiderLeg1.setPos(-4.0F, 15.0F, 2.0F);
        this.spiderLeg2 = new ModelRenderer(this, 18, 0);
        this.spiderLeg2.addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F);
        this.spiderLeg2.setPos(4.0F, 15.0F, 2.0F);
        this.spiderLeg3 = new ModelRenderer(this, 18, 0);
        this.spiderLeg3.addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F);
        this.spiderLeg3.setPos(-4.0F, 15.0F, 1.0F);
        this.spiderLeg4 = new ModelRenderer(this, 18, 0);
        this.spiderLeg4.addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F);
        this.spiderLeg4.setPos(4.0F, 15.0F, 1.0F);
        this.spiderLeg5 = new ModelRenderer(this, 18, 0);
        this.spiderLeg5.addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F);
        this.spiderLeg5.setPos(-4.0F, 15.0F, 0.0F);
        this.spiderLeg6 = new ModelRenderer(this, 18, 0);
        this.spiderLeg6.addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F);
        this.spiderLeg6.setPos(4.0F, 15.0F, 0.0F);
        this.spiderLeg7 = new ModelRenderer(this, 18, 0);
        this.spiderLeg7.addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F);
        this.spiderLeg7.setPos(-4.0F, 15.0F, -1.0F);
        this.spiderLeg8 = new ModelRenderer(this, 18, 0);
        this.spiderLeg8.addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F);
        this.spiderLeg8.setPos(4.0F, 15.0F, -1.0F);
    }

    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(this.spiderHead, this.spiderNeck, this.spiderBody, this.spiderLeg1, this.spiderLeg2, this.spiderLeg3, this.spiderLeg4, this.spiderLeg5, this.spiderLeg6, this.spiderLeg7, this.spiderLeg8);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.spiderHead.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.spiderHead.xRot = headPitch * ((float)Math.PI / 180F);
        float f = ((float)Math.PI / 4F);
        this.spiderLeg1.zRot = (-(float)Math.PI / 4F);
        this.spiderLeg2.zRot = ((float)Math.PI / 4F);
        this.spiderLeg3.zRot = -0.58119464F;
        this.spiderLeg4.zRot = 0.58119464F;
        this.spiderLeg5.zRot = -0.58119464F;
        this.spiderLeg6.zRot = 0.58119464F;
        this.spiderLeg7.zRot = (-(float)Math.PI / 4F);
        this.spiderLeg8.zRot = ((float)Math.PI / 4F);
        float f1 = -0.0F;
        float f2 = ((float)Math.PI / 8F);
        this.spiderLeg1.yRot = ((float)Math.PI / 4F);
        this.spiderLeg2.yRot = (-(float)Math.PI / 4F);
        this.spiderLeg3.yRot = ((float)Math.PI / 8F);
        this.spiderLeg4.yRot = (-(float)Math.PI / 8F);
        this.spiderLeg5.yRot = (-(float)Math.PI / 8F);
        this.spiderLeg6.yRot = ((float)Math.PI / 8F);
        this.spiderLeg7.yRot = (-(float)Math.PI / 4F);
        this.spiderLeg8.yRot = ((float)Math.PI / 4F);
        float f3 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
        float f4 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float)Math.PI) * 0.4F) * limbSwingAmount;
        float f5 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float)Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float f6 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float)Math.PI * 1.5F)) * 0.4F) * limbSwingAmount;
        float f7 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;
        float f8 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float)Math.PI) * 0.4F) * limbSwingAmount;
        float f9 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float)Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float f10 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float)Math.PI * 1.5F)) * 0.4F) * limbSwingAmount;
        this.spiderLeg1.yRot += f3;
        this.spiderLeg2.yRot += -f3;
        this.spiderLeg3.yRot += f4;
        this.spiderLeg4.yRot += -f4;
        this.spiderLeg5.yRot += f5;
        this.spiderLeg6.yRot += -f5;
        this.spiderLeg7.yRot += f6;
        this.spiderLeg8.yRot += -f6;
        this.spiderLeg1.zRot += f7;
        this.spiderLeg2.zRot += -f7;
        this.spiderLeg3.zRot += f8;
        this.spiderLeg4.zRot += -f8;
        this.spiderLeg5.zRot += f9;
        this.spiderLeg6.zRot += -f9;
        this.spiderLeg7.zRot += f10;
        this.spiderLeg8.zRot += -f10;

        if(entityIn.isAttacking() && entityIn.level.random.nextFloat() > 0.5F) {
            float randAngle = (float)entityIn.level.random.nextInt(45);
            this.spiderLeg7.yRot += randAngle;
            this.spiderLeg8.yRot += randAngle;
        }
    }

    protected ModelRenderer getLegForSide(HandSide side) {
        return side == HandSide.LEFT ? this.spiderLeg7 : this.spiderLeg8;
    }

    @Override
    public void translateToHand(HandSide sideIn, MatrixStack matrixStackIn) {
        this.getLegForSide(sideIn).translateAndRotate(matrixStackIn);
        matrixStackIn.mulPose(Vector3f.XP.rotation(-90.0F));
        matrixStackIn.mulPose(Vector3f.YP.rotation(180.0F));
        matrixStackIn.mulPose(Vector3f.XP.rotation(90.0F));
        boolean flag = sideIn == HandSide.LEFT;

        matrixStackIn.mulPose(new Vector3f(0.0F, flag ? -0.23F : 0.23F, flag ? -0.23F : 0.23F).rotation(90F));
        matrixStackIn.translate((float)(flag ? 0.05F : -0.7F), flag ? -0.00F : -1F, flag ? -0.25F : 0.35F);
    }
}