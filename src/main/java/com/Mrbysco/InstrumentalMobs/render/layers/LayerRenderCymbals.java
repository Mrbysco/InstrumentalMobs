package com.mrbysco.instrumentalmobs.render.layers;

import com.mrbysco.instrumentalmobs.entities.EntityCymbalHusk;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerRenderCymbals implements LayerRenderer<EntityLivingBase>
{
    protected final RenderLivingBase<?> livingEntityRenderer;

    public LayerRenderCymbals(RenderLivingBase<?> livingEntityRendererIn)
    {
        this.livingEntityRenderer = livingEntityRendererIn;
    }

    public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        boolean flag = entitylivingbaseIn.getPrimaryHand() == EnumHandSide.RIGHT;
        ItemStack itemstack = flag ? entitylivingbaseIn.getHeldItemOffhand() : entitylivingbaseIn.getHeldItemMainhand();
        ItemStack itemstack1 = flag ? entitylivingbaseIn.getHeldItemMainhand() : entitylivingbaseIn.getHeldItemOffhand();

        if (!itemstack.isEmpty() || !itemstack1.isEmpty())
        {
            GlStateManager.pushMatrix();

            if (this.livingEntityRenderer.getMainModel().isChild)
            {
                float f = 0.5F;
                GlStateManager.translate(0.0F, 0.75F, 0.0F);
                GlStateManager.scale(0.5F, 0.5F, 0.5F);
            }

            this.renderHeldItem(entitylivingbaseIn, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, EnumHandSide.RIGHT);
            this.renderHeldItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, EnumHandSide.LEFT);
            GlStateManager.popMatrix();
        }
    }

    private void renderHeldItem(EntityLivingBase livingBase, ItemStack stack, ItemCameraTransforms.TransformType type, EnumHandSide handSide)
    {
        if (!stack.isEmpty())
        {
            GlStateManager.pushMatrix();

            if (livingBase.isSneaking())
            {
                GlStateManager.translate(0.0F, 0.2F, 0.0F);
            }
            // Forge: moved this call down, fixes incorrect offset while sneaking.
            this.translateToHand(handSide);
            GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            boolean flag = handSide == EnumHandSide.LEFT;
            GlStateManager.rotate(90F, 0.0F, (float)(flag ? -0.23F : 0.23F), (float)(flag ? -0.23F : 0.23F));
            GlStateManager.translate((float)(flag ? 0.5 : -0.5), -0.5F, -0.5F);
            if(livingBase instanceof EntityCymbalHusk)
            {
            	EntityCymbalHusk husk = (EntityCymbalHusk)livingBase;
            	boolean flag2 = husk.isClapping();
                if(flag2)
                {
                	GlStateManager.rotate(-5.0F, 1.0F, (float)(flag ? -1F : 1F), (float)(flag ? -0.0F : 0.0F));
                    GlStateManager.translate((float)(flag ? -0.1 : 0.1), 0.1F, 0.1F);
                }
            }
            Minecraft.getMinecraft().getItemRenderer().renderItemSide(livingBase, stack, type, flag);
            GlStateManager.popMatrix();
        }
    }

    protected void translateToHand(EnumHandSide handSide)
    {
        ((ModelBiped)this.livingEntityRenderer.getMainModel()).postRenderArm(0.0625F, handSide);
    }

    public boolean shouldCombineTextures()
    {
        return false;
    }
}