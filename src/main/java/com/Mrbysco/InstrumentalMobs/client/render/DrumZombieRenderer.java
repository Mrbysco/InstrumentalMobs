package com.mrbysco.instrumentalmobs.client.render;

import com.mrbysco.instrumentalmobs.client.render.layers.DrumLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;

public class DrumZombieRenderer extends ZombieRenderer {
	public DrumZombieRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.addLayer(new DrumLayer(this, context.getItemInHandRenderer()));
	}
}
