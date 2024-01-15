package com.mrbysco.instrumentalmobs.client.render.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mrbysco.instrumentalmobs.entities.MaracaSpider;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.HumanoidArm;
import org.joml.Vector3f;

public class MaracaSpiderModel<T extends MaracaSpider> extends SpiderModel<T> implements ArmedModel {

	public MaracaSpiderModel(ModelPart part) {
		super(part);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		if (entityIn.isAttacking() && entityIn.getRandom().nextFloat() > 0.5F) {
			float randAngle = (float) entityIn.getRandom().nextInt(45);
			this.rightFrontLeg.yRot += randAngle;
			this.leftFrontLeg.yRot += randAngle;
		}
	}

	protected ModelPart getLegForSide(HumanoidArm side) {
		return side == HumanoidArm.LEFT ? this.rightFrontLeg : this.leftFrontLeg;
	}

	@Override
	public void translateToHand(HumanoidArm arm, PoseStack poseStack) {
		this.getLegForSide(arm).translateAndRotate(poseStack);
		poseStack.mulPose(Axis.XP.rotation(-90.0F));
		poseStack.mulPose(Axis.YP.rotation(180.0F));
		poseStack.mulPose(Axis.XP.rotation(90.0F));
		boolean flag = arm == HumanoidArm.LEFT;

		poseStack.mulPose(Axis.of(new Vector3f(0.0F, flag ? -0.23F : 0.23F, flag ? -0.23F : 0.23F)).rotationDegrees(90F));

		poseStack.translate((float) (flag ? 0.315F : -0.6125F), flag ? -0.125F : -1.3125F, flag ? -0.6875F : 0.425F);
	}
}