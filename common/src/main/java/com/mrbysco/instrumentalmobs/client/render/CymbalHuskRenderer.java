package com.mrbysco.instrumentalmobs.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mrbysco.instrumentalmobs.client.render.model.CymbalHuskModel;
import com.mrbysco.instrumentalmobs.entities.CymbalHusk;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;

public class CymbalHuskRenderer extends AbstractZombieRenderer<CymbalHusk, CymbalHuskModel<CymbalHusk>> {
	private static final ResourceLocation HUSK_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/zombie/husk.png");

	public CymbalHuskRenderer(EntityRendererProvider.Context context) {
		super(context, new CymbalHuskModel<>(context.bakeLayer(ModelLayers.HUSK)),
				new CymbalHuskModel<>(context.bakeLayer(ModelLayers.HUSK_INNER_ARMOR)),
				new CymbalHuskModel<>(context.bakeLayer(ModelLayers.HUSK_OUTER_ARMOR)));
	}

	@Override
	protected void scale(CymbalHusk husk, PoseStack poseStack, float tick) {
		float $$3 = 1.0625F;
		poseStack.scale(1.0625F, 1.0625F, 1.0625F);
		super.scale(husk, poseStack, tick);
	}

	public ResourceLocation getTextureLocation(Zombie $$0) {
		return HUSK_LOCATION;
	}
}
