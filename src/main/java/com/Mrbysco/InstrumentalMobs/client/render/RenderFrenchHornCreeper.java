package com.mrbysco.instrumentalmobs.client.render;

import com.mrbysco.instrumentalmobs.client.render.layers.FrenchHornLayer;
import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class RenderFrenchHornCreeper extends CreeperRenderer {
	public RenderFrenchHornCreeper(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
        this.addLayer(new FrenchHornLayer(this));
	}
}
