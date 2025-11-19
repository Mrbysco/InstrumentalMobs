package com.mrbysco.instrumentalmobs.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mrbysco.instrumentalmobs.client.render.model.CymbalHuskModel;
import com.mrbysco.instrumentalmobs.client.render.state.CymbalRenderState;
import com.mrbysco.instrumentalmobs.entities.CymbalHusk;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class CymbalHuskRenderer extends AbstractZombieRenderer<CymbalHusk, CymbalRenderState, CymbalHuskModel<CymbalRenderState>> {
	private static final ResourceLocation HUSK_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/zombie/husk.png");

	public CymbalHuskRenderer(EntityRendererProvider.Context context) {
		super(context,
				new CymbalHuskModel<>(context.bakeLayer(ModelLayers.HUSK)),
				new CymbalHuskModel<>(context.bakeLayer(ModelLayers.HUSK_BABY)),
				ArmorModelSet.bake(ModelLayers.HUSK_ARMOR, context.getModelSet(), CymbalHuskModel::new),
				ArmorModelSet.bake(ModelLayers.HUSK_BABY_ARMOR, context.getModelSet(), CymbalHuskModel::new)
		);
	}

	@NotNull
	@Override
	public CymbalRenderState createRenderState() {
		return new CymbalRenderState();
	}

	@Override
	public void extractRenderState(@NotNull CymbalHusk husk, @NotNull CymbalRenderState state, float partialTick) {
		super.extractRenderState(husk, state, partialTick);
		state.isClapping = husk.isClapping();
	}

	@Override
	protected void scale(@NotNull CymbalRenderState state, PoseStack poseStack) {
		float size = 1.0625F;
		poseStack.scale(size, size, size);
		super.scale(state, poseStack);
	}

	@NotNull
	@Override
	public ResourceLocation getTextureLocation(@NotNull CymbalRenderState state) {
		return HUSK_LOCATION;
	}
}
