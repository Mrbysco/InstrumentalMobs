package com.mrbysco.instrumentalmobs.client.render.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.mrbysco.instrumentalmobs.entities.CymbalHuskEntity;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

public class CymbalHuskModel<T extends CymbalHuskEntity> extends ZombieModel<T> {
	public CymbalHuskModel(ModelPart part) {
		super(part);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		boolean flag2 = entityIn.isClapping();

		//clap?
		float f3 = (flag2 ? ((Mth.cos(ageInTicks * 0.75F)) * 0.75F) : 0.0F) * 0.8F;
		this.rightArm.yRot = f3;
		this.leftArm.yRot += -f3;
	}

	@Override
	public void translateToHand(HumanoidArm sideIn, PoseStack matrixStackIn) {
		boolean flag = sideIn == HumanoidArm.LEFT;
		matrixStackIn.translate(flag ? 0.15D : -0.15D, 0.25D, 0.075D);
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(flag ? 25F : -25F));
		super.translateToHand(sideIn, matrixStackIn);
	}
}