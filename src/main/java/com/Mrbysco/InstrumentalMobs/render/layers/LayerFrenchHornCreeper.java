package com.mrbysco.instrumentalmobs.render.layers;

import com.mrbysco.instrumentalmobs.entities.EntityFrenchHornCreeper;
import com.mrbysco.instrumentalmobs.render.RenderFrenchHornCreeper;

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
public class LayerFrenchHornCreeper implements LayerRenderer<EntityFrenchHornCreeper>{
    private final RenderFrenchHornCreeper creeperRenderer;

    public LayerFrenchHornCreeper(RenderFrenchHornCreeper creeperRendererIn)
    {
        this.creeperRenderer = creeperRendererIn;
    }
    
	@Override
	public void doRenderLayer(EntityFrenchHornCreeper entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		ItemStack itemstack = entitylivingbaseIn.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
		
		if (!itemstack.isEmpty())
        {
			GlStateManager.enableRescaleNormal();
            GlStateManager.pushMatrix();
			GlStateManager.translate(-0.05F, 0.175F, -0.7F);
			GlStateManager.scale(0.75F, 0.75F, 0.75F);

            GlStateManager.rotate(90.0F, 1.0F, 90.0F, 0.0F);
            GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(-10.0F, 1.0F, -1.0F, 1.0F);

            GlStateManager.rotate(40.0F, 0.0F, 0.0F, 1.0F);
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
