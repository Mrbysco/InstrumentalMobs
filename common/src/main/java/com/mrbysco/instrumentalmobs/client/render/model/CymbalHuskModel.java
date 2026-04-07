package com.mrbysco.instrumentalmobs.client.render.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mrbysco.instrumentalmobs.client.render.state.CymbalRenderState;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.monster.zombie.ZombieModel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import org.jetbrains.annotations.NotNull;

public class CymbalHuskModel<S extends CymbalRenderState> extends ZombieModel<S> {
	public CymbalHuskModel(ModelPart part) {
		super(part);
	}

	@Override
	public void setupAnim(@NotNull S state) {
		super.setupAnim(state);

		boolean clapping = state.isClapping;
		float f3 = (clapping ? ((Mth.cos(state.ageInTicks * 0.75F)) * 0.75F) : 0.0F) * 0.8F;
		this.rightArm.yRot = f3;
		this.leftArm.yRot -= f3;
	}

	@Override
	public void translateToHand(CymbalRenderState renderState, HumanoidArm arm, PoseStack poseStack) {
		boolean flag = arm == HumanoidArm.LEFT;
		poseStack.translate(flag ? 0.15D : -0.15D, 0.25D, 0.075D);
		poseStack.mulPose(Axis.YP.rotationDegrees(flag ? 25F : -25F));
		super.translateToHand(renderState, arm, poseStack);
	}
}