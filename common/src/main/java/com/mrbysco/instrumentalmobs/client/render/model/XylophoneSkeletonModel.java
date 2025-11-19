package com.mrbysco.instrumentalmobs.client.render.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mrbysco.instrumentalmobs.client.render.state.XylophoneRenderState;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import org.jetbrains.annotations.NotNull;

public class XylophoneSkeletonModel extends SkeletonModel<XylophoneRenderState> {

	public XylophoneSkeletonModel(ModelPart part) {
		super(part);
	}

	@Override
	public void setupAnim(@NotNull XylophoneRenderState state) {
		super.setupAnim(state);
		if (state.isPlaying) {
			float f = Mth.sin(state.attackTime * (float) Math.PI);
			float f1 = Mth.sin((1.0F - (1.0F - state.attackTime) * (1.0F - state.attackTime)) * (float) Math.PI);
			this.rightArm.zRot = 0.0F;
			this.leftArm.zRot = 0.0F;
			this.rightArm.yRot = -(0.1F - f * 0.6F);
			this.leftArm.yRot = 0.1F - f * 0.6F;

			//NewStuff
			float f3 = Mth.cos(state.ageInTicks * 0.09F) * (-(float) Math.PI / 0.4F);
			this.rightArm.zRot = -f3;
			this.leftArm.zRot = f3;
			this.leftArm.xRot = 1F;
			this.rightArm.xRot = -1F;

			this.rightArm.xRot = -((float) Math.PI / 2F);
			this.leftArm.xRot = -((float) Math.PI / 2F);
			this.rightArm.xRot -= f * 1.2F - f1 * 0.4F;
			this.leftArm.xRot -= f * 1.2F - f1 * 0.4F;
			this.rightArm.zRot += Mth.cos(state.ageInTicks * 0.09F) * 0.05F + 0.05F;
			this.leftArm.zRot -= Mth.cos(state.ageInTicks * 0.09F) * 0.05F + 0.05F;
			this.rightArm.xRot += Mth.sin(state.ageInTicks * 0.067F) * 0.05F;
			this.leftArm.xRot -= Mth.sin(state.ageInTicks * 0.067F) * 0.05F;
		}
	}

	@Override
	public void translateToHand(XylophoneRenderState renderState, HumanoidArm arm, PoseStack poseStack) {
		float f = arm == HumanoidArm.RIGHT ? 1.0F : -1.0F;
		ModelPart modelPart = this.getArm(arm);
		modelPart.x += f;
		modelPart.translateAndRotate(poseStack);
		modelPart.x -= f;
	}
}