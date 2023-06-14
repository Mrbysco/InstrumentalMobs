package com.mrbysco.instrumentalmobs.client.render.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mrbysco.instrumentalmobs.entities.MaracaSpiderEntity;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.HumanoidArm;
import org.joml.Vector3f;

public class MaracaSpiderModel<T extends MaracaSpiderEntity> extends SpiderModel<T> implements ArmedModel {

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
	public void translateToHand(HumanoidArm sideIn, PoseStack matrixStackIn) {
		this.getLegForSide(sideIn).translateAndRotate(matrixStackIn);
		matrixStackIn.mulPose(Axis.XP.rotation(-90.0F));
		matrixStackIn.mulPose(Axis.YP.rotation(180.0F));
		matrixStackIn.mulPose(Axis.XP.rotation(90.0F));
		boolean flag = sideIn == HumanoidArm.LEFT;

		matrixStackIn.mulPose(Axis.of(new Vector3f(0.0F, flag ? -0.23F : 0.23F, flag ? -0.23F : 0.23F)).rotationDegrees(90F));

		matrixStackIn.translate((float) (flag ? 0.05F : -0.7F), flag ? -0.00F : -1F, flag ? -0.25F : 0.35F);
	}
}