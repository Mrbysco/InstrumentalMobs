package com.mrbysco.instrumentalmobs.render;

import com.mrbysco.instrumentalmobs.entities.EntityMicrophoneGhast;
import com.mrbysco.instrumentalmobs.render.layers.LayerMicrophoneForGhast;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderGhast;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMicrophoneGhast extends RenderGhast{
	public static final Factory FACTORY = new Factory();
	
	public RenderMicrophoneGhast(RenderManager renderManagerIn) {
		super(renderManagerIn);
        this.addLayer(new LayerMicrophoneForGhast(this));
	}

	public static class Factory implements IRenderFactory<EntityMicrophoneGhast> {
		@Override
		public Render<? super EntityMicrophoneGhast> createRenderFor(RenderManager manager) {
			return new RenderMicrophoneGhast(manager);
		}
	}
}
