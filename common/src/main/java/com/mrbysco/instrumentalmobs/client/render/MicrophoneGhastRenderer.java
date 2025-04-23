package com.mrbysco.instrumentalmobs.client.render;

import com.mrbysco.instrumentalmobs.client.render.layers.MicrophoneLayer;
import com.mrbysco.instrumentalmobs.client.render.state.MicrophoneRenderState;
import com.mrbysco.instrumentalmobs.entities.MicrophoneGhast;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.GhastRenderer;
import net.minecraft.client.renderer.entity.state.GhastRenderState;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.item.ItemDisplayContext;
import org.jetbrains.annotations.NotNull;

public class MicrophoneGhastRenderer extends GhastRenderer {

	public MicrophoneGhastRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.addLayer(new MicrophoneLayer(this));
	}

	@NotNull
	@Override
	public GhastRenderState createRenderState() {
		return new MicrophoneRenderState();
	}

	@Override
	public void extractRenderState(@NotNull Ghast ghast, @NotNull GhastRenderState state, float partialTick) {
		super.extractRenderState(ghast, state, partialTick);
		if (ghast instanceof MicrophoneGhast microphoneGhast && state instanceof MicrophoneRenderState microphoneState) {
			microphoneState.isSinging = microphoneGhast.isSinging();
			itemModelResolver.updateForLiving(
					microphoneState.headItem, microphoneGhast.getItemBySlot(EquipmentSlot.HEAD),
					ItemDisplayContext.NONE, false, microphoneGhast
			);
		}
	}
}
