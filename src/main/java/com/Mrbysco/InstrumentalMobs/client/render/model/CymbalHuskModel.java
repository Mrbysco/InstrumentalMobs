package com.mrbysco.instrumentalmobs.client.render.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mrbysco.instrumentalmobs.entities.CymbalHuskEntity;
import net.minecraft.client.renderer.entity.model.ZombieModel;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class CymbalHuskModel<T extends CymbalHuskEntity>  extends ZombieModel<T> {
    public CymbalHuskModel() {
        this(0.0F, false);
    }

    public CymbalHuskModel(float modelSize, boolean p_i1168_2_) {
        super(modelSize, 0.0F, 64, p_i1168_2_ ? 32 : 64);
    }

    @Override
    public void setupAnim(CymbalHuskEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim((T) entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        boolean flag2 = entityIn.isClapping();

        //clap?
        float f3 = (flag2 ? ((MathHelper.cos(ageInTicks * 0.75F)) * 0.75F) : 0.0F) * 0.8F;
        this.rightArm.yRot = f3;
        this.leftArm.yRot += -f3;
    }

    @Override
    public void translateToHand(HandSide sideIn, MatrixStack matrixStackIn) {
        boolean flag = sideIn == HandSide.LEFT;
        matrixStackIn.translate(flag ? 0.15D : -0.15D, 0.25D, 0.075D);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(flag ? 25F : -25F));
        super.translateToHand(sideIn, matrixStackIn);
    }
}