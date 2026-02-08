package com.mrbysco.instrumentalmobs.render;

import com.mrbysco.instrumentalmobs.entities.EntityMaracaSpider;
import com.mrbysco.instrumentalmobs.render.layers.LayerMaracaSpiderEyes;
import com.mrbysco.instrumentalmobs.render.layers.LayerRenderMaracas;
import com.mrbysco.instrumentalmobs.render.model.ModelMaracaSpider;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMaracaSpider<T extends EntityMaracaSpider> extends RenderLiving<T> {
	public static final Factory FACTORY = new Factory();
	private static final ResourceLocation SPIDER_TEXTURES = new ResourceLocation("textures/entity/spider/spider.png");
	
	public RenderMaracaSpider(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelMaracaSpider(), 1.0F);
        this.addLayer(new LayerMaracaSpiderEyes<>(this));
        this.addLayer(new LayerRenderMaracas(this));
	}

	protected float getDeathMaxRotation(T entityLivingBaseIn)
    {
        return 180.0F;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(T entity)
    {
        return SPIDER_TEXTURES;
    }
    
	public static class Factory implements IRenderFactory<EntityMaracaSpider> {
		@Override
		public Render<? super EntityMaracaSpider> createRenderFor(RenderManager manager) {
			return new RenderMaracaSpider<>(manager);
		}
	}
}
