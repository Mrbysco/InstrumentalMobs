package com.mrbysco.instrumentalmobs.client.render.model;

import com.mrbysco.instrumentalmobs.entities.TubaEndermanEntity;
import net.minecraft.client.renderer.entity.model.EndermanModel;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.model.ModelRenderer;

public class TubaEndermanModel<T extends TubaEndermanEntity> extends EndermanModel<T> implements IHasHead {

    public TubaEndermanModel(float scale) {
        super(scale);
    }

    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.head.visible = true;
        float f = -14.0F;
        this.body.xRot = 0.0F;
        this.body.y = -14.0F;
        this.body.z = -0.0F;
        this.rightLeg.xRot -= 0.0F;
        this.leftLeg.xRot -= 0.0F;
        this.rightArm.xRot = (float)((double)this.rightArm.xRot * 0.5D);
        this.leftArm.xRot = (float)((double)this.leftArm.xRot * 0.5D);
        this.rightLeg.xRot = (float)((double)this.rightLeg.xRot * 0.5D);
        this.leftLeg.xRot = (float)((double)this.leftLeg.xRot * 0.5D);
        float f1 = 0.4F;
        if (this.rightArm.xRot > 0.4F) {
            this.rightArm.xRot = 0.4F;
        }

        if (this.leftArm.xRot > 0.4F) {
            this.leftArm.xRot = 0.4F;
        }

        if (this.rightArm.xRot < -0.4F) {
            this.rightArm.xRot = -0.4F;
        }

        if (this.leftArm.xRot < -0.4F) {
            this.leftArm.xRot = -0.4F;
        }

        if (this.rightLeg.xRot > 0.4F) {
            this.rightLeg.xRot = 0.4F;
        }

        if (this.leftLeg.xRot > 0.4F) {
            this.leftLeg.xRot = 0.4F;
        }

        if (this.rightLeg.xRot < -0.4F) {
            this.rightLeg.xRot = -0.4F;
        }

        if (this.leftLeg.xRot < -0.4F) {
            this.leftLeg.xRot = -0.4F;
        }

        if (this.carrying) {
            this.rightArm.xRot = -0.5F;
            this.leftArm.xRot = -0.5F;
            this.rightArm.zRot = 0.05F;
            this.leftArm.zRot = -0.05F;
        }

        this.rightArm.z = 0.0F;
        this.leftArm.z = 0.0F;
        this.rightLeg.z = 0.0F;
        this.leftLeg.z = 0.0F;
        this.rightLeg.y = -5.0F;
        this.leftLeg.y = -5.0F;
        this.head.z = -0.0F;
        this.head.y = -13.0F;
        this.hat.x = this.head.x;
        this.hat.y = this.head.y;
        this.hat.z = this.head.z;
        this.hat.xRot = this.head.xRot;
        this.hat.yRot = this.head.yRot;
        this.hat.zRot = this.head.zRot;
        if (this.creepy) {
            float f2 = 1.0F;
            this.head.y -= 5.0F;
        }

        float f3 = -14.0F;
        this.rightArm.setPos(-5.0F, -12.0F, 0.0F);
        this.leftArm.setPos(5.0F, -12.0F, 0.0F);
    }

    @Override
    public ModelRenderer getHead() {
        return this.head;
    }
}