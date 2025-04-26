package com.mrbysco.instrumentalmobs.client.render.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mrbysco.instrumentalmobs.client.render.state.MaracaRenderState;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import java.util.Random;

public class MaracaSpiderModel extends EntityModel<MaracaRenderState> implements ArmedModel {
	private Random random = new Random();
	private final ModelPart head;
	private final ModelPart rightHindLeg;
	private final ModelPart leftHindLeg;
	private final ModelPart rightMiddleHindLeg;
	private final ModelPart leftMiddleHindLeg;
	private final ModelPart rightMiddleFrontLeg;
	private final ModelPart leftMiddleFrontLeg;
	public final ModelPart rightFrontLeg;
	public final ModelPart leftFrontLeg;

	public MaracaSpiderModel(ModelPart part) {
		super(part);
		this.head = part.getChild("head");
		this.rightHindLeg = part.getChild("right_hind_leg");
		this.leftHindLeg = part.getChild("left_hind_leg");
		this.rightMiddleHindLeg = part.getChild("right_middle_hind_leg");
		this.leftMiddleHindLeg = part.getChild("left_middle_hind_leg");
		this.rightMiddleFrontLeg = part.getChild("right_middle_front_leg");
		this.leftMiddleFrontLeg = part.getChild("left_middle_front_leg");
		this.rightFrontLeg = part.getChild("right_front_leg");
		this.leftFrontLeg = part.getChild("left_front_leg");
	}

	@Override
	public void setupAnim(@NotNull MaracaRenderState state) {
		super.setupAnim(state);
		this.head.yRot = state.yRot * (float) (Math.PI / 180.0);
		this.head.xRot = state.xRot * (float) (Math.PI / 180.0);
		float f = state.walkAnimationPos * 0.6662F;
		float f1 = state.walkAnimationSpeed;
		float f2 = -(Mth.cos(f * 2.0F + 0.0F) * 0.4F) * f1;
		float f3 = -(Mth.cos(f * 2.0F + (float) Math.PI) * 0.4F) * f1;
		float f4 = -(Mth.cos(f * 2.0F + (float) (Math.PI / 2)) * 0.4F) * f1;
		float f5 = -(Mth.cos(f * 2.0F + (float) (Math.PI * 3.0 / 2.0)) * 0.4F) * f1;
		float f6 = Math.abs(Mth.sin(f + 0.0F) * 0.4F) * f1;
		float f7 = Math.abs(Mth.sin(f + (float) Math.PI) * 0.4F) * f1;
		float f8 = Math.abs(Mth.sin(f + (float) (Math.PI / 2)) * 0.4F) * f1;
		float f9 = Math.abs(Mth.sin(f + (float) (Math.PI * 3.0 / 2.0)) * 0.4F) * f1;
		this.rightHindLeg.yRot += f2;
		this.leftHindLeg.yRot -= f2;
		this.rightMiddleHindLeg.yRot += f3;
		this.leftMiddleHindLeg.yRot -= f3;
		this.rightMiddleFrontLeg.yRot += f4;
		this.leftMiddleFrontLeg.yRot -= f4;
		this.rightFrontLeg.yRot += f5;
		this.leftFrontLeg.yRot -= f5;
		this.rightHindLeg.zRot += f6;
		this.leftHindLeg.zRot -= f6;
		this.rightMiddleHindLeg.zRot += f7;
		this.leftMiddleHindLeg.zRot -= f7;
		this.rightMiddleFrontLeg.zRot += f8;
		this.leftMiddleFrontLeg.zRot -= f8;
		this.rightFrontLeg.zRot += f9;
		this.leftFrontLeg.zRot -= f9;
		if (state.isAttacking && random.nextFloat() > 0.5F) {
			float randAngle = (float) random.nextInt(45);
			this.rightFrontLeg.yRot += randAngle;
			this.leftFrontLeg.yRot += randAngle;
		}
	}

	protected ModelPart getLegForSide(HumanoidArm side) {
		return side == HumanoidArm.LEFT ? this.rightFrontLeg : this.leftFrontLeg;
	}

	@Override
	public void translateToHand(@NotNull HumanoidArm arm, @NotNull PoseStack poseStack) {
		this.getLegForSide(arm).translateAndRotate(poseStack);
		poseStack.mulPose(Axis.XP.rotation(-90.0F));
		poseStack.mulPose(Axis.YP.rotation(180.0F));
		poseStack.mulPose(Axis.XP.rotation(90.0F));
		boolean flag = arm == HumanoidArm.LEFT;

		poseStack.mulPose(Axis.of(new Vector3f(0.0F, flag ? -0.23F : 0.23F, flag ? -0.23F : 0.23F)).rotationDegrees(90F));

		poseStack.translate((float) (flag ? 0.315F : -0.6125F), flag ? -0.125F : -1.3125F, flag ? -0.6875F : 0.425F);
	}
}