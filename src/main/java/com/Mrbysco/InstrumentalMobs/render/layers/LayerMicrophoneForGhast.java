package com.mrbysco.instrumentalmobs.render.layers;

import com.mrbysco.instrumentalmobs.entities.EntityMicrophoneGhast;
import com.mrbysco.instrumentalmobs.render.RenderMicrophoneGhast;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerMicrophoneForGhast implements LayerRenderer<EntityMicrophoneGhast> {
    private final RenderMicrophoneGhast ghastRenderer;

    public LayerMicrophoneForGhast(RenderMicrophoneGhast ghastRendererIn)
    {
        this.ghastRenderer = ghastRendererIn;
    }
    
    @Override
    public void doRenderLayer(EntityMicrophoneGhast entitylivingbaseIn, float limbSwing, float limbSwingAmount,
    		float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    	ItemStack stack = entitylivingbaseIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
		
		if (!stack.isEmpty() && !entitylivingbaseIn.isSinging()) {
            GlStateManager.pushMatrix();

            GlStateManager.scale(0.25F, 0.25F, 0.25F);
            GlStateManager.rotate(-180.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.translate(-0.5F, -6F, 2F);
            Minecraft.getMinecraft().getItemRenderer().renderItemSide(entitylivingbaseIn, stack, ItemCameraTransforms.TransformType.NONE, false);
            GlStateManager.popMatrix();
        }
    }
    
	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
}
