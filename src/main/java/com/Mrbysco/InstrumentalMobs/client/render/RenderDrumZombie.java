package com.mrbysco.instrumentalmobs.client.render;

import com.mrbysco.instrumentalmobs.client.render.layers.DrumLayer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.ZombieRenderer;

public class RenderDrumZombie extends ZombieRenderer {
	public RenderDrumZombie(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
        this.addLayer(new DrumLayer(this));
	}
}
