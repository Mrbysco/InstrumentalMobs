package com.mrbysco.instrumentalmobs.render.model;

import net.minecraft.client.model.ModelEnderman;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelTubaEnderman extends ModelEnderman {

    public ModelTubaEnderman(float scale) {
        super(scale);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        this.bipedHead.showModel = true;
        float f = -14.0F;
        this.bipedBody.rotateAngleX = 0.0F;
        this.bipedBody.rotationPointY = -14.0F;
        this.bipedBody.rotationPointZ = -0.0F;
        this.bipedRightLeg.rotateAngleX -= 0.0F;
        this.bipedLeftLeg.rotateAngleX -= 0.0F;
        this.bipedRightArm.rotateAngleX = (float)((double)this.bipedRightArm.rotateAngleX * 0.5D);
        this.bipedLeftArm.rotateAngleX = (float)((double)this.bipedLeftArm.rotateAngleX * 0.5D);
        this.bipedRightLeg.rotateAngleX = (float)((double)this.bipedRightLeg.rotateAngleX * 0.5D);
        this.bipedLeftLeg.rotateAngleX = (float)((double)this.bipedLeftLeg.rotateAngleX * 0.5D);
        float f1 = 0.4F;

        if (this.bipedRightArm.rotateAngleX > 0.4F)
        {
            this.bipedRightArm.rotateAngleX = 0.4F;
        }

        if (this.bipedLeftArm.rotateAngleX > 0.4F)
        {
            this.bipedLeftArm.rotateAngleX = 0.4F;
        }

        if (this.bipedRightArm.rotateAngleX < -0.4F)
        {
            this.bipedRightArm.rotateAngleX = -0.4F;
        }

        if (this.bipedLeftArm.rotateAngleX < -0.4F)
        {
            this.bipedLeftArm.rotateAngleX = -0.4F;
        }

        if (this.bipedRightLeg.rotateAngleX > 0.4F)
        {
            this.bipedRightLeg.rotateAngleX = 0.4F;
        }

        if (this.bipedLeftLeg.rotateAngleX > 0.4F)
        {
            this.bipedLeftLeg.rotateAngleX = 0.4F;
        }

        if (this.bipedRightLeg.rotateAngleX < -0.4F)
        {
            this.bipedRightLeg.rotateAngleX = -0.4F;
        }

        if (this.bipedLeftLeg.rotateAngleX < -0.4F)
        {
            this.bipedLeftLeg.rotateAngleX = -0.4F;
        }

        if (this.isCarrying)
        {
            this.bipedRightArm.rotateAngleX = -1.5F;
            this.bipedLeftArm.rotateAngleX = -1.5F;
            this.bipedRightArm.rotateAngleZ = -2.05F;
            this.bipedLeftArm.rotateAngleZ = 2.05F;
        }

        this.bipedRightArm.rotationPointZ = 0.0F;
        this.bipedLeftArm.rotationPointZ = 0.0F;
        this.bipedRightLeg.rotationPointZ = 0.0F;
        this.bipedLeftLeg.rotationPointZ = 0.0F;
        this.bipedRightLeg.rotationPointY = -5.0F;
        this.bipedLeftLeg.rotationPointY = -5.0F;
        this.bipedHead.rotationPointZ = -0.0F;
        this.bipedHead.rotationPointY = -13.0F;
        this.bipedHeadwear.rotationPointX = this.bipedHead.rotationPointX;
        this.bipedHeadwear.rotationPointY = this.bipedHead.rotationPointY;
        this.bipedHeadwear.rotationPointZ = this.bipedHead.rotationPointZ;
        this.bipedHeadwear.rotateAngleX = this.bipedHead.rotateAngleX;
        this.bipedHeadwear.rotateAngleY = this.bipedHead.rotateAngleY;
        this.bipedHeadwear.rotateAngleZ = this.bipedHead.rotateAngleZ;

        if (this.isAttacking)
        {
            float f2 = 1.0F;
            this.bipedHead.rotationPointY -= 5.0F;
        }
    }
}