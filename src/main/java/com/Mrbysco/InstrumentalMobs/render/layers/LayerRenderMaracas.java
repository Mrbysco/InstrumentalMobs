package com.mrbysco.instrumentalmobs.render.layers;

import com.mrbysco.instrumentalmobs.entities.EntityMaracaSpider;
import com.mrbysco.instrumentalmobs.render.model.ModelMaracaSpider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;

public class LayerRenderMaracas implements LayerRenderer<EntityMaracaSpider>
{
    protected final RenderLivingBase<?> livingEntityRenderer;

    public LayerRenderMaracas(RenderLivingBase<?> livingEntityRendererIn)
    {
        this.livingEntityRenderer = livingEntityRendererIn;
    }

    public void doRenderLayer(EntityMaracaSpider entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
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

    private void renderHeldItem(EntityMaracaSpider spiderIn, ItemStack stack, ItemCameraTransforms.TransformType transformType, EnumHandSide handSide)
    {
        if (!stack.isEmpty())
        {
            GlStateManager.pushMatrix();

            this.translateToLeg(handSide);
            GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            boolean flag = handSide == EnumHandSide.LEFT;
            if(flag)
            {
                GlStateManager.translate(0.325F, 0.6F, -0.85F);
            }
            else
            {
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(0.8F, 0.1F, -0.85F);
            }
            if(spiderIn.isAttacking() && spiderIn.world.rand.nextFloat() > 0.5F)
            {
            	float randAngle = (float)spiderIn.world.rand.nextInt(45);
                GlStateManager.rotate((flag ? -randAngle : randAngle), 1F, 0F, 0F);
            }
            Minecraft.getMinecraft().getItemRenderer().renderItemSide(spiderIn, stack, transformType, flag);
            GlStateManager.popMatrix();
        }
    }

    protected void translateToLeg(EnumHandSide handSide)
    {
        ((ModelMaracaSpider)this.livingEntityRenderer.getMainModel()).postRenderLeg(0.0625F, handSide);
    }

    public boolean shouldCombineTextures()
    {
        return false;
    }
}