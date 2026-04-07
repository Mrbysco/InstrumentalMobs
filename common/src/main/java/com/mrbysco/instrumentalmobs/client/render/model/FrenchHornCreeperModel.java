package com.mrbysco.instrumentalmobs.client.render.model;

import com.mrbysco.instrumentalmobs.client.render.state.FrenchRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class FrenchHornCreeperModel extends EntityModel<FrenchRenderState> implements HeadedModel{
	public final ModelPart head;
	private final ModelPart rightHindLeg;
	private final ModelPart leftHindLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart leftFrontLeg;
	private static final int Y_OFFSET = 6;

	public FrenchHornCreeperModel(ModelPart root) {
		super(root);
		this.head = root.getChild("head");
		this.leftHindLeg = root.getChild("right_hind_leg");
		this.rightHindLeg = root.getChild("left_hind_leg");
		this.leftFrontLeg = root.getChild("right_front_leg");
		this.rightFrontLeg = root.getChild("left_front_leg");
	}

	@Override
	public void setupAnim(@NotNull FrenchRenderState state) {
		super.setupAnim(state);
		this.head.yRot = state.yRot * ((float)Math.PI / 180F);
		this.head.xRot = state.xRot * ((float)Math.PI / 180F);
		float $$1 = state.walkAnimationSpeed;
		float $$2 = state.walkAnimationPos;
		this.rightHindLeg.xRot = Mth.cos($$2 * 0.6662F) * 1.4F * $$1;
		this.leftHindLeg.xRot = Mth.cos($$2 * 0.6662F + (float)Math.PI) * 1.4F * $$1;
		this.rightFrontLeg.xRot = Mth.cos($$2 * 0.6662F + (float)Math.PI) * 1.4F * $$1;
		this.leftFrontLeg.xRot = Mth.cos($$2 * 0.6662F) * 1.4F * $$1;
	}


	@NotNull
	@Override
	public ModelPart getHead() {
		return this.head;
	}
}
