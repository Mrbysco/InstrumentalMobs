package com.mrbysco.instrumentalmobs.client.render;

import com.mrbysco.instrumentalmobs.client.render.layers.MicrophoneLayer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.GhastRenderer;

public class RenderMicrophoneGhast extends GhastRenderer {

	public RenderMicrophoneGhast(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
        this.addLayer(new MicrophoneLayer(this));
	}
}
