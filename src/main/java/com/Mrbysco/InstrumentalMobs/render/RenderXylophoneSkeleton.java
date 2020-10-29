package com.mrbysco.instrumentalmobs.render;

import com.mrbysco.instrumentalmobs.entities.EntityXylophoneSkeleton;
import com.mrbysco.instrumentalmobs.render.layers.LayerHeldBones;
import com.mrbysco.instrumentalmobs.render.model.ModelXylophoneSkeleton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderXylophoneSkeleton extends RenderCustomBiped<EntityXylophoneSkeleton> {
	public static final Factory FACTORY = new Factory();
    private static final ResourceLocation SKELETON_TEXTURES = new ResourceLocation("textures/entity/skeleton/skeleton.png");

	public RenderXylophoneSkeleton(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelXylophoneSkeleton(), 0.5F);
        this.addLayer(new LayerHeldBones(this));
        this.addLayer(new LayerBipedArmor(this) {
            protected void initArmor() {
                this.modelLeggings = new ModelXylophoneSkeleton(0.5F, true);
                this.modelArmor = new ModelXylophoneSkeleton(1.0F, true);
            }
        });
	}

	public void transformHeldFull3DItemLayer()
    {
        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
    }
	
	protected ResourceLocation getEntityTexture(EntityXylophoneSkeleton entity)
    {
        return SKELETON_TEXTURES;
    }
	
	public static class Factory implements IRenderFactory<EntityXylophoneSkeleton> {
		@Override
		public Render<? super EntityXylophoneSkeleton> createRenderFor(RenderManager manager) {
			return new RenderXylophoneSkeleton(manager);
		}
	}
}
