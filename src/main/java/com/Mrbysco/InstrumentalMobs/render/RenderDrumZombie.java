package com.Mrbysco.InstrumentalMobs.render;

import com.Mrbysco.InstrumentalMobs.entities.EntityDrumZombie;
import com.Mrbysco.InstrumentalMobs.render.layers.LayerDrumForZombie;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDrumZombie extends RenderZombie{
	public static final Factory FACTORY = new Factory();
	
	public RenderDrumZombie(RenderManager renderManagerIn) {
		super(renderManagerIn);
        this.addLayer(new LayerDrumForZombie(this));
	}

	public static class Factory implements IRenderFactory<EntityDrumZombie> {
		@Override
		public Render<? super EntityDrumZombie> createRenderFor(RenderManager manager) {
			return new RenderDrumZombie(manager);
		}
	}
}
