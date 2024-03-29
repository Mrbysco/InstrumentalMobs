package com.mrbysco.instrumentalmobs.client.render;

import com.mrbysco.instrumentalmobs.client.render.layers.MicrophoneLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.GhastRenderer;

public class MicrophoneGhastRenderer extends GhastRenderer {

	public MicrophoneGhastRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.addLayer(new MicrophoneLayer<>(this, context.getItemInHandRenderer()));
	}
}
