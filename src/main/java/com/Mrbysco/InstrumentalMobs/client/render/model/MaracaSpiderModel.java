package com.mrbysco.instrumentalmobs.client.render.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.mrbysco.instrumentalmobs.entities.MaracaSpiderEntity;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.HumanoidArm;

public class MaracaSpiderModel<T extends MaracaSpiderEntity> extends SpiderModel<T> implements ArmedModel {

	public MaracaSpiderModel(ModelPart part) {
		super(part);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		if(entityIn.isAttacking() && entityIn.level.random.nextFloat() > 0.5F) {
			float randAngle = (float)entityIn.level.random.nextInt(45);
			this.rightFrontLeg.yRot += randAngle;
			this.leftFrontLeg.yRot += randAngle;
		}
	}

	protected ModelPart getLegForSide(HumanoidArm side) {
		return side == HumanoidArm.LEFT ? this.rightFrontLeg : this.leftFrontLeg;
	}

	@Override
	public void translateToHand(HumanoidArm sideIn, PoseStack matrixStackIn) {
		this.getLegForSide(sideIn).translateAndRotate(matrixStackIn);
		matrixStackIn.mulPose(Vector3f.XP.rotation(-90.0F));
		matrixStackIn.mulPose(Vector3f.YP.rotation(180.0F));
		matrixStackIn.mulPose(Vector3f.XP.rotation(90.0F));
		boolean flag = sideIn == HumanoidArm.LEFT;

		matrixStackIn.mulPose(new Vector3f(0.0F, flag ? -0.23F : 0.23F, flag ? -0.23F : 0.23F).rotation(90F));
		matrixStackIn.translate((float)(flag ? 0.05F : -0.7F), flag ? -0.00F : -1F, flag ? -0.25F : 0.35F);
	}
}