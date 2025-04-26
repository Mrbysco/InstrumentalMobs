package com.mrbysco.instrumentalmobs.client.render;

import com.mrbysco.instrumentalmobs.client.render.layers.MicrophoneLayer;
import com.mrbysco.instrumentalmobs.client.render.state.MicrophoneRenderState;
import com.mrbysco.instrumentalmobs.entities.MicrophoneGhast;
import net.minecraft.client.model.GhastModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemDisplayContext;
import org.jetbrains.annotations.NotNull;

public class MicrophoneGhastRenderer extends MobRenderer<MicrophoneGhast, MicrophoneRenderState, GhastModel> {
	private static final ResourceLocation GHAST_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/ghast/ghast.png");
	private static final ResourceLocation GHAST_SHOOTING_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/ghast/ghast_shooting.png");

	public MicrophoneGhastRenderer(EntityRendererProvider.Context context) {
		super(context, new GhastModel(context.bakeLayer(ModelLayers.GHAST)), 1.5F);
		this.addLayer(new MicrophoneLayer(this));
	}

	@NotNull
	@Override
	public MicrophoneRenderState createRenderState() {
		return new MicrophoneRenderState();
	}

	@Override
	public void extractRenderState(@NotNull MicrophoneGhast ghast, @NotNull MicrophoneRenderState state, float partialTick) {
		super.extractRenderState(ghast, state, partialTick);
		state.isCharging = ghast.isCharging();
		state.isSinging = ghast.isSinging();
		itemModelResolver.updateForLiving(
				state.headItem, ghast.getItemBySlot(EquipmentSlot.HEAD),
				ItemDisplayContext.NONE, ghast
		);
	}

	@NotNull
	@Override
	public ResourceLocation getTextureLocation(MicrophoneRenderState state) {
		return state.isCharging ? GHAST_SHOOTING_LOCATION : GHAST_LOCATION;
	}
}
