package com.mrbysco.instrumentalmobs.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mrbysco.instrumentalmobs.client.render.layers.FrenchHornCreeperCharge;
import com.mrbysco.instrumentalmobs.client.render.layers.FrenchHornLayer;
import com.mrbysco.instrumentalmobs.client.render.model.FrenchHornCreeperModel;
import com.mrbysco.instrumentalmobs.client.render.state.FrenchRenderState;
import com.mrbysco.instrumentalmobs.entities.FrenchHornCreeper;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemDisplayContext;
import org.jetbrains.annotations.NotNull;

public class FrenchHornCreeperRenderer extends MobRenderer<FrenchHornCreeper, FrenchRenderState, FrenchHornCreeperModel> {
	private static final ResourceLocation CREEPER_TEXTURES = ResourceLocation.withDefaultNamespace("textures/entity/creeper/creeper.png");

	public FrenchHornCreeperRenderer(EntityRendererProvider.Context context) {
		super(context, new FrenchHornCreeperModel(context.bakeLayer(ModelLayers.CREEPER)), 0.5F);
		this.addLayer(new FrenchHornCreeperCharge(this, context.getModelSet()));
		this.addLayer(new FrenchHornLayer<>(this));
	}

	@Override
	protected void scale(FrenchRenderState state, PoseStack poseStack) {
		float f = state.swelling;
		float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
		f = Mth.clamp(f, 0.0F, 1.0F);
		f = f * f;
		f = f * f;
		float f2 = (1.0F + f * 0.4F) * f1;
		float f3 = (1.0F + f * 0.1F) / f1;
		poseStack.scale(f2, f3, f2);
	}

	@Override
	protected float getWhiteOverlayProgress(FrenchRenderState state) {
		float f = state.swelling;
		return (int) (f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
	}

	@NotNull
	@Override
	public FrenchRenderState createRenderState() {
		return new FrenchRenderState();
	}

	@Override
	public void extractRenderState(@NotNull FrenchHornCreeper hornCreeper, @NotNull FrenchRenderState state, float partialTick) {
		super.extractRenderState(hornCreeper, state, partialTick);
		state.swelling = hornCreeper.getSwelling(partialTick);
		state.isPowered = hornCreeper.isPowered();
		itemModelResolver.updateForLiving(
				state.heldItem, hornCreeper.getItemHeldByArm(HumanoidArm.RIGHT), ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, false, hornCreeper
		);
	}

	/**
	 * Returns the location of an frenchHornCreeper's texture.
	 */
	@NotNull
	@Override
	public ResourceLocation getTextureLocation(@NotNull FrenchRenderState state) {
		return CREEPER_TEXTURES;
	}
}