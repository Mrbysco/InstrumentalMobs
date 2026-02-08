package com.mrbysco.instrumentalmobs.render;

import com.mrbysco.instrumentalmobs.entities.EntityTubaEnderman;
import com.mrbysco.instrumentalmobs.render.layers.LayerTubaEndermanEyes;
import com.mrbysco.instrumentalmobs.render.layers.LayerTubaEndermanHoldItem;
import com.mrbysco.instrumentalmobs.render.model.ModelTubaEnderman;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@SideOnly(Side.CLIENT)
public class RenderTubaEnderman extends RenderLiving<EntityTubaEnderman> {
	private static final ResourceLocation ENDERMAN_TEXTURES = new ResourceLocation("textures/entity/enderman/enderman.png");
	private final Random rnd = new Random();
	
	public static final Factory FACTORY = new Factory();
	
	public RenderTubaEnderman(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelTubaEnderman(0.0F), 0.5F);
        this.addLayer(new LayerTubaEndermanEyes(this));
        this.addLayer(new LayerTubaEndermanHoldItem(this));
	}
	
	public ModelTubaEnderman getMainModel()
    {
        return (ModelTubaEnderman)super.getMainModel();
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityTubaEnderman entity, double x, double y, double z, float entityYaw, float partialTicks) {
        ItemStack heldStack = entity.getHeldItem(EnumHand.MAIN_HAND);
        ModelTubaEnderman modelenderman = this.getMainModel();
        modelenderman.isCarrying = !heldStack.isEmpty();
        modelenderman.isAttacking = entity.isScreaming();

        if (entity.isScreaming()) {
            double d0 = 0.02D;
            x += this.rnd.nextGaussian() * d0;
            z += this.rnd.nextGaussian() * d0;
        }

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityTubaEnderman entity)
    {
        return ENDERMAN_TEXTURES;
    }
    
	public static class Factory implements IRenderFactory<EntityTubaEnderman> {
		@Override
		public Render<? super EntityTubaEnderman> createRenderFor(RenderManager manager) {
			return new RenderTubaEnderman(manager);
		}
	}
}
