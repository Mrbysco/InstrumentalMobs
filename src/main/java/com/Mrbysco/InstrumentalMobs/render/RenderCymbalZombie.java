package com.Mrbysco.InstrumentalMobs.render;

import com.Mrbysco.InstrumentalMobs.entities.EntityCymbalHusk;
import com.Mrbysco.InstrumentalMobs.render.layers.LayerRenderCymbals;
import com.Mrbysco.InstrumentalMobs.render.model.ModelCymbalHusk;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCymbalZombie extends RenderCustomBiped<EntityCymbalHusk>{
    private static final ResourceLocation HUSK_ZOMBIE_TEXTURES = new ResourceLocation("textures/entity/zombie/husk.png");

	public static final Factory FACTORY = new Factory();
	
	public RenderCymbalZombie(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelCymbalHusk(), 0.5F);
        this.addLayer(new LayerRenderCymbals(this));

        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
                this.modelLeggings = new ModelCymbalHusk(0.5F, true);
                this.modelArmor = new ModelCymbalHusk(1.0F, true);
            }
        };
        this.addLayer(layerbipedarmor);
	}
	
	protected ResourceLocation getEntityTexture(EntityCymbalHusk entity) {
        return HUSK_ZOMBIE_TEXTURES;

	}
	
	protected void preRenderCallback(EntityCymbalHusk entitylivingbaseIn, float partialTickTime)
    {
        float f = 1.0625F;
        GlStateManager.scale(1.0625F, 1.0625F, 1.0625F);
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }
	
	public static class Factory implements IRenderFactory<EntityCymbalHusk> {
		@Override
		public Render<? super EntityCymbalHusk> createRenderFor(RenderManager manager) {
			return new RenderCymbalZombie(manager);
		}
	}
}
