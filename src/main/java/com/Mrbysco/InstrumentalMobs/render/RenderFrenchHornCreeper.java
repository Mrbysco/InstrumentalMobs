package com.mrbysco.instrumentalmobs.render;

import com.mrbysco.instrumentalmobs.entities.EntityFrenchHornCreeper;
import com.mrbysco.instrumentalmobs.render.layers.LayerFrenchHornCreeper;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFrenchHornCreeper extends RenderCreeper{
	public static final Factory FACTORY = new Factory();
	
	public RenderFrenchHornCreeper(RenderManager renderManagerIn) {
		super(renderManagerIn);
        this.addLayer(new LayerFrenchHornCreeper(this));
	}

	public static class Factory implements IRenderFactory<EntityFrenchHornCreeper> {
		@Override
		public Render<? super EntityFrenchHornCreeper> createRenderFor(RenderManager manager) {
			return new RenderFrenchHornCreeper(manager);
		}
	}
}
