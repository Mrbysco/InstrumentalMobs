package com.Mrbysco.InstrumentalMobs.render;

import com.Mrbysco.InstrumentalMobs.entities.EntityXylophoneSkeletal;
import com.Mrbysco.InstrumentalMobs.render.layers.LayerHeldBones;
import com.Mrbysco.InstrumentalMobs.render.model.ModelXylophoneSkeleton;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderXylophoneSkeleton extends RenderCustomBiped<EntityXylophoneSkeletal>{
	public static final Factory FACTORY = new Factory();
    private static final ResourceLocation SKELETON_TEXTURES = new ResourceLocation("textures/entity/skeleton/skeleton.png");

	public RenderXylophoneSkeleton(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelXylophoneSkeleton(), 0.5F);
        this.addLayer(new LayerHeldBones(this));
        this.addLayer(new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
                this.modelLeggings = new ModelXylophoneSkeleton(0.5F, true);
                this.modelArmor = new ModelXylophoneSkeleton(1.0F, true);
            }
        });
	}

	public void transformHeldFull3DItemLayer()
    {
        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
    }
	
	protected ResourceLocation getEntityTexture(EntityXylophoneSkeletal entity)
    {
        return SKELETON_TEXTURES;
    }
	
	public static class Factory implements IRenderFactory<EntityXylophoneSkeletal> {
		@Override
		public Render<? super EntityXylophoneSkeletal> createRenderFor(RenderManager manager) {
			return new RenderXylophoneSkeleton(manager);
		}
	}
}
