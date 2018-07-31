package com.Mrbysco.InstrumentalMobs.render.layers;

import com.Mrbysco.InstrumentalMobs.entities.EntityDrumZombie;
import com.Mrbysco.InstrumentalMobs.render.RenderDrumZombie;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerDrumForZombie implements LayerRenderer<EntityDrumZombie>{
    private final RenderDrumZombie zombieRenderer;

    public LayerDrumForZombie(RenderDrumZombie zombieRendererIn)
    {
        this.zombieRenderer = zombieRendererIn;
    }
    
	@Override
	public void doRenderLayer(EntityDrumZombie entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		ItemStack itemstack = entitylivingbaseIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		
		if (!itemstack.isEmpty())
        {
			GlStateManager.enableRescaleNormal();
            GlStateManager.pushMatrix();
			GlStateManager.translate(0.0F, 0.775F, -0.55F);
			GlStateManager.scale(0.75F, 0.75F, 0.75F);
			if(entitylivingbaseIn.isChild())
			{
				GlStateManager.translate(0.0F, 0.4F, 0.4F);
				GlStateManager.scale(0.35F, 0.35F, 0.35F);
			}
            GlStateManager.rotate(-10.0F, 1.0F, 0.0F, 0.0F);

			int i = entitylivingbaseIn.getBrightnessForRender();
            int j = i % 65536;
            int k = i / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j, (float)k);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			Minecraft.getMinecraft().getRenderItem().renderItem(itemstack, ItemCameraTransforms.TransformType.NONE);
            GlStateManager.popMatrix();
            GlStateManager.disableRescaleNormal();
        }
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
}
